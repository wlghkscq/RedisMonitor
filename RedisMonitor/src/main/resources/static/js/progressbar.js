$(document).ready(function() {
  
  
  var faxDevicePortId = document.querySelectorAll(".faxDevicePortId");
  
  var current = document.querySelectorAll("#dynamic");
  
  for(var i=1; i <= current.length+1; i++){
	  
	console.log(document.getElementById("currentPage" + i).innerHTML);
	  
  }
  
  
  
  	
  var current_progress = 0;
  var current_progress2 = 0;
  
  var interval = setInterval(function() {
	  $("#03").css("background-color", "red")
      current_progress += 18.9;
      current_progress2 += 10
      $("#current-progress")
      .css("display", "inline-block")
      .css("width", current_progress + "px")
      .css("height", "15px")
      .css("border", "1px solid #7c7c7c;")
      .css("background-color", "#1ab394")
      .css("font-size", "10px")
      .css("text-align", "center")
      .attr("aria-valuenow", current_progress)
      .text(current_progress2 + "%");
      if (current_progress >= 189)
          clearInterval(interval);
  }, 1000);
    
});