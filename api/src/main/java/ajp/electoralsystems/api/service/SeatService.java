package ajp.electoralsystems.api.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ajp.electoralsystems.algorithm.engine.AlgorithmEngine;
import ajp.electoralsystems.api.model.SeatsRequestBean;
import ajp.electoralsystems.api.model.SeatsResponseBean;
import ajp.electoralsystems.core.model.DistrictAlgorithmResult;
import ajp.electoralsystems.core.model.Elections;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Service
@Slf4j
public class SeatService {

	@Resource
	private @Getter @Setter AlgorithmEngine algorithmEngine;
	
	public SeatsResponseBean getSeats(SeatsRequestBean request) {
		log.info("service");
		DistrictAlgorithmResult dar = algorithmEngine.execute(request.getConfig(), request.getDistrict());
		
		Elections elections = new Elections();
		elections.addDistrictAlgorithmResult(request.getDistrict(), dar);
		
		SeatsResponseBean response = new SeatsResponseBean(elections);
		response.setElections(elections);
		return response;
	}	
	
}
