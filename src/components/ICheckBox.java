package components;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

/**
 * @author Jordan Aranda Tejada
 */
public class ICheckBox extends JCheckBox implements Internationalizable
{
	private static final long serialVersionUID = -4923966929804117866L;

	/**
	 * Creates a checkbox
	 */
	public ICheckBox()
	{
		super();
	}

	/**
	 * @param a Action of the checkbox
	 */
	public ICheckBox(final Action a)
	{
		super(a);
	}

	/**
	 * @param icon Icon for the checkbox
	 */
	public ICheckBox(final Icon icon)
	{
		super(icon);
	}

	/**
	 * @param text Text for the checkbox
	 * @param icon Image for the checkbox
	 */
	public ICheckBox(final String text, final Icon icon)
	{
		super(text, icon);
	}

	/**
	 * @param text Text for the checkbox
	 */
	public ICheckBox(final String text)
	{
		super(text);
	}
	
	/**
	 * @param text Text for the checkbox
	 * @param selected If checkbox is selected
	 */
	public ICheckBox(final String text, boolean selected)
	{
		super(text, selected);
	}
	
	/**
	 * @param text Text for the checkbox
	 * @param icon Image for the checkbox
	 * @param selected If checkbox is selected
	 */
	public ICheckBox(final String text, Icon icon, boolean selected)
	{
		super(text, icon, selected);
	}

	public void changeLanguage(String newText) 
	{
		setText(newText);
	}
}