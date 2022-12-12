package com.brights.bookcrewproject3.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    UserDetailsService loadUserByUsername(String username) throws UsernameNotFoundException;
}
