package com.huation.huefax.service;

import com.huation.huefax.redis.RefreshToken;

public interface AdminUserRedisService {
	RefreshToken save(RefreshToken adminUserToken);
    RefreshToken findById(String userId);
}
