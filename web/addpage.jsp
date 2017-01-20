<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="Consts"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new page</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css"/>
</head>
<body>
<div align="center">
    <c:url var="addUrl" scope="page" value="<%=Consts.SERVLET_URL_EDIT%>"/>
    <form action="${addUrl}" method="post">
        Name: <input type="text" name="<%=Consts.PARAM_PAGE_NAME%>">
        Rows: <input name="<%=Consts.PARAM_ROWS%>" type="text" size="2">
        Columns: <input name="<%=Consts.PARAM_COLS%>" type="text" size="2">
        Interval: <input name="<%=Consts.PARAM_INTERVAL%>" type="text" size="2">
        <input type="submit" value="<%=Consts.ACTION_ADD%>" name="<%=Consts.PARAM_ACTION%>">
    </form>
</div>
<br>
<div align="center"><a href="<%=Consts.JSP_PAGES_MANAGER%>">Back to page manager</a></div>
</body>
</html>