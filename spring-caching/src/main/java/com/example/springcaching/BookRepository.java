package com.example.springcaching;

/**
 * 功能描述：
 *
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/23 18:18
 **/
public interface BookRepository {
    Book getByIsbn(String isbn);
}
