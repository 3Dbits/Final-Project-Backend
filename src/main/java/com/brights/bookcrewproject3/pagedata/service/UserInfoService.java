package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo saveUser(UserInfo userInfo) ;
    List<UserInfo> getAllUserInfo();
    List<UserInfo> getAllUserInfoByFirstName(String first_name);
    UserInfo getUserInfoById(long id);
    UserInfo updateUserInfoById(long id, UserInfo userInfo);

    void deleteUserInfoById(long id);

    void deleteAllUserInfo();
}
