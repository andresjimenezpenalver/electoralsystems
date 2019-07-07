package ajp.electoralsystems.algorithm.highestaverage.model;

import java.util.Arrays;

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
		HighestAverageAlgorithmResult algorithmResult = new HighestAverageAlgorithmResult(this.getClass(), district);		
			
		if (district != null) {
			int numberOfParties = district.getNumberOfParties();
			int numberOfSeats = district.getSeats();
			
			algorithmResult.setAssignmentTable(new float[numberOfParties][numberOfSeats]);
			algorithmResult.setSeatCostArray(new float[numberOfSeats]);
			algorithmResult.setWinnerAssignmentTable(new boolean[numberOfParties][numberOfSeats]);
			
			float[] cursor = new float[numberOfParties];
			int[] seats = new int[numberOfParties];
			Arrays.fill(seats, 0);
			for (int i = 0; i < cursor.length; i++) {
				cursor[i] = (float) district.getParties()[i].getVotes();
			}
			
			double threshold=config.getVoteThreshold();
			Party party;
			for (int i = 1; i <= numberOfSeats; i++) {				
				float largest = cursor[0];
				int index = 0;
				for (int j=1; j<cursor.length; j++) {
					party = district.getParties()[j];
					if (!party.isThresholdPassed(threshold) || party.getVotes() == 0) {
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
				seats[index]+=1;				
				//cursor[index]=party.getVotes()/(1+seats[index]);
				cursor[index]=getQuota(party.getVotes(), seats[index]);
			}
			
			for (int i=0; i < seats.length; i++) {
				int partySeats = seats[i];
				party = district.getParties()[i];
				PartyResult partyResult = new PartyResult(party);
				if (partySeats > 0) {
					partyResult.setSeats(partySeats);
					partyResult.setSeatPercentage(new Double((partySeats*100)/district.getSeats()));
					partyResult.setSeatCost(new Double(party.getVotes()/partyResult.getSeats()));					
				}
				algorithmResult.addPartyResult(partyResult);	
			}						
		}
		return algorithmResult;	
	}
	
}