package ajp.electoralsystems.algorithm.highestaverage.model;

import ajp.electoralsystems.core.model.algorithm.StepAlgorithm;

/**
 * @author Andres Jimenez Penalver
 */
//TODO: i18n
@StepAlgorithm(step="Se dispone una tabla con ne columnas y np filas. En las filas estan los partidos politicos, ordenados en orden decreciente segun el numero de votos. En las columnas estan los escaños a distribuir")
@StepAlgorithm(step="Cada celda (i, j) corresponde al resultado de dividir el nº de votos del partido politico que esta en la fila i-esima por el nº de escaño correspondiente a la columna j-esima.")
@StepAlgorithm(step="Las ne celdas que tengan mayor valor son las que han obtenido escaño.")
public class DhontAlgorithm extends HighestAverageAlgorithm {
	
	public DhontAlgorithm() {
		super();
	}
	
	public String getName() {
		return "DHONT";
	}
		
	public float getQuota(long votes, int seats) {
		float quota = (float) votes / (float) (seats +1);
		return quota;
	}
	
}