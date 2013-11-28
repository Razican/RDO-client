package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Jordan Aranda Tejada
 */
public class ITextField extends JTextField implements FocusListener,
Internationalizable {

	private String				hint;
	private boolean				showingHint;
	private Icon				icon;

	private static final long	serialVersionUID	= 5728251792417526726L;

	/**
	 * @param hint The hint text
	 * @param icon The icon
	 */
	public ITextField(String hint, Icon icon)
	{
		super(hint);
		setOpaque(false);
		this.hint = hint;
		this.showingHint = true;
		this.setForeground(Color.LIGHT_GRAY);
		this.setFont(new Font("Calibri", Font.PLAIN, 18));
		super.addFocusListener(this);
		this.icon = icon;
	}

	@Override
	public void focusGained(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText("");
			super.setForeground(Color.BLACK);
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if (this.getText().isEmpty())
		{
			super.setText(hint);
			super.setForeground(Color.LIGHT_GRAY);
			showingHint = true;
		}
	}

	@Override
	public String getText()
	{
		return showingHint ? "" : super.getText();
	}

	@Override
	public void changeLanguage(String newText)
	{
		this.hint = newText;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (icon != null)
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