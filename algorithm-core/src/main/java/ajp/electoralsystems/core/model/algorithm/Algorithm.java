package ajp.electoralsystems.core.model.algorithm;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;

/**
 * @author Andres Jimenez Penalver
 */
public interface Algorithm {

	AlgorithmResult apply(AlgorithmConfig config, District district) throws AppException;
	String getName();	
	String getProvider();	
	boolean isEnabled();
	void setEnabled(boolean enabled);
	
	default public String getId() {
		return getName() + "-" + getProvider();
	}	
	
}
