package ajp.electoralsystems.algorithm.quota.model;

import java.beans.Transient;

import ajp.electoralsystems.algorithm.quota.view.QuotaPanelUI;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.algorithm.AbstractAlgorithmResult;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.view.algorithm.AlgorithmPanelUI;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class QuotaAlgorithmResult extends AbstractAlgorithmResult {
	
	private @Getter @Setter Integer quota;
	
	public QuotaAlgorithmResult(Class<? extends Algorithm> algorithmProviderClass, District district) {
		super(algorithmProviderClass, district);
	}		
	
	@Transient
	public AlgorithmPanelUI getUI() {
		return new QuotaPanelUI();
	}
	
}
