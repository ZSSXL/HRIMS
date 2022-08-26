package com.zss.hrims.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author ZSS
 * @date 2022/8/2 16:34
 * @desc 分页工具
 */
public class PageUtil {

    public static <T> IPage<T> of(Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        return new Page<>(pageIndex, pageSize);
    }

    /**
     * 创建排序
     *
     * @param targetColumn 排序字段
     * @param sort        true: DESC, false: ASC, Default: ASC
     * @param <T>          targetClass
     * @return QueryWrapper
     */
    public static <T> QueryWrapper<T> orderBy(String targetColumn, Boolean sort) {
        QueryWrapper<T> query = new QueryWrapper<>();
        if (sort != null) {
            if (sort) {
                query.orderByDesc(targetColumn);
            } else {
                query.orderByAsc(targetColumn);
            }
        } else {
            query.orderByAsc(targetColumn);
        }
        return query;
    }
}
