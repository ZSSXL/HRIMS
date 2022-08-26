package com.zss.hrims.utils;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zss
 * @date 2020/7/15 13:27
 * @desc dozer - 转换器
 */
@Component
@SuppressWarnings("unused")
public class GeneralConverter {

    @Resource
    Mapper mapper;

    /**
     * List 实体类 转化器
     *
     * @param source 源数据
     * @param clazz  转化类型
     * @param <T>    泛型
     * @param <S>    泛型
     * @return List<T>
     */
    public <T, S> List<T> converter(List<S> source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (S s : source) {
            list.add(mapper.map(s, clazz));
        }
        return list;
    }

    /**
     * 实体类 转换
     *
     * @param source 源数据
     * @param clazz  目标对象
     * @param <T>    泛型
     * @param <S>    泛型
     * @return T
     */
    public <T, S> T converter(S source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, clazz);
    }

    /**
     * 转换器
     *
     * @param source 源数据
     * @param object 目标对象
     */
    public void converter(Object source, Object object) {
        mapper.map(source, object);
    }

    /**
     * 转换器
     *
     * @param source 源数据
     * @param object 目标对象
     * @param <T>    泛型
     */
    public <T> void copyConverter(T source, Object object) {
        mapper.map(source, object);
    }

}
