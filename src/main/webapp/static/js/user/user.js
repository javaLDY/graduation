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
	$.ajax({
		url : "./getUserInfoById",
		type : "post",
		data : {id:id},
		success : function(data){
			$.colorbox({
		        html :
		            "<table style='border-collapse:separate; border-spacing:10px;width: 453px;height: 354px' id='table1'>"+
		            "<tbody>"+
		            "<tr>"+
		            "<td style='background: #808080;width: 0;height: 46px'>用户修改</td>"+
		            "</tr>"+
		            "<tr>"+
		            "<td style='margin: 0'>"+
		            "<form id='formid' action='/shinowit/update' method='post'>"+
		            "<input  type='text' value="+data.id+" name='id' style='visibility: hidden'/>"+
		            "<ul style='border-collapse:separate; border-spacing:10px;margin: 10px 92px 30px 92px' id='newul'>"+
		            "<li style='margin-left: 15px'>"+
		            "<label >姓名:</label><input name='recman' value="+data.name+" type='text'/>"+
		            "</li></br>"+
		            "<li>"+
		            "<label >角色:</label><input name='recaddress' value="+data.roleId+" type='text'/>"+
		            "</li></br>"+
		            "<li>"+
		            "<label >电话:</label><input name='recaddress' value="+data.phone+" type='text'/>"+
		            "</li></br>"+
		            "<li>"+
		            "<label >邮政编码:</label><input name='postcode' value="+data.email+" type='text'/>"+
		            "</li></br>"+
		            "<li>"+
		            "<input type='submit' style='margin-left: 105px;width: 95px;background: red'/>"+
		            "</li>"+
		            "</ul>"+
		            "</form>"+
		            "</td>"+
		            "</tr>"+
		            "</tbody>"+
		            "</table>"
		    });
		}
	});
}
