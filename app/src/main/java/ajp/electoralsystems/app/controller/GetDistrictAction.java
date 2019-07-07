package ajp.electoralsystems.app.controller;

import java.text.ParseException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.app.view.TileData;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.utils.NumberUtils;
import ajp.electoralsystems.i18n.Messages;

/**
 * @author Andres Jimenez Penalver
 */
public class GetDistrictAction implements Action {
	
	private MainWindow mainWindow;
	
	public GetDistrictAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	public Object execute(Object object) throws AppException {		
		District district = null;
		try {
			TileData dataTile = mainWindow.getDataTile();
			district = new District();
			
			//name 
			String name = dataTile.getTxtName().getText();			
			district.setName(name);
			
			//census
			String census = dataTile.getTxtCensus().getText();
			district.setCensus(census.isEmpty() ? 0 : new Long(census));
			
			//seats
			String seats = dataTile.getTxtSeats().getText();
			district.setSeats(seats.isEmpty() ? 0 : new Integer(seats));

			//blank votes
			String blankVotes = dataTile.getTxtBlankVotes().getText();
			district.setBlankVotes(blankVotes.isEmpty() ? 0 : new Long(blankVotes));
			
			//invalid votes
			String invalidVotes = dataTile.getTxtInvalidVotes().getText();
			district.setInvalidVotes(invalidVotes.isEmpty() ? 0 : new Long(invalidVotes));	
			
			//parties
			int numberOfParties = dataTile.getTblParties().getRowCount();
			Party[] parties = new Party[numberOfParties];
			int totalVotes = 0;
			for (int i = 0; i < numberOfParties; i++) {
				Party party = new Party();
				Object partyName = dataTile.getTblParties().getValueAt(i, 0);
				Object partyVotes = dataTile.getTblParties().getValueAt(i, 1);
				party.setName(partyName.toString());
				party.setVotes((partyVotes == null || partyVotes.toString().isEmpty()) ? 0 : new Long(partyVotes.toString()));
					
				parties[i] = party;
				totalVotes += party.getVotes();
			}
			district.setParties(parties);
			totalVotes+=district.getBlankVotes();
			totalVotes+=district.getInvalidVotes();
			for (int i = 0; i < numberOfParties; i++) {
				try {					
					String s = NumberUtils.getNumberFormat().format(parties[i].getVotes().longValue()* 100.0/ totalVotes);							
					Double f = NumberUtils.getNumberFormat().parse(s).doubleValue();
					parties[i].setVotePercentage(f);
							
				} catch (ParseException e) {
					throw new AppException(e);
				}	
			}			
			
			//validate district
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		    Validator validator = factory.getValidator();
		    Set<ConstraintViolation<District>> constraintViolations = validator.validate(district);
		    if(constraintViolations.size() > 0){
		    	String msg = constraintViolations.stream().map(c -> Messages.getString(c.getMessage())).collect(Collectors.joining(System.lineSeparator()));	    	
		    	throw new AppException(msg);
		    }
		
		} catch (AppException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), Messages.getString("Error.InvalidDistrictData"), JOptionPane.ERROR_MESSAGE);
			throw e;
			
		} catch (Exception e) {
			throw new AppException(Messages.getString("Error.InvalidDistrictData"), e);
		}
		return district;		
	}
	
}
