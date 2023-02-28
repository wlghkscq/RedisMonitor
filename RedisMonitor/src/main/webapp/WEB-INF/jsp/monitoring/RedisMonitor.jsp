<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html>   
<html>   
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/redisAjax.js"></script>
<script type="text/javascript" src="../js/progressbar.js"></script>
<title>Insert title here</title> 
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/content.css">
<link rel="stylesheet" href="../css/progressbar.css">
<script type="text/javascript">
$(document).ready(function() {
	setInterval( 
			function(){
						//location.reload(); 
						//RedisAjax();
						
						}
			,10000);
});	

</script>
</head>  
<body> 
	<div>레디스 모티터링 화면 </div>  
	
	
	
	
	<div id="wrapper">
		<header id="header">
			<ul class="tab-menu-top">
			</ul>
		</header>
		
		<div id="container">
		<!-- 모니터 : 서버 ON 클래스 추가(class="on") -->
		<div class="box">
			<h2>서울<strong></strong></h2>
			<ol class="cont">
			
			<c:forEach items="${moList}" var="item" end="29">
				<c:if test="${item.portStatus eq '1'}">
				 	<li class="on"><span title="${item.ANI}"><strong>${item.portNo}</strong>PAGE : ${item.page} Speed : ${item.speed}</span></li>
				</c:if>
				<c:if test="${item.portStatus eq '0' }">
					<li><span><strong>${item.portNo}</strong></span></li>
				</c:if>
			</c:forEach>

			</ol>
			<ol start="31" class="cont">
			<c:forEach items="${moList}" var="item" begin="30" end="59">
					<c:if test="${item.portStatus eq '1'}">
				 	<li class="on"><span title="${item.ANI}"><strong>${item.portNo}</strong>PAGE : ${item.page} Speed : ${item.speed}</span></li>
				</c:if>
				<c:if test="${item.portStatus eq '0' }">
				 	<li><span><strong>${item.portNo}</strong></span></li>
				</c:if>
			</c:forEach>

			</ol>
		</div>
		
		<div class="box">
			<h2>부산<strong></strong></h2>
			<ol class="cont">
			
			<c:forEach items="${moList}" var="item" begin="60" end="89">
					<c:if test="${item.portStatus eq '1'}">
				 	<li class="on"><span title="${item.ANI}"><strong>${item.portNo}</strong>PAGE : ${item.page} Speed : ${item.speed}</span></li>
				</c:if>
				<c:if test="${item.portStatus eq '0' }">
				 	<li><span><strong>${item.portNo}</strong></span></li>
				</c:if>
			</c:forEach>

			</ol>
			
			<ol start="31" class="cont">
			<c:forEach items="${moList}" var="item" begin="90">
					<c:if test="${item.portStatus eq '1'}">
				 	<li class="on"><span title="${item.ANI}"><strong>${item.portNo}</strong>PAGE : ${item.page} Speed : ${item.speed}</span></li>
				</c:if>
				<c:if test="${item.portStatus eq '0' }">
				 	<li><span><strong>${item.portNo}</strong></span></li>
				</c:if>
			</c:forEach>
			</ol>
			
			
		
		</div>
		<!-- //모니터 -->
		</div>
		
	</div>
		
			
		<ol class="progress progress-striped">
			  <li id="dynamic" class="on progress-bar progress-bar-danger" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%">
			    <span id="current-progress"></span>
			  </li>
			</ol>
		
		
		
		
	
</body>   

</html>