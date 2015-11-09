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
	<form action="${request.contextPath }/webchat!aiRequest.do">
		ask：<input type="text" name="ask" /><br/>
		reply：<input type="text" name="reply" /><br/>
		<input type="submit" value="submit" />
	</form>
	
	<hr />
	<c:forEach items="${allReplyList }" var="item">
		<div style="background-color:purple;color:white;margin-bottom:10px">
			[ask]:${item.ask }<br/>
			[reply]:${item.answer}
		</div>
	</c:forEach>
</body>
</html>