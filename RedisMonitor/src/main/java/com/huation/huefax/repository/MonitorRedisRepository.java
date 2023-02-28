package com.huation.huefax.repository;

import org.springframework.data.repository.CrudRepository;

import com.huation.huefax.monitor.MonitorDTO;

public interface MonitorRedisRepository extends CrudRepository<MonitorDTO, String>{

}
