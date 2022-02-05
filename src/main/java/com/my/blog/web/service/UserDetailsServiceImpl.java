//package com.my.blog.web.service;
//
//import com.my.blog.web.domain.UserEntity;
//import com.my.blog.web.dto.UserInfo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserService userInfoRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        UserEntity userEntity = userInfoRepository.findByEmail(email);
//        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);
//        return customUserDetails;
//    }
//}
