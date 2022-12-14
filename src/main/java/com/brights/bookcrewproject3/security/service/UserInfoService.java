package com.brights.bookcrewproject3.security.service;

import com.brights.bookcrewproject3.security.model.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {

    UserInfo saveUser(UserInfo userInfo) ;
    List<UserInfo> getAllUserInfo();
    List<UserInfo> getAllUserInfoByFirstName(String first_name);
    UserInfo getUserInfoById(long id);
    UserInfo updateUserInfoById(long id, UserInfo userInfo);

    void deleteUserInfoById(long id);

    void deleteAllUserInfo();
}
