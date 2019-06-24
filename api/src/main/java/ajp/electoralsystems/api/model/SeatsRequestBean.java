package ajp.electoralsystems.api.model;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;

/**
 * @author Andres Jimenez Penalver
 */
public class SeatsRequestBean {

	private AlgorithmConfig config;
	private District district;
	
	public SeatsRequestBean() {		
	}
	
	public SeatsRequestBean(AlgorithmConfig config, District district) {
		setConfig(config);
		setDistrict(district);
	}
	
	public AlgorithmConfig getConfig() {
		return config;
	}
	public void setConfig(AlgorithmConfig config) {
		this.config = config;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	
}
