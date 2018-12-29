package com.example.factory.model;

/**
 * 基础用户接口
 * @author linzx
 * @date 2018/12/27
 */
public interface Author {
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    String getPortrait();

    void setPortrait(String portrait);
}
