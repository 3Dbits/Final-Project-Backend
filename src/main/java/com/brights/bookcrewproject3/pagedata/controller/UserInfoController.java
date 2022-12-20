package com.brights.bookcrewproject3.pagedata.controller;

import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.pagedata.service.UserInfoService;
import com.brights.bookcrewproject3.security.model.User;
import com.brights.bookcrewproject3.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) //MC all urls can access this controller
@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRepository userRepository;

    //Get User info for login user
    @GetMapping
    public ResponseEntity<UserInfo> getUserInfo(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByUsername(userDetails.getUsername()).get();

        return new ResponseEntity<>(user.getUserInfo(), HttpStatus.OK);
    }

    //Get User Info when searching for people
    @GetMapping("/all")
    public ResponseEntity<List<UserInfo>> getAllUserInfo(Authentication authentication) {
        List<UserInfo> userInfos = userInfoService.getAllUserInfo();

        if (userInfos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<User>> getUsersByUsername(@PathVariable(value = "username") String username) {
        List<User> users = new ArrayList<>();
        if (userRepository.findByUsername(username).orElse(null) != null) {
            users.add(userRepository.findByUsername(username).get());
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
