package ajp.electoralsystems.app.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictFileReader {

    /**
     * Reads a district from the file
     * 
     * @param file
     * @return a District object
     * @throws AppException
     */
    public District read(File file) throws IOException {
    	District district = null;
		String str = new String(Files.readAllBytes(file.toPath()));    	
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		district = mapper.readValue(str, District.class);
		
		//validate district
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();
	    Set<ConstraintViolation<District>> constraintViolations = validator.validate(district);
	    if(constraintViolations.size() > 0){
	    	String msg = constraintViolations.stream().map(c -> Messages.getString(c.getMessage())).collect(Collectors.joining(System.lineSeparator()));	    	
	    	throw new AppException(msg);
	    }
		
		return district;		
    }

}
