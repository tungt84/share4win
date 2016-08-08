<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper col1">
  <div id="topbar">
    <p>Liên hệ: runningchild2014@gmail.com</p>
    <ul>
<c:choose>
<c:when test="${user_logined}">
	<li><a href="#"><c:out value="Chào ${USER_LOGIN.email}"></c:out></a></li>
	<li class="last"><a href="/user/logout">Đăng xuất</a></li>
</c:when>
<c:otherwise>
	<li class="last"><a href="/user/login">Đăng nhập</a></li>
</c:otherwise>
</c:choose>
    </ul>
    <br class="clear" />
  </div>
</div>