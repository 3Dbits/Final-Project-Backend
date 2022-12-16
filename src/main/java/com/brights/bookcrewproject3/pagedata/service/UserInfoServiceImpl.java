package com.brights.bookcrewproject3.pagedata.service;

import com.brights.bookcrewproject3.pagedata.model.Friendship;
import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.pagedata.repository.FriendshipRepository;
import com.brights.bookcrewproject3.pagedata.repository.UserInfoRepository;
import com.brights.bookcrewproject3.security.model.User;
import com.brights.bookcrewproject3.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final FriendshipRepository friendshipRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository,  FriendshipRepository friendshipRepository,UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
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

    @Override
    public List<UserInfo> getAllFriends(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<UserInfo> userInfos = new ArrayList<>();

        for (var e : friendshipRepository.findAll()) {
            if (Objects.equals(e.getFriend1_id(), user.getUserInfo().getId())) {
                userInfos.add(userInfoRepository.findUserInfoById(e.getFriend2_id()));
            } else if (Objects.equals(e.getFriend2_id(), user.getUserInfo().getId())) {
                userInfos.add(userInfoRepository.findUserInfoById(e.getFriend1_id()));
            }
        }

        return userInfos;
    }

    @Override
    public void saveFriend(String username, long id) {
        User user = userRepository.findByUsername(username).orElseThrow();
        UserInfo userInfo = user.getUserInfo();

        friendshipRepository.save(new Friendship(userInfo, userInfo.getId(), id));
    }
}
