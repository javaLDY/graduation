<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script src="<%=request.getContextPath() %>/static/js/user/user.js" type="text/javascript"></script>
<meta charset="utf-8">
     <div class="header">
            
            <h1 class="page-title">系统管理</h1>
                    <ul class="breadcrumb">
            <li><a href="index.html">首页</a> </li>
            <li><a href="users.html">系统管理</a> </li>
        </ul>
   <div class="main-content">
            
<ul class="nav nav-tabs">
  <li class="active"><a href="#home" data-toggle="tab">用户操作</a></li>
</ul>

<div class="row">
  <div class="col-md-4">
    <br>
    <div id="myTabContent" class="tab-content">
      <div class="tab-pane active in" id="home">
      <form id="tab">
        <div class="form-group">
        <label>姓名</label>
        <input type="text" id="insertName" class="form-control">
        </div>
        <div class="form-group">
        <label>密码</label>
        <input type="text" id="insertPass" class="form-control">
        </div>
        <div class="form-group">
        <label>角色</label></br>
        <input type="radio" name="role" class="insertRole"  value="1"><label>管理员</label>
        <input type="radio" name="role" class="insertRole" value="2"><label>普通用户</label>
        </div>
        <div class="form-group">
        <label>电话</label>
        <input type="text" id="insertPhone" class="form-control">
        </div>
        <div class="form-group">
        <label>Email</label>
        <input type="text" id="insertEmail" class="form-control">
        </div>
     
    <div class="btn-toolbar list-toolbar">
    <input class="btn btn-primary" type="button" id="save" value="保存"/></i>
    </div>
        </form>
      </div>
    </div>
  </div>
</div>

<div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Delete Confirmation</h3>
      </div>
      <div class="modal-body">
        
        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?</p>
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