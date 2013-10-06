package components;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author Jordan Aranda Tejada
 */

public class IPanel extends JPanel implements Internationalizable {

	private static final long	serialVersionUID	= - 433751015975886891L;

	/**
	 * Creates a panel
	 */
	public IPanel()
	{
		super();
	}

	@Override
	public void changeLanguage(final String newText)
	{
		setBorder(new TitledBorder(null, newText, TitledBorder.LEADING,
		TitledBorder.ABOVE_TOP, new Font("Calibri", Font.ITALIC, 14), null));
	}
}