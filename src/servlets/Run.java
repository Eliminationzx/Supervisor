import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Run<HttpServletResponse> extends Main {
private static final long serialVersionUID = 1L;
public Run() {
super();
}
protected void doGet(HttpServletRequest request, ServletResponse response) throws ServletException, IOException {
String pageName = request.getParameter(Consts.PARAM_PAGE_NAME);
Page page = getPage(pageName);
javax.servlet.http.HttpSession session = request.getSession();
if (page != null) {
session.setAttribute(page.getName(), page);
String path = request.getSession().getServletContext().getRealPath("/scripts/") + "\\";
File file = new File(path + pageName + ".js");
String server = request.getServerName();
int port = request.getServerPort();
PrintWriter script = new PrintWriter(file);
script.print("function rescale() {\n\tvar table = document.getElementById(\"table\");\n\twidth = (screen.availWidth / table.rows[0].cells.length - 1).toFixed(0);\n\theight = (screen.availHeight / table.rows.length * 2 - 35).toFixed(0);\n\turl = \"http://" + server + ":" + port + "/prtgStat/GetImage?id=\";");
String func_resc = "";
String func_resc2 = "";
Iterator<Image> iterator = page.getImages().values().iterator();
while (iterator.hasNext()) {
Image image = (Image) iterator.next();
int id = image.getId();
int row = image.getRow();
int col = image.getCol();
String interval = image.getInterval();
func_resc += "\n\tr" + row + "c" + col + "();";
func_resc2 += "function r" + row + "c" + col + "() {\n\tvar img = document.getElementById(\"" +
id + "\");\n\tif (img != null) {\n\t\tvar time = new Date();\n\t\timg.setAttribute(\"src\", " + "url + \"" + id + "&page=" + page.getName() + "&width=\" + width + \"&height=\" + height + \"&time=\" + time);\n\t}\n\t" +
"setTimeout('r" + row + "c" + col + "()', " + interval + "000);\n}\n\n";
}
func_resc2 += "function refresh() {\n\tvar xmlhttp;\n\tif (window.XMLHttpRequest) {\n\t\txmlhttp = new XMLHttpRequest();\n\t} else {\n\t\txmlhttp = new ActiveXObject(\"Microsoft.XMLHTTP\");\n\t}\n\txmlhttp.onreadystatechange = function() {\n\t\tif (xmlhttp.readyState == 4 && xmlhttp.status == 200) {\n\t\t\tif (xmlhttp.responseText != document.getElementById(\"version\").innerHTML) {\n\t\t\t\twindow.location.reload();\n\t\t\t}\n\t\t}\n\t}\n\tvar url = \"http://" + server + ":" + port + "/prtgStat/GetImage?refresh=what&time=\" + new Date();\n\txmlhttp.open(\"GET\", url, true);\n\txmlhttp.send();\n\tsetTimeout('refresh()', " + page.getInterval() + "000);\n}";
func_resc += "\n}\n\n";
script.print(func_resc);
script.print(func_resc2);
script.close();
RequestDispatcher dispatcher = session.getServletContext().getRequestDispatcher(page.getUrl()); 
dispatcher.forward(request, response);
} else {
RequestDispatcher dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_PAGES_MANAGER); 
dispatcher.forward(request, response);
}}}