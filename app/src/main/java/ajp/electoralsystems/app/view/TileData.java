package ajp.electoralsystems.app.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import ajp.electoralsystems.app.view.documentfilter.IntegerDocumentFilter;
import ajp.electoralsystems.app.view.documentfilter.LongDocumentFilter;
import ajp.electoralsystems.app.view.documentfilter.StringDocumentFilter;
import ajp.electoralsystems.i18n.LocaleChangeListener;
import ajp.electoralsystems.i18n.Messages;
import lombok.Getter;

/**
 * @author Andres Jimenez Penalver
 */
public class TileData implements LocaleChangeListener {

	private @Getter JPanel panel;
	private JButton btnAddParty;
	private JButton btnRemoveParty;
	private @Getter JLabel lblCensus;
	private @Getter JLabel lblName;
	private @Getter JLabel lblSeats;
	private JLabel lblBlankVotes;
	private JLabel lblInvalidVotes;
	private @Getter JTextField txtCensus;
	private @Getter JTextField txtName;
	private @Getter JTextField txtSeats;
	private @Getter JTextField txtBlankVotes;
	private @Getter JTextField txtInvalidVotes;
	private @Getter JTable tblParties;
	private @Getter JScrollPane spParties;
	private @Getter JLabel lblTurnout;
	private @Getter JLabel lblAbstention;
	private @Getter JLabel lblVotes;
	private @Getter JTextField txtTurnout;
	private @Getter JTextField txtVotes;
	private @Getter JTextField txtAbstention;

	
	public TileData() {	
		create();	
	}	
	
	public void clearPartyTable() {
		tblParties = (new JTable(0, 2));
		tblParties.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
							
		TableColumnModel tcm = tblParties.getColumnModel();				
		TableColumn tc0 = tcm.getColumn(0);
		tc0.setHeaderValue(Messages.getString("Party"));		
		tc0.setCellEditor(new PartyTableCellEditor(new StringDocumentFilter(25)));
		tc0.setCellRenderer(tblParties.getDefaultRenderer(String.class));			

		TableColumn tc1 = tcm.getColumn(1);
		tc1.setHeaderValue(Messages.getString("Votes"));		
		tc1.setCellEditor(new PartyTableCellEditor(new LongDocumentFilter(8)));
		tc1.setCellRenderer(tblParties.getDefaultRenderer(Number.class));

		if (spParties != null) {
			spParties.setViewportView(tblParties);
		}
	}
	
	public void clear() {
		txtCensus.setText("");
		txtSeats.setText("");			
		txtName.setText("");
		txtBlankVotes.setText("");
		txtInvalidVotes.setText("");
				
		txtVotes.setVisible(false);
		txtTurnout.setVisible(false);		
		txtAbstention.setVisible(false);	

		clearPartyTable();
	}
	
	public void onLocaleChanged(String lang) {
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("District.Data", lang)));
		lblName.setText(Messages.getString("Name", lang));
		lblCensus.setText(Messages.getString("Census", lang));
		lblSeats.setText(Messages.getString("Seats", lang));
		lblBlankVotes.setText(Messages.getString("Votes.Blank", lang));
		lblInvalidVotes.setText(Messages.getString("Votes.Invalid", lang));
		lblVotes.setText(Messages.getString("Votes.Total", lang));
		lblTurnout.setText(Messages.getString("Turnout", lang));
		lblAbstention.setText(Messages.getString("Abstention", lang));
		lblTurnout.setText(Messages.getString("Turnout", lang));
		
		btnAddParty.setToolTipText(Messages.getString("Party.Add", lang));
		btnRemoveParty.setToolTipText(Messages.getString("Party.Delete", lang));
		
		tblParties.getColumnModel().getColumn(0).setHeaderValue(Messages.getString("Party", lang));
		tblParties.getColumnModel().getColumn(1).setHeaderValue(Messages.getString("Votes", lang));
	}
	
	private void create() {	
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(9, 2));
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		//panel.setBounds(20,60,250,800);
		panel.setBorder(BorderFactory.createTitledBorder(Messages.getString("District.Data")));
		
		lblName = new JLabel(Messages.getString("Name"));
		lblName.setHorizontalAlignment(JLabel.RIGHT);
		lblName.setVisible(true);
		aux.add(lblName);
				
		txtName = new JTextField();
		Document textDocNombre = txtName.getDocument();
        DocumentFilter filterNombre = new StringDocumentFilter(25);
        ((AbstractDocument) textDocNombre).setDocumentFilter(filterNombre);
        txtName.setDocument(textDocNombre);
        txtName.setToolTipText(Messages.getString("Name"));
		txtName.setVisible(true);
		aux.add(txtName);

		lblCensus = new JLabel(Messages.getString("Census"));
		lblCensus.setHorizontalAlignment(JLabel.RIGHT);
		lblCensus.setVisible(true);
		aux.add(lblCensus);
		
		txtCensus = new JTextField();
		txtCensus.setHorizontalAlignment(JTextField.RIGHT);
		Document textDocCenso = txtCensus.getDocument();
        DocumentFilter filterCenso = new IntegerDocumentFilter(8);
        ((AbstractDocument) textDocCenso).setDocumentFilter(filterCenso);
        txtCensus.setDocument(textDocCenso);
        txtCensus.setToolTipText(Messages.getString("Census"));
		txtCensus.setVisible(true);						
		aux.add(txtCensus);

		lblSeats = new JLabel(Messages.getString("Seats"));
		lblSeats.setHorizontalAlignment(JLabel.RIGHT);
		lblSeats.setVisible(true);
		aux.add(lblSeats);

		txtSeats = new JTextField();
		txtSeats.setHorizontalAlignment(JTextField.RIGHT);
		Document textDocEscanos = txtSeats.getDocument();
        DocumentFilter filterEscanos = new IntegerDocumentFilter(3);
        ((AbstractDocument) textDocEscanos).setDocumentFilter(filterEscanos);
        txtSeats.setDocument(textDocEscanos);
		txtSeats.setToolTipText(Messages.getString("Seats"));
		txtSeats.setVisible(true);
		aux.add(txtSeats);
		
		lblBlankVotes = new JLabel(Messages.getString("Votes.Blank"));
		lblBlankVotes.setHorizontalAlignment(JLabel.RIGHT);
		lblBlankVotes.setVisible(true);
		aux.add(lblBlankVotes);

		txtBlankVotes = new JTextField();
		txtBlankVotes.setHorizontalAlignment(JTextField.RIGHT);
		Document textDocBlankVotes = txtBlankVotes.getDocument();
        DocumentFilter filterBlankVotes = new IntegerDocumentFilter(8);
        ((AbstractDocument) textDocBlankVotes).setDocumentFilter(filterBlankVotes);
        txtBlankVotes.setDocument(textDocBlankVotes);
        txtBlankVotes.setToolTipText(Messages.getString("Votes.Blank"));
        txtBlankVotes.setVisible(true);
		aux.add(txtBlankVotes);
				
		lblInvalidVotes = new JLabel(Messages.getString("Votes.Invalid"));
		lblInvalidVotes.setHorizontalAlignment(JLabel.RIGHT);
		lblInvalidVotes.setVisible(true);
		aux.add(lblInvalidVotes);

		txtInvalidVotes = new JTextField();
		txtInvalidVotes.setHorizontalAlignment(JTextField.RIGHT);
		Document textDocInvalidVotes = txtInvalidVotes.getDocument();
        DocumentFilter filterInvalidVotes = new IntegerDocumentFilter(8);
        ((AbstractDocument) textDocInvalidVotes).setDocumentFilter(filterInvalidVotes);
        txtInvalidVotes.setDocument(textDocInvalidVotes);
        txtInvalidVotes.setToolTipText(Messages.getString("Votes.Invalid"));
        txtInvalidVotes.setVisible(true);
		aux.add(txtInvalidVotes);
		
		lblVotes = new JLabel(Messages.getString("Votes.Total"));
		lblVotes.setHorizontalAlignment(JLabel.RIGHT);		
		lblVotes.setVisible(true);

		txtVotes = new JTextField();
		txtVotes.setHorizontalAlignment(JTextField.RIGHT);
		txtVotes.setEditable(false);
		txtVotes.setToolTipText(Messages.getString("Votes.Total"));
		txtVotes.setVisible(true);
		
		lblTurnout = new JLabel(Messages.getString("Turnout"));
		lblTurnout.setHorizontalAlignment(JLabel.RIGHT);
		lblTurnout.setVisible(true);

		txtTurnout = new JTextField();
		txtTurnout.setHorizontalAlignment(JTextField.RIGHT);
		txtTurnout.setEditable(false);
		txtTurnout.setToolTipText(Messages.getString("Turnout"));
		txtTurnout.setVisible(true);
		
		
		lblAbstention = new JLabel(Messages.getString("Abstention"));
		lblAbstention.setHorizontalAlignment(JLabel.RIGHT);
		lblAbstention.setVisible(true);

		txtAbstention = new JTextField();
		txtAbstention.setHorizontalAlignment(JTextField.RIGHT);
		txtAbstention.setEditable(false);
		txtAbstention.setToolTipText(Messages.getString("Abstention"));
		txtAbstention.setVisible(true);

		aux.add(lblVotes);
		aux.add(txtVotes);
		aux.add(lblTurnout);
		aux.add(txtTurnout);
		aux.add(lblAbstention);
		aux.add(txtAbstention);	
				
		btnAddParty = new JButton("+");
		btnAddParty.addActionListener(e -> {
			DefaultTableModel dtm = (DefaultTableModel) tblParties.getModel();
			dtm.addRow(new Object[] {"", null});
		});
		btnAddParty.setToolTipText(Messages.getString("Party.Add"));
		aux.add(btnAddParty);

		btnRemoveParty = new JButton("-");
		btnRemoveParty.addActionListener(e -> {
			ListSelectionModel lsm = tblParties.getSelectionModel();
			if (lsm.getMinSelectionIndex() != -1) {
				int rowIndex = lsm.getMinSelectionIndex();
				DefaultTableModel dtm = (DefaultTableModel) tblParties.getModel();
				dtm.removeRow(rowIndex);						
			}	
		});
		btnRemoveParty.setToolTipText(Messages.getString("Party.Delete"));
		aux.add(btnRemoveParty);
		
		clearPartyTable();	
		spParties = new JScrollPane(tblParties);
		spParties.setVisible(true);
		spParties.setViewportView(tblParties);
		
		panel.add(aux);
		panel.add(spParties);
	}

	public class FocusHandler extends FocusTraversalPolicy {

		public Component getDefaultComponent(Container container) {
			return txtName;
		}

		public Component getFirstComponent(Container container) {
			return txtName;
		}

		public Component getLastComponent(Container container) {
			return txtSeats;
		}

		//TODO: add fields
		public Component getComponentBefore(Container container, Component component) {
			if (component == txtCensus) {
				return txtName;
			} else if (component == txtSeats) {
				return txtCensus;
			} else if (component == txtBlankVotes) {
				return txtSeats;
			} else if (component == txtInvalidVotes) {
				return txtBlankVotes;
			} else if (component == spParties) {
				return txtInvalidVotes;
			} else if (component == btnAddParty) {
				return spParties;
			} else if (component == btnRemoveParty) {
				return btnAddParty;
			} else {
				return txtName;
			}
		}

		public Component getComponentAfter(Container container, Component component) {
			if (component == txtName) {
				return txtCensus;
			} else if (component == txtCensus) {
				return txtSeats;
			} else if (component == txtSeats) {
				return txtBlankVotes;
			} else if (component == txtBlankVotes) {
				return txtInvalidVotes;
			} else if (component == txtInvalidVotes) {
				return spParties;
			} else if (component == spParties) {
				return btnAddParty;
			} else if (component == btnAddParty) {
				return btnRemoveParty;
			} else {
				return txtName;
			}
		}
	}
	
}
