package com.blog.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    public static<T> T copyBean(Object source, Class<T> clazz) {
        T o = null;
        try {
            o = clazz.newInstance();
            BeanUtils.copyProperties(source, o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }


    public static<O,V> List<V> copyBeanList(List<O> list, Class<V> clazz) {
        return list.stream().map(o->
                copyBean(o,clazz)
        ).collect(Collectors.toList());
    }
}