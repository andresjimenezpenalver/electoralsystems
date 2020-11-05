package ajp.electoralsystems.app.controller;

import java.util.Set;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import ajp.electoralsystems.algorithm.engine.AlgorithmEngine;
import ajp.electoralsystems.app.view.MainWindow;
import ajp.electoralsystems.app.view.TileData;
import ajp.electoralsystems.app.view.TileResult;
import ajp.electoralsystems.core.exception.AppException;
import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.DistrictAlgorithmResult;
import ajp.electoralsystems.core.model.Party;
import ajp.electoralsystems.core.model.algorithm.Algorithm;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.utils.NumberUtils;
import ajp.electoralsystems.core.view.algorithm.AlgorithmComparationPanel;
import ajp.electoralsystems.core.view.algorithm.AlgorithmPanelUI;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Andres Jimenez Penalver
 */
public class ShowDistrictAction implements Action {
	
	private MainWindow mainWindow;
	private @Getter @Setter AlgorithmEngine algorithmEngine;
	
	public ShowDistrictAction(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}
	
	/**
	 * 
	 * @param object
	 * @throws AppException
	 */
	public Object execute(Object object) throws AppException {
		District district = (District) object;
		if (district != null) {				
			showDistrictData(district);			
			showDistrictResults(district);
		}	
		return null;
	}

	private void showDistrictData(District district) {
		TileData dataTile = mainWindow.getDataTile();
		
		dataTile.getTxtName().setText(district.getName());
		dataTile.getTxtCensus().setText(district.getCensus().toString());
		dataTile.getTxtSeats().setText(district.getSeats().toString());
		dataTile.getTxtBlankVotes().setText(district.getBlankVotes().toString());
		dataTile.getTxtInvalidVotes().setText(district.getInvalidVotes().toString());
		
		new ClearPartyTableAction(mainWindow).execute(null);
		for (int i = 0; i < district.getNumberOfParties().intValue(); i++) {
			((DefaultTableModel) dataTile.getTblParties().getModel()).addRow(new Object[] {"", null});
			Party party = district.getParties()[i];
			dataTile.getTblParties().setValueAt(party.getName(), i, 0);
			dataTile.getTblParties().setValueAt(party.getVotes(), i, 1);
		}		
		dataTile.getSpParties().setViewportView(dataTile.getTblParties());
		
		dataTile.getLblValidVotes().setVisible(true);
		dataTile.getTxtValidVotes().setVisible(true);
		dataTile.getTxtValidVotes().setText(String.valueOf(district.getTotalValidVotes()));		
		dataTile.getLblVotes().setVisible(true);
		dataTile.getTxtVotes().setVisible(true);
		dataTile.getTxtVotes().setText(String.valueOf(district.getTotalVotes()));
		dataTile.getLblTurnout().setVisible(true);
		dataTile.getTxtTurnout().setVisible(true);
		dataTile.getTxtTurnout().setText(NumberUtils.getNumberFormat().format(district.getTurnout()));
		dataTile.getLblAbstention().setVisible(true);
		dataTile.getTxtAbstention().setVisible(true);
		dataTile.getTxtAbstention().setText(NumberUtils.getNumberFormat().format(district.getAbstention()));
	}
	
	private void showDistrictResults(District district) {
		if (district.hasParties()) {
			TileResult resultTile = mainWindow.getResultTile();
			resultTile.getTabbedPane().setVisible(true);
			
			DistrictAlgorithmResult data = getAlgorithmEngine().execute(district);		
			
			AlgorithmComparationPanel algorithmComparationPanel = new AlgorithmComparationPanel();
			mainWindow.addLocaleChangeListener(algorithmComparationPanel);
			resultTile.getTabbedPane().addTab(Messages.getString("Algorithm.Comparator"), algorithmComparationPanel.createPanel(district, data));
			
			Set<Algorithm> keys = data.getAlgorithmsApplied();
			for (Algorithm key : keys) {
				AlgorithmResult result = data.getAlgorithmResult(key);
				AlgorithmPanelUI algorithmPanel = result.getUI();
				mainWindow.addLocaleChangeListener(algorithmPanel);
				JPanel panel = algorithmPanel.createPanel(result);
				resultTile.getTabbedPane().addTab(key.getId(), panel);				
			}
			resultTile.getPanel().setVisible(true);
		}
	}	
	
}
