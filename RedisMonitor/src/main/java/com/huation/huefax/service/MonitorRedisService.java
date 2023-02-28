package com.huation.huefax.service;

import com.huation.huefax.monitor.MonitorDTO;

public interface MonitorRedisService {
	MonitorDTO save(MonitorDTO dto);
	MonitorDTO findById(String faxDevicePortId);
}
