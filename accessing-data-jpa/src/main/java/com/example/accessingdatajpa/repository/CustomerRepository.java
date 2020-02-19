package com.example.accessingdatajpa.repository;

import com.example.accessingdatajpa.pojo.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 功能描述：
 * CustomerRepository jpa接口
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/17 20:19
 **/
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
}
