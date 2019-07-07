package ajp.electoralsystems.core.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ajp.electoralsystems.core.exception.InvalidDistrictException;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictValidator implements ConstraintValidator<DistrictValid, District> {

    @Override
    public void initialize(DistrictValid constraintAnnotation) {
    }
	
	@Override
	public boolean isValid(District district, ConstraintValidatorContext context) {
		boolean valid=true;
		context.disableDefaultConstraintViolation();		
		
		if (district.getCensus() == 0) {
			valid =false;
			context.buildConstraintViolationWithTemplate(InvalidDistrictException.ERROR_INVALID_CENSUS).addConstraintViolation();
		} 
		if (district.getTotalValidVotes() == 0) {
			valid =false;
			context.buildConstraintViolationWithTemplate(InvalidDistrictException.ERROR_NO_VALID_VOTES).addConstraintViolation();
		}
		if (district.getTotalVotes() > district.getCensus()) {
			valid =false;
			context.buildConstraintViolationWithTemplate(InvalidDistrictException.ERROR_TOTALVOTES_BIGGER_CENSUS).addConstraintViolation();
		} 
		if (district.getSeats() > district.getCensus()) {
			valid =false;
			context.buildConstraintViolationWithTemplate(InvalidDistrictException.ERROR_TOTALSEATS_BIGGER_CENSUS).addConstraintViolation();
		} 
		if (district.getNumberOfParties() > district.getCensus()) {
			valid =false;
			context.buildConstraintViolationWithTemplate(InvalidDistrictException.ERROR_NUMBEROFPARTIES_BIGGER_CENSUS).addConstraintViolation();
		}
		
		return valid;
	}

}