package ajp.electoralsystems.algorithm.quota.model;

/**
 * @author Andres Jimenez Penalver
 */
public class DroopQuotaAlgorithm extends QuotaAlgorithm {
	
	public DroopQuotaAlgorithm() {
		super();
	}
	
	public String getName() {
		return "DROOP-QUOTA";
	}
	
	public int getQuota(long validVotes, int seats) {
		float quota = 1 + ((float) validVotes / (float) (seats+1));
		return Math.round(quota);
	}

}