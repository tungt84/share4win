<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="wrapper col4">
  <div id="breadcrumb">
    <ul>
      <li class="first">Bạn đang ở</li>
      <li>&#187;</li>
      <li><a href="/home">Trang chủ</a></li>
       <li>&#187;</li>
      <li class="current"><a href="#"><c:out value="${PAGEINFO.title}"></c:out></a></li>
    </ul>
  </div>
</div>