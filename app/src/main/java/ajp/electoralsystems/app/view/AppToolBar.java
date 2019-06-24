package ajp.electoralsystems.app.view;

import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JToolBar;

import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class AppToolBar extends AbstractMenuToolBar implements LocaleChangeListener {

	private @Getter JToolBar toolBar;
	private @Getter JButton btnNew;
	private @Getter JButton btnOpen;
	private @Getter JButton btnSave;
	private @Getter JButton btnCalculate;
	
	public AppToolBar() {
		createToolBar();
	}
		
	public void onLocaleChanged(String lang) {
		btnNew.setToolTipText(Messages.getString("District.New", lang)); 
		btnOpen.setToolTipText(Messages.getString("District.Open", lang)); 
		btnSave.setToolTipText(Messages.getString("District.Save", lang)); 
		btnCalculate.setToolTipText(Messages.getString("District.CalculateSeats", lang)); 
	}
	
	private void createToolBar() {		
		toolBar = new JToolBar();
		toolBar.setFloatable(true);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.setMargin(null);
		
		btnNew = new JButton(getImageIcon("ajp/electoralsystems/images/New24.gif")); 
		btnNew.setMargin(new Insets(0, 0, 0, 0));
		btnNew.setMnemonic(ACTION_NEW_MNEMONIC);
		btnNew.setToolTipText(Messages.getString("District.New")); 
		toolBar.add(btnNew);
		
		btnOpen = new JButton(getImageIcon("ajp/electoralsystems/images/Open24.gif")); 
		btnOpen.setMargin(new Insets(0, 0, 0, 0));
		btnOpen.setMnemonic(ACTION_OPEN_MNEMONIC);
		btnOpen.setToolTipText(Messages.getString("District.Open")); 
		toolBar.add(btnOpen);

		btnSave = new JButton(getImageIcon("ajp/electoralsystems/images/Save24.gif")); 
		btnSave.setMargin(new Insets(0, 0, 0, 0));
		btnSave.setMnemonic(ACTION_SAVE_MNEMONIC);
		btnSave.setToolTipText(Messages.getString("District.Save"));		 
		toolBar.add(btnSave);
		
		btnCalculate = new JButton(getImageIcon("ajp/electoralsystems/images/Play24.gif")); 
		btnCalculate.setMargin(new Insets(0, 0, 0, 0));
		btnCalculate.setMnemonic(ACTION_CALCULATE_MNEMONIC);
		btnCalculate.setToolTipText(Messages.getString("District.CalculateSeats")); 
		toolBar.add(btnCalculate);
		
		toolBar.setBounds(0,0,500,50);
		toolBar.setVisible(true);		
	}

}
