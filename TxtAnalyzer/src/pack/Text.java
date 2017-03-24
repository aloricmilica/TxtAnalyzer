package pack;
public class Text {

	String fileLoc;
	Metrics metrics;

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
