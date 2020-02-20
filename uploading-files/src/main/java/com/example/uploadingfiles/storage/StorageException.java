package com.example.uploadingfiles.storage;

/**
 * 功能描述：
 *
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/20 23:03
 **/
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
