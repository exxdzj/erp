package com.exx.dzj.unique;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author
 * @Date 2019/1/8 0008 11:22
 * @Description
 */
public class TestStandaloneIdGenerator {
    @Test
    public void test1(){
        IdGenerator idGenerator = new DefaultIdGenerator();

        System.out.println("--------简单测试------------------");
        for (int i=0; i<100; i++){
            System.out.println(idGenerator.next());
        }
    }

    @Test
    public void test2(){
        IdGenerator idGenerator = new DefaultIdGenerator();

        //多线程测试
        System.out.println("--------多线程测试不重复------------------");
        Set<String> idSet = Collections.synchronizedSet(new HashSet<>());
        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i=0; i<2000000; i++){
            es.submit(() -> {
                String val = idGenerator.next();
                if (idSet.contains(val)){
                    System.out.println("重复了: " + val);
                }else{
                    idSet.add(val);
                }
            });
        }
        es.shutdown();
        System.out.println("启用顺序关闭");
        while(true){
            if(es.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                System.out.println("子线程的任务还没运行完");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("共生成: " + idSet.size() + "个");
    }

    @Test
    public void  test3(){
        //测试单机性能
        System.out.println("--------测试单线程性能------------------");
        IdGenerator idGenerator2 = new DefaultIdGenerator();
        long t1 = System.currentTimeMillis();
        int total = 10000000;
        for (int i=0; i<total; i++){
            idGenerator2.next();
        }
        System.out.println("单线程生成" + total + "个ID共耗时: " + (System.currentTimeMillis() - t1) + "ms");
    }

    //500个线程并发, 每个线程获取10000个ID
    @Test
    public void test4(){
        //测试多线程性能
        System.out.println("--------测试多线程性能------------------");
        ExecutorService es1 = Executors.newFixedThreadPool(500);
        IdGenerator idGenerator3 = new DefaultIdGenerator();
        long t1 = System.currentTimeMillis();
        for (int i=0; i<500; i++){
            es1.submit(() -> {
                int count = 0;
                while (count < 10000){
                    idGenerator3.next();

                    count++;
                }
            });
        }
        es1.shutdown();
        System.out.println("启用顺序关闭");
        while(true){
            if(es1.isTerminated()){
                System.out.println("所有的子线程都结束了！");
                break;
            }
            try {
                System.out.println("子线程的任务还没运行完");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("500线程,每个线程生成10000个序列号.共耗时: " + (System.currentTimeMillis() - t1) + " ms");
    }

    @Test
    public void test5(){
        System.out.println("--------测试生成的ID是否有时间滚动----------");
        IdGenerator idGenerator = new DefaultIdGenerator();
        for (int i=0; i<20; i++){
            String id = idGenerator.next();
            System.out.println(id);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test6(){
        System.out.println("--------ID生成器的特殊设置相关----------");
        IdGeneratorConfig config = new DefaultIdGeneratorConfig() {
            @Override
            public String getSplitString() {
                return "-";
            }
            @Override
            public int getInitial() {
                return 1000000;
            }
            @Override
            public String getPrefix() {
                return "NODE01";
            }
        };
        IdGenerator idGenerator = new DefaultIdGenerator(config);
        for (int i=0; i<20; i++){
            String id = idGenerator.next();
            System.out.println(id);
            try {
                Thread.sleep(1000 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
