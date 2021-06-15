package com.nocodebdd.demo.usermanagementservice.repository;

import com.nocodebdd.demo.usermanagementservice.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserManagementRepository extends CrudRepository<UserEntity, String> {
}
