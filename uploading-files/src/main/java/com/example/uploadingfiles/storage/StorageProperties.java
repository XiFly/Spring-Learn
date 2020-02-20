package com.example.uploadingfiles.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 功能描述：
 *
 * @author wangxiangfei
 * @version 1.0
 * @date 2020/2/20 22:59
 **/
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
