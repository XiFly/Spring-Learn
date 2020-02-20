package com.example.uploadingfiles.storage;

/**
 * 功能描述：
 *
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/20 23:04
 **/
public class StorageFileNotFoundException extends StorageException {
    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
