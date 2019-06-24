package ajp.electoralsystems.core.model;

/**
 * @author Andres Jimenez Penalver
 */
public class PartyUtils {

	private PartyUtils() {		
	}
	
	/**
	 * Returns an String array with incremental values starting with "0"
	 * 
	 * @param size the size array
	 * @return String[]
	 */	
	public static String[] getIncrementalArray(int size) {
		String[] array = new String[size];
		for (int i=0; i<size; i++) {
			array[i] = String.valueOf(i+1);
		}
		return array;
	}
	
	/**
	 * Returns a matrix with the party names as first array and
	 * party seats as second array
	 * Only returns parties with one seat at least
	 * 
	 * @param parties
	 * @param map
	 * @return Object[][]
	 */
	public static Object[][] getPartyNameAndSeatArray(DistrictResult districResult) {
       int n = districResult.getParties().size();
       int n2 = 0;
       for (PartyResult result : districResult.getParties()) {
    	   if (result.getSeats() > 0) {
    		   n2++;
    	   }
	   }
       
       Object[][] array = new Object[2][n2];
       String[] nameArray = new String [n2];
       Integer[] seatArray = new Integer[n2];
       array[0] = nameArray;
       array[1] = seatArray;

       int i=0;
       for (PartyResult result : districResult.getParties()) {
           nameArray[i] = result.getParty().getName();
           seatArray[i] = result.getSeats();
           i++;
           if (i==n2) {
        	   break;
           }
	   }

       return array;
	}
	
	/**
	 * Returns an array with the party names
	 * 
	 * @param parties
	 * @return String[]
	 */
	public static String[] getPartyNameArray(Party[] parties) {
		int n = parties.length;
		String[] array = new String[n];
		for (int i = 0; i < n; i++) {
			array[i] = parties[i].getName();
		}	
		return array;
	}		
	
	/**
	 * Returns an array with the party vote percentages
	 * 
	 * @param parties
	 * @return double[]
	 */
	public static double[] getPartyVotePercentageArray(Party[] parties) {
		int n = parties.length;
		double[] array = new double[n];
		for (int i = 0; i < n; i++) {
			array[i] = parties[i].getVotePercentage();
		}		
		return array;
	}	
	
}
