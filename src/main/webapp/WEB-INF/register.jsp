<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
Template Name: Modular Business
Author: <a href="http://www.os-templates.com/">OS Templates</a>
Author URI: http://www.os-templates.com/
Licence: Free to use under our free template licence terms
Licence URI: http://www.os-templates.com/template-terms
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><c:out value="${PAGEINFO.title}"></c:out></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="../layout/styles/layout.css" type="text/css" />
</head>
<body id="top">
<jsp:include page="layout/full/topbar.jsp" />
<!-- ####################################################################################################### -->
<jsp:include page="layout/full/top-header.jsp" />
<!-- ####################################################################################################### -->
<jsp:include page="layout/full/topnav.jsp" />
<!-- ####################################################################################################### -->
<jsp:include page="layout/full/breadcrumb.jsp" />
<!-- ####################################################################################################### -->
<div class="wrapper col5">
  <div id="container">
    <form action="/public/register" method="post">
          <p>
            
            <input type="text" name="mail" id="mail" value="" size="22" />
            <label for="mail"><small>Gmail</small></label>
          </p>
          <p>
            
            <input type="text" name="phone" id="phone" value="" size="22" />
            <label for="Phone"><small>Phone</small></label>
          </p>
          <p>
            <input name="submit" type="submit" id="submit" value="Đăng ký" />
          </p>
        </form>
  </div>
</div>
<!-- ####################################################################################################### -->
<jsp:include page="/WEB-INF/layout/full/footer.jsp"/>
<!-- ####################################################################################################### -->
<jsp:include page="/WEB-INF/layout/full/copyright.jsp"/>
</body>
</html>