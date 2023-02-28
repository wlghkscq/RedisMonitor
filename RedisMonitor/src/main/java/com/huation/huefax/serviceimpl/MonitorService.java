package com.huation.huefax.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huation.SqlMapper.MonitoringMapper;
import com.huation.huefax.monitor.MonitorDTO;
import com.huation.huefax.repository.MonitorRedisRepository;
import com.huation.huefax.service.MonitorRedisService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MonitorService implements MonitorRedisService{
	
	private final MonitorRedisRepository monitorRedisRepository;
	
	
	@Autowired(required = true)
	MonitoringMapper mapper;
	

	public List<MonitorDTO> selectFaxdeviceMonitor(MonitorDTO dto) {
		
		return mapper.selectFaxdeviceMonitor(dto);
	}


	@Override
	public MonitorDTO save(MonitorDTO dto) {
		return monitorRedisRepository.save(dto);
	}


	@Override
	public MonitorDTO findById(String faxDevicePortId) {
		return monitorRedisRepository.findById(faxDevicePortId).get();
	}
	
	
	
	

}
