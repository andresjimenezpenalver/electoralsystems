package ajp.electoralsystems.algorithm.dhont.model;

import java.text.ParseException;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.PartyResult;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.utils.NumberUtils;

/**
 * @author Andres Jimenez Penalver
 */
public class DhontSlowAlgorithm extends AbstractAlgorithm {
	
	public DhontSlowAlgorithm() {
		super();
	}
		
	public String getName() {
		return "DHONT";
	}
	
	public AlgorithmResult apply(AlgorithmConfig config, District district) throws AppException {
		DhontAlgorithmResult algorithmResult = new DhontAlgorithmResult(this.getClass(), district);
		calculateAssignmentTable(algorithmResult);
		calculateSeats(algorithmResult);				
		return algorithmResult;
	}	
	
	private void calculateAssignmentTable(DhontAlgorithmResult algorithmResult) {
		District district = algorithmResult.getDistrict();
		if (district != null) {
			int numberOfParties = district.getNumberOfParties();
			int numberOfSeats = district.getSeats();
			
			algorithmResult.setAssignmentTable(new float[numberOfParties][numberOfSeats]);
			algorithmResult.setWinnerAssignmentTable(new boolean[numberOfParties][numberOfSeats]);
			algorithmResult.setSeatCostArray(new float[numberOfSeats]);
										
			for (int i = 0; i < numberOfParties; i++) {
				for (int j = 0; j < numberOfSeats; j++) {
					try {
						String s = NumberUtils.getNumberFormat().format(district.getParties()[i].getVotes().floatValue() / (j + 1));							
						Float f = NumberUtils.getNumberFormat().parse(s).floatValue();
						algorithmResult.getAssignmentTable()[i][j] = f;
						algorithmResult.getWinnerAssignmentTable()[i][j] = false;
						
					} catch (ParseException e) {
						throw new AppException(e);
					}					
				}
			}	
		}
	}
	
	private void calculateSeats(DhontAlgorithmResult algorithmResult) {
		int i;
		int j;
		int im;
		int jm;
		float bigger;
		District district = algorithmResult.getDistrict();
		
		if (district != null) {
			int numberOfParties = district.getNumberOfParties();
			int numberOfSeats = district.getSeats();
			
			for (int it = 0; it < numberOfSeats; it++) {
				bigger = 0;
				im = 0;
				jm = 0;
				for (i = 0; i < numberOfParties; i++) {
					for (j = 0; j < numberOfSeats; j++) {
						if (algorithmResult.getWinnerAssignmentTable()[i][j] == false
							&& algorithmResult.getAssignmentTable()[i][j] > bigger) {
							bigger = algorithmResult.getAssignmentTable()[i][j];
							im = i;
							jm = j;
						}
					}
				}
				algorithmResult.getWinnerAssignmentTable()[im][jm] = true;
				algorithmResult.getSeatCostArray()[it] = algorithmResult.getAssignmentTable()[im][jm];
			}
			
			for (i = 0; i < numberOfParties; i++) {
				int partySeats = 0;
				for (j = 0; j < numberOfSeats; j++) {
					if (algorithmResult.getWinnerAssignmentTable()[i][j] == true) {
						partySeats += 1;
					} else {
						break;
					}
				}					
				Party party = district.getParties()[i];
				PartyResult partyResult = new PartyResult(party);
				if (partySeats > 0) {
					partyResult.setSeats(partySeats);
					partyResult.setSeatPercentage(new Double((partySeats*100)/district.getSeats()));
					partyResult.setSeatCost(new Double(party.getVotes()/partyResult.getSeats()));					
				}
				algorithmResult.addPartyResult(partyResult);
			}							
		}
	}
	
}
