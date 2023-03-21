package com.example.cloudservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.cloudservice.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
}
