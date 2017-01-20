<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="Main"%>
<%@ page import="Consts"%>
<%@ page import="Image"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="by.beloil.beans.Page"%><html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:set var="pageName" scope="page" value="<%=request.getParameter(Consts.PARAM_PAGE_NAME)%>"/>
    <c:set var="page" scope="page" value="<%=Main.getPage(request.getParameter(Consts.PARAM_PAGE_NAME))%>"/>
    <title>Edit page: ${pageName}</title>
    <link rel="stylesheet" type="text/css" href="css/edit.css"/>
    <script type="text/javascript" src="scripts/edit.js"></script>
</head>
<body>
<p align="center">Editing of ${pageName}</p>
<c:url var="editUrl" scope="page" value="<%=Consts.SERVLET_URL_EDIT%>"/>
<form action="${editUrl}" method="post">
    <input type="hidden" name="<%=Consts.PARAM_PAGE_NAME%>" value="${pageName}">
    <table width="30%" align="center" class="editTtable">
        <tr>
            <td align="left">Rows</td>
            <td align="right"><input name="<%=Consts.PARAM_ROWS%>" type="text" size="2" value="<%=((Page)pageContext.getAttribute(Consts.PARAM_PAGE)).getRows()%>"></td>
        </tr>
        <tr>
            <td align="left">Columns</td>
            <td align="right"><input name="<%=Consts.PARAM_COLS%>" type="text" size="2" value="<%=((Page)pageContext.getAttribute(Consts.PARAM_PAGE)).getCols()%>"></td>
        </tr>
        <tr>
            <td align="left">Refresh page interval</td>
            <td align="right"><input name="<%=Consts.PARAM_INTERVAL%>" type="text" size="2" value="<%=((Page)pageContext.getAttribute(Consts.PARAM_PAGE)).getInterval()%>"> sec</td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="<%=Consts.ACTION_UPDATE%>" name="<%=Consts.PARAM_ACTION%>"></td>
        </tr>
    </table>
    <div align="center"><a href="<%=Consts.JSP_PAGES_MANAGER%>">Back to page manager</a></div>
</form>
<table cellpadding="0" cellspacing="0" align="center">
    <%
        Page curPage = (Page) pageContext.getAttribute(Consts.PARAM_PAGE);
        for(int row = 1; row <= curPage.getRows(); row++) {
    %><tr><%
    for(int col = 1; col <= curPage.getCols(); col++) {
        Image image = curPage.getImage(row + "" + col);
        if (image != null) {
%><td class="<%=image.getColor()%>">
    <div align="center">Name:</div>
    <form action="${editUrl}" method="post" onkeypress="update(this, event)">
        <input type="hidden" name="<%=Consts.PARAM_ROW%>" value="<%=row%>">
        <input type="hidden" name="<%=Consts.PARAM_COL%>" value="<%=col%>">
        <input type="hidden" name="<%=Consts.PARAM_PAGE_NAME%>" value="${pageName}">
        <input type="text" name="<%=Consts.PARAM_NAME%>" value="<%=image.getName()%>"><br>
        id:<input type="text" name="<%=Consts.PARAM_ID%>" value="<%=image.getId()%>" size="1">
        subid:<input type="text" name="<%=Consts.PARAM_SUBID%>" value="<%=image.getSubid()%>" size="1"><br>
        interval:<input type="text" name="<%=Consts.PARAM_INTERVAL%>" value="<%=image.getInterval()%>" size="1"><br>
        width:<input type="text" name="<%=Consts.PARAM_WIDTH%>" value="<%=image.getWidth()%>" size="1">
        height:<input type="text" name="<%=Consts.PARAM_HEIGHT%>" value="<%=image.getHeight()%>" size="1"><br>
        host:<input type="text" name="<%=Consts.PARAM_HOST%>" value="<%=image.getHost()%>" size="14"><br>
        color:
        <select name="<%=Consts.PARAM_COLOR%>">
            <option value="red">red</option>
            <option value="green">green</option>
            <option value="blue">blue</option>
        </select>
    </form>
</td><%
} else {
%><td class="red">
    <div align="center">Name:</div>
    <form action="${editUrl}" method="post" onkeypress="update(this, event)">
        <input type="hidden" name="<%=Consts.PARAM_ROW%>" value="<%=row%>">
        <input type="hidden" name="<%=Consts.PARAM_COL%>" value="<%=col%>">
        <input type="hidden" name="<%=Consts.PARAM_PAGE_NAME%>" value="${pageName}">
        <input type="text" name="<%=Consts.PARAM_NAME%>"><br>
        id:<input type="text" name="<%=Consts.PARAM_ID%>" size="1">
        subid:<input type="text" name="<%=Consts.PARAM_SUBID%>" size="1"><br>
        interval:<input type="text" name="<%=Consts.PARAM_INTERVAL%>" size="1"><br>
        width:<input type="text" name="<%=Consts.PARAM_WIDTH%>" size="1">
        height:<input type="text" name="<%=Consts.PARAM_HEIGHT%>" size="1"><br>
        host:<input type="text" name="<%=Consts.PARAM_HOST%>" size="14"><br>
        color:
        <select name="color">
            <option value="red">red</option>
            <option value="green">green</option>
            <option value="blue">blue</option>
        </select>
    </form>
</td><%}}
%></tr><%}%>
</table>
</body>
</html>