package ajp.electoralsystems.core.model.algorithm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class AlgorithmConfig {

	private @Getter @Setter boolean abstentionVotesAsParty=false;
	private @Getter @Setter boolean blankVotesAsParty=false;
	private @Getter @Setter boolean invalidVotesAsParty=false;
	private @Getter @Setter int voteThreshold=3;
	
	public void toggleAbstentionVotesAsParty() {
		this.abstentionVotesAsParty = !this.abstentionVotesAsParty;
	}

	public void toggleBlankVotesAsParty() {
		this.blankVotesAsParty = !this.blankVotesAsParty;
	}


	public void toggleInvalidVotesAsParty() {
		this.invalidVotesAsParty = !this.invalidVotesAsParty;
	}
	
	public boolean isAnyonePhantomPartyEnabled() {
		return isAbstentionVotesAsParty() || isBlankVotesAsParty() || isInvalidVotesAsParty();
	}
	
}
