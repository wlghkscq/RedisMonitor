package com.huation.huefax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.huation.huefax.monitor.MonitorDTO;
import com.huation.huefax.serviceimpl.MonitorService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class RedisController {
	
	@Autowired
	private MonitorService service;
	
	
	@Autowired 
    private RedisTemplate<String, String> redisTemplate;
 
	@RequestMapping(value="/redisTest", method = RequestMethod.POST)
    public ResponseEntity<?> addRedisKey() {
    	System.out.println(" addRedisKey 호출 성공 ");
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set("yellow", "banana");
        vop.set("red", "apple");
        vop.set("green", "watermelon");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
 
    @RequestMapping(value="/redisTest/{key}", method = RequestMethod.GET)
    public ResponseEntity<?> getRedisKey(@PathVariable String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String value = vop.get(key);
        return new ResponseEntity<>(value, HttpStatus.OK);
    }
    
    @GetMapping("/progress")
    public ModelAndView viewProgress(HttpServletRequest request, MonitorDTO dto) {
    	
    	ModelAndView mv = new ModelAndView();
    	
    	String DevID = null;
    	String PortNo = null;
    	String keys = null;
    	String page[] = null;
    	String currentPage = null; 
    	String totalPage = null;
    	Object o = null;
    	 
    	
    	List<MonitorDTO> list = service.selectFaxdeviceMonitor(dto);
    	List<MonitorDTO> Sendinglist = new ArrayList<>();
    	List<MonitorDTO> EmptyList = new ArrayList<>();
    	
    	HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
    
    	
    	for(int i = 0; i < list.size();i ++) {
    		if(list.get(i).getPortStatus().equals("1") ) { // 1 = 발신 중 
    			
	    		DevID =  Integer.toString(list.get(i).getDeviceId());
	    		PortNo =  Integer.toString(list.get(i).getPortNo());
	    		
	    		list.get(i).setFaxDevicePortId(DevID + PortNo);
	    		
	    		keys = list.get(i).getFaxDevicePortId();
	    		
	    		hash.put(keys, "faxDevicePortId", list.get(i).getFaxDevicePortId());
	    		hash.put(keys, "ANI", list.get(i).getANI());
	    		hash.put(keys, "speed", list.get(i).getSpeed());
	    		hash.put(keys, "page", list.get(i).getPage());
	    		hash.put(keys, "portNo", Integer.toString(list.get(i).getPortNo()));
	    		
	    		System.out.println("======================Redis ( key = value ) ================================");
	        	System.out.println("faxDevicePortId = "+hash.get(keys, "faxDevicePortId"));
	        	System.out.println("ANI = "+hash.get(keys, "ANI"));
	        	System.out.println("speed = "+hash.get(keys, "speed"));
	        	System.out.println("page = "+hash.get(keys, "page"));
	        	System.out.println("portNo = "+hash.get(keys, "portNo"));
	        	System.out.println("============================================================================");
	        	
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "faxDevicePortId"));
	        	list.get(i).setANI((String) hash.get(keys, "ANI"));
	        	list.get(i).setSpeed((String) hash.get(keys, "speed"));
	        	list.get(i).setPage( (String) hash.get(keys, "page"));
	        	
	        	page = list.get(i).getPage().split("/");
	        	
	        	for(int j=0; j < page.length; j++) {
	        		currentPage = page[j];
	        		totalPage = page[page.length-1];
	        		
	        		
	        		list.get(i).setCurrentPage(currentPage);
	        		list.get(i).setTotalPage(totalPage);
	        		
	        		
	        		
	        	}
	        	
	        	
	        	list.get(i).setPortNo( Integer.parseInt((String) hash.get(keys, "portNo")));
	        	
	        	System.out.println("faxDevicePortId = "+list.get(i).getFaxDevicePortId());
	        	System.out.println("팩스번호 = "+list.get(i).getANI());
	        	System.out.println("속도 = "+list.get(i).getSpeed());
	        	System.out.println("페이지 = "+list.get(i).getPage());
	        	System.out.println("현재 페이지 = "+list.get(i).getCurrentPage());
	        	System.out.println("총 페이지 = "+list.get(i).getTotalPage());
	        	System.out.println("포트번호 = "+list.get(i).getPortNo());
	        	
    		}else {
    			list.get(i).setCurrentPage("NULL");
    			list.get(i).setTotalPage("NULL");
    		}
    		System.out.println(list.get(i).getCurrentPage());
    		
    	}
    	
    	
    	System.out.println("리스트 사이즈 = "+ list.size() );
    	
		mv.addObject("moList", list);
    	
    	mv.setViewName("/monitoring/progressBar");
    	
    	return mv;
    }
    
    
    
    @GetMapping("/redis")
    @ResponseBody
    public ModelAndView viewRedis(HttpServletRequest request, MonitorDTO dto) {
    	
    	ModelAndView mv = new ModelAndView();
    	
    	String DevID = null;
    	String PortNo = null;
    	String keys = null;
    	//MonitorDTO dto = new MonitorDTO();
    	Object o = null;
    	 
    	
    	List<MonitorDTO> list = service.selectFaxdeviceMonitor(dto);
    	List<MonitorDTO> Sendinglist = new ArrayList<>();
    	List<MonitorDTO> EmptyList = new ArrayList<>();
    	
    	HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
    	
    	/*
    	for(int i =0; i < list.size(); i++) {
    		
    		if(list.get(i).getPortStatus().equals("1") ) { // 1 = 발신 중 
    			
    			Sendinglist.add(list.get(i));
    			
    		}
    		
    		
    	}
    	*/
    	
    	for(int i = 0; i < list.size();i ++) {
    		if(list.get(i).getPortStatus().equals("1") ) { // 1 = 발신 중 
    			
	    		DevID =  Integer.toString(list.get(i).getDeviceId());
	    		PortNo =  Integer.toString(list.get(i).getPortNo());
	    		
	    		list.get(i).setFaxDevicePortId(DevID + PortNo);
	    		
	    		keys = list.get(i).getFaxDevicePortId();
	    		
	    		hash.put(keys, "faxDevicePortId", list.get(i).getFaxDevicePortId());
	    		hash.put(keys, "ANI", list.get(i).getANI());
	    		hash.put(keys, "speed", list.get(i).getSpeed());
	    		hash.put(keys, "page", list.get(i).getPage());
	    		hash.put(keys, "portNo", Integer.toString(list.get(i).getPortNo()));
	    		
	    		System.out.println("======================================================");
	        	System.out.println("faxDevicePortId = "+hash.get(keys, "faxDevicePortId"));
	        	System.out.println("ANI = "+hash.get(keys, "ANI"));
	        	System.out.println("speed = "+hash.get(keys, "speed"));
	        	System.out.println("page = "+hash.get(keys, "page"));
	        	System.out.println("portNo = "+hash.get(keys, "portNo"));
	        	System.out.println("======================================================");
	        	
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "faxDevicePortId"));
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "ANI"));
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "speed"));
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "page"));
	        	list.get(i).setFaxDevicePortId((String) hash.get(keys, "portNo"));
    		}
        	// status 가 0 이 되면 발송완료가 되면 메모리 초기화 
        	/*
        	if(list.get(i).getPortStatus().equals("0")){ // 0 = 발신 완료 
    			redisTemplate.opsForHash().delete(keys); // 즉시 키 삭제 
    		}
    		*/
    		
    	}
    	
    	
    	//redisTemplate.expire(keys, 60, TimeUnit.SECONDS); // 60초후 redis 메모리 초기화 
    	
    	
    	System.out.println("리스트 사이즈 = "+ list.size() );
		//System.out.println("요청건 = "+ list.toString());
    	
		//mv.addObject("Sendinglist", list);
		mv.addObject("moList", list);
		mv.setViewName("/monitoring/RedisMonitor");
		
		
    	return mv;
    }
    
    @GetMapping("/redismonitor")
    @ResponseBody
    public ModelAndView viewMonotor(HttpServletRequest request
    								,MonitorDTO dto) {
    	
    	ModelAndView mv = new ModelAndView();
    	
    	List<MonitorDTO> list = service.selectFaxdeviceMonitor(dto);
    	List<MonitorDTO> Sendinglist = new ArrayList<>();
    	
    	HashMap<String, ArrayList<String>> map = new HashMap<>();
    	ArrayList<String> mapList = new ArrayList<String>();
    	 
    	
    	System.out.println("리스트 사이즈 = "+list.size());
    	
    	
    	// portStatus = 1 이면 현재 발송 중 건만 Redis DB에 고유 키값 직접 부여 후, set 해준다 
    	
    	for(int i=0; i < list.size(); i++) {
    		if(list.get(i).getPortStatus().equals("1") ) {
    			
    			
    			Sendinglist.add(list.get(i));
    			System.out.println("리스트 사이즈 = "+ Sendinglist.size() );
    			
    			System.out.println("요청건 = "+ Sendinglist.toString());
    			
    			String DevID =  Integer.toString(list.get(i).getDeviceId());
    			String PortNo =  Integer.toString(list.get(i).getPortNo());
    			
    			String Ani = list.get(i).getANI();
    			String Speed = list.get(i).getSpeed();
    			String Page = list.get(i).getPage();
    			
    			String key = DevID + PortNo;
    			
    			
    			
    			for(int j=0; j < Sendinglist.size(); j++) {
    				
    				
    				
    				for(int h =0; h < Sendinglist.size(); h ++) {
    					
    					mapList.add(Ani);
    					mapList.add(Speed);
    					mapList.add(Page);
    					
    					
    				}
    				
    				map.put(key, mapList);
    				
    			}
    			 
    			
    			 ValueOperations<String, String> vop = redisTemplate.opsForValue();
    			 vop.set(key, Ani);
    			 vop.set(key, Speed);
    			 vop.set(key, Page);
    			 
    			 System.out.println("key = "+key+", value = "+vop.get(key));
    		}
    		
    	}
    	System.out.println("Sendinglist = "+Sendinglist.toString());
		System.out.println("map = "+map);
    	
    	
    			
    	
    	mv.addObject("moList", list);
    	
    	mv.setViewName("/monitoring/RedisMonitor");
    	
    	return mv;
    }
    
    
    
}
