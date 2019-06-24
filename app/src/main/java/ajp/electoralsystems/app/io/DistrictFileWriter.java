package ajp.electoralsystems.app.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;

/**
 * @author Andres Jimenez Penalver
 */
public class DistrictFileWriter {

    /**
     * Writes the district in the file
     * 
     * @param district 
     * @param file 
     * @throws AppException
     */
    public void write(District district, File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		String result = mapper.writeValueAsString(district);
    	Files.write(file.toPath(), result.getBytes());        	
    }
}
