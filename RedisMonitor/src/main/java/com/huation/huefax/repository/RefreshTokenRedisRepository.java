package com.huation.huefax.repository;

import org.springframework.data.repository.CrudRepository;

import com.huation.huefax.redis.RefreshToken;

public interface RefreshTokenRedisRepository extends CrudRepository<RefreshToken, String>{

}
