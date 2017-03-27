package pack;

public class PathProcessor {
	//file input config
	String inFilePath;
	String outFolderPath;
	String dictPath;
	String resName;
	String resLocation;
	
	public String getInFilePath() {
		return inFilePath;
	}

	public String getOutFolderPath() {
		return outFolderPath;
	}

	public String getDictPath() {
		return dictPath;
	}

	public String getResName() {
		return resName;
	}

	public String getResLocation() {
		return resLocation;
	}
	
	public void process(String inFilePath, String outFolderPath,
			String dictPath, String resName){
		this.inFilePath = inFilePath;
		this.outFolderPath = outFolderPath;
		this.dictPath = dictPath;
		this.resName = resName;
		resLocation = outFolderPath+"."+resName+"csv";
	}
	

}
