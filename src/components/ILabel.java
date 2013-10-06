package components;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * @author Jordan Aranda Tejada
 */
public class ILabel extends JLabel implements Internationalizable {

	private static final long	serialVersionUID	= 774349398573896902L;

	/**
	 * Creates a label
	 */
	public ILabel()
	{
		super();
	}

	/**
	 * @param image Image for the label
	 * @param horizontalAlignment Horizontal alignment
	 */
	public ILabel(final Icon image, final int horizontalAlignment)
	{
		super(image, horizontalAlignment);
	}

	/**
	 * @param image Image for the label
	 */
	public ILabel(final Icon image)
	{
		super(image);
	}

	/**
	 * @param text Text for the label
	 * @param icon Icon for the label
	 * @param horizontalAlignment Horizontal alignment
	 */
	public ILabel(final String text, final Icon icon,
	final int horizontalAlignment)
	{
		super(text, icon, horizontalAlignment);
	}

	/**
	 * @param text Text for the label
	 * @param horizontalAlignment Horizontal alignment
	 */
	public ILabel(final String text, final int horizontalAlignment)
	{
		super(text, horizontalAlignment);
	}

	/**
	 * @param text Text for the label
	 */
	public ILabel(final String text)
	{
		super(text);
	}

	@Override
	public void changeLanguage(final String newText)
	{
		setText(newText);
	}
}