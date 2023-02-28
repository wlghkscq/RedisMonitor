package com.huation.huefax.serviceimpl;

import org.springframework.stereotype.Service;

import com.huation.huefax.redis.RefreshToken;
import com.huation.huefax.repository.RefreshTokenRedisRepository;
import com.huation.huefax.service.AdminUserRedisService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUserRedisServiceImpl implements AdminUserRedisService{
	private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public RefreshToken save(RefreshToken adminUserToken) {
        return refreshTokenRedisRepository.save(adminUserToken);
    }

    @Override
    public RefreshToken findById(String userId) {
        return refreshTokenRedisRepository.findById(userId).get();
    }
}
