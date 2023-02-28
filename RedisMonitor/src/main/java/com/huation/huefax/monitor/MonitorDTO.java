package com.huation.huefax.monitor;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Builder
@RedisHash(value = "faxDevicePortId")
public class MonitorDTO {
	
	@Id
	String faxDevicePortId;
	
	String totalPage;
	String currentPage;
	
	//@TimeToLive
    //long expiredTime;
	
	int deviceId;
	int portNo;
	
	String timeStamp;
	String extentionNo;
	String DNIS;
	String ANI;
	String portType;
	String portStatus;
	String ioStartTimeStamp;
	String deletedind;
	String updateTime;
	String speed;
	String page;
	
	

}
