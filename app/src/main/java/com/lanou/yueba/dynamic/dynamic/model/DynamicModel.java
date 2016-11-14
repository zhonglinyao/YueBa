package com.lanou.yueba.dynamic.dynamic.model;

import com.lanou.yueba.dynamic.dynamic.OnQueryListener;

import java.util.List;

/**
 * Created by dllo on 16/11/8.
 */

public interface DynamicModel {
    void queryFriend(String username, OnQueryListener queryListener);
    void queryDynamic(List<String> user, OnQueryListener queryListener);
}
