package com.exx.dzj.query;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.exx.dzj.entity.datarules.DataPermissionRules;
import com.exx.dzj.util.ConvertUtils;
import com.exx.dzj.util.DataAutorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.NumberUtils;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author
 * @Date 2019/5/28
 * @Description 查询条件生成工具类
 */
@Slf4j
public class QueryGenerator {
	
	public static final String SQL_RULES_COLUMN = "SQL_RULES_COLUMN";
	
	private static final String BEGIN = "_begin";
	private static final String END = "_end";
	private static final String STAR = "*";
	private static final String COMMA = ",";
	private static final String NOT_EQUAL = "!";

	/**高级查询前端传来的参数名*/
	private static final String SUPER_QUERY_PARAMS = "superQueryParams";
	
	/**排序列*/
	private static final String ORDER_COLUMN = "column";

	/**排序方式*/
	private static final String ORDER_TYPE = "order";
	private static final String ORDER_TYPE_ASC = "ASC";
	
	/**时间格式化 */
	private static final ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();
	private static SimpleDateFormat getTime(){
		SimpleDateFormat time = local.get();
		if(time == null){
			time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			local.set(time);
		}
		return time;
	}
	
	/**
	 * 获取查询条件构造器 QueryWrapper 实例 通用查询条件已被封装完成
	 *
	 * @param searchObj 	查询实体
	 * @param parameterMap request.getParameterMap()
	 * @return QueryWrapper	实例
	 */
	public static <T> QueryWrapper<T> initQueryWrapper(T searchObj, Map<String, String[]> parameterMap){
		long start = System.currentTimeMillis();
		QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
		installMplus(queryWrapper, searchObj, parameterMap);
		log.debug("---查询条件构造器初始化完成,耗时:"+(System.currentTimeMillis()-start)+"毫秒----");
		return queryWrapper;
	}
	
	/**
	 * 组装Mybatis Plus 查询条件
	 *
	 * <p>使用此方法 需要有如下几点注意:   
	 * <br>1.使用QueryWrapper 而非LambdaQueryWrapper;
	 * <br>2.实例化QueryWrapper时不可将实体传入参数   
	 * <br>错误示例:如QueryWrapper<MenuInfo> queryWrapper = new QueryWrapper<MenuInfo>(menuInfo);
	 * <br>正确示例:QueryWrapper<MenuInfo> queryWrapper = new QueryWrapper<MenuInfo>();
	 * <br>3.也可以不使用这个方法直接调用 {@link #initQueryWrapper}直接获取实例
	 *
	 * @param queryWrapper  查询条件对象
	 * @param searchObj		查询的实体类
	 * @param parameterMap	从 request 中获取到的参数
	 */
	public static void installMplus(QueryWrapper<?> queryWrapper, Object searchObj, Map<String, String[]> parameterMap) {

		/**
		 * 注意:权限查询由前端配置数据规则 当一个人有多个所属部门时候可以在规则配置包含条件 deptCode 包含 #{dept_code}
		 * 但是不支持在自定义SQL中写 deptCode in #{dept_code}
		 * 当一个人只有一个部门 就直接配置等于条件: deptCode 等于 #{dept_code} 或者配置自定义SQL: deptCode = '#{dept_code}'
		 */

		//区间条件组装 模糊查询 高级查询组装 简单排序 权限查询
		/**
		 * 获取类的属性描述，而不是类的属性
		 */
		PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(searchObj);

		/**
		 * 获取数据权限, 条件字段为 key, 规则值或自定义SQL为值
		 */
		Map<String, DataPermissionRules> ruleMap = getRuleMap();
		
		//权限规则自定义SQL表达式
		for (String c : ruleMap.keySet()) {
			/**
			 * 判断是否是自定义 SQL
			 */
			if(ConvertUtils.isNotEmpty(c) && c.startsWith(SQL_RULES_COLUMN)){
				queryWrapper.and(i ->i.apply(getSqlRuleValue(ruleMap.get(c).getRuleValue())));
			}
		}
		
		String name, type;

		/**
		 * 根据类的成员变量作循环
		 */
		for (int i = 0; i < origDescriptors.length; i++) {
			//aliasName = origDescriptors[i].getName();  mybatis  不存在实体属性 不用处理别名的情况
			name = origDescriptors[i].getName();
			type = origDescriptors[i].getPropertyType().toString();
			try {
				/**
				 * 判断成员变量是否可读 或 是关键字
				 */
				if (judgedIsUselessField(name)|| !PropertyUtils.isReadable(searchObj, name)) {
					continue;
				}
				
				//数据权限查询，数据权限是否包含该成员变量
				if(ruleMap.containsKey(name)) {
					/**
					 * 将数据规则添加到查询条件中
					 */
					addRuleToQueryWrapper(ruleMap.get(name), name, origDescriptors[i].getPropertyType(), queryWrapper);
				}
				
				// 添加 判断是否有区间值
				String endValue = null, beginValue = null;
				if (parameterMap != null && parameterMap.containsKey(name + BEGIN)) {
					beginValue = parameterMap.get(name + BEGIN)[0].trim();
					addQueryByRule(queryWrapper, name, type, beginValue, QueryRuleEnum.GE);
					
				}
				if (parameterMap != null && parameterMap.containsKey(name + END)) {
					endValue = parameterMap.get(name + END)[0].trim();
					addQueryByRule(queryWrapper, name, type, endValue, QueryRuleEnum.LE);
				}
				
				//判断单值  参数带不同标识字符串 走不同的查询
				//TODO 这种前后带逗号的支持分割后模糊查询需要使多选字段的查询生效
				Object value = PropertyUtils.getSimpleProperty(searchObj, name);

				// 判断 value 不等于kong, 并且前后都包含有逗号
				if (null != value && value.toString().startsWith(COMMA) && value.toString().endsWith(COMMA)) {
					// 将连续的两个逗号给替换为一个逗号
					String multiLikeval = value.toString().replace(",,", COMMA);
					// 将第一个逗号给剔除，并且以逗号切分
					String[] vals = multiLikeval.substring(1, multiLikeval.length()).split(COMMA);
					// 将成员名，由驼峰改为下划线
					final String field = ConvertUtils.camelToUnderline(name);
					// 组装称 LIKE '%value%' 查询
					if(vals.length>1) {
						queryWrapper.and(j -> {
							j = j.like(field,vals[0]);
							for (int k=1;k<vals.length;k++) {
								j = j.or().like(field,vals[k]);
							}
							return j;
						});
					}else {
						queryWrapper.and(j -> j.like(field,vals[0]));
					}
				}else {
					// 根据参数值带什么关键字符串判断走什么类型的查询
					QueryRuleEnum rule = convert2Rule(value);
					// 根据规则条件将规则值中的特殊字符给剔除， 比如 value 中的 *
					value = replaceValue(rule, value);
					addEasyQuery(queryWrapper, name, rule, value);
				}
				
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}

		// 排序逻辑 处理 
		doMultiFieldsOrder(queryWrapper, parameterMap);
				
		//高级查询
		doSuperQuery(queryWrapper, parameterMap);
		
	}
	
	/**
	 * 多字段排序 TODO 需要修改前端
	 * @param queryWrapper  查询条件对象
	 * @param parameterMap	从 request 中获取到的参数
	 */
	public static void doMultiFieldsOrder(QueryWrapper<?> queryWrapper,Map<String, String[]> parameterMap) {
		String column=null,order=null;

		// 是否有排序字段
		if(null != parameterMap && parameterMap.containsKey(ORDER_COLUMN)) {
			column = parameterMap.get(ORDER_COLUMN)[0];
		}

		// 是否有排序方式
		if(null != parameterMap&& parameterMap.containsKey(ORDER_TYPE)) {
			order = parameterMap.get(ORDER_TYPE)[0];
		}
		log.debug("排序规则>>列:"+column+",排序方式:"+order);
		if (ConvertUtils.isNotEmpty(column) && ConvertUtils.isNotEmpty(order)) {
			if (order.toUpperCase().indexOf(ORDER_TYPE_ASC)>=0) {
				queryWrapper.orderByAsc(ConvertUtils.camelToUnderline(column));
			} else {
				queryWrapper.orderByDesc(ConvertUtils.camelToUnderline(column));
			}
		}
	}
	
	/**
	 * 高级查询
	 * @param queryWrapper	查询条件对象
	 * @param parameterMap	从 request 中获取到的参数
	 */
	public static void doSuperQuery(QueryWrapper<?> queryWrapper,Map<String, String[]> parameterMap) {
		// 判断是否有前端传进来的查询参数
		if(null != parameterMap && parameterMap.containsKey(SUPER_QUERY_PARAMS)){
			String superQueryParams = parameterMap.get(SUPER_QUERY_PARAMS)[0];
			// 解码
			try {
				superQueryParams = URLDecoder.decode(superQueryParams, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("--高级查询参数转码失败!", e);
			}

			// 将参数解析为 QueryCondition 数组
			List<QueryCondition> conditions = JSON.parseArray(superQueryParams, QueryCondition.class);
			log.info("---高级查询参数-->"+conditions.toString());
			
			for (QueryCondition rule : conditions) {
				if(ConvertUtils.isNotEmpty(rule.getField())
						&& ConvertUtils.isNotEmpty(rule.getRule())
						&& ConvertUtils.isNotEmpty(rule.getVal())){
					addEasyQuery(queryWrapper, rule.getField(), QueryRuleEnum.getByValue(rule.getRule()), rule.getVal());
				}
			}
		}
	}
	/**
	 * 根据所传的值 转化成对应的比较方式
	 * 支持><= like in !
	 * @param value  规则条件
	 * @return
	 */
	public static QueryRuleEnum convert2Rule(Object value) {
		// 避免空数据
		if (value == null) {
			return null;
		}
		String val = (value + "").trim();
		if (val.length() == 0) {
			return null;
		}
		QueryRuleEnum rule =null;
		// step 2 .>= =<
		if (rule == null && val.length() >= 2) {
			rule = QueryRuleEnum.getByValue(val.substring(0, 2));
		}
		// step 1 .> <
		if (rule == null && val.length() >= 1) {
			rule = QueryRuleEnum.getByValue(val.substring(0, 1));
		}
		
		// step 3 like， 判断 val 是否包含 *
		if (rule == null && val.contains(STAR)) {
			if (val.startsWith(STAR) && val.endsWith(STAR)) {
				// 前后都有 *, 则是 '%val%'
				rule = QueryRuleEnum.LIKE;
			} else if (val.startsWith(STAR)) {
				// 前面有 *, 则是 '%val'
				rule = QueryRuleEnum.LEFT_LIKE;
			} else if(val.endsWith(STAR)){
				// 后面有 *, 则是 'val%'
				rule = QueryRuleEnum.RIGHT_LIKE;
			}
		}
		// step 4 in, 包含逗号则用 IN
		if (rule == null && val.contains(COMMA)) {
			//TODO in 查询这里应该有个bug  如果一字段本身就是多选 此时用in查询 未必能查询出来
			rule = QueryRuleEnum.IN;
		}
		// step 5 != 
		if(rule == null && val.startsWith(NOT_EQUAL)){
			rule = QueryRuleEnum.NE;
		}
		// 默认为 等于
		return rule != null ? rule : QueryRuleEnum.EQ;
	}
	
	/**
	 * 替换掉关键字字符
	 * @param rule   规则条件
	 * @param value	规则值
	 * @return
	 */
	public static Object replaceValue(QueryRuleEnum rule, Object value) {
		if (rule == null) {
			return null;
		}
		if (! (value instanceof String)){
			return value;
		}
		String val = (value + "").trim();
		if (rule == QueryRuleEnum.LIKE) {
			value = val.substring(1, val.length() - 1);
		} else if (rule == QueryRuleEnum.LEFT_LIKE || rule == QueryRuleEnum.NE) {
			value = val.substring(1);
		} else if (rule == QueryRuleEnum.RIGHT_LIKE) {
			value = val.substring(0, val.length() - 1);
		} else if (rule == QueryRuleEnum.IN) {
			value = val.split(",");
		} else {
			value = val.replace(rule.getValue(),"");
		}
		return value;
	}

	/**
	 * 将成员的值进行数据类型转换，并且生成
	 * @param queryWrapper	查询条件
	 * @param name			成员名称
	 * @param type			成员数据类型
	 * @param value			条件值
	 * @param rule			规则条件
	 * @throws ParseException
	 */
	private static void addQueryByRule(QueryWrapper<?> queryWrapper, String name, String type, String value, QueryRuleEnum rule) throws ParseException {
		if(!"".equals(value)) {
			Object temp;
			switch (type) {
			case "class java.lang.Integer":
				temp =  Integer.parseInt(value);
				break;
			case "class java.math.BigDecimal":
				temp =  new BigDecimal(value);
				break;
			case "class java.lang.Short":
				temp =  Short.parseShort(value);
				break;
			case "class java.lang.Long":
				temp =  Long.parseLong(value);
				break;
			case "class java.lang.Float":
				temp =   Float.parseFloat(value);
				break;
			case "class java.lang.Double":
				temp =  Double.parseDouble(value);
				break;
			case "class java.util.Date":
				temp = getDateQueryByRule(value, rule);
				break;
			default:
				temp = value;
				break;
			}
			addEasyQuery(queryWrapper, name, rule, temp);
		}
	}
	
	/**
	 * 获取日期类型的值
	 * @param value
	 * @param rule
	 * @return
	 * @throws ParseException
	 */
	private static Date getDateQueryByRule(String value,QueryRuleEnum rule) throws ParseException {
		Date date = null;
		if(value.length()==10) {
			if(rule==QueryRuleEnum.GE) {
				//比较大于
				date = getTime().parse(value + " 00:00:00");
			}else if(rule==QueryRuleEnum.LE) {
				//比较小于
				date = getTime().parse(value + " 23:59:59");
			}
			//TODO 日期类型比较特殊 可能oracle下不一定好使
		}
		if(date==null) {
			date = getTime().parse(value);
		}
		return date;
	}
	
	/**
	 * 根据规则走不同的查询
	 * @param queryWrapper  QueryWrapper
	 * @param name         字段名字
	 * @param rule         查询规则
	 * @param value        查询条件值
	 */
	private static void addEasyQuery(QueryWrapper<?> queryWrapper, String name, QueryRuleEnum rule, Object value) {
		if (value == null || rule == null) {
			return;
		}
		name = ConvertUtils.camelToUnderline(name);
		log.info("--查询规则-->"+name+" "+rule.getValue()+" "+value);
		switch (rule) {
		case GT:
			queryWrapper.gt(name, value);
			break;
		case GE:
			queryWrapper.ge(name, value);
			break;
		case LT:
			queryWrapper.lt(name, value);
			break;
		case LE:
			queryWrapper.le(name, value);
			break;
		case EQ:
			queryWrapper.eq(name, value);
			break;
		case NE:
			queryWrapper.ne(name, value);
			break;
		case IN:
			if(value instanceof String) {
				queryWrapper.in(name, (Object[])value.toString().split(","));
			}else if(value instanceof String[]) {
				queryWrapper.in(name, (Object[]) value);
			}else {
				queryWrapper.in(name, value);
			}
			break;
		case LIKE:
			queryWrapper.like(name, value);
			break;
		case LEFT_LIKE:
			queryWrapper.likeLeft(name, value);
			break;
		case RIGHT_LIKE:
			queryWrapper.likeRight(name, value);
			break;
		default:
			log.info("--查询规则未匹配到---");
			break;
		}
	}

	/**
	 * 判断成员变量名是否是关键字
	 * @param name
	 * @return
	 */
	private static boolean judgedIsUselessField(String name) {
		return "class".equals(name) || "ids".equals(name)
				|| "page".equals(name) || "rows".equals(name)
				|| "sort".equals(name) || "order".equals(name);
	}

	

	/**
	 * 字段为 key , 规则数据为 value
	 * @return
	 */
	public static Map<String, DataPermissionRules> getRuleMap() {
		Map<String, DataPermissionRules> ruleMap = new HashMap<>();

		// 从 request 域中获取数据规则，AOP 已将数据规则的数据放入到 request 域中
		List<DataPermissionRules> list = DataAutorUtils.loadDataSearchConditon();
		if(null != list && list.size() > 0){
			if(null == list.get(0)){
				return ruleMap;
			}
			for (DataPermissionRules rule : list) {
				// 获取查询条件字段(规则字段)
				String column = rule.getRuleColumn();
				//判断是否是自定义SQL
				if(QueryRuleEnum.SQL_RULES.getValue().equals(rule.getRuleConditions())) {
					column = SQL_RULES_COLUMN + rule.getRuleCode();
				}
				ruleMap.put(column, rule);
			}
		}
		return ruleMap;
	}

	/**
	 * 根据数据规则和成员，生成查询条件
	 * @param dataRule		数据规则
	 * @param name			成员名称
	 * @param propertyType	成员类型
	 * @param queryWrapper	查询添加
	 */
	private static void addRuleToQueryWrapper(DataPermissionRules dataRule, String name, Class propertyType, QueryWrapper<?> queryWrapper) {
		/**
		 * 通过规则条件(大于、等于、模糊等)获取到枚举类型
		 */
		QueryRuleEnum rule = QueryRuleEnum.getByValue(dataRule.getRuleConditions());
		/**
		 * 规则条件是 IN (包含), 并且数据类型不是 String
		 */
		if(rule.equals(QueryRuleEnum.IN) && !propertyType.equals(String.class)) {
			String[] values = dataRule.getRuleValue().split(",");
			Object[] objs = new Object[values.length];
			for (int i = 0; i < values.length; i++) {
				// 将数据转为对应的数据类型, 使用 decode/valueof 方法，将给定的数据解析为给定目标类的一个数字实例
				objs[i] = NumberUtils.parseNumber(values[i], propertyType);
			}
			// 生成查询条件
			addEasyQuery(queryWrapper, name, rule, objs);
		}else {
			if (propertyType.equals(String.class)) {
				addEasyQuery(queryWrapper, name, rule, converRuleValue(dataRule.getRuleValue()));
			} else {
				addEasyQuery(queryWrapper, name, rule, NumberUtils.parseNumber(dataRule.getRuleValue(), propertyType));
			}
		}
	}

	/**
	 * 获取(转换)数据规则值
	 * @param ruleValue
	 * @return
	 */
	public static String converRuleValue(String ruleValue) {
		String value = null;/*JwtUtil.getSessionData(ruleValue);
		if(ConvertUtils.isEmpty(value)) {
			value = JwtUtil.getUserSystemData(ruleValue,null);
		}*/
		return value!= null ? value : ruleValue;
	}

	/**
	 * 将自定义的 SQL 中的参数绑定，替换为值，实现 SQL 的拼接
	 * @param sqlRule
	 * @return
	 */
	public static String getSqlRuleValue(String sqlRule){
		try {
			// 获取到 SQL 中的 #{key} 中的 key
			Set<String> varParams = getSqlRuleParams(sqlRule);
			for(String var : varParams){
				// 获取到规则值
				String tempValue = converRuleValue(var);
				// 将规则值，替换掉 #{key}, 相当于 SQL 的拼接
				sqlRule = sqlRule.replace("#{"+var+"}",tempValue);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return sqlRule;
	}
	
	/**
	 * 获取sql中的 #{key}, 将 key 取出
	 * @param sql
	 * @return
	 */
	public static Set<String> getSqlRuleParams(String sql) {
		if(ConvertUtils.isEmpty(sql)){
			return null;
		}
		Set<String> varParams = new HashSet<>();
		String regex = "\\#\\{\\w+\\}";
		
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(sql);
		while(m.find()){
			String var = m.group();
			varParams.add(var.substring(var.indexOf("{")+1,var.indexOf("}")));
		}
		return varParams;
	}
	
	/**
	 * 获取查询条件 
	 * @param field
	 * @param alias
	 * @param value
	 * @param isString
	 * @return
	 */
	public static String getSingleQueryConditionSql(String field, String alias, Object value, boolean isString) {
		if (value == null) {
			return "";
		}
		field =  alias+ConvertUtils.camelToUnderline(field);
		QueryRuleEnum rule = QueryGenerator.convert2Rule(value);
		String res = "";
		switch (rule) {
		case GT:
			res =field+rule.getValue()+getFieldConditionValue(value, isString);
			break;
		case GE:
			res = field+rule.getValue()+getFieldConditionValue(value, isString);
			break;
		case LT:
			res = field+rule.getValue()+getFieldConditionValue(value, isString);
			break;
		case LE:
			res = field+rule.getValue()+getFieldConditionValue(value, isString);
			break;
		case EQ:
			res = field+rule.getValue()+getFieldConditionValue(value, isString);
			break;
		case NE:
			res = field+" <> "+getFieldConditionValue(value, isString);
			break;
		case IN:
			res = field + " in "+getInConditionValue(value, isString);
			break;
		case LIKE:
			res = field + " like "+getLikeConditionValue(value);
			break;
		case LEFT_LIKE:
			res = field + " like "+getLikeConditionValue(value);
			break;
		case RIGHT_LIKE:
			res = field + " like "+getLikeConditionValue(value);
			break;
		default:
			res = field+" = "+getFieldConditionValue(value, isString);
			break;
		}
		return res;
	}

	/**
	 * 将 value 中的特殊字符给剔除
	 * @param value
	 * @param isString
	 * @return
	 */
	private static String getFieldConditionValue(Object value, boolean isString) {
		String str = value.toString().trim();
		if(str.startsWith("!")) {
			str = str.substring(1);
		}else if(str.startsWith(">=")) {
			str = str.substring(2);
		}else if(str.startsWith("<=")) {
			str = str.substring(2);
		}else if(str.startsWith(">")) {
			str = str.substring(1);
		}else if(str.startsWith("<")) {
			str = str.substring(1);
		}
		if(isString) {
			return " '"+str+"' ";
		}else {
			return value.toString();
		}
	}

	/**
	 * 获取 IN 查询条件的值
	 * @param value
	 * @param isString
	 * @return
	 */
	private static String getInConditionValue(Object value, boolean isString) {
		if(isString) {
			String temp[] = value.toString().split(",");
			String res="";
			for (String string : temp) {
				res+=",'"+string+"'";
			}
			return "("+res.substring(1)+")";
		}else {
			return "("+value.toString()+")";
		}
	}

	/**
	 * 获取 LIKE 条件的值
	 * @param value
	 * @return
	 */
	private static String getLikeConditionValue(Object value) {
		String str = value.toString().trim();
		if(str.startsWith("*") && str.endsWith("*")) {
			return "'%"+str.substring(1,str.length()-1)+"%'";
		}else if(str.startsWith("*")) {
			return "'%"+str.substring(1)+"'";
		}else if(str.endsWith("*")) {
			return "'"+str.substring(0,str.length()-1)+"%'";
		}else {
			return str;
		}
	}
	
	
}
