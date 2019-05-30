package com.exx.dzj.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * @Date 2019/5/28 0028 9:54
 * @Description 查询条件
 */
@Data
public class QueryCondition implements Serializable {

	private static final long serialVersionUID = 4740166316629191651L;
	
	private String field;
	private String type;
	private String rule;
	private String val;

	@Override
	public String toString(){
		StringBuffer sb =new StringBuffer();
		if(field == null || "".equals(field)){
			return "";
		}
		sb.append(this.field).append(" ").append(this.rule).append(" ").append(this.type).append(" ").append(this.val);
		return sb.toString();
	}
}
