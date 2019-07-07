package ajp.electoralsystems.core.exception;

/**
 * @author Andres Jimenez Penalver
 */
public class InvalidDistrictException extends AppException {

	public static final String ERROR_TOTALVOTES_BIGGER_CENSUS = "Error.InvalidDistrictData.TotalVotesBiggerThanCensus";
	public static final String ERROR_TOTALSEATS_BIGGER_CENSUS = "Error.InvalidDistrictData.TotalSeatsBiggerThanCensus";
	public static final String ERROR_NUMBEROFPARTIES_BIGGER_CENSUS = "Error.InvalidDistrictData.NumberOfPartiesBiggerThanCensus";
	public static final String ERROR_NO_PARTIES = "Error.InvalidDistrictData.NoParties";
	public static final String ERROR_INVALID_NAME = "Error.InvalidDistrictData.InvalidName";
	public static final String ERROR_INVALID_CENSUS = "Error.InvalidDistrictData.InvalidCensus";
	public static final String ERROR_INVALID_SEATS = "Error.InvalidDistrictData.InvalidSeats";
	public static final String ERROR_INVALID_PARTY = "Error.InvalidDistrictData.InvalidParty";
	public static final String ERROR_NO_VALID_VOTES = "Error.InvalidDistrictData.NoValidVotes";
	
	private static final long serialVersionUID = -4783086316395982389L;	
	
	public InvalidDistrictException(String message) {
		super(message);
	}
	
}
