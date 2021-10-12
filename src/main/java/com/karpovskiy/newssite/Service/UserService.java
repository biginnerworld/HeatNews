package com.karpovskiy.newssite.Service;

import com.karpovskiy.newssite.Entity.UserEntity;
import com.karpovskiy.newssite.Generator.IDGenerator;
import com.karpovskiy.newssite.Repository.UserRepository;
import com.karpovskiy.newssite.Security.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getListOfUsers(){
        return userRepository.findAll();
    }

    public UserEntity findUserByUsername(String username){
        return userRepository.findUserEntityByUsername(username);
    }

    public UserEntity findUserById(String id){
        return userRepository.findUserEntityById(id);
    }

    public void saveUserToDb(UserEntity userEntity){
        userEntity.setId(IDGenerator.generateID());
        userEntity.setActive(true);
        userEntity.setRole(Role.USER);
        userRepository.save(userEntity);
    }

    public void patchUser(UserEntity userEntity){
        userRepository.save(userEntity);
    }
}
