package ajp.electoralsystems.core.model;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictAlgorithmResult {

	private @Getter @Setter Map<Algorithm, AlgorithmResult> algorithms = new HashMap<Algorithm, AlgorithmResult>();
	
	public void addAlgorithmResult(Algorithm algorithm, AlgorithmResult result) {
		algorithms.put(algorithm, result);
	}
	
	@Transient
	public Set<Algorithm> getAlgorithmsApplied() {
		return algorithms.keySet();
	}
	
	public AlgorithmResult getAlgorithmResult(Algorithm algorithm) {
		return algorithms.get(algorithm);
	}
	
}
