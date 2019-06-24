package ajp.electoralsystems.app.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;

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
		return district;		
    }

}
