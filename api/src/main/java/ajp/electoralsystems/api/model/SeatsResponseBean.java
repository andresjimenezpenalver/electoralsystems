package ajp.electoralsystems.api.model;

import ajp.electoralsystems.core.model.Elections;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class SeatsResponseBean {

	private @Getter @Setter Elections elections; 
	
	public SeatsResponseBean(Elections elections) {
		setElections(elections);
	}
		
}
