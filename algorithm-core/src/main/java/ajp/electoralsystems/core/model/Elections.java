package ajp.electoralsystems.core.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class Elections {

	private @Getter @Setter Set<District> districts = new HashSet<District>();
	private @Getter @Setter Map<String, DistrictAlgorithmResult> results = new HashMap<String, DistrictAlgorithmResult>();
		
	public void addDistrictAlgorithmResult(District district, DistrictAlgorithmResult dar) {		
		districts.add(district);
		results.put(district.getName(), dar);
	}
		
}
