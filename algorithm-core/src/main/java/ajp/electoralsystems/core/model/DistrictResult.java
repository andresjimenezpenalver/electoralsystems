package ajp.electoralsystems.core.model;

import java.util.Set;
import java.util.TreeSet;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictResult {

	private @Getter @Setter Set<PartyResult> parties = new TreeSet<PartyResult>();
	
	public void addPartyResult(PartyResult partyResult) {
		parties.add(partyResult);
	}
	
	public PartyResult getPartyResult(Party party) {
		for (PartyResult partyResult : parties) {
			if (party.equals(partyResult.getParty())) {
				return partyResult;
			}
		}
		return null;
	}
	
}
