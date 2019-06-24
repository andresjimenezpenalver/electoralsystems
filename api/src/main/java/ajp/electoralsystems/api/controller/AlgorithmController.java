package ajp.electoralsystems.api.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ajp.electoralsystems.api.model.AlgorithmEnabledRequestBean;
import ajp.electoralsystems.api.service.AlgorithmService;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Andres Jimenez Penalver
 */
@RestController
@RequestMapping("algorithms")
@EnableSwagger2
@Slf4j
public class AlgorithmController {
	
	@Autowired AlgorithmService service;
	
	@GetMapping
	public List<Algorithm> getAlgorithms() {
		log.info("getAlgorithms");
		List<Algorithm> response = service.getAlgorithms();
		return response;
	}	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PATCH)	
	public Algorithm setEnabledAlgorithm(@PathVariable @NotNull String id, @RequestBody AlgorithmEnabledRequestBean algorithmEnabledRequestBean) {
		log.info("setEnabledAlgorithm");
		algorithmEnabledRequestBean.setId(id);		
		Algorithm response = service.setEnabledAlgorithm(algorithmEnabledRequestBean);
		return response;
	}

}
