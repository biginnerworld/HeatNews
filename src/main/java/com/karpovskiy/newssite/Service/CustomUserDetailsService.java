package com.karpovskiy.newssite.Service;

import com.karpovskiy.newssite.Entity.UserEntity;
import com.karpovskiy.newssite.Repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity tempUser = userRepository.findUserEntityByUsername(username);
        if (tempUser == null){
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        UserDetails user = User.builder()
                .username(tempUser.getUsername())
                .password(tempUser.getPassword())
                .roles(tempUser.getRole().name())
                .authorities(tempUser.getRole().getGrantedAuthority())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .build();
        return user;
    }
}
