package com.study.gateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.study.gateway.utils.WeightRandom;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.apache.commons.math3.util.Pair;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

/**
 * @author GAOFENG
 * @create 2019/1/21
 * @description
 */
@Configuration
public class PreFilter extends ZuulFilter {
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // a filter has already forwarded
        // a filter has already determined serviceId
        return !ctx.containsKey(FORWARD_TO_KEY)
                && !ctx.containsKey(SERVICE_ID_KEY);
    }

    @Override
    public Object run() {
        List<Pair<String,Integer>> list = new ArrayList<>() ;
        list.add(new Pair<>("1",8)) ;
        list.add(new Pair<>("2",2)) ;
        WeightRandom<String,Integer> weightRandom = new WeightRandom<>(list) ;
        RibbonFilterContextHolder.getCurrentContext().add("lancher",weightRandom.random()) ;
        return null;
    }
}
