<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>10000知道</title>
<link href="${request.contextPath}/css/lyhcss/robot.css" rel="stylesheet" type="text/css" />
<script src="${request.contextPath}/js/lyhjs/index/jquery-1.11.0.min.js"></script>
</head>
<body>
	<div class="robot Js-content-info">
		<div class="robot-title">
			智能客服
		</div>
		<div class="robot-main fn-clear">
			<div class="chat-wrap">
				<div id="list_div" class="chat-box Js-list-warp">
					<div class="chat-box-clear fn-clear">
						<div class="robot-chat">
							<div class="decorate"></div>
							<div class="date-wrap one-row Js-time-info"></div>
							你好，有什么可以帮助您？
						</div>
					</div>
					<!--
					<div class="chat-box-clear fn-clear">
						<div class="user-chat">
							<div class="decorate"></div>
							<div class="date-wrap one-row">2013-11-5  10:31</div>
							你好
						</div>
					</div>
					
					<div class="chat-box-clear fn-clear">
						<div class="robot-chat">
							<div class="decorate"></div>
							<div class="date-wrap one-row">10:31</div>
							你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好
						</div>
					</div>
					<div class="chat-box-clear fn-clear">
						<div class="user-chat">
							<div class="decorate"></div>
							<div class="date-wrap one-row">10:31</div>
							你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好你好
						</div>
					</div>
-->
				
				</div>
				<div class="chat-input-wrap fn-clear">
					<div class="input-wrap fn-clear">
						<div class="input-box">
							<input type="text" x-webkit-speech class="Js-message-info">
						</div>
						<a href="javascript:void(0)" class="send"></a>
					</div>
				</div>
			</div>
			<div class="chat-img">
			    <!--动态加载-->
                <div class="loading" id="load">
                    <img src="http://sysimages.tq.cn/images/analysis_images/ajax-loader.gif" />
                </div>
                <!--动态加载-->
				<!-- 插入 -->
                <div>
                    <iframe style="visibility:hidden" onreadystatechange="stateChangeIE(this)" onload="stateChangeFirefox(this)" class="Js-detail-view-ifrm" width="798" height="455" src="" frameborder="no" marginwidth="0" marginheight="0" allowtransparency="yes" scrolling="auto"></iframe>
                </div>
                <!-- 插入 -->
			</div>
		</div>
	</div>
	<input type="hidden" class="Js-token" value="${token }" />
</body>
</html>
<script>
	$(function(){
		var d = new Date();
		var time = d.getFullYear()+"-"+(parseInt(d.getMonth())+1)+"-"+d.getDate()+"　"+d.getHours()+":"+d.getMinutes();
		$(".Js-time-info").html(time);
		//用户发送信息
		var user_send = function(){
			var message = $.trim($(".Js-message-info").val());
			var $content_user = '<div class="chat-box-clear fn-clear">' +
								'<div class="user-chat">' +
								'<div class="decorate"></div>' +
								'<div class="date-wrap one-row">' + time + '</div>' + message + '</div></div>';
			$(".Js-list-warp").append($content_user);
			//使滚动条滚动到最下面
			document.getElementById("list_div").scrollTop = document.getElementById("list_div").scrollHeight;
		};
		//机器人回复
		var robot_send = function(){
			var message = $.trim($(".Js-message-info").val());
			$.ajax({
			   type: "POST",
			   url: "${request.contextPath}/webchat!searchData.do",
			   data: "q=" + message + "&pageSize=5&token=" + $(".Js-token").val(),
			   success: function(data){
				    if(data.success && (data.list != null || data.list_yj != null)){
						var $content_robot = '<div class="chat-box-clear fn-clear">';
						    $content_robot += '<div class="robot-chat">';
						    $content_robot += '<div class="decorate"></div>';
							$content_robot += '<div class="date-wrap one-row">' + time + '</div>';
						var defaultOpenUrl = null;
						// yunji
						if (data.list_yj != null) {
							$.each(data.list_yj, function(index,item){
								var href = "";
								switch(item.templateName){
									case "mobilephone":
										href = "/scene/mobilephone!detail.do";
										break;
									case "bundle":
										href = "/scene/bundle!detail.do";
										break;
									case "business_office":
										href = "/scene/businessoffice!detail.do";
										break;
									case "deals":
										href = "/scene/deals!detail.do";
										break;
									case "Nation":
										href = "/scene/internationalservice!detail.do";
										break;
									default:
										href = "/search!detail.do";
										break;
								}
								if (index == 0){
									defaultOpenUrl = '${request.contextPath}'+href+'?id='+data.list_yj[index].id+'&printable=true';
								}
								$content_robot += '<p><a class="Js-yunji-klg" data-query="' + data.list_yj[index].query + '" data-cid="' + data.list_yj[index].cid + '" href="${request.contextPath}'+href+'?id='+data.list_yj[index].id+'&printable=true"><u>' +
								data.list_yj[index].title + '</u></a><br/>(' + (data.list_yj[index].score != null ? data.list_yj[index].score.substring(0,4) : "无") + ')-来自云极</p>';
							});
						}
						// yse
						if (data.list != null){
							$.each(data.list, function(index,item){
								var href = "";
								switch(item.templateName){
									case "mobilephone":
										href = "/scene/mobilephone!detail.do";
										break;
									case "bundle":
										href = "/scene/bundle!detail.do";
										break;
									case "business_office":
										href = "/scene/businessoffice!detail.do";
										break;
									case "deals":
										href = "/scene/deals!detail.do";
										break;
									case "Nation":
										href = "/scene/internationalservice!detail.do";
										break;
									default:
										href = "/search!detail.do";
										break;
								}
								if (index == 0 && defaultOpenUrl == null){
									defaultOpenUrl = '${request.contextPath}'+href+'?id='+data.list[index].id+'&printable=true';
								}
								$content_robot += '<p' + (data.uniqueHit && index != 0 ? ' style="display:none"' : '') + '><a href="${request.contextPath}'+href+'?id='+data.list[index].id+'&printable=true"><u>' +
								//data.list[index].title + '</u></a><br/>(' + (data.list[index].score != null ? data.list[index].score : "无") + ')</p>';
								data.list[index].title + '</u></a>' + ( (data.uniqueHit && index == 0) ? '<span class="Js-look-more" style="margin-left:5px;cursor:pointer;color:purple;font-size:10px">查看其他相关答案</span>' : '') + '</p>';
							});
						}
						$content_robot += '</div></div>';
					   	$(".Js-list-warp").append($content_robot);
						//使滚动条滚动到最下面
						document.getElementById("list_div").scrollTop = document.getElementById("list_div").scrollHeight;
						if (defaultOpenUrl != null){
							$(".Js-detail-view-ifrm").attr("src", defaultOpenUrl);
							$("#load").show();
						}
				    } else {
						var $content_robot = '<div class="chat-box-clear fn-clear">';
					    $content_robot += '<div class="robot-chat">';
					    $content_robot += '<div class="decorate"></div>';
						$content_robot += '<div class="date-wrap one-row">' + time + '</div>';
						//$content_robot += '<p>亲，你在说神马，好腻害的样子。。。</p>';
						$content_robot += '<p>亲，你问的问题太高深了，我学习下再回复你。。。</p>';
						$content_robot += '</div></div>';
						$(".Js-list-warp").append($content_robot);
						//使滚动条滚动到最下面
						document.getElementById("list_div").scrollTop = document.getElementById("list_div").scrollHeight;
				    }
				   $(".Js-message-info").val("");
			   }
			});
		};
		//点击发送按钮
		$(document).on("click", ".send", function(){
			var message = $.trim($(".Js-message-info").val());
			if(message.length == 0){
				alert("请输入要发送的信息");
			}else{
				user_send();
				robot_send();
			}
		});
		//输入信息后点击回车
		$(document).on("keydown", ".Js-message-info", function(event){
			var message = $.trim($(".Js-message-info").val());
			if(event.keyCode == 13){
				if(message.length == 0){
					alert("请输入要发送的信息");
				}else{
					user_send();
					robot_send();
				}
			}
		});
	   	$(".Js-list-warp").on("click", "a", function(){
	   		$(".Js-detail-view-ifrm").attr("src", $(this).attr("href"));
	   		$("#load").show();
	   		var $this = $(this);
	   		if ($this.hasClass("Js-yunji-klg") && $this.data("cid") != null
	   				&& $this.data("query") != null)
	   		{
				var reqData = "keyword=" + $this.data("query") + "&cid=" + $this.data("cid");
				$.post("/webchat!feedbackYJ.do", reqData, function(data) {});
	   		}
	   		
	   		return false;
	   	});
		//查看其他相关答案
		$(document).on("click", ".Js-look-more", function(){
			$(this).parents("div.robot-chat").find("p").show();
			$(this).hide();
		});
	   	
	   	setInterval(function(){
				var token = $.trim($(".Js-token").val());
				$.ajax({
				   type: "POST",
				   url: "${request.contextPath}/webchat!scanReply.do",
				   data: "token=" + token,
				   success: function(data){
					    if (data.success && data.list != null) {
					    	//var time = data.time;
					    	var $content_robot = "";
							$.each(data.list, function(index,item){
								$content_robot = '<div class="chat-box-clear fn-clear">';
							    $content_robot += '<div class="robot-chat">';
							    $content_robot += '<div class="decorate"></div>';
								$content_robot += '<div class="date-wrap one-row">' + time + '</div>';
								//$content_robot += '<p>亲，你在说神马，好腻害的样子。。。</p>';
								$content_robot += '<p style="color:purple">你的问题“' + item.ask + '”已有了回复<br/>[' + item.answer + ']</p>';
								$content_robot += '</div></div>';
							});
							$(".Js-list-warp").append($content_robot);
							//使滚动条滚动到最下面
							document.getElementById("list_div").scrollTop = document.getElementById("list_div").scrollHeight;  
					    }
				   }
				});
			}, 2000);
	});
	
	
    function stateChangeIE(_frame)
    { 
     if (_frame.readyState=="interactive")//state: loading ,interactive,   complete
     {
     var loader = document.getElementById("load"); 
        loader.innerHTML      = "";    
        loader.style.display = "none";  
        _frame.style.visibility = "visible";
     }   
    }
    function stateChangeFirefox(_frame)
    { 
     var loader = document.getElementById("load"); 
        loader.innerHTML      = "";    
        loader.style.display = "none";  
        _frame.style.visibility = "visible"; 
    }
</script>