package com.exx.dzj.common.export;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.exx.dzj.entity.newyeargoods.NewGiftDeatilBean;
import com.exx.dzj.entity.newyeargoods.NewGiftMainBean;
import com.exx.dzj.entity.newyeargoods.NewYearGoodsBean;
import com.exx.dzj.util.excel.export.model.NewYearGift2020Model;
import com.exx.dzj.util.excel.export.model.NewYearGoodsModel;

import javax.servlet.ServletOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewYearGiftExportUtil
 * @Description: 新春礼品导出
 * @Author yangyun
 * @Date 2019/10/10 0010 14:09
 * @Version 1.0
 **/
public class NewYearGiftExportUtil extends ExportUtil {

    public static ExcelWriter newYears2020GiftExport (Map<String, Object> map, ServletOutputStream outputStream){
        ExcelWriter writer = null;
        try {
            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            String sheetOneName = "新春礼品";
            Map<Integer, Integer> mapOneStyle = new HashMap<>();
            for (int i = 0; i < NewYearGift2020Model.class.getDeclaredFields().length; i++) {
                mapOneStyle.put(i, 4000);
            }
            Sheet sheetOne = gainSheet(mapOneStyle, sheetOneName, 1);
            Table titleOne = new Table(1);
            titleOne.setClazz(NewYearGift2020Model.class);
            writer.write(null, sheetOne, titleOne);

            List<NewGiftMainBean> data = (List<NewGiftMainBean>)map.get("data");

            NewYearGift2020Model model = null;
            List<NewYearGift2020Model> list = new ArrayList<>();
            NewGiftDeatilBean detail = null;
            NewYearGoodsBean goods = null;

            List<NewYearGoodsBean> goodsList = null;
            List<NewGiftDeatilBean> detailList = null;

            for (NewGiftMainBean main : data){
                model =  new NewYearGift2020Model();
                list.add(model);
                model.setCategoryName(main.getCategoryName());
                detailList = main.getDetailList();
                for (int i = 0; i< detailList.size(); i++){
                    detail = detailList.get(i);

                    if (i > 0){
                        model = new NewYearGift2020Model();
                        list.add(model);
                    }

                    model.setStockGroupName(detail.getShowName());

                    goodsList = detail.getGoodsList();

                    for (int j = 0; j < goodsList.size(); j++){
                        goods = goodsList.get(j);
                        if (j > 0){
                            model = new NewYearGift2020Model();
                            list.add(model);
                        }
                        model.setStockCode(goods.getStockCode()).setStockName(goods.getStockName()).setGoodsNum(goods.getGoodsNum()).setVolume(goods.getSumVolume().doubleValue());
                    }
                    model = new NewYearGift2020Model();
                    model.setStockName("小计: ").setGoodsNum(detail.getSumGoodsNum()).setVolume(detail.getSumVolume().doubleValue());
                    list.add(model);
                }
                model = new NewYearGift2020Model();
                model.setStockName("合计: ").setGoodsNum(main.getSumGoodsNum()).setVolume(main.getSumVolume().doubleValue());
                list.add(model);
            }
            model = new NewYearGift2020Model();
            model.setStockName("总计: ").setGoodsNum((Double) map.get("sumNum")).setVolume(((BigDecimal)map.get("sumVolume")).doubleValue());
            list.add(model);
            writer.write(list, sheetOne);

        }catch (Exception e){
            e.printStackTrace();
        }

        return writer;
    }

    public static ExcelWriter exportGoodsListData (Map<String, Object> data, ServletOutputStream outputStream){
        ExcelWriter writer = null;
        try {
            writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);

            String sheetTwoName = "商品排行";
            Map<Integer, Integer> mapTwoStyle = new HashMap<>();
            for (int i = 0; i < NewYearGoodsModel.class.getDeclaredFields().length; i++) {
                mapTwoStyle.put(i, 4000);
            }

            Sheet sheetTwo = gainSheet(mapTwoStyle, sheetTwoName, 1);
            Table titleTwo = new Table(1);
            titleTwo.setClazz(NewYearGoodsModel.class);
            writer.write(new ArrayList<>(), sheetTwo, titleTwo);

            List<NewYearGoodsBean> goodsList = (List<NewYearGoodsBean>)data.get("goodsLists");

            NewYearGoodsModel model = null;
            List<NewYearGoodsModel> list = new ArrayList<>();

            for (NewYearGoodsBean temp : goodsList){
                model = new NewYearGoodsModel();
                list.add(model);

                model.setStockCode(temp.getStockCode()).setStockName(temp.getStockName()).setGoodsNum(temp.getGoodsNum());
            }

            writer.write(list, sheetTwo);

        }catch (Exception e){
            e.printStackTrace();
        }

        return writer;
    }
}
