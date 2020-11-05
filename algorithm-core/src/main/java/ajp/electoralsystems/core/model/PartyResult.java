package ajp.electoralsystems.core.model;

import java.beans.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class PartyResult implements Comparable<PartyResult> {

	private @Setter Party party;
	private @Getter @Setter Integer seats = 0;
	private @Getter @Setter double seatPercentage = 0.0;
	private @Getter @Setter double seatCost = 0.0;
	private @Getter @Setter Integer diffVotesForLastSeat = 0;
	private @Getter @Setter Boolean hasLastSeat = false;
	
	public PartyResult() {
	}

	public PartyResult(Party party) {
		setParty(party);
	}
	
	@Transient
	public Party getParty() {
		return party;
	}

	public String getName() {
		return party.getName();
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Party)) {
			return false;
		}
		PartyResult other = (PartyResult) o;
		boolean partyEquals = this.getParty().getName().equals(other.getParty().getName());
		return partyEquals;
	}

	public int compareTo(PartyResult partyResult) {
		int compare = seats.compareTo(partyResult.getSeats());
		if (compare == 0) {
			compare = getParty().getVotes().compareTo(partyResult.getParty().getVotes());
		}
		// sorting from highest to lowest
		return -1 * compare;
	}

	public void reset() {
		seats = 0;
		seatPercentage = 0.0;
		seatCost = 0.0;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("seats: ").append(seats).append('\n');
		sb.append("seat percentage: ").append(seatPercentage).append('\n');
		sb.append("seat cost: ").append(seatCost).append('\n');
		sb.append("diff votes last seat: ").append(diffVotesForLastSeat).append('\n');
		return sb.toString();
	}

}
