package com.exx.dzj;

import java.math.BigDecimal;

/**
 * @Author
 * @Date 2019/7/29 0029 15:01
 * @Description 单元测试父类
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class AppTests {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(-1);
        BigDecimal b = new BigDecimal(0);
        System.out.println(a.compareTo(b) <= 0);
    }
}
