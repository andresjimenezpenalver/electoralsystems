package ajp.electoralsystems.core.model;

import java.text.ParseException;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import ajp.electoralsystems.core.utils.NumberUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
@JsonRootName(value = "district")
public class District {
	
	private @Getter @Setter Long census = 0L;
	private @Getter @Setter String name = "";
	private @Getter @Setter Integer seats = 0;
	private @Getter Party parties[];
	private @Getter @Setter Long blankVotes=0L;
	private @Getter @Setter Long invalidVotes=0L;	
	
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
				String s = NumberUtils.getNumberFormat().format(100.0*party.getVotes()/ totalVotes);							
				Double f = NumberUtils.getNumberFormat().parse(s).doubleValue();				
				party.setVotePercentage(f);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@JsonIgnore
	public long getTotalVotes() {
		long total = 0;
		if (parties != null) {
			for (int i=0; i<parties.length; i++) {
				if (parties[i].getVotes() != null) {
					total += parties[i].getVotes().intValue();
				} 				
			}
		}
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
	public Party getBlankVotesParty() {
		Party party = new Party();
		party.setName("BLANKVOTES PARTY");
		party.setVotes(getBlankVotes());
		party.setVotePercentage(new Double(100*getBlankVotes())/census);
		return party;
	}
	
	@JsonIgnore
	public Party getInvalidVotesParty() {
		Party party = new Party();
		party.setName("INVALIDVOTES PARTY");
		party.setVotes(getInvalidVotes());
		party.setVotePercentage(new Double(100*getInvalidVotes())/census);
		return party;
	}

	@JsonIgnore
	public Party getAbstentionParty() {
		Party party = new Party();
		party.setName("ABSTENTION PARTY");
		party.setVotes(census - getTotalVotes());
		party.setVotePercentage(new Double(100*(census-getTotalVotes())/census));
		return party;
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
