package ajp.electoralsystems.core.model.algorithm;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public abstract class AbstractAlgorithm implements Algorithm {

	private @Getter @Setter boolean enabled;
	
	public String getProvider() {
		return this.getClass().getName();
	}
	
}
