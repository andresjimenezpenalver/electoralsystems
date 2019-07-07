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
		QuotaAlgorithmResult algorithmResult = new QuotaAlgorithmResult(this.getClass(), district);		
			
		if (district != null) {
			int numberOfParties = district.getNumberOfParties();
			int numberOfSeats = district.getSeats();
			double threshold=config.getVoteThreshold();				
			int quota = getQuota(district.getTotalValidVotesOverThreshold(threshold), numberOfSeats);			
			algorithmResult.setQuota(quota);
			
			List<PartyResultWithResidualVotes> partyResults = new ArrayList<PartyResultWithResidualVotes>();
			int assignedSeats = 0;
			for (int i = 0; i < numberOfParties; i++) {
				Party party = district.getParties()[i];
				PartyResultWithResidualVotes partyResult = new PartyResultWithResidualVotes(party);				
				if (party.isThresholdPassed(threshold) && party.getVotes() > 0) {
					int seats = Math.round(party.getVotes() / quota);
					int residualVotes = (int) (party.getVotes() - (quota*seats));
					assignedSeats+=seats;
					
					partyResult.setSeats(seats);
					partyResult.setResidualVotes(residualVotes);										
				}	
				partyResults.add(partyResult);				
				algorithmResult.addPartyResult(partyResult);			
			} 	
			
			Collections.sort(partyResults, new PartyResultWithResidualVotesComparator());			
			for (int i = 0; i < (numberOfSeats-assignedSeats); i++) {
				partyResults.get(i).incSeats();
			}			
		}
		return algorithmResult;	
	}
	
}