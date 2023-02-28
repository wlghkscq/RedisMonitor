package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.huation.huefax.RedisMonitorApplication;
import com.huation.huefax.monitor.MonitorDTO;
import com.huation.huefax.redis.RefreshToken;
import com.huation.huefax.service.AdminUserRedisService;
import com.huation.huefax.serviceimpl.MonitorService;

@SpringBootTest
@ContextConfiguration(classes = RedisMonitorApplication.class)
class RedisMonitorApplicationTests {

	@Autowired
    private AdminUserRedisService adminUserRedisService;
	
	@Autowired
	private MonitorService service;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @DisplayName("token redis 저장 success")
    @Test
    void tokenSave(){
        //given
        RefreshToken token = RefreshToken.builder()
                .userId("test")
                .refreshToken("test_token")
                .expiredTime(60)    //테스트용 1분
                .build();
        //when
        RefreshToken refreshToken = adminUserRedisService.save(token);
        //then
        RefreshToken findToken = adminUserRedisService.findById(token.getUserId());
        System.out.println(refreshToken.getRefreshToken());
        System.out.println(findToken.getRefreshToken());
        assertEquals(refreshToken.getRefreshToken(), findToken.getRefreshToken());
    }
    
    
    @DisplayName("redis template 사용")
    @Test
    @Transactional
    void tokenSaveRedisTemplate(){
        //given
        String userId = "tester1";

        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .refreshToken("test_token")
                .expiredTime(60)    //테스트용 1분
                .build();

        //when
        String key = "refreshToken:"+userId;
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, "userId", userId);
        hash.put(key, "refreshToken", token.getRefreshToken());

        //then
        Object o = redisTemplate.opsForHash().get(key, "userId");
        System.out.println(o);
    }
    
    
    
    @DisplayName("monitor redis 저장 ")
    @Test
    @Transactional
    void monitorRedisTest() {
    	String DevID = null;
    	String PortNo = null;
    	String keys = null;
    	MonitorDTO dto = new MonitorDTO();
    	Object o = null;
    	 
    	
    	List<MonitorDTO> list = service.selectFaxdeviceMonitor(dto);
    	List<MonitorDTO> Sendinglist = new ArrayList<>();
    	
    	HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
    	
    	for(int i =0; i < list.size(); i++) {
    		
    		if(list.get(i).getPortStatus().equals("1") ) { // 1 = 발신 중 
    			
    			Sendinglist.add(list.get(i));
    			
    		}
    	}
    	
    	
    	for(int i = 0; i < Sendinglist.size();i ++) {
    		DevID =  Integer.toString(Sendinglist.get(i).getDeviceId());
    		PortNo =  Integer.toString(Sendinglist.get(i).getPortNo());
    		
    		Sendinglist.get(i).setFaxDevicePortId(DevID + PortNo);
    		
    		keys = Sendinglist.get(i).getFaxDevicePortId();
    		
    		hash.put(keys, "faxDevicePortId", Sendinglist.get(i).getFaxDevicePortId());
    		hash.put(keys, "ANI", Sendinglist.get(i).getANI());
    		hash.put(keys, "speed", Sendinglist.get(i).getSpeed());
    		hash.put(keys, "page", Sendinglist.get(i).getPage());
    		
    		System.out.println("======================================================");
        	System.out.println("faxDevicePortId = "+hash.get(keys, "faxDevicePortId"));
        	System.out.println("faxDevicePortId = "+hash.get(keys, "ANI"));
        	System.out.println("faxDevicePortId = "+hash.get(keys, "speed"));
        	System.out.println("faxDevicePortId = "+hash.get(keys, "page"));
        	System.out.println("======================================================");
        	
        	// status 가 0 이 되면 발송완료가 되면 메모리 초기화 
        	if(Sendinglist.get(i).getPortStatus().equals("0")){ // 0 = 발신 완료 
    			redisTemplate.opsForHash().delete(keys); // 즉시 키 삭제 
    		}
    		
    	}
    	
    	
    	//redisTemplate.expire(keys, 60, TimeUnit.SECONDS); // 60초후 redis 메모리 초기화 
    	
    	
    	System.out.println("리스트 사이즈 = "+ Sendinglist.size() );
		System.out.println("요청건 = "+ Sendinglist.toString());
		
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
