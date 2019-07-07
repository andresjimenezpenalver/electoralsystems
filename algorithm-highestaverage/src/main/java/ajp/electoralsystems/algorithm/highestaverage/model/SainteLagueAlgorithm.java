package ajp.electoralsystems.algorithm.highestaverage.model;

/**
 * @author Andres Jimenez Penalver
 */
public class SainteLagueAlgorithm extends HighestAverageAlgorithm {
	
	public SainteLagueAlgorithm() {
		super();
	}
	
	public String getName() {
		return "SAINTE-LAGUE";
	}
		
	public float getQuota(long votes, int seats) {
		float quota = (float) votes / (float) (2*seats +1);
		return quota;
	}
	
}