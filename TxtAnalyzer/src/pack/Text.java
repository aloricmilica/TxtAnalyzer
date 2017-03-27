package pack;
public class Text {

	private String txt;
	private Metrics metrics;
	private String fileLoc;

	public void setTxt(String txt) {
		this.fileLoc = txt;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public String getTxt() {
		return fileLoc;
	}
	
	public Metrics getMetrics() {
		return metrics;
	}
	
}
