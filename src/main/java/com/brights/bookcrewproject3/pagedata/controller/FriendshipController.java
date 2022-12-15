package com.brights.bookcrewproject3.pagedata.controller;

import com.brights.bookcrewproject3.pagedata.model.Friendship;
import com.brights.bookcrewproject3.pagedata.model.UserInfo;
import com.brights.bookcrewproject3.pagedata.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600) //MC all urls can access this controller
@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping()
    public ResponseEntity<List<UserInfo>> getAllFriend(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        List<UserInfo> userInfos = userInfoService.getAllFriends(userDetails.getUsername());

        if (userInfos.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userInfos, HttpStatus.OK);
    }

    @PostMapping("/add/{id}")
    public ResponseEntity<HttpStatus> addNewFriend(Authentication authentication,
                                                   @PathVariable(value = "id") long id) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        userInfoService.saveFriend(userDetails.getUsername(), id);

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
