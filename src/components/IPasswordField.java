package components;

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

	private static final long	serialVersionUID	= 5728251792417526726L;

	/**
	 * @param icon The icon
	 */
	public IPasswordField(Icon icon)
	{
		super();
		this.setForeground(Color.BLACK);
		this.setFont(new Font("Calibri", Font.PLAIN, 18));
		this.setMargin(new Insets(0, 3, 0, 0));
		this.icon = icon;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		int iconHeight = icon.getIconHeight();
		int iconWidth = icon.getIconWidth();
		int y = (this.getHeight() - iconHeight) / 2;
		icon.paintIcon(this, g, 5, y);
		setBorder(new EmptyBorder(3, iconWidth + 20, 0, 2));
	}
}