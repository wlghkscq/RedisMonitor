<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/default.css">
<link rel="stylesheet" href="../css/content.css">
<link rel="stylesheet" href="../css/progressbar.css">
<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
 --><script type="text/javascript" src="../js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../js/progressbar.js"></script>
<script type="text/javascript" src="../js/redisAjax.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	

});	
</script>
</head>
<body>
<div id="wrapper">
		<header id="header">
			<ul class="tab-menu-top">
			</ul>
		</header>
		
		<div id="container">
		<!-- 모니터 : 서버 ON 클래스 추가(class="on") -->
		<div class="box" style="height: 845px;">
		
			<h2>서울<strong></strong></h2>
			
			<ol class="server">
			<c:forEach items="${moList}" var="item" end="29" varStatus="status">
			  <c:if test="${item.portStatus eq '1'}">
				  <li class="on" id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span title="${item.ANI}" class="faxDevicePortId" id="${item.faxDevicePortId}">PAGE : ${item.currentPage} / ${item.totalPage} Speed : ${item.speed}</span>
				    <span class="current" id="currentPage${status.count}">${item.currentPage}</span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			  
			  <c:if test="${item.portStatus eq '0' }">
			  	<li id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span title="${item.ANI}" class="faxDevicePortId" id="${item.faxDevicePortId}">PAGE : ${item.currentPage} / ${item.totalPage} Speed : ${item.speed}</span>
				    <span class="current" id="currentPage${status.count}">${item.currentPage}</span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			</c:forEach>
			</ol>
			
			<ol start="31" class="server">
			<c:forEach items="${moList}" var="item" begin="30" end="59">
			  <c:if test="${item.portStatus eq '1'}">
				  <li class="on" id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span title="${item.ANI}" id="${item.faxDevicePortId}">PAGE : ${item.currentPage} / ${item.totalPage} Speed : ${item.speed}</span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			  
			  <c:if test="${item.portStatus eq '0' }">
			  	<li id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span id="${item.faxDevicePortId}"></span>
				    <span class="port-status" id="03"></span>
				 </li>
			  </c:if>
			  </c:forEach>
			</ol>
		</div>
		
		<div class="box" style="height: 845px;">
			<h2>부산<strong></strong></h2>
			
			<ol class="server">
			  <c:forEach items="${moList}" var="item" begin="60" end="89">
			  <c:if test="${item.portStatus eq '1'}">
				  <li class="on" id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span title="${item.ANI}" id="${item.faxDevicePortId}">PAGE : ${item.currentPage} / ${item.totalPage} Speed : ${item.speed}</span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			  
			  <c:if test="${item.portStatus eq '0' }">
			  	<li id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span id="${item.faxDevicePortId}"></span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			</c:forEach>
			</ol>
			
			<ol start="31" class="server">
			<c:forEach items="${moList}" var="item"  begin="90">
			  <c:if test="${item.portStatus eq '1'}">
				  <li class="on" id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span title="${item.ANI}" id="${item.faxDevicePortId}">PAGE : ${item.currentPage} / ${item.totalPage} Speed : ${item.speed}</span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			  
			  <c:if test="${item.portStatus eq '0' }">
			  	<li id="dynamic"  role="progressbar" >
				  	<strong>${item.portNo}</strong>
				    <span id="${item.faxDevicePortId}"></span>
				    <span class="port-status" id="03"></span>
				  </li>
			  </c:if>
			  </c:forEach>
			</ol>
		</div>	
		
		
		<!-- //모니터 -->
	</div>
	
</div>



	
</body>
</html>