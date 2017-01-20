import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Edit<HttpSession> extends Main {
private static final long serialVersionUID = 1L;
public Edit() {
super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
if (request.getParameter(Consts.PARAM_ACTION) != null) {doPost(request, response);} else {
javax.servlet.http.HttpSession session = request.getSession();
session.setAttribute("pages", getPages());
RequestDispatcher dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_PAGES_MANAGER); 
dispatcher.forward(request, response);
}}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
javax.servlet.http.HttpSession session = request.getSession();
RequestDispatcher dispatcher = null;
String action = request.getParameter(Consts.PARAM_ACTION);
String rowsString = request.getParameter(Consts.PARAM_ROWS);
String colsString = request.getParameter(Consts.PARAM_COLS);
String interval = request.getParameter(Consts.PARAM_INTERVAL);
String pageName = request.getParameter(Consts.PARAM_PAGE_NAME);
String row = request.getParameter(Consts.PARAM_ROW);
String col = request.getParameter(Consts.PARAM_COL);
String name = request.getParameter(Consts.PARAM_NAME);
String id = request.getParameter(Consts.PARAM_ID);
String subid = request.getParameter(Consts.PARAM_SUBID);
String width = request.getParameter(Consts.PARAM_WIDTH);
String height = request.getParameter(Consts.PARAM_HEIGHT);
String host = request.getParameter(Consts.PARAM_HOST);
String color = request.getParameter(Consts.PARAM_COLOR);
if (action != null && action.equals(Consts.ACTION_DELETE)) {
deletePage(request, pageName);
session.setAttribute("pages", getPages());
dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_PAGES_MANAGER);
dispatcher.forward(request, response);
} else { if (rowsString != null && colsString != null && interval != null && pageName != null) {
if (action != null && action.equals(Consts.ACTION_UPDATE)) {
Page page = getPage(pageName);
page.setInterval(Integer.parseInt(interval));
page.setRows(Integer.parseInt(rowsString));
page.setCols(Integer.parseInt(colsString));
session.removeAttribute(pageName);
dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_EDIT);
dispatcher.forward(request, response);
} else {if (action != null && action.equals(Consts.ACTION_ADD)) {
Page page = getPage(pageName);
if (page == null) {
int rows = Integer.parseInt(rowsString);
int cols = Integer.parseInt(colsString);
page = new Page(pageName, 0, "/" + pageName + ".jsp", Integer.parseInt(interval), rows, cols, new HashMap<String, Image>());
addPage(request, page);}
session.setAttribute("pages", getPages());
dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_PAGES_MANAGER);
dispatcher.forward(request, response);
}}}}
if (row != null && col != null && pageName != null && name != null && id != null && subid != null && interval != null && width != null && height != null && host != null && color != null) {
Page page = getPage(pageName);
session.removeAttribute(pageName);
Image image = page.getImage(Integer.parseInt(row) + Integer.parseInt(col));
if (image != null) {
image.setColor(color);
image.setHeight(height);
image.setHost(host);
image.setId(Integer.parseInt(id));
image.setInterval(interval);
image.setName(name);
image.setSubid(subid);
image.setWidth(width);
DataModel.updateImage(page, image);
} else {
image = new Image(Integer.parseInt(id), 0, name, subid, interval, width, height, host, color, Integer.parseInt(row), Integer.parseInt(col));
page.getImages().put(image.getRow() + "" + image.getCol(), image);
DataModel.updateImage(page, image);}
dispatcher = session.getServletContext().getRequestDispatcher(Consts.JSP_URI_EDIT);
dispatcher.forward(request, response);}}}
