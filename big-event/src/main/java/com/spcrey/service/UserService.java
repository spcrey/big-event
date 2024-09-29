package com.spcrey.service;

import com.spcrey.pojo.User;

public interface UserService {

    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);
    
    void updatePwd(String newPwd);  
}
