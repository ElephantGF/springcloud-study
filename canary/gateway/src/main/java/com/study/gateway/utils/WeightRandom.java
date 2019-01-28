package com.study.gateway.utils;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.math3.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class WeightRandom<K,V extends Number> {
    private TreeMap<Double, K> weightMap = new TreeMap<Double, K>();
    private static final Logger logger = LoggerFactory.getLogger(WeightRandom.class);

    public WeightRandom(List<Pair<K, V>> list) {
        Preconditions.checkNotNull(list, "list can NOT be null!");
            for (Pair<K, V> pair : list) {
            double lastWeight = this.weightMap.size() == 0 ? 0 : this.weightMap.lastKey().doubleValue();//统一转为double
            this.weightMap.put(pair.getValue().doubleValue() + lastWeight, pair.getKey());//权重累加
        }
    }

    public K random() {
        double randomWeight = this.weightMap.lastKey() * Math.random();
        SortedMap<Double, K> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    public static void main(String[] args) {
        List<Pair<String,Integer>> list = new ArrayList<>() ;
        list.add(new Pair<>("A",7)) ;
        list.add(new Pair<>("B",3)) ;
        WeightRandom<String,Integer> weightRandom = new WeightRandom<>(list) ;
        int i = 0 ;
        int aCount = 0 ;
        int bCount = 0;
        while (i<1000){
            if("A".equals(weightRandom.random())){
                aCount++ ;
            }else {
                bCount++ ;
            }
            i++ ;
        }

        logger.info("A出现次数："+aCount);
        logger.info("B出现次数："+bCount);

    }


}
