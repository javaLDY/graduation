$(document).ready(function(){
	$("#system").click(function(){
		$("#content").load("/graduation/userList");
	});
	$("#addNewUsers").click(function(){
		$("#content").load("/graduation/user");
	});
	$("#save").click(function(){
		var name = $("#insertName").val();
		var pass = $("#insertPass").val();
		var role = $(".insertRole").val();
		var phone = $("#insertPhone").val();
		var email = $("#insertEmail").val();
		$.ajax({
			url : "./insertUserInfo",
			type : "post",
			data : {userName:name,passWord:pass,roleId:role,phone:phone,email:email},
			success : function(data){
				if(data.success){
					alert(data.message);
					$("#content").load("/graduation/user");
				}else{
					alert(data.message);
				}
			}
		});	
	});
});
function profile(id){
	$("#content").load("/graduation/user#profile");
}