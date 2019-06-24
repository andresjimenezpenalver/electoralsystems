package ajp.electoralsystems.app.controller;

import java.text.ParseException;

import javax.swing.JOptionPane;

import org.springframework.util.StringUtils;

import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.app.view.TileData;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.exception.InvalidDistrictException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictValidator;
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
	
 	/**
	 * Returns the district of the GUI
	 * 
	 * @param object 
	 * @return a District object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {		
		District district = null;
		try {
			TileData dataTile = mainWindow.getDataTile();
			district = new District();
			
			//name 
			String name = dataTile.getTxtName().getText();
			if (StringUtils.isEmpty(name)) {
				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_NAME);
			}
			district.setName(name);
			
			//census
			String census = dataTile.getTxtCensus().getText();
			if (StringUtils.isEmpty(census)) {
				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_CENSUS);
			}
			district.setCensus(new Long(census));
			
			//seats
			String seats = dataTile.getTxtSeats().getText();
			if (StringUtils.isEmpty(seats)) {
				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_SEATS);
			}
			district.setSeats(new Integer(seats));

			//blank votes
			String blankVotes = dataTile.getTxtBlankVotes().getText();
			if (!blankVotes.isEmpty()) {
				district.setBlankVotes(new Long(blankVotes));
			}
//			if (StringUtils.isEmpty(seats)) {
//				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_SEATS);
//			}
			
			//invalid votes
			String invalidVotes = dataTile.getTxtInvalidVotes().getText();
			if (!invalidVotes.isEmpty()) {
				district.setInvalidVotes(new Long(invalidVotes));	
			}
//			if (StringUtils.isEmpty(seats)) {
//				throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_SEATS);
//			}
			
			//parties
			int numberOfParties = dataTile.getTblParties().getRowCount();
			if (numberOfParties == 0) {
				throw new InvalidDistrictException(InvalidDistrictException.ERROR_NO_PARTIES);
			}
			Party[] parties = new Party[numberOfParties];
			int totalVotes = 0;
			for (int i = 0; i < numberOfParties; i++) {
				Party party = new Party();
				Object partyName = dataTile.getTblParties().getValueAt(i, 0);
				Object partyVotes = dataTile.getTblParties().getValueAt(i, 1);
				if (partyName == null || partyVotes == null) {
					throw new InvalidDistrictException(InvalidDistrictException.ERROR_INVALID_PARTY);
				}
				party.setName(partyName.toString());
				party.setVotes(new Long(partyVotes.toString()));
					
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
			new DistrictValidator().validate(district);
		
		} catch (AppException e) {
			JOptionPane.showMessageDialog(null, Messages.getString(e.getMessage()), Messages.getString("Error.InvalidDistrictData"), JOptionPane.ERROR_MESSAGE);
			throw e;
			
		} catch (Exception e) {
			throw new AppException(Messages.getString("Error.InvalidDistrictData"), e);
		}
		return district;		
	}
	
}
