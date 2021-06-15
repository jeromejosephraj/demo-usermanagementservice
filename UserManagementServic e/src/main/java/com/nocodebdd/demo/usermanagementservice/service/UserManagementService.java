package com.nocodebdd.demo.usermanagementservice.service;

import com.nocodebdd.demo.usermanagementservice.entity.UserEntity;
import com.nocodebdd.demo.usermanagementservice.repository.UserManagementRepository;
import com.nocodebdd.demo.usermanagementservice.to.UserTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserManagementService {

    @Autowired
    UserManagementRepository userManagementRepository;

    public void add(UserTO userTO){
        validate(userTO);
        checkUserName(userTO);
        UserEntity userEntity = new ModelMapper().map(userTO, UserEntity.class);
        userManagementRepository.save(userEntity);
        log.info("record added succesfully");
    }

    private void checkUserName(UserTO userTO) {
        if(isUserExist(userTO)){
            throw new IllegalStateException("User already exists");
        }
    }

    private void validate(UserTO userTO) {
        if(userTO.getFirstName() == null || userTO.getFirstName().isEmpty() || userTO.getFirstName().equalsIgnoreCase("null")){
            throw new IllegalStateException("First Name can't be empty");
        }

        if(userTO.getLastName() == null || userTO.getLastName().isEmpty() || userTO.getLastName().equalsIgnoreCase("null")){
            throw new IllegalStateException("Last Name can't be empty");
        }

        if(userTO.getUserName() == null || userTO.getUserName().isEmpty() || userTO.getUserName().equalsIgnoreCase("null")){
            throw new IllegalStateException("User Name can't be empty");
        }

    }

    public boolean isUserExist(UserTO userTO){
        Optional<UserEntity> userEntityOptional =  userManagementRepository.findById(userTO.getUserName()) ;
        if(!userEntityOptional.isPresent()){
            return false;
        }
        UserEntity userEntity = userEntityOptional.get();
        log.info("Retrieved userentity is : {} ", userEntity);
        return userEntity != null && userEntity.getUserName() != null && !userEntity.getUserName().isEmpty();
    }
}
