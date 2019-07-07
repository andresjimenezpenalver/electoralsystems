package ajp.electoralsystems.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;

/**
 * @author Andres Jimenez Penalver
 */
public class SeatsRequestBean {

	@Valid
	private AlgorithmConfig config;
	@Valid
	@NotNull	
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
