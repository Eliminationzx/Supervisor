import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class Main extends HttpServlet {
private static final long serialVersionUID = 1L;
private static List<Page> pages;
private static long version;
static {
version = new Date().getTime();
fillPages();
}
public Main() {
super();
}
public static long getVersion() {
return version;
}
public static void setVersion(long version) {
Main.version = version;
}
public static List<Page> getPages() {
return pages;
}
public static Page getPage(String pageName) {
Iterator<Page> iterator = getPages().iterator();
Page page = null;
while (iterator.hasNext()) {
page = (Page) iterator.next();
if (page.getName().equalsIgnoreCase(pageName)) {
break;} else {page = null;}}return page;}
public static void deletePage(HttpServletRequest request, String pageName) {
Page page = getPage(pageName);
if (page != null) {
String path = request.getSession().getServletContext().getRealPath("/") + "\\";
File file = new File(path + page.getName() + ".jsp");
file.delete();
file = new File(path + "/scripts/" + pageName + ".js");
file.delete();
pages.remove(page);
DataModel.deletePage(page);}}
public static void fillPages() {
	pages = DataModel.getPages();
}
public static void addPage(HttpServletRequest request, Page page) {
pages.add(page);
String path = request.getSession().getServletContext().getRealPath("/") + "\\";
String pageContent = "<%@ page language=\"java\" contentType=\"text/html; charset=UTF-8\" pageEncoding=\"UTF-8\"%>\n<%@ page import=\"by.beloil.beans.*\"%>\n<%@ taglib prefix=\"c\" uri=\"http://java.sun.com/jsp/jstl/core\"%>\n<%@ page import=\"by.beloil.model.Consts\"%>\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<html>\n\t<head>\n\t\t<c:if test=\"${" + page.getName() + " == null}\">\n\t\t\t<c:redirect url=\"<%=Consts.SERVLET_URL_RUN%>\">\n\t\t\t\t<c:param name=\"<%=Consts.PARAM_PAGE_NAME%>\" value=\"" + page.getName() + "\"/>\n\t\t\t</c:redirect>\n\t\t</c:if>\n\t\t<%Page curPage = (Page) session.getAttribute(\"" + page.getName() + "\");%>\n\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n\t\t<title><%=curPage.getName()%></title>\n\t\t<script type=\"text/javascript\" src=\"scripts/" + page.getName() + ".js\"></script>\n\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/main.css\"/>\n\t</head>\n\t<body onload=\"rescale();refresh();\">\n\t\t<table id=\"table\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\">\n\t\t\t<%\n\t\t\t\tfor(int row = 1; row <= curPage.getRows(); row++) {\n\t\t\t\t\tString head = \"<tr>\";\n\t\t\t\t\tString body = \"<tr>\";\n\t\t\t\t\tfor(int col = 1; col <= curPage.getCols(); col++) {\n\t\t\t\t\t\tImage image = curPage.getImage(row + \"\" + col);\n\t\t\t\t\t\tif (image != null) {\n\t\t\t\t\t\t\thead += \"<td class=\\\"\" + image.getColor() + \"\\\">\" + image.getName() + \"</td>\";\n\t\t\t\t\t\t\tbody += \"<td><img id=\\\"\" + image.getId() + \"\\\"></td>\";\n\t\t\t\t\t\t} else {\n\t\t\t\t\t\t\thead += \"<td class=\\\"red\\\"></td>\";\n\t\t\t\t\t\t\tbody += \"<td class=\\\"red\\\"></td>\";\n\t\t\t\t\t\t}\n\t\t\t\t\t}\n\t\t\t\t\thead += \"</tr>\";\n\t\t\t\t\tbody += \"</tr>\";\n\t\t\t\t\tout.println(head);\n\t\t\t\t\tout.println(body);\n\t\t\t\t}\n\t\t\t%>\n\t\t</table>\n\t</body>\n</html>";
File file = new File(path + page.getName() + ".jsp");
PrintWriter printWriter;
try {
printWriter = new PrintWriter(file);
printWriter.print(pageContent);
printWriter.close();
} catch (FileNotFoundException e) {
}}}
