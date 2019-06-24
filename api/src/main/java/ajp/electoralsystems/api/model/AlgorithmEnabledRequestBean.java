package ajp.electoralsystems.api.model;

/**
 * @author Andres Jimenez Penalver
 */
public class AlgorithmEnabledRequestBean {

	private String id;
	private boolean enabled;
	
	public AlgorithmEnabledRequestBean() {		
	}
	
	public AlgorithmEnabledRequestBean(String id, boolean enabled) {
		setId(id);
		setEnabled(enabled);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean status) {
		this.enabled = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
