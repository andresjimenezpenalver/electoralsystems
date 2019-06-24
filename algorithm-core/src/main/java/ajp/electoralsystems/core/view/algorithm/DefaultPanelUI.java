package ajp.electoralsystems.core.view.algorithm;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import ajp.electoralsystems.core.model.District;
import ajp.electoralsystems.core.model.PartyUtils;
import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.core.model.algorithm.DefaultResultTableModel;
import ajp.electoralsystems.i18n.Messages;
import ajp.utils.ui.ImageCanvas;
import ajp.utils.ui.JFreeChartUtil;

/**
 * @author Andres Jimenez Penalver
 */
public class DefaultPanelUI extends AlgorithmPanelUI {

	private JRadioButton ringChartButton; 
	private JRadioButton barChart2DButton;
	//private JRadioButton pieChartButton;
	private JTable tblTabla;
	private JPanel seatDistributionPanel;
	
	private JLabel lblAlgorithmClass;
	private JLabel lblAlgorithmClassValue;
	private JLabel lblAlgorithmTime;
	private JLabel lblAlgorithmTimeValue;
	private JPanel algorithmPanel;
	
	public void onLocaleChanged(String lang) {
		ringChartButton.setText(Messages.getString("Chart.Ring", lang));
		barChart2DButton.setText(Messages.getString("Chart.Bar", lang));
		seatDistributionPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Seats.Distribution", lang)));
		DefaultResultTableModel.onLocaleChanged(lang, tblTabla.getColumnModel());
		
		lblAlgorithmClass.setText(Messages.getString("Algorithm.Impl", lang));
		lblAlgorithmTime.setText(Messages.getString("ExecTime", lang) + "(" + Messages.getString("Millis", lang) + ")");		
		algorithmPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Algorithm", lang)));
		
		//TODO: change charts
	}
	
	public final JPanel createPanel(AlgorithmResult algorithmResult) {
		District district = algorithmResult.getDistrict();
		JPanel seatPanel = getSeatDistributionPanel(district, algorithmResult);
		JPanel algorithmDetailPanel = getCommonAlgorithmDetailPanel(district, algorithmResult);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(seatPanel); 
		panel.add(algorithmDetailPanel);
		return panel;
	}
	
	public JPanel getAlgorithmDetailPanel(District district, AlgorithmResult algorithmResult) {
		return null;
	}
	
	private JPanel getCommonAlgorithmDetailPanel(District district, AlgorithmResult algorithmResult) {		
		lblAlgorithmClass = new JLabel(Messages.getString("Algorithm.Impl")); 
		lblAlgorithmClass.setFont(lblAlgorithmClass.getFont().deriveFont(Font.PLAIN));
		
		lblAlgorithmClassValue = new JLabel(algorithmResult.getAlgorithmProviderClass().getName()); 
		lblAlgorithmClassValue.setFont(lblAlgorithmClassValue.getFont().deriveFont(Font.PLAIN));
		
		lblAlgorithmTime = new JLabel(Messages.getString("ExecTime") + "(" + Messages.getString("Millis") + ")");
		lblAlgorithmTime.setFont(lblAlgorithmTime.getFont().deriveFont(Font.PLAIN));

		lblAlgorithmTimeValue = new JLabel(String.valueOf(algorithmResult.getCalculationTime()));
		lblAlgorithmTimeValue.setFont(lblAlgorithmTimeValue.getFont().deriveFont(Font.PLAIN));

		JPanel commonPanel = new JPanel();
		commonPanel.setLayout(new GridLayout(3,2));
		commonPanel.add(lblAlgorithmClass);
		commonPanel.add(lblAlgorithmClassValue);
		commonPanel.add(lblAlgorithmTime);		
		commonPanel.add(lblAlgorithmTimeValue);		

		JPanel detailPanel = getAlgorithmDetailPanel(district, algorithmResult);
		
		algorithmPanel = new JPanel();
		algorithmPanel.setLayout(new BoxLayout(algorithmPanel, BoxLayout.Y_AXIS));
		algorithmPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Algorithm")));
		algorithmPanel.add(commonPanel);
		if (detailPanel != null) {
			algorithmPanel.add(detailPanel);
		}
		
		return algorithmPanel;
	}
	
	private JPanel getSeatDistributionPanel(District district, AlgorithmResult algorithmResult) {		
		Font font = new Font(Messages.getString("Chart.Font"),Font.PLAIN,12); 	
		
		ringChartButton = new JRadioButton(Messages.getString("Chart.Ring"), true);
		//pieChartButton = new JRadioButton(Messages.getString("Chart.Pie"));
		barChart2DButton = new JRadioButton(Messages.getString("Chart.Bar"));
		
		ButtonGroup bg = new ButtonGroup();		
		//bg.add(pieChartButton);
		bg.add(barChart2DButton);
		bg.add(ringChartButton);
		
//		final ImageCanvas pieChart = createPieChart(district, algorithmResult, font);			
		final ImageCanvas barChart = createBarChart2D(district, algorithmResult, font);					
		final ImageCanvas ringChart = createRingChart(district, algorithmResult, font);				
		
		GridLayout gridLayout = new GridLayout(0, 1);
		JPanel radioPanel = new JPanel(gridLayout);
		radioPanel.add(ringChartButton);
        //radioPanel.add(pieChartButton);
        radioPanel.add(barChart2DButton);
        final JPanel chartPanel = new JPanel();
		chartPanel.add(ringChart);
        
        ringChartButton.addActionListener(new ActionListener() {			
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		chartPanel.removeAll();
        		chartPanel.add(ringChart);
        	}
        });
//        pieChartButton.addActionListener(new ActionListener() {			
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		chartPanel.removeAll();
//        		chartPanel.add(pieChart);
//        	}
//        });
        barChart2DButton.addActionListener(new ActionListener() {			
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		chartPanel.removeAll();
        		chartPanel.add(barChart);
        	}
        });
        
		JPanel graphs = new JPanel();
		graphs.setLayout(new FlowLayout());
		graphs.add(radioPanel);
		graphs.add(chartPanel);
		
		DefaultResultTableModel dataModel = new DefaultResultTableModel(algorithmResult);		
		tblTabla = new JTable(dataModel);
				
		JScrollPane spTabla = new JScrollPane(tblTabla);
		spTabla.setVisible(true);
		
		seatDistributionPanel = new JPanel();
		seatDistributionPanel.setBorder(BorderFactory.createTitledBorder(Messages.getString("Seats.Distribution")));
		seatDistributionPanel.setLayout(new BoxLayout(seatDistributionPanel, BoxLayout.Y_AXIS));
		seatDistributionPanel.add(graphs);
		seatDistributionPanel.add(spTabla);
		return seatDistributionPanel;
	}
	
	protected ImageCanvas createBarChart2D(District district, AlgorithmResult algorithmResult, Font font) {
		Object[] array = PartyUtils.getPartyNameAndSeatArray(algorithmResult.getResults());	
		JFreeChart chart = JFreeChartUtil.createBarChart2D(
				Messages.getString("Seats.Distribution")
				,Messages.getString("Party") 
				,Messages.getString("Seats.Number") 
				,new String[] {Messages.getString("Seats")} 
				,(String[]) array[0]
				,new Number[][] {(Integer[]) array[1]}
				,PlotOrientation.HORIZONTAL,false,false,false
		);
		chart.getTitle().setFont(font);
		BufferedImage bi = JFreeChartUtil.getBufferedImage(chart, 680, 240);		
		ImageCanvas canvas = new ImageCanvas(bi);
		canvas.setBounds(0, 0, 700, 260);
		canvas.setVisible(true);
		return canvas;
	}

	protected ImageCanvas createPieChart(District district, AlgorithmResult algorithmResults, Font font) {
		Object[] array = PartyUtils.getPartyNameAndSeatArray(algorithmResults.getResults());
		JFreeChart chart = JFreeChartUtil.createPieChart2D(
			Messages.getString("Votes.Percentage")
			, (String[]) array[0]
			, (Integer[]) array[1]
			,true,true,false
		);
		chart.getTitle().setFont(font);
		BufferedImage bi = JFreeChartUtil.getBufferedImage(chart, 680, 240);		
		ImageCanvas canvas = new ImageCanvas(bi);
		canvas.setBounds(0, 0, 700, 260);
		canvas.setVisible(true);
		return canvas;
	}
	
	protected ImageCanvas createRingChart(District district, AlgorithmResult algorithmResults, Font font) {
		Object[] array = PartyUtils.getPartyNameAndSeatArray(algorithmResults.getResults());
		String[] names = new String[((Object[]) array[0]).length];
		for (int i = 0; i < names.length; i++) {
			names[i]=((Object[]) array[0])[i].toString()+"(" +((Object[]) array[1])[i].toString()+")"; 
		}
		JFreeChart chart = JFreeChartUtil.createRingChart(
				Messages.getString("Seats") 
				,names
				,(Integer[]) array[1]
				,true,true,false
			);
			chart.getTitle().setFont(font);
			BufferedImage bi = JFreeChartUtil.getBufferedImage(chart, 680, 240);		
			ImageCanvas canvas = new ImageCanvas(bi);
			canvas.setBounds(0, 0, 700, 260);
			canvas.setVisible(true);
			return canvas;
	}
	
}