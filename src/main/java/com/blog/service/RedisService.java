package com.blog.service;

import java.util.List;
import java.util.Set;

/**
 * redis操作Service
 */
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超期时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     *自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);

    /**
     *  去除所有的key-value
     */
    Set<String> listAll();

}
