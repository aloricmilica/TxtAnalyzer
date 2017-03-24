package pack;

public class PathProcessor {
	
	String inFilePath;
	String outFolderPath;
	String dictPath;
	String resName;
	String resLocation;
	
	public void process(String inFilePath, String outFolderPath,
			String dictPath, String resName){
		this.inFilePath = inFilePath;
		this.outFolderPath = outFolderPath;
		this.dictPath = dictPath;
		this.resName = resName;
		resLocation = outFolderPath+"."+resName+"csv";
	}
	
}
