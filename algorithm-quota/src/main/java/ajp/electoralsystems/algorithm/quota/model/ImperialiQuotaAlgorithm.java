package ajp.electoralsystems.algorithm.quota.model;

/**
 * @author Andres Jimenez Penalver
 */
public class ImperialiQuotaAlgorithm extends QuotaAlgorithm {
	
	public ImperialiQuotaAlgorithm() {
		super();
	}
	
	public String getName() {
		return "IMPERIALI-QUOTA";
	}
	
	public int getQuota(long validVotes, int seats) {
		float quota = (float) validVotes / (float) (seats+2);
		return Math.round(quota);
	}

}