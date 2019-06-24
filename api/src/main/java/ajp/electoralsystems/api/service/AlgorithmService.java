package ajp.electoralsystems.api.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ajp.electoralsystems.algorithm.engine.AlgorithmEngine;
import ajp.electoralsystems.api.model.AlgorithmEnabledRequestBean;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Service
@Slf4j
public class AlgorithmService {

	@Resource
	private @Getter @Setter AlgorithmEngine algorithmEngine;
	
	public List<Algorithm> getAlgorithms() {
		log.info("getAlgorithms");
		return algorithmEngine.getAlgorithms();
	}	

	public Algorithm setEnabledAlgorithm(AlgorithmEnabledRequestBean algorithmBean) {
		log.info("setEnabledAlgorithm");
		return algorithmEngine.setEnabledAlgorithm(algorithmBean.getId(), algorithmBean.isEnabled());
	}	
	
}
