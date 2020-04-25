package ajp.electoralsystems.algorithm.quota.model;

import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.PartyResult;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class PartyResultWithResidualVotes extends PartyResult {

	private @Getter @Setter Integer residualVotes = 0;
	private @Getter @Setter Integer votesPerQuota = 0;

	public PartyResultWithResidualVotes(Party party) {
		setParty(party);
	}

	public void incrementSeats() {
		setSeats(getSeats()+1);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.toString());
		sb.append("residualVotes: ").append(residualVotes).append('\n');
		sb.append("votesPerQuota: ").append(votesPerQuota).append('\n');
		return sb.toString();
	}

}
