package com.natrumax.services;

import com.natrumax.models.User;
import com.natrumax.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String accountName) throws UsernameNotFoundException {

        User user = userRepository.findByAccountName(accountName)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with accountName: " + accountName));

        return UserDetailsImpl.build(user);
    }


    @Transactional
    public UserDetails loadUserByPhoneNumber(String phoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with phone number: " + phoneNumber));

        return UserDetailsImpl.build(user);
    }
}
