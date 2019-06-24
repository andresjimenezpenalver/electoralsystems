package ajp.electoralsystems.core.model.algorithm;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictResult;
import ajp.electoralsystems.core.view.algorithm.AlgorithmPanelUI;

/**
 * @author Andres Jimenez Penalver
 */
public interface AlgorithmResult {

	public Class<? extends Algorithm> getAlgorithmProviderClass();
		
	public District getDistrict();
	
	public DistrictResult getResults();
	
	public long getCalculationTime();
	
	public void setCalculationTime(long millis);
	
	public AlgorithmPanelUI getUI();
	
}
