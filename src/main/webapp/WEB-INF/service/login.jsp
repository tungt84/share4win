<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${empty USER_LOGIN}">
{
	"rs":"fail"
}    
    </c:when>
    <c:otherwise>
{
	"rs":"OK",
	users:{
		"id":'<c:out value="${USER_LOGIN.id}"/>',
		"email":'<c:out value="${USER_LOGIN.email}"/>',
		"password":'<c:out value="${USER_LOGIN.password}"/>',
		"status":'<c:out value="${USER_LOGIN.status}"/>',
		"hash":'<c:out value="${USER_LOGIN.hash}"/>',
		"level":'<c:out value="${USER_LOGIN.level}"/>',
		"mfp":'<c:out value="${USER_LOGIN.mfp}"/>',
		"sp":'<c:out value="${USER_LOGIN.sp}"/>',
		"exp":'<c:out value="${USER_LOGIN.exp}"/>',
		"mf_poision":'<c:out value="${USER_LOGIN.mf_poision}"/>',
		"s_poision":'<c:out value="${USER_LOGIN.s_poision}"/>',
		"diamon":'<c:out value="${USER_LOGIN.diamon}"/>',
		"phone":'<c:out value="${USER_LOGIN.phone}"/>',
		"phone_actived":'<c:out value="${USER_LOGIN.phone_actived}"/>',
		"mk_date":'<c:out value="${USER_LOGIN.mk_date}"/>',
		"s_date":'<c:out value="${USER_LOGIN.s_date}"/>',
		"phoneactive":'<c:out value="${USER_LOGIN.phoneactive}"/>'
	}
}   
    </c:otherwise>
</c:choose>