package ajp.electoralsystems.algorithm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.exception.NotFoundAlgorithmException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictAlgorithmResult;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmConfig;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Andres Jimenez Penalver
 */
@Slf4j
public class AlgorithmEngine {

	private List<Algorithm> algorithms = new ArrayList<>();
	private AlgorithmConfig config = new AlgorithmConfig();
	
	public AlgorithmEngine() {
		log.info("AlgorithmEngine startup with " + algorithms.size() + " algorithms");
	}
	
	private void addPhantomPartiesAndSort(AlgorithmConfig config, District district) {
		TreeSet<Party> tree = new TreeSet<>();
		Party[] parties = district.getParties();
		for (Party party : parties) {
			tree.add(party);					
		}
		if (config.isAbstentionVotesAsParty()) {
			tree.add(district.getAbstentionParty());					
		}	
		if (config.isBlankVotesAsParty()) {
			tree.add(district.getBlankVotesParty());					
		}	
		if (config.isInvalidVotesAsParty()) {
			tree.add(district.getInvalidVotesParty());					
		}	
		Party[] newparties = new Party[tree.size()];
		tree.toArray(newparties);
		district.setParties(newparties);		
		district.updatePartyPercentage();	
		district.updatePartyPercentageOverCensus();
	}	

	public DistrictAlgorithmResult execute(District district) throws AppException {
		return execute(getConfig(), district);
	}
	
	public DistrictAlgorithmResult execute(AlgorithmConfig configuration, District district) throws AppException {
		if (configuration == null) {
			configuration = getConfig();
		}
		if (algorithms == null || algorithms.size() == 0) {
			new AppException("No algorithms!");
		}
		addPhantomPartiesAndSort(configuration, district);
		DistrictAlgorithmResult dar = new DistrictAlgorithmResult();
		for (Algorithm algorithm : algorithms) {
			if (algorithm.isEnabled()) {
				long s = (System.currentTimeMillis());				
				AlgorithmResult algorithmResult = algorithm.apply(configuration, district);
				dar.addAlgorithmResult(algorithm, algorithmResult);
				long e = (System.currentTimeMillis());
				log.info("execution time of algorithm " + algorithm.getClass().getName() + " was " + (e-s) + " millis");
				algorithmResult.setCalculationTime((e-s));
				
			}else {
				log.info("skipping algorithm " + algorithm.getName());
			}
		}
		return dar;
	}	
	
	private Algorithm getAlgorithm(String id) {
		for (Algorithm algorithm : algorithms) {
			if (algorithm.getId().equals(id)) {
				return algorithm;
			}
		}
		throw new NotFoundAlgorithmException(id);
	}

	public List<Algorithm> getAlgorithms() {
		return algorithms;
	}
	
	public AlgorithmConfig getConfig() {
		return config;
	}
		
	public void disableAllAlgorithms(List<Algorithm> algorithms) {
		setEnabledAlgorithms(algorithms, false);
	}		
	
	public Algorithm disableAlgorithm(Algorithm algorithm) {
		algorithm.setEnabled(false);
		return algorithm;
	}

	public Algorithm disableAlgorithm(String id) {
		return setEnabledAlgorithm(id, false);
	}
	
	public void enableAllAlgorithms(List<Algorithm> algorithms) {
		setEnabledAlgorithms(algorithms, true);
	}	
	
	public Algorithm enableAlgorithm(Algorithm algorithm) {
		algorithm.setEnabled(true);
		return algorithm;
	}

	public Algorithm enableAlgorithm(String id) {
		return setEnabledAlgorithm(id, true);		
	}
	
	public boolean hasSomeoneAlgorithmEnabled() {
		for (Algorithm algorithm : algorithms) {
			if (algorithm.isEnabled()) {
				return true;
			}
		}
		return false;
	}	

	public void setAlgorithms(List<Algorithm> algorithms) {
		int size = algorithms == null ? 0 : algorithms.size();
		log.info("setting " + size + " algorithms");
		this.algorithms = algorithms;
		enableAllAlgorithms(algorithms);
	}
	
	public Algorithm setEnabledAlgorithm(String id, boolean enabled) {
		Algorithm algorithm = getAlgorithm(id);
		algorithm.setEnabled(enabled);
		return algorithm;
	}
		
	private void setEnabledAlgorithms(List<Algorithm> algorithms, boolean enabled) {
		for (Algorithm algorithm : algorithms) {
			algorithm.setEnabled(enabled);
		}
	}	
		
}
