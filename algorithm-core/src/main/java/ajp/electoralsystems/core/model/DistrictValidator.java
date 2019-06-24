package ajp.electoralsystems.core.model;

import ajp.electoralsystems.core.exception.InvalidDistrictException;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictValidator {

	public void validate(District district) throws InvalidDistrictException {
		
		if (isEmpty(district.getName())) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_NAME);
		}
		if (isEmpty(district.getCensus())) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_CENSUS);
		}
		if (isEmpty(district.getSeats())) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_SEATS);
		}
		if (district.getTotalVotes() > district.getCensus()) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_TOTALVOTES_BIGGER_CENSUS);

		} else if (district.getSeats() > district.getCensus()) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_TOTALSEATS_BIGGER_CENSUS);

		} else if (district.getNumberOfParties() > district.getCensus()) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_NUMBEROFPARTIES_BIGGER_CENSUS);

		} else if (district.getParties() == null || district.getParties().length == 0) {
			throw new InvalidDistrictException(InvalidDistrictException.ERROR_NO_PARTIES);
		}
		
		for (int i = 0; i < district.getParties().length; i++) {
			Party party = district.getParties()[i];
			if (isEmpty(party.getName()) || isEmpty(party.getVotes())) {
				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_PARTY);
			}
		}
	}
	
	private boolean isEmpty(Object object) {
		return object == null || object.toString().trim().length()==0;
	}

}