<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="Consts"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Pages manager</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css" />
</head>
<body>
<c:if test="${pages == null}">
    <c:redirect url="<%=Consts.SERVLET_URL_EDIT%>" />
</c:if>
<p align="center">Editing and viewing pages</p>
<table width="50%" align="center" class="editTtable">
    <c:forEach items="${pages}" var="page" varStatus="status">
        <tr>
            <td align="left"><c:url value="${page.url}" var="pageUrl" scope="page" /> <a href="${pageUrl}" target="_blank">${page.name}</a>
            </td>
            <td align="right"><c:url value="<%=Consts.JSP_PAGEEDIT%>" var="pageEdit" scope="page">
                <c:param name="<%=Consts.PARAM_PAGE_NAME%>" value="${page.name}" /> </c:url> <a href="${pageEdit}">Edit</a> <c:url value="<%=Consts.SERVLET_URL_EDIT%>" var="pageEdit" scope="page"> <c:param name="<%=Consts.PARAM_PAGE_NAME%>" value="${page.name}" /><c:param name="<%=Consts.PARAM_ACTION%>" value="<%=Consts.ACTION_DELETE%>" />
            </c:url>
                <a href="${pageEdit}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<div align="center"><a href="<%=Consts.JSP_ADD_PAGE%>">Add new page</a></div>
<%response.setHeader("Cache-Control", "no-cache");%>
</body>
</html>