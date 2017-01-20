
import javax.servlet.*;
import javax.servlet.http.*;
import javax.imageio.*;
import java.net.*;
import java.io.*;

public class GetImage extends Main {
private static final long serialVersionUID = 1L;
public GetImage() {
super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String id = request.getParameter("id");
String w = request.getParameter("width");
String h = request.getParameter("height");
String refresh = request.getParameter("refresh");
String pageName = request.getParameter("page");
if (refresh != null) {
response.getOutputStream().print(getVersion());
} else {
if (id != null && w != null && h != null && pageName != null) {Page page = getPage(pageName);
if (page != null) {
Image image = page.getImage(Integer.parseInt(id));
if (image != null) {
String url = null;
url = "http://127.0.0.1:8080/chart.png?id=" + id + "&width=" + w + "&height=" + h;
URLConnection connection = new URL(url).openConnection();
InputStream inputStream = connection.getInputStream();
try {
ImageIO.write(ImageIO.read(inputStream), "png", response.getOutputStream());
} catch (IllegalArgumentException e) {
System.out.println(id);}}}}}}
}
