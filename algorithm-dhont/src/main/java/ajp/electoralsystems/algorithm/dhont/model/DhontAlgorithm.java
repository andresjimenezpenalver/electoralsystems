package ajp.electoralsystems.algorithm.dhont.model;

import java.util.Arrays;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.PartyResult;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.model.algorithm.StepAlgorithm;

/**
 * @author Andres Jimenez Penalver
 */
//TODO: i18n
@StepAlgorithm(step="Se dispone una tabla con ne columnas y np filas. En las filas estan los partidos politicos, ordenados en orden decreciente segun el numero de votos. En las columnas estan los escaños a distribuir")
@StepAlgorithm(step="Cada celda (i, j) corresponde al resultado de dividir el nº de votos del partido politico que esta en la fila i-esima por el nº de escaño correspondiente a la columna j-esima.")
@StepAlgorithm(step="Las ne celdas que tengan mayor valor son las que han obtenido escaño.")
public class DhontAlgorithm extends AbstractAlgorithm {
	
	public DhontAlgorithm() {
		super();
	}
	
	public String getName() {
		return "DHONT";
	}
		
	public AlgorithmResult apply(AlgorithmConfig config, District district) throws AppException {
		DhontAlgorithmResult algorithmResult = new DhontAlgorithmResult(this.getClass(), district);		
			
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
					if (!party.isThresholdPassed(threshold)) {
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
				cursor[index]=party.getVotes()/(1+seats[index]);				
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