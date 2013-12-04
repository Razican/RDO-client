package display.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

/**
 * @author Jordan Aranda Tejada
 */
public class IPasswordField extends JPasswordField {

	private Icon				icon;
	private Icon				errorIcon;
	private boolean				showError;
	private static final long	serialVersionUID	= 5728251792417526726L;

	/**
	 * @param icon The icon
	 */
	public IPasswordField(Icon icon)
	{
		super();
		this.setForeground(Color.BLACK);
		this.setFont(new Font("Calibri", Font.PLAIN, 20));
		this.setMargin(new Insets(0, 3, 0, 0));
		this.icon = icon;
		this.showError = false;
	}

	/**
	 * @param icon The error icon to be displayed
	 */
	public void setErrorIcon(Icon icon)
	{
		this.errorIcon = icon;
	}

	/**
	 * Method to display error icon
	 */
	public void showError()
	{
		this.showError = true;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (showError && errorIcon != null)
		{
			if (icon != null)
			{
				int iconHeight = icon.getIconHeight();
				int iconWidth = icon.getIconWidth();
				int iconHeight2 = errorIcon.getIconHeight();
				int iconWidth2 = errorIcon.getIconWidth();
				int y = (this.getHeight() - iconHeight) / 2;
				int y2 = (this.getHeight() - iconHeight2) / 2;

				icon.paintIcon(this, g, 5, y);
				errorIcon.paintIcon(this, g, this.getWidth() - iconWidth2 - 5,
				y2);

				setBorder(new EmptyBorder(3, iconWidth + 20, 0, iconWidth2 + 15));
			}
			else
			{
				int iconHeight = errorIcon.getIconHeight();
				int iconWidth = errorIcon.getIconWidth();
				int y = (this.getHeight() - iconHeight) / 2;
				errorIcon.paintIcon(this, g,
				this.getWidth() - errorIcon.getIconWidth(), y);
				setBorder(new EmptyBorder(3, 10, 0, iconWidth + 15));
			}
		}
		else if (icon != null)
		{
			int iconHeight = icon.getIconHeight();
			int iconWidth = icon.getIconWidth();
			int y = (this.getHeight() - iconHeight) / 2;
			icon.paintIcon(this, g, 5, y);
			setBorder(new EmptyBorder(3, iconWidth + 20, 0, 2));
		}
		else
		{
			setBorder(new EmptyBorder(3, 10, 0, 2));
		}
	}
}