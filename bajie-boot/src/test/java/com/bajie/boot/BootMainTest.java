package com.bajie.boot;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.bajie.base.domain.BaseDomain;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * boot测试
 *
 * @author bajie
 * @date 2022-05-06 2:34 下午
 * @since 1.0.0
 */
public class BootMainTest {

    public static void main(String[] args) {
        BaseDomain bajie = new BaseDomain().setCreateBy("bajie").setCreateTime(LocalDateTime.now());
        System.out.println(bajie);
        System.out.println(JSON.toJSONString(bajie));

        List<BaseDomain> bajieList = new LinkedList<>();
        bajieList.add(bajie);
        bajieList.add(bajie);
        String bajieListJsonStr = JSON.toJSONString(bajieList, JSONWriter.Feature.BeanToArray);
        System.out.println(bajieListJsonStr);

        String str = "{\"createBy\":\"bajie\",\"createTime\":\"2022-05-06T14:39:32.662\"}";


    }

}
