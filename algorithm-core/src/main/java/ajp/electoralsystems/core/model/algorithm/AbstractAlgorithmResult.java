package ajp.electoralsystems.core.model.algorithm;

import java.beans.Transient;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictResult;
import ajp.electoralsystems.core.model.PartyResult;
import ajp.electoralsystems.core.view.algorithm.AlgorithmPanelUI;
import ajp.electoralsystems.core.view.algorithm.DefaultPanelUI;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public abstract class AbstractAlgorithmResult implements AlgorithmResult {

	private @Getter @Setter long calculationTime;
	private Class<? extends Algorithm> algorithmProviderClass;
	private District district;	
	private @Getter @Setter DistrictResult results = new DistrictResult();
	
	public AbstractAlgorithmResult(Class<? extends Algorithm> algorithmProviderClass, District district) {
		setAlgorithmProviderClass(algorithmProviderClass);
		setDistrict(district);
	}
	
	public Class<? extends Algorithm> getAlgorithmProviderClass() {
		return algorithmProviderClass;
	}

	public void setAlgorithmProviderClass(Class<? extends Algorithm> algorithmProviderClass) {
		this.algorithmProviderClass = algorithmProviderClass;
	}
	
	@Transient
	public District getDistrict() {
		return district;	
	}
	public void setDistrict(District district) {
		this.district = district;		
	}

	public void addPartyResult(PartyResult partyResult) {		
		getResults().addPartyResult(partyResult);
	}
	
	public AlgorithmPanelUI getUI() {
		return new DefaultPanelUI();
	}
}
