package ajp.electoralsystems.algorithm.highestaverage.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.PartyResult;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;

/**
 * @author Andres Jimenez Penalver
 */
public abstract class HighestAverageAlgorithm extends AbstractAlgorithm {
	
	public HighestAverageAlgorithm() {
		super();
	}
			
	public abstract float getQuota(long votes, int seats);
	
	public AlgorithmResult apply(AlgorithmConfig config, District district) throws AppException {
		assert(config != null);
		assert(district != null);
			
		HighestAverageAlgorithmResult algorithmResult = initialize(district);
		
		int[] seats = new int[district.getNumberOfParties()];
		Arrays.fill(seats, 0);	
		List<PartyResult> partyResults = new ArrayList<PartyResult>();
		float[] cursor = new float[district.getNumberOfParties()];		
		for (int i = 0; i < district.getNumberOfParties(); i++) {
			Party party = district.getParties()[i];
			cursor[i] = (float) party.getVotes();
			PartyResult partyResult = new PartyResult(party); 
			partyResults.add(partyResult);
			algorithmResult.addPartyResult(partyResult);
		}
				
		Party party;
		PartyResult partyResult;
		
		float largest=0;
		for (int i = 1; i <= district.getSeats(); i++) {				
			largest = cursor[0];
			int index = 0;
			for (int j=1; j<cursor.length; j++) {
				party = district.getParties()[j];
				if (!party.isThresholdPassed(config.getVoteThreshold()) || party.getVotes() == 0) {
					break;
					
				} else if (cursor[j] > largest) {
					largest = cursor[j];
					index = j;
				}	
			}
			algorithmResult.getAssignmentTable()[index][seats[index]] = largest;
			algorithmResult.getSeatCostArray()[i-1] = largest;
			algorithmResult.getWinnerAssignmentTable()[index][seats[index]] = true;
			party=district.getParties()[index];
			partyResult = partyResults.get(index);			
			seats[index]+=1;				
			cursor[index]=getQuota(party.getVotes(), seats[index]);
			
			partyResult.setHasLastSeat(i == district.getSeats());			
		}
	
		for (int i=0; i < district.getNumberOfParties(); i++) {
			int partySeats = seats[i];
			party = district.getParties()[i];
			partyResult = partyResults.get(i);
			if (partySeats > 0) {
				partyResult.setSeats(partySeats);
				partyResult.setSeatPercentage(new Double((partySeats*100)/district.getSeats()));
				partyResult.setSeatCost(new Double(party.getVotes()/partyResult.getSeats()));					
			}
			if (partyResult.getHasLastSeat()) {
				partyResult.setDiffVotesForLastSeat(0);
				
			} else {
				int diff = (int) ((party.getVotes() / (partyResult.getSeats()+1)) - largest);				
				partyResult.setDiffVotesForLastSeat(diff);
			}
		}		
		
		return algorithmResult;	
	}

	private HighestAverageAlgorithmResult initialize(District district) {
		HighestAverageAlgorithmResult algorithmResult = new HighestAverageAlgorithmResult(this.getClass(), district);		
		algorithmResult.setAssignmentTable(new float[district.getNumberOfParties()][district.getSeats()]);
		algorithmResult.setSeatCostArray(new float[district.getSeats()]);
		algorithmResult.setWinnerAssignmentTable(new boolean[district.getNumberOfParties()][district.getSeats()]);
		return algorithmResult;
	}
	
}