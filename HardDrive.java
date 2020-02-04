package Homeworks;

public class HardDrive{
	private TxtFile[] drive;
	private int numUsed;
	private final int DRIVE_SIZE=1000;
	
	public HardDrive() {
		drive=new TxtFile[DRIVE_SIZE];
		this.numUsed=0;
	}
	
	public boolean addFile(String filename,String data) {
		if(numUsed>=DRIVE_SIZE) {
				return false;
		}
		for(int i=0;i<numUsed;i++) {
			if(exists(filename)) {
				return false;
			}}
		for(int i=0;i<DRIVE_SIZE;i++) {
			if(drive[i]==null) {
				drive[i]=new TxtFile(filename,data);
				
				numUsed++;
				break;
			}
		}
		return true;
		
	}
	public int indexOfFile(String filename){
		for(int i=0;i<DRIVE_SIZE;i++) {
			if(drive[i]!=null && drive[i].getName().equals(filename)) {
				return i;
				}
		}
		return -1;
	}
	public boolean exists(String filename) {
		if(this.indexOfFile(filename)==-1) {
			return false;
		}
		return true;
	}
	public String getContent(String filename) {
		int index=this.indexOfFile(filename);
		if(index==-1) 
			return "null";
		else {
			return drive[index].getData();
		}
	}
	public boolean deleteFile(String filename) {
		int counter=this.indexOfFile(filename);
		if(counter==-1) {
			return false;
		}
		drive[counter]=null;
		numUsed--;
		return true;
	}
	
	public boolean renameFile(String from, String to) {
		if(this.exists(from) && !this.exists(to)) {
			drive[this.indexOfFile(from)].setName(to);
			return true;
		}
		
		return false;
	
	}
	
	public boolean appendFile(String filename,String extraData) {
		if(this.exists(filename)) {
			drive[this.indexOfFile(filename)].appendData(extraData);
			return true;
		}
		return false;
	}
	
	public String[] listFiles() {
		int counter=0;
		
		String list[]=new String [numUsed];
		for(int i=0,j=0;i<DRIVE_SIZE;i++) {
			if(drive[i]!=null) {
				list[i]=drive[j].getName();
				j++;}
		}
		return list;
	}
}

























