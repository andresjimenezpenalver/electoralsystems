package ajp.electoralsystems.algorithm.highestaverage.model;

/**
 * @author Andres Jimenez Penalver
 */
public class DhontAlgorithm extends HighestAverageAlgorithm {
	
	public DhontAlgorithm() {
		super();
	}
	
	public String getName() {
		return "DHONT";
	}
		
	public float getQuota(long votes, int seats) {
		float quota = (float) votes / (float) (seats +1);
		return quota;
	}
	
}