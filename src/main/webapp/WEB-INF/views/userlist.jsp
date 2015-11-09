  <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <script src="<%=request.getContextPath() %>/static/lib/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.colorbox.js"></script>
 <script src="<%=request.getContextPath() %>/static/js/user/user.js" type="text/javascript"></script>
 <script type="text/javascript">var _path = '${ctx}'</script>
 <script type="text/javascript">
 	$(document).ready(function(){
 		$.ajax({
 			url : "/graduation/getAllOfUserInfo",
 			type : "post",
 			success : function(data){
 				var newData = "";
 				for(var i = 0; i<data.length; i++){
 					newData +=
			    		  "<tr>"+
			    		  "<td style='display:none'>"+data[i].id+"</td>"+
			    		  "<td>"+data[i].username+"</td>"+
			    		  "<td>"+data[i].roleName+"</td>"+
			    		  "<td>"+data[i].phone+"</td>"+
			    		  "<td>"+data[i].email+"</td>"+
			    		  "<td>"+
			    		  "<i class='fa fa-pencil' id='profile"+i+"' onclick='profile("+data[i].id+")'></i>"+
			    		  "<a href='#myModal' role='button' data-toggle='modal'><i class='fa fa-trash-o'></i></a>"+
			    		  "</td>"+
			    		  "</tr>"
 				}
 				document.getElementById("userList").innerHTML = newData;
 			}
 		});
 	});
 
 </script>
 <div class="header">
            
            <h1 class="page-title" >用户信息</h1>
                    <ul class="breadcrumb">
            <li><a href="index.html">首页</a> </li>
            <li class="active">系统管理</li>
        </ul>

        </div>
        <div class="main-content">
            
<div class="btn-toolbar list-toolbar">
    <button class="btn btn-primary" id="addNewUsers"><i class="fa fa-plus"></i> 新添用户</button>
  <div class="btn-group">
  </div>
</div>
<table class="table">
  <thead>
    <tr>
      <th>姓名</th>
      <th>角色</th>
      <th>电话</th>
      <th>邮箱</th>
      <th>操作</th>
      <th style="width: 3.5em;"></th>
    </tr>
  </thead>
  <tbody id="userList">
   
  </tbody>
</table>

<ul class="pagination">
  <li><a href="#">&laquo;</a></li>
  <li><a href="#">1</a></li>
  <li><a href="#">2</a></li>
  <li><a href="#">3</a></li>
  <li><a href="#">4</a></li>
  <li><a href="#">5</a></li>
  <li><a href="#">&raquo;</a></li>
</ul>

<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Delete Confirmation</h3>
        </div>
        <div class="modal-body">
            <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?<br>This cannot be undone.</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
            <button class="btn btn-danger" data-dismiss="modal">Delete</button>
        </div>
      </div>
    </div>
</div>


            <footer>
                <hr>

                <!-- Purchase a site license to remove this link from the footer: http://www.portnine.com/bootstrap-themes -->
                <p class="pull-right">A <a href="http://www.portnine.com/bootstrap-themes" target="_blank">Free Bootstrap Theme</a> by <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
                <p>© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
            </footer>
        </div>