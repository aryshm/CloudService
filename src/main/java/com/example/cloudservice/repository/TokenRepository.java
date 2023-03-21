package com.example.cloudservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.cloudservice.entities.TokenEntity;

@Repository
public interface TokenRepository extends CrudRepository<TokenEntity, String> {
}
