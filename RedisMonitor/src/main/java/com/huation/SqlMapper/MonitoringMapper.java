package com.huation.SqlMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.huation.huefax.monitor.MonitorDTO;

@Mapper
public interface MonitoringMapper {
	

	public List<MonitorDTO> selectFaxdeviceMonitor(MonitorDTO dto);
}
