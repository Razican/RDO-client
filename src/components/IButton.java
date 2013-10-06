package components;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @author Razican (Iban Eguia)
 */
public class IButton extends JButton implements Internationalizable {

	private static final long	serialVersionUID	= - 2868342852969455388L;

	/**
	 * Creates a button
	 */
	public IButton()
	{
		super();
	}

	/**
	 * @param a Action of the button
	 */
	public IButton(final Action a)
	{
		super(a);
	}

	/**
	 * @param icon Icon for the button
	 */
	public IButton(final Icon icon)
	{
		super(icon);
	}

	/**
	 * @param text Text for the button
	 * @param icon Image for the button
	 */
	public IButton(final String text, final Icon icon)
	{
		super(text, icon);
	}

	/**
	 * @param text Text for the button
	 */
	public IButton(final String text)
	{
		super(text);
	}

	@Override
	public void changeLanguage(final String newText)
	{
		if (getIcon() == null)
		{
			setText(newText);
		}
		else
		{
			setToolTipText(newText);
		}
	}
}