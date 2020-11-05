package ajp.electoralsystems.algorithm.quota.model;

import java.util.Comparator;

/**
 * @author Andres Jimenez Penalver
 */
public class PartyResultWithResidualVotesComparator implements Comparator<PartyResultWithResidualVotes> {

	public int compare(PartyResultWithResidualVotes partyResult1, PartyResultWithResidualVotes partyResult2) {
		int compare = partyResult1.getResidualVotes().compareTo(partyResult2.getResidualVotes());
		return -1 * compare; // sorting from highest to lowest		
	}
	
}
