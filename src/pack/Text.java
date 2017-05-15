package pack;
public class Text {

	private String txt;
	private Metrics metrics;
	private String fileLoc;

    public Text() {
    }

    public Text(String txt, Metrics metrics, String fileLoc) {
        this.txt = txt;
        this.metrics = metrics;
        this.fileLoc = fileLoc;
    }
    
    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public void setMetrics(Metrics metrics) {
        this.metrics = metrics;
    }

    public String getFileLoc() {
        return fileLoc;
    }

    public void setFileLoc(String fileLoc) {
        this.fileLoc = fileLoc;
    }

	
	
}
