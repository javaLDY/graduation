<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="s" value="${pageContext.request.contextPath}/static" />
<c:set var="color" value="#${param.color}" />
<c:set var="height" value="${param.height}" />
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>机器人客服</title>
		
		<link rel="stylesheet" href="${s}/icons/iconfont.css" />
		<link rel="stylesheet" href="${s}/css/style.css" />
		<!--<link rel="stylesheet" href="${s}/css/style2.css" />-->
		<link rel="stylesheet" href="${s}/css/jquery.typeahead.css" />
		<link rel="stylesheet" href="${s}/js/mCustomScrollbar/jquery.mCustomScrollbar.css" />
		<link rel="stylesheet" href="${s}/js/layer/skin/layer.css" />
		<style>
			body{overflow:hidden}.mfc{color:${color}}.container{border-left:1px solid ${color};border-right:1px solid ${color};display:table;width:100%}.questions-left-warp{display:table-cell}.info-right-warp{display:table-cell;width:320px;border-left:1px solid ${color};vertical-align:top}.info-right-warp .info-tab{height:30px;border-bottom:1px solid ${color};margin-bottom:5px}.info-right-warp .info-tab ul{margin:5px 0;padding:0}.info-right-warp .info-tab ul li{float:left;width:160px;text-align:center;height:27px;color:#aaa;cursor:pointer}.info-right-warp .info-tab ul li.cur{color:${color};border-bottom:3px solid ${color}}.info-right-warp .info-tab ul div.rd{line-height:24px;border-right:1px solid ${color}}.info-right-warp .info-warp{width:100%;display:none;position:relative}.flex-container-btn{position:absolute;top:40%;width:14px;height:60px;background:${color};color:#fff;text-align:center;line-height:60px}.flex-container-btn .iconfont{font-size:12px}.flex-container-btn div{cursor:pointer;display:none}.flex-container-btn div.cur{display:block}.flex-container-btn i{margin-right:2px}.info-right-warp .info-warp.cur{display:block}.info-right-warp .info-warp ul li{padding:3px 0;border-bottom:1px solid #eee}.info-right-warp .info-warp ul li a{color:#999}.info-right-warp .info-warp .info-title{color:${color};text-align:right;padding-right:20px}.knowledge-title{font-size:16px;text-align:center;margin-bottom:10px}.knowledge-important{color:#666}.knowledge-row-item{margin-top:5px}.knowledge-row-item .item-title{color:#333;font-weight:700;font-size:12px}.chat{margin-top:20px;margin-bottom:20px;padding-left:23px;padding-right:30px;position:relative}.triangle{position:absolute;top:50%;margin-top:-8px;left:15px;display:block;width:0;height:0;overflow:hidden;line-height:0;font-size:0;border-bottom:8px solid #FFF;border-top:8px solid #FFF;border-right:8px solid ${color};border-left:none}.chat.chat-left .triangle{border-bottom:8px solid #FFF;border-top:8px solid #FFF;border-right:8px solid #f0f0f0}.chat .name{color:#858282}.chat .article{float:left;color:#FFF;display:inline-block;zoom:1;padding:5px 5px;border:1px solid ${color};background:#eee;border-radius:5px;background-color:${color};background-image:-webkit-gradient(linear,left top,left bottom,from(${color}),to(${color}));background-image:-webkit-linear-gradient(top,${color},${color});background-image:-moz-linear-gradient(center top,${color},${color});background-image:linear-gradient(top,${color},${color})}.chat.chat-left .article{color:#999;min-height:30px;border:1px solid #f0f0f0;background-color:#f0f0f0;background-image:-webkit-gradient(linear,left top,left bottom,from(#f0f0f0),to(#f0f0f0));background-image:-webkit-linear-gradient(top,#f0f0f0,#f0f0f0);background-image:-moz-linear-gradient(center top,#f0f0f0,#f0f0f0);background-image:linear-gradient(top,#f0f0f0,#f0f0f0)}.fr{padding-left:10px;padding-right:50px}.fr .triangle{left:auto;right:42px;border-bottom:8px solid #FFF;border-top:8px solid #FFF;border-right:none;border-left:8px solid ${color}}.fr .article{float:right}.article .article-template-warp .template-item{padding:5px 10px;background:#fff;margin-bottom:5px;border:1px solid #ddd}.article .article-template-warp .template-item.cur{border:1px solid ${color}}.article .article-template-warp .template-item:hover{border:1px solid ${color};box-shadow:0 0 8px ${color}}.article .article-template-warp .template-item a{color:#000}.header{width:100%;height:60px;background:${color};border-top-left-radius:10px;border-top-right-radius:10px;color:#fff;border-bottom:1px solid #eee}.header .nav{padding:10px}.header .nav li{float:left}.header .nav li.nav-logo{padding-top:5px}.header .nav li.nav-title{margin-left:20px}.header .nav li.nav-title h3{font-size:14px}.header .nav li.nav-title h6{font-size:12px;margin-top:2px}.header .nav li.nav-buttons{float:right;margin-right:5px}.header .nav li.nav-buttons i{margin-right:5px;cursor:pointer}.footer{border-top:1px solid #d9d9d9;border-left:1px solid ${color};border-right:1px solid ${color};border-bottom:1px solid ${color};padding-bottom:10px}.footer .link-artificial{text-align:right;color:${color};padding:3px}.footer .textarea-warp{padding:0 10px 10px 10px}.footer .textarea-warp textarea{border:0;width:100%;height:21px}.footer .textarea-botton{text-align:right}.footer .textarea-botton button{border:0;color:#fff;background:${color};padding:5px 15px 5px 15px;border-radius:5px;margin-right:10px}button,input,textarea{outline:0;resize:none}.synopsis{width:100%;color:#666;padding:0 0 5px 0;height:auto}.knowledge-more{text-align:center;cursor:pointer;display:none}.phone-images-warp{min-height:50px;float:left}.phone-content-warp{float:left;margin-left:5px}.phone-content-warp ul{margin:0;width:200px;height:100%;padding:0}.phone-content-warp ul li{float:left;width:100px}.phone-images-warp img{border:1px solid ${color};padding:3px;width:auto;max-height:50px;margin-bottom:5px}
			
		</style>
		
		
		<script type="text/javascript" src="${s}/js/lib/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${s}/js/lib/handlebars-v1.3.0.js"></script>
		<script type="text/javascript" src="${s}/js/lib/jquery.typeahead.js"></script>
		<script type="text/javascript" src="${s}/js/layer/layer.js"></script>
		
		<script type="text/javascript" src="${s}/js/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
		<script type="text/javascript" src="${s}/js/lib/jquery.typeahead.js"></script>
		<script>
			var base = "${ctx}";
			var baseStatic = "${s}";
			var baseOption = {
				channel:4,
				locationId:31399
			}
			var color = "${color}";
			var windowHeight = "${height}";
			
		</script>
	</head>
	<body>
		<div class="header">
			<ul class="fn-clearfix nav">
				<li class="nav-logo"><img src="${s}/img/icon-cs.png"/></li>
				<li class="nav-title">
					<h3>电信机器人</h3>
					<h6>
						<select style="color: #000;font-weight: normal;">
						<c:forEach items="${locationInfos}" var="locInfo">
							<option value="${locInfo.id}">${locInfo.name}</option>
						</c:forEach>
						</select>
					</h6>
				</li>
				<li class="nav-buttons">
					<i class="icon iconfont icon-jian" title="最小化"></i>
					<i class="icon iconfont icon-quanping" title="全屏"></i>
					<i class="icon iconfont icon-cuowu" title="关闭"></i>
				</li>
			</ul>
		</div>
		<div class="container" >
			<div class="questions-left-warp" >
				
				<div class="bubble Js-bubble-warp">
					<div class="sroll-warp">
						<div class="chat chat-left fn-clearfix">
							<div class="name">机器人小应</div>
							<span class="triangle"></span>
							<div class="article">您好，机器人小应为您服务！</div>
						</div>
					</div>
					
				</div>
			</div>
			<div class="info-right-warp">
					<div class="info-tab Js-info-tab">
						<ul>
							<li data-tab="1" class="cur"><div class="rd">热门词条</div></li>
							<li data-tab="3" ><div class="rd">详情</div></li>
						</ul>
					</div>
					<div class="info-warp Js-info-warp cur" data-tab="1">
						<ul style="padding: 15px;">
							<li><a href="javascript:void(0)">天翼4G套餐</a></li>
							<li><a href="javascript:void(0)">天翼4G套餐</a></li>
							<li><a href="javascript:void(0)">朝阳门营业厅</a></li>
							<li><a href="javascript:void(0)">天翼4G套餐</a></li>
							<li><a href="javascript:void(0)">天翼4G套餐</a></li>
						</ul>
					</div>
					<div class="info-warp Js-info-warp" data-tab="3" style="overflow: hidden;">
						<div class="info-title">
							<i class="icon iconfont icon-tiaozhuanwaibulianjie" title="跳转详情内页"></i>
						</div>
						<div class="flex-container-btn Js-flex-container-btn">
							<div class="cur" data-type="left"><i class="icon iconfont icon-xiangzuo" ></i></div>
							<div data-type="right"><i class="icon iconfont icon-xiangyou " ></i></div>
						</div>
						<div class="knowledge-content" style="width: 300px;padding-left: 20px;">
							<div class="sroll-warp">
							</div>
						</div>
						
					</div>
				</div>
			</div>
			
		</div>
		<div class="footer">
			<div class="link-artificial">
				<span></span>
			</div>
			<div class="textarea-warp">
				<form action="" onsubmit="return false;">
					<div class="typeahead-container">
				        <div class="typeahead-field">
				            <span class="typeahead-query">
								<textarea class="Js-questions-textarea" placeholder="请输入..." maxlength="200"></textarea>
							</span>
						</div>
					</div>
				</form>
			</div>
			<div class="textarea-botton">
				<button type="button" class="Js-questions-send-btn">发送</button>
			</div>
		</div>
	
	<!-- 客服回复 -->
	<script id="TP-reply-warp-template" type="text/x-handlebars-template">
		<div class="chat chat-left Js-chat-left-warp fn-clearfix">
			<div class="name">机器人小应</div>
			<span class="triangle"></span>
			<div class="article" >
				<div class="reply-warp">
					<img  class="Js-loading-img" src="${s}/img/loading/335.GIF"/>
				</div>
				<div class="knowledge-more Js-more-btn">更多&nbsp;<i class="icon iconfont icon-xjiantou" style="margin-left: 3px;" title="更多"></i></div>
			</div>
			
		</div>
	</script>
	
	<script id="TP-reply-template-warp-template" type="text/x-handlebars-template">
		{{#if bundle}}
		<div class="article-template-warp">
			{{#each bundle}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;"  class="Js-title-item"><i class="icon iconfont icon-taocan mfc"></i>&nbsp;{{{delHtmlTag kName}}}</a>&nbsp;<i class="icon iconfont icon-tiaozhuanwaibulianjie Js-show-kContent-btn" title="简略信息"></i>
					<div class="businessoffice-synopsis synopsis">
						{{#if this.bundle.description}}
							简介：{{{this.bundle.description}}}&nbsp;&nbsp;<br />
						{{/if}}
						{{#if this.bundle.startTime}}
							推出日期：{{this.bundle.startTime}}&nbsp;&nbsp;&nbsp;
						{{/if}}
						{{#if this.bundle.endTime}}
							停售日期：{{this.bundle.endTime}}
						{{/if}}
						
					</div>
				</div>
			{{/each}}
			
		</div>
		{{/if}}
		
		{{#if deals}}
		<div class="article-template-warp">
			{{#each deals}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;"  class="Js-title-item"><i class="icon iconfont icon-you mfc"></i>&nbsp;{{{kName}}}</a>&nbsp;
					<div class="businessoffice-synopsis synopsis">
						{{#if this.deals.description}}
							<div>
								{{{this.deals.description}}}
							</div>
						{{/if}}
						<div>
							{{#if this.deals.applicantType}}
								用户类型：{{this.deals.applicantType}}&nbsp;&nbsp;&nbsp;
							{{/if}}
							{{#if this.deals.dealType}}
								活动类型：{{this.deals.dealType}}
							{{/if}}
						</div>
					</div>
				</div>
			{{/each}}
		</div>
		{{/if}}
		
		{{#if mobilephone}}
		<div class="article-template-warp">
			{{#each mobilephone}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;"  class="Js-title-item"><i class="icon iconfont icon-wodeshouji mfc"></i>&nbsp;{{{kName}}}</a>&nbsp;
					<div class="businessoffice-synopsis synopsis fn-clear">	
						<div class="phone-images-warp">
								<img src="http://2f.zol-img.com.cn/product/154_120x90/485/cegFtZodSwL6.jpg"/>
						</div>
						<div class="phone-content-warp">
							<ul class="fn-clear">
								{{#if this.mobilephone.price}}<li>价格：{{{this.mobilephone.price}}}&nbsp;</li>{{/if}}
								{{#if this.mobilephone.screenSize}}<li>尺寸：{{{this.mobilephone.screenSize}}}&nbsp;</li>{{/if}}
								{{#if this.mobilephone.osType}}<li>系统：{{{this.mobilephone.osType}}}&nbsp;</li>{{/if}}
								{{#if this.mobilephone.ExpandableMemoryStorage}}<li>RAM：{{{this.mobilephone.ExpandableMemoryStorage}}}&nbsp;</li>{{/if}}
								{{#if this.mobilephone.internalMemoryStorage}}<li>ROM：{{{this.mobilephone.internalMemoryStorage}}}&nbsp;</li>{{/if}}
							</ul>
						</div>
					</div>
				</div>
			{{/each}}
		</div>
		{{/if}}
		
		{{#if businessoffice}}
		<div class="article-template-warp">
			{{#each businessoffice}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;" class="Js-title-item"><i class="icon iconfont icon-youxiangyingyeting mfc"></i>&nbsp;{{{delHtmlTag kName}}}</a>&nbsp;
					<div class="businessoffice-synopsis synopsis">
						{{#if this.business_office.address}}
							地址：{{this.business_office.address}}&nbsp;&nbsp;<br />
						{{/if}}
						{{#if this.business_office.businessTime}}
							营业时间：{{this.business_office.businessTime}}&nbsp;&nbsp;&nbsp;
						{{/if}}
						{{#if this.business_office.phoneNumber}}
							联系电话：{{this.business_office.phoneNumber}}
						{{/if}}
						
					</div>
				</div>
			{{/each}}
		</div>
		{{/if}}
		
		{{#if faq}}
		<div class="article-template-warp">
			{{#each faq}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;" class="Js-title-item"><i class="icon iconfont icon-askwen mfc"></i>&nbsp;{{{delHtmlTag kName}}}</a>&nbsp;
					<div class="businessoffice-synopsis synopsis">
						{{#if this.faq.answer}}
							<i class="icon iconfont icon-askda mfc"></i>&nbsp;{{delHtmlTag this.faq.answer}}&nbsp;&nbsp;<br />
						{{/if}}
					</div>
				</div>
			{{/each}}
		</div>
		{{/if}}
		
		{{#if other}}
		<div class="article-template-warp">
			{{#each other}}
				<div class="template-item" data-id="{{kId}}">
					<a href="javascript:;"  class="Js-title-item">{{{kName}}}</a>&nbsp;
					<div class="businessoffice-synopsis synopsis">
						{{#if this.kContent}}
							{{{kContent}}}
						{{/if}}
					</div>
				</div>
			{{/each}}
		</div>
		{{/if}}
	</script>
	
	<!-- 用户提问 -->
	<script id="TP-questions-warp-template" type="text/x-handlebars-template">
		<div class="chat chat-right fn-clearfix fr">
			<span class="triangle"></span>
			<div class="article">{{questions}}</div>
		</div>
	</script>
	
	<!-- 右侧详情框 -->
	<script id="TP-knowledge-iten-warp-template" type="text/x-handlebars-template">
		<div class="knowledge-title">{{{name}}}</div>
		<div class="knowledge-important">所属模板：{{templateDisplayName}}</div>
		{{#each attrs}}
			<div class="knowledge-row-item">
				<div class="item-title">{{{displayName}}}:</div>
				<div class="item-content">{{{value}}}</div>
			</div>
		{{/each}}
	</script>
	
	<script type="text/javascript" src="${s}/js/view/main.js" ></script>
	</body>

</html>