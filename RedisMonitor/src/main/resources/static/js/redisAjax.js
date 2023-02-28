/**
 * 
 */

 function RedisAjax(){
	 
	 $.ajax({
		type:"GET",
		url:"/redis",
		processData: false,
		contentType: false,
		cache: false,
		
		success: function (data) {
			
			console.log("SUCCESS : ", data);
			//alert("통신 성공!")
			
			
			
		},
		error: function (request,status,error) {
			//alert("통신 실패!")
			console.log(request);
			console.log(status);
			console.log(error);
		},
		complete:function(){ // 통신 완료 후 
			
			$('#container').load(location.href+' #container'); // 전체 페이지 새로고침없이 해당 태그부분만 리로드 
			
			
		}
		
	});
	 
	 
	 
 }