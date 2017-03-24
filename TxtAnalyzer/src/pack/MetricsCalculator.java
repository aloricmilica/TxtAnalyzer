package pack;

import java.io.IOException;

public interface MetricsCalculator {
	
	public Metrics calculate(PathProcessor pp) throws ClassNotFoundException, IOException;
	
}
