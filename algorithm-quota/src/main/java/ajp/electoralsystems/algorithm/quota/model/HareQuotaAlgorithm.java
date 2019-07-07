package ajp.electoralsystems.algorithm.quota.model;

/**
 * @author Andres Jimenez Penalver
 */
public class HareQuotaAlgorithm extends QuotaAlgorithm {
	
	public HareQuotaAlgorithm() {
		super();
	}
	
	public String getName() {
		return "HARE-QUOTA";
	}
	
	public int getQuota(long validVotes, int seats) {
		float quota = (float) validVotes / (float) seats;
		return Math.round(quota);
	}

}