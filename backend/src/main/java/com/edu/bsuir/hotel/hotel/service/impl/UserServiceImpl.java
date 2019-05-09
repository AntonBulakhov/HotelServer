package com.edu.bsuir.hotel.hotel.service.impl;

import com.edu.bsuir.hotel.hotel.entity.UserEntity;
import com.edu.bsuir.hotel.hotel.repository.UserRepository;
import com.edu.bsuir.hotel.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("customUserDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCrypt;

    @Override
    public UserEntity findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        userEntity.setPassword(bCrypt.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> findAllByBlocked(byte bl) {
        return userRepository.findAllByBlocked(bl);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity user = findByLogin(login);
        return new  org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + (user.getRole() == 0 ? "USER" : "ADMIN")));
        return authorities;
    }
}
