$(document).ready(function(){
	$("#login").click(function(){
		var name = $("#name").val();
		var pass = $("#pass").val();
		if(name == ""){
			$("#nameError").text("姓名不能为空");
		}else{
			$("#nameError").text("");
		}
		if(pass == ""){
			$("#passError").text("密码不能为空");
		}else{
			$("#passError").text("");
		}
		if((name != "")&&(pass != "")){
			$.ajax({
				url : "./getUserByNameOrPass",
				type : "post",
				data : {userName:name,passWord:pass},
				success : function(data){
					var message = data.message;
					if(data.success){
						window.location.href="./show";
					}else{
						alert(message);
					}
				}
			});
		}
	});
});