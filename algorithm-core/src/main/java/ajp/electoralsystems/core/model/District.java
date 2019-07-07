package ajp.electoralsystems.core.model;

import java.text.ParseException;
import java.util.Arrays;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import ajp.electoralsystems.core.utils.NumberUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
@JsonRootName(value = "district")
@DistrictValid
public class District {
	
	@NotNull
	private @Getter @Setter Long census;
	
	@NotEmpty(message = "Error.InvalidDistrictData.InvalidName")	
	private @Getter @Setter String name;
	
	@NotNull	
	private @Getter @Setter Integer seats;
	
	@Valid @NotEmpty(message="Error.InvalidDistrictData.NoParties")
	private @Getter Party parties[];
	
	@NotNull	
	private @Getter @Setter Long blankVotes;
	
	@NotNull	
	private @Getter @Setter Long invalidVotes;	
	
	public District() {
	}
	
	public void updatePartyPercentageOverCensus() {
		for (Party party : parties) {
			try {
				String s = NumberUtils.getNumberFormat().format(100.0*party.getVotes()/ census);							
				Double f = NumberUtils.getNumberFormat().parse(s).doubleValue();				
				party.setVotePercentageOverCensus(f);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void updatePartyPercentage() {
		long totalVotes = getTotalVotes();
		for (Party party : parties) {
			try {
				if (totalVotes == 0) {
					party.setVotePercentage(0.0);
					
				} else {
					String s = NumberUtils.getNumberFormat().format(100.0*party.getVotes()/ totalVotes);							
					Double f = NumberUtils.getNumberFormat().parse(s).doubleValue();				
					party.setVotePercentage(f);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@JsonIgnore
	public long getTotalValidVotesOverThreshold(double threshold) {
		long total = 0;		
		if (parties != null) {
			for (int i=0; i<parties.length; i++) {
				if (parties[i].isThresholdPassed(threshold) && parties[i].getVotes() != null) {
					total += parties[i].getVotes().intValue();
				} 				
			}
		}		
		return total;
	}
	
	@JsonIgnore
	public long getTotalValidVotes() {
		long total = 0;		
		if (parties != null) {
			for (int i=0; i<parties.length; i++) {
				if (parties[i].getVotes() != null) {
					total += parties[i].getVotes().intValue();
				} 				
			}
		}		
		return total;
	}
	
	@JsonIgnore
	public long getTotalVotes() {
		long total = 0;
		total+=getTotalValidVotes();
		total+=getBlankVotes();
		total+=getInvalidVotes();		
		return total;
	}
	
	public boolean hasParties() {
		return parties != null && parties.length > 0;
	}
	

	public void setParties(Party[] parties) {		
		this.parties = parties;
		Arrays.sort(this.parties);
	}
	
	@JsonIgnore
	private Party getPhantomParty(String name, Long votes) {
		Party party = new Party();
		party.setName(name);
		party.setVotes(votes);
		party.setVotePercentage(new Double(100*votes)/census);
		return party;
	}
	
	@JsonIgnore
	public Party getBlankVotesParty() {
		return getPhantomParty("BLANKVOTES PARTY", getBlankVotes());
	}
	
	@JsonIgnore
	public Party getInvalidVotesParty() {
		return getPhantomParty("INVALIDVOTES PARTY", getInvalidVotes());
	}

	@JsonIgnore
	public Party getAbstentionParty() {
		return getPhantomParty("ABSTENTION PARTY", census - getTotalVotes());
	}
	
	@JsonIgnore
	public Double getAbstention() {
		return 100 - getTurnout();
	}
	
	@JsonIgnore
	public Double getTurnout() {
		double turnout = 100*((double) getTotalVotes())/getCensus();
		return turnout;
	}

	@JsonIgnore	
	public Integer getNumberOfParties() {
		return getParties() == null ? 0 : getParties().length;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name: ").append(name).append('\n');
		sb.append("census: ").append(census).append('\n');
		sb.append("seats: ").append(seats).append('\n');
		sb.append("blankVotes: ").append(blankVotes).append('\n');
		sb.append("invalidVotes: ").append(invalidVotes).append('\n');
		sb.append("parties: ").append('\n');
		if (parties != null) {
			for (int i = 0; i < parties.length; i++) {
				sb.append("party: ").append(parties[i]).append('\n');
			}
		}
		sb.append("abstention: ").append(getAbstention()).append('\n');
		sb.append("turnout: ").append(getTurnout()).append('\n');
		return sb.toString();		
	}
	
}
