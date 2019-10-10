package com.exx.dzj.facade.newyeargoods;

import com.exx.dzj.constant.CommonConstant;
import com.exx.dzj.entity.newyeargoods.NewGiftBaseInfo;
import com.exx.dzj.entity.newyeargoods.NewGiftDeatilBean;
import com.exx.dzj.entity.newyeargoods.NewGiftMainBean;
import com.exx.dzj.entity.newyeargoods.NewYearGoodsBean;
import com.exx.dzj.service.newyeargoods.NewYearGoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName NewYearGoodsFacade
 * @Description:
 * @Author yangyun
 * @Date 2019/10/9 0009 10:23
 * @Version 1.0
 **/
@Component
public class NewYearGoodsFacade {

    @Autowired
    private NewYearGoodsService newYearGoodsService;

    public  Map<String, Object> query2020NewYearList() {
        List<NewGiftBaseInfo> newGiftBaseInfos = newYearGoodsService.query2020NewYearList();
        Map<String, Object> mapData = new HashMap<>();
        List<NewGiftMainBean> data = new ArrayList<>();
        mapData.put("data", data);

        if (CollectionUtils.isEmpty(newGiftBaseInfos)){
            return mapData;
        }

        Map<String, Object> map = sort(newGiftBaseInfos);
        LinkedHashMap<String, LinkedHashMap<String, List<NewGiftBaseInfo>>> collect = (LinkedHashMap<String, LinkedHashMap<String, List<NewGiftBaseInfo>>>)map.get("map");
        List<NewYearGoodsBean> goodsLists = (List<NewYearGoodsBean>)map.get("goodsList");
        List<NewYearGoodsBean> collect1 = null;
        if (!CollectionUtils.isEmpty(goodsLists)){

            Comparator<NewYearGoodsBean> com = (a, b) -> b.getGoodsNum().compareTo(a.getGoodsNum());
            goodsLists = goodsLists.stream().sorted(com).collect(Collectors.toList());
        }


        mapData.put("goodsLists", goodsLists); // 商品列表

        List<NewGiftDeatilBean> detailList = null;
        List<NewYearGoodsBean> goodsList = null;
        NewGiftMainBean mainGift = null;
        NewGiftDeatilBean detailGift = null;
        NewGiftBaseInfo info = null;
        NewYearGoodsBean goodsBean = null;
        for (String str : collect.keySet()) {
            mainGift = new NewGiftMainBean();
            detailList = mainGift.getDetailList();
            mainGift.setCategoryCode(str);

            data.add(mainGift);

            Map<String, List<NewGiftBaseInfo>> listMap = collect.get(str);
            for (String key : listMap.keySet()){
                detailGift = new NewGiftDeatilBean();
                goodsList = detailGift.getGoodsList();
                detailGift.setShowName(key);

                detailList.add(detailGift);

                List<NewGiftBaseInfo> baseList = listMap.get(key);

                if (!CollectionUtils.isEmpty(baseList)){
                    for (int i = 0; i < baseList.size(); i++){
                        info = baseList.get(i);
                        if (StringUtils.isNotEmpty(info.getStockCode())){
                            goodsBean = new NewYearGoodsBean();
                            goodsBean.setStockName(info.getStockName()).setGoodsNum(info.getGoodsNum()).setSumVolume(info.getSumVolume()).setStockCode(info.getStockCode());
                            goodsList.add(goodsBean);
                        }

                        if (i == 0){
                            mainGift.setId(info.getId());
                            mainGift.setCategoryName(info.getCategoryName());


                            detailGift.setCategoryCode(info.getCategoryCode());
                            detailGift.setStockCode(info.getDetailStockCode());
                            detailGift.setId(info.getDetailId());
                        }

                    }
                }
            }
        }

        calculateVolume(data);

        double sumNum = data.stream().mapToDouble(NewGiftMainBean::getSumGoodsNum).sum();
        BigDecimal sumVolume = data.stream().map(NewGiftMainBean::getSumVolume).reduce(BigDecimal.ZERO, BigDecimal::add);

        mapData.put("data", data);
        mapData.put("sumNum", sumNum); //  总数量
        mapData.put("sumVolume", sumVolume); // 总金额

        // 今日
        NewYearGoodsBean goodsBean1 = queryDayData();
        mapData.put("dayData", goodsBean1);

        return mapData;
    }

    private void calculateVolume (List<NewGiftMainBean> data){
        Double goodsNum = 0.0;
        BigDecimal goodsVolume = BigDecimal.ZERO;

        Double giftDeatilGoodsNum = 0.0;
        BigDecimal giftDeatilGoodsVolume = BigDecimal.ZERO;

        for (NewGiftMainBean main : data){
            for (NewGiftDeatilBean detail : main.getDetailList()) {
                goodsNum = detail.getGoodsList().stream().mapToDouble(NewYearGoodsBean::getGoodsNum).sum();
                goodsVolume = detail.getGoodsList().stream().map(NewYearGoodsBean::getSumVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
                detail.setSumGoodsNum(goodsNum);
                detail.setSumVolume(goodsVolume);
            }

            giftDeatilGoodsNum = main.getDetailList().stream().mapToDouble(NewGiftDeatilBean::getSumGoodsNum).sum();
            giftDeatilGoodsVolume = main.getDetailList().stream().map(NewGiftDeatilBean::getSumVolume).reduce(BigDecimal.ZERO, BigDecimal::add);
            main.setSumGoodsNum(giftDeatilGoodsNum);
            main.setSumVolume(giftDeatilGoodsVolume);
        }
    }

    private Map<String, Object> sort (List<NewGiftBaseInfo> newGiftBaseInfos){
        Map<String, Object> data = new HashMap<>();
        LinkedHashMap<String, LinkedHashMap<String, List<NewGiftBaseInfo>>> map = new LinkedHashMap<>();
        List<NewYearGoodsBean> goodsList = new ArrayList<>();
        data.put("map", map);
        data.put("goodsList", goodsList);

        List<NewGiftBaseInfo> list = null;
        LinkedHashMap<String, List<NewGiftBaseInfo>> mainMap = null;

        List<NewGiftBaseInfo> giftBaseInfos = null;

        NewYearGoodsBean goodsBean = null;

        for (NewGiftBaseInfo temp : newGiftBaseInfos){
            if (StringUtils.isNotEmpty(temp.getStockCode())){
                goodsBean = new NewYearGoodsBean();
                goodsBean.setStockName(temp.getStockName()).setGoodsNum(temp.getGoodsNum()).setSumVolume(temp.getSumVolume()).setStockCode(temp.getStockCode());

                goodsList.add(goodsBean);
            }

            mainMap = map.get(temp.getCategoryCode());
            if (mainMap == null){
                mainMap = new LinkedHashMap<>();
                map.put(temp.getCategoryCode(), mainMap);

                list =  new ArrayList<>();

                mainMap.put(temp.getShowName(), list);
                list.add(temp);
            } else {
                giftBaseInfos = mainMap.get(temp.getShowName());
                if (giftBaseInfos ==  null){
                    giftBaseInfos = new ArrayList<>();
                    mainMap.put(temp.getShowName(), giftBaseInfos);
                }
                giftBaseInfos.add(temp);
            }
        }


        return data;
    }

    public NewYearGoodsBean queryDayData() {
        return newYearGoodsService.queryDayData();
    }
}
