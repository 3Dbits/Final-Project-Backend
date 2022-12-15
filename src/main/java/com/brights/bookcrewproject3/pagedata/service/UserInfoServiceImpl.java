package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.pagedata.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return userInfoRepository.findAll();
    }

    @Override
    public List<UserInfo> getAllUserInfoByFirstName(String first_name) {
        return userInfoRepository.findByFirstName(first_name);
    }

    @Override
    public UserInfo getUserInfoById(long id) {
        Optional<UserInfo> userInfoData = userInfoRepository.findById(id);

        return userInfoData.orElse(null);
    }

    @Override
    public UserInfo updateUserInfoById(long id, UserInfo userInfo) {
        UserInfo userInfoData = getUserInfoById(id);

        if (userInfoData!= null ) {
            userInfoData.setFirstName(userInfo.getFirstName());
            userInfoData.setLastName(userInfo.getLastName());
            userInfoData.setDateOfBirth(userInfo.getDateOfBirth());
            userInfoData.setSignupSate(userInfo.getSignupSate());
            return userInfoData;
        }
        return null;
    }

    @Override
    public void deleteUserInfoById(long id) {
        userInfoRepository.deleteById(id);
    }

    @Override
    public void deleteAllUserInfo() {
        userInfoRepository.deleteAll();
    }
}
