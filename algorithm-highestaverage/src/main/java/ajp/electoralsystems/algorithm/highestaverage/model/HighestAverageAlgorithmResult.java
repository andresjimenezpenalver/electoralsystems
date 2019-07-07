package ajp.electoralsystems.algorithm.highestaverage.model;

import java.beans.Transient;

import ajp.electoralsystems.algorithm.highestaverage.view.HighestAveragePanelUI;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithmResult;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.view.algorithm.AlgorithmPanelUI;

/**
 * @author Andres Jimenez Penalver
 */
public class HighestAverageAlgorithmResult extends AbstractAlgorithmResult {

	/**
	 * Array bidimensional que almacena booleanos que indican que celdas
	 * de la tabla tablaAsignacion han conseguido escaño
	 */
	
	private boolean winnerAssignmentTable[][];
	/**
	 * Array bidimensional cuyas celdas se corresponden con el dato
	 * numero de votos/numero de escaño
	 */
	private float assignmentTable[][];
	/**
	 * Array que almacena los numero de votos necesarios para la 
	 * consecucion de cada uno de los escaños	
	 */
	private float[] seatCostArray;

	public HighestAverageAlgorithmResult(Class<? extends Algorithm> algorithmProviderClass, District district) {
		super(algorithmProviderClass, district);
	}	
	
	@Transient	
	public float[] getSeatCostArray() {
		return seatCostArray;
	}

	@Transient	
	public float[][] getAssignmentTable() {
		return assignmentTable;
	}
	
	@Transient
	public boolean[][] getWinnerAssignmentTable() {
		return winnerAssignmentTable;
	}

	public void setSeatCostArray(float[] seatCostArray) {
		this.seatCostArray = seatCostArray;
	}

	public void setAssignmentTable(float[][] assignmentTable) {
		this.assignmentTable = assignmentTable;
	}

	public void setWinnerAssignmentTable(boolean[][] winnerAssignmentTable) {
		this.winnerAssignmentTable = winnerAssignmentTable;
	}

	@Transient
	public AlgorithmPanelUI getUI() {
		return new HighestAveragePanelUI();
	}
	
}
