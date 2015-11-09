<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<title>智能服务机器人</title>

	<style type="text/css">
	.frm-boxs {
	  position:relative;
	}
	.frm-boxs .loading {
	    width:777px;
	    height:510px;
	    position:absolute;
	    z-index:9;
	    text-align:center;
	    display:none;
	}
	.frm-boxs .loading img {
	    position:absolute;
	    top:50%;
	    margin-top:-8px;
	}
	body{
		height: 100%;
		overflow: hidden;
	}
	
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;}
		#golist {display: none;}
		@media (max-device-width: 800px){#golist{display: block!important;}}
	</style>
	<script src="../../../static/js/lib/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div>这是一个测试页面</div>
	<iframe src="http://localhost:8088/webchat/test?color=f737f2" style="border: 0px;height: 600px; width: 700px;position: fixed;right: 0px;bottom: 0px;"></iframe>
	
	
	<!-- 聊天提问模板 -->        
	<script id="TP-webchat-ask-template" type="text/x-handlebars-template">
	<li>
		<div class="right">
			<div class="bgs">
                {{{message}}}
				<span class="arry"></span>
			</div>
		</div>
		<div class="fn-clear"></div>
	</li>
	</script>
	<!-- 聊天回复（text）模板 -->        
	<script id="TP-webchat-reply-text-template" type="text/x-handlebars-template">
	<li>
		<div class="left">
			<div class="bgs">
                {{{message}}}
				<span class="arry"></span>
 			</div>
		</div>
        <div class="fn-clear"></div>
	</li>
	</script>
	<!-- 聊天回复（html）模板 -->        
	<script id="TP-webchat-reply-html-template" type="text/x-handlebars-template">
	<li>
		<div class="links">
			<div class="bgs">
				{{{message}}}
				<span class="arry"></span>
			</div>
		</div>
		<div class="fn-clear"></div>
	</li>
	</script>
	<script>
		$(function(){
			//发送信息
			var userAsk = function() {
				var message = $.trim($(".JS-msg-input").val());
	            var t = Handlebars.compile($("#TP-webchat-ask-template").html());
				$(".JS-wc-list").append(t({"message" : message}));
				scrollToFoot();
			};
			//回复信息
			var robotReply = function() {
				var message = $.trim($(".JS-msg-input").val());
				$(".JS-msg-input").val("");
				$.ajax({
				   type: "POST",
				   url: "${request.contextPath}/webchat/searchData",
				   data: "q=" + message + "&pageSize=5&token=" + $(".JS-token").val(),
				   success: function(data) {
					    if (data.success && data.list != null) {
							var replyContent = "";
							var defaultOpenUrl = null;
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
								
								if (index == 0)
									defaultOpenUrl = '${request.contextPath}'+href+'?id='+data.list[index].id+'&printable=true';
									
								replyContent += '<p><a href="'+href+'?id='+data.list[index].id+'&printable=true">' +
												data.list[index].title + '</a></p>';
							});
							var t = Handlebars.compile($("#TP-webchat-reply-html-template").html());
						   	$(".JS-wc-list").append(t({"message" : replyContent}));
							if (defaultOpenUrl != null) {
								$(".JS-wc-detail-view-ifrm").attr("src", defaultOpenUrl);
								$("#JS-wc-detail-view-loading").show();
							}
						   	scrollToFoot();
					    } else {
							var t = Handlebars.compile($("#TP-webchat-reply-text-template").html());
						   	$(".JS-wc-list").append(t({"message" : "亲，你问的问题太高深了，我学习下再回复你。。。"}));
						   	scrollToFoot();
					    }
				   }
				});
			};
			var scrollToFoot = function() {
				//使滚动条滚动到最下面
				document.getElementById("JS-wc-list-panel").scrollTop = document.getElementById("JS-wc-list-panel").scrollHeight;
			}
			//提交问题
			$(document).on("click", ".JS-msg-send-btn", function(){
				var message = $.trim($(".JS-msg-input").val());
				if (message.length == 0) {
					alert("请输入要发送的信息");
				} else {
					userAsk();
					robotReply();
				}
			});
			//输入信息后点击回车
			/*
			$(document).on("keydown", ".JS-msg-input", function(event){
				var message = $.trim($(".JS-msg-input").val());
				if(event.keyCode == 13){
					if(message.length == 0){
						alert("请输入要发送的信息");
					}else{
						userAsk();
						robotReply();
					}
				}
			});
			*/
			//回复link点击事件bind
		   	$(".JS-wc-list").on("click", "a", function(){
		   		$(".JS-wc-detail-view-ifrm").attr("src", $(this).attr("href"));
		   		$("#JS-wc-detail-view-loading").show();
		   		
		   		var $this = $(this);
		   		if ($this.hasClass("Js-yunji-klg") && $this.data("cid") != null
		   				&& $this.data("query") != null)
		   		{
					var reqData = "keyword=" + $this.data("query") + "&cid=" + $this.data("cid");
					$.post("/webchat/feedbackYJ", reqData, function(data) {});
		   		}
		   		
		   		return false;
		   	});
			//定时扫描人工座席回复
		   	setInterval(function(){
				var token = $.trim($(".JS-token").val());
				$.ajax({
				   type: "POST",
				   url: "${request.contextPath}/webchat/scanReply",
				   data: "token=" + token,
				   success: function(data) {
					    if (data.success && data.list != null) {
					    	var allscanReplys = "";
							$.each(data.list, function(index,item){
								var t = Handlebars.compile($("#TP-webchat-reply-text-template").html());
								allscanReplys += t({"message" : "你的问题“" + item.ask + "”已有了回复<br/>[" + item.answer + "]"});
							});
							$(".JS-wc-list").append(allscanReplys);
							scrollToFoot();
					    }
				   }
				});
			}, 2000);
		});
		//iframe加载相关事件捆绑
	    function stateChangeIE(_frame) { 
	    	if (_frame.readyState=="interactive") {
		    	var loader = document.getElementById("JS-wc-detail-view-loading"); 
		        loader.innerHTML      = "";    
		        loader.style.display = "none";  
		        _frame.style.visibility = "visible";
	     	}   
	    }
	    function stateChangeFirefox(_frame) { 
	    	var loader = document.getElementById("JS-wc-detail-view-loading"); 
	        loader.innerHTML      = "";    
	        loader.style.display = "none";  
	        _frame.style.visibility = "visible"; 
	    }
	</script>
</body>
</html>