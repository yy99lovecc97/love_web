package com.lovecc.love_web.service.impl;

import com.lovecc.love_web.dao.UserDao;
import com.lovecc.love_web.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userDao.findByUsername(s);
    }

    public User createUser(User user){
        return userDao.save(user);
    }

    public boolean existsUsername(String username){
        return userDao.existsByUsername(username);
    }

    public boolean existsOnlyId(String only_id){
        return  userDao.existsByOnlyId(only_id);
    }
}
