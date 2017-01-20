import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Page {
	private String url;
	private int interval;
	private int rows;
	private String name;
	private static HashMap<String,Image> images;
	private int cols;
	private int id;
	private HashMap<String, Image> hm;
	
	public Page(String name,int id,String url, int interval, int rows,  int cols,  HashMap<String, Image> hm) {
		this.url = url;
		this.interval = interval;
		this.rows = rows;
		this.name = name;
		this.cols = cols;
		this.id = id;
		this.hm = hm;
	}
	public HashMap<String, Image> getImages() { 
		return images; 
		}
	public Image getImage(int id) { 
		Iterator<Entry<String, Image>> iterator = getImages().entrySet().iterator(); 
		Image image = null; 
		while (iterator.hasNext()) { 
		image = (Image) iterator.next(); 
		if (image.getName().equalsIgnoreCase(Integer.toString(id))) { 
		break;} else {image = null;}}return image;}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows= rows;
	}
	public int getCols() {
		return cols;
	}
	public void setCols(int cols) {
		this.cols = cols;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HashMap<String, Image> getHm() {
		return hm;
	}
	public void setHm(HashMap<String, Image> hm) {
		this.hm = hm;
	}
	
}
