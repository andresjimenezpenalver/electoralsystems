package ajp.electoralsystems.algorithm.quota.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;

/**
 * @author Andres Jimenez Penalver
 */
public abstract class QuotaAlgorithm extends AbstractAlgorithm {
	
	public QuotaAlgorithm() {
		super();
	}
		
	public abstract int getQuota(long validVotes, int seats);
			
	public AlgorithmResult apply(AlgorithmConfig config, District district) throws AppException {
		assert(config != null);
		assert(district != null);			
			
		int quota = getQuota(district.getTotalValidVotesOverThreshold(config.getVoteThreshold()), district.getSeats());
		
		QuotaAlgorithmResult algorithmResult = new QuotaAlgorithmResult(this.getClass(), district);		
		algorithmResult.setQuota(quota);
		
		List<PartyResultWithResidualVotes> partyResults = new ArrayList<PartyResultWithResidualVotes>();
		int assignedSeats = distributeSeats(config, district, quota, algorithmResult, partyResults);
		distributeSeatsFromResidualVotes(district.getSeats(), assignedSeats, partyResults);			
	
		return algorithmResult;	
	}

	private int distributeSeats(AlgorithmConfig config, District district, int quota,
			QuotaAlgorithmResult algorithmResult, List<PartyResultWithResidualVotes> partyResults) {
		int assignedSeats = 0;
		for (int i = 0; i < district.getNumberOfParties(); i++) {
			Party party = district.getParties()[i];
			PartyResultWithResidualVotes partyResult = new PartyResultWithResidualVotes(party);				
			if (party.isThresholdPassed(config.getVoteThreshold()) && party.getVotes() > 0) {
				int seats = Math.round(party.getVotes() / quota);
				int residualVotes = (int) (party.getVotes() - (quota*seats));
				assignedSeats += seats;
				
				partyResult.setSeats(seats);
				partyResult.setResidualVotes(residualVotes);										
			}	
			partyResults.add(partyResult);
			
			algorithmResult.addPartyResult(partyResult);			
		}
		return assignedSeats;
	}

	private void distributeSeatsFromResidualVotes(int seats, int assignedSeats, List<PartyResultWithResidualVotes> partyResults) {
		Collections.sort(partyResults, new PartyResultWithResidualVotesComparator());			
		for (int i = 0; i < (seats-assignedSeats); i++) {
			partyResults.get(i).incrementSeats();
		}
	}
	
}