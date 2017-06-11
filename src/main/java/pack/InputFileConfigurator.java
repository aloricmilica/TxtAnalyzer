package pack;

public class InputFileConfigurator {
	//file input config
	private String inFilePath;
	private String outFolderPath;
	private String dictPath;
	private String resName;

    public InputFileConfigurator(String inFilePath, String outFolderPath, String dictPath, String resName) {
        this.inFilePath = inFilePath;
        this.outFolderPath = outFolderPath;
        this.dictPath = dictPath;
        this.resName = resName;
    }
	
        
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
		

}
