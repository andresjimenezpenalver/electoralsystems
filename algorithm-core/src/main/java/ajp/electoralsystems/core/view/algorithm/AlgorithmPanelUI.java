package ajp.electoralsystems.core.view.algorithm;

import javax.swing.JPanel;

import ajp.electoralsystems.core.model.algorithm.AlgorithmResult;
import ajp.electoralsystems.i18n.LocaleChangeListener;

/**
 * @author Andres Jimenez Penalver
 */
public abstract class AlgorithmPanelUI implements LocaleChangeListener {

	public abstract JPanel createPanel(AlgorithmResult algorithmResult);
	
}