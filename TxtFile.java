package Homeworks;

public class TxtFile {
	private String filename;
	private String data;
	
	
	public TxtFile(String name, String text) {
		this.filename=name;
		this.data=text;
	}
	
	public String getName() {
		return this.filename;
	}
	
	public void setName(String newName) {
		this.filename=newName;
	}
	
	public String getData() {
		return this.data;
	}
	
	public void setData(String newData) {
		this.data=newData;
	}
	
	public void appendData(String newData) {
		this.data+=newData;
	}
	
	
}
