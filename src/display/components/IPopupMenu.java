package display.components;

import javax.swing.JPopupMenu;

/**
 * @author Jordan Aranda Tejada
 */
public class IPopupMenu extends JPopupMenu {

	private static final long	serialVersionUID	= 2237265624621743436L;

	/**
	 * Default constructor
	 */
	public IPopupMenu()
	{

	}

	/**
	 * Constructs a IPopupMenu with the specified title
	 * 
	 * @param label The title
	 */
	public IPopupMenu(String label)
	{
		super(label);
	}
}