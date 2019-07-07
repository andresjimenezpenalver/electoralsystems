package ajp.electoralsystems.algorithm.quota.model;

import java.util.Comparator;

public class PartyResultWithResidualVotesComparator implements Comparator<PartyResultWithResidualVotes> {

	public int compare(PartyResultWithResidualVotes partyResult1, PartyResultWithResidualVotes partyResult2) {
		int compare = partyResult1.getResidualVotes().compareTo(partyResult2.getResidualVotes());
		// sorting from highest to lowest
		return -1 * compare;
	}
	
}
