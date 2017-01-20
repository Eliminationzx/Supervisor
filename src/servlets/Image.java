
public class Image {
	private int Id;
	private int Col;
	private int Row;
	private String Interval;
	private String Color;
	private String Height;
	private String Host;
	private String Name;
	private String Subid;
	private String Width;
	
	public String getColor() {
		return Color;
	}
	public Image(int id, int i, String name, String subid, String interval,
			String width, String height, String host, String color, int row, int col) {
		Id = id;
		Col = col;
		Row = row;
		Interval = interval;
		Color = color;
		Height = height;
		Host = host;
		Name = name;
		Subid = subid;
		Width = width;
	}
	public String getHeight() {
		return Height;
	}
	public String getHost() {
		return Host;
	}
	public String getName() {
		return Name;
	}
	public String getSubid() {
		return Subid;
	}
	public String getWidth() {
		return Width;
	}
	public void setCol(int col) {
		Col = col;
	}
	public void setRow(int row) {
		Row = row;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getInterval() {
		return Interval;
	}
	public void setInterval(String interval) {
		Interval = interval;
	}
	public int getCol() {
		return Col;
	}
	public int getRow() {
		return Row;
	}
	public void setColor(String color) {
		Color = color;
	}
	public void setHeight(String height) {
		Height = height;
	}
	public void setHost(String host) {
		Host = host;
	}
	public void setName(String name) {
		Name = name;
	}
	public void setSubid(String subid) {
		Subid = subid;
	}
	public void setWidth(String width) {
		Width = width;
	}
	
}
