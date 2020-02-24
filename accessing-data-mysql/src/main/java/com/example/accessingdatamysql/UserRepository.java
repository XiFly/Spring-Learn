package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

/**
 * 功能描述：
 *
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/23 22:15
 **/
public interface UserRepository extends CrudRepository<User,Integer> {

}
