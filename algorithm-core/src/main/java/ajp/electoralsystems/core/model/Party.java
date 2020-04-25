package ajp.electoralsystems.core.model;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class Party implements Comparable<Party> {

	@NotEmpty(message="Error.InvalidDistrictData.InvalidPartyName")
	private @Getter @Setter String name;
	private @Getter @Setter Long votes = 0L;
	private @Getter @Setter Double votePercentage = 0.0;
	private @Getter @Setter Double votePercentageOverCensus = 0.0;
	
	public Party() {
	}
	
	public boolean equals(Object o) {
	    if (o == this) {
	        return true;
	    } 
	    if (!(o instanceof Party)) {
	        return false;
	    } 
	    Party other = (Party)o;
	    boolean partyEquals = this.getName().equals(other.getName());
	    return partyEquals;
	}
	
	public int compareTo(Party party) {
		int compare = votes.compareTo(party.getVotes());
		if (compare == 0) {
			compare = name.compareTo(party.getName());
		}
		// sorting from highest to lowest 
		return -1*compare;
	}

	public void reset() {
		name = "";
		votes = 0L;
	}
	
	public boolean isThresholdPassed(double threshold) {
		return getVotePercentage() >= threshold;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name: ").append(name).append('\n');
		sb.append("votes: ").append(votes).append('\n');
		sb.append("vote percentage: ").append(votePercentage).append('\n');	
		sb.append("vote percentage over census: ").append(votePercentageOverCensus).append('\n');		
		return sb.toString();		
	}
	
	public Party withName(String name) {
		setName(name);
		return this;
	}

	public Party withVotes(Long votes) {
		setVotes(votes);
		return this;
	}

}
