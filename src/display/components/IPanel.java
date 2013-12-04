package display.components;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author Jordan Aranda Tejada
 */

public class IPanel extends JPanel implements Internationalizable {

	private static final long	serialVersionUID	= - 433751015975886891L;

	private ImageIcon			bgImage;

	/**
	 * Creates a panel
	 */
	public IPanel()
	{
		super();
		this.setOpaque(false);
	}

	@Override
	public void changeLanguage(final String newText)
	{
		setBorder(new TitledBorder(null, newText, TitledBorder.LEADING,
		TitledBorder.ABOVE_TOP, new Font("Calibri", Font.ITALIC, 14), null));
	}

	/**
	 * Lo utilizaremos para establecerle su imagen de fondo.
	 * 
	 * @param bgImage La imagen en cuestion
	 */
	public void setBackgroundImage(ImageIcon bgImage)
	{
		this.bgImage = bgImage;
	}

	@Override
	public void paint(Graphics g)
	{

		// Pintamos la imagen de fondo...
		if (bgImage != null)
		{
			g
			.drawImage(bgImage.getImage(), 0, 0, getWidth(), getHeight(), null);
		}

		// Y pintamos el resto de cosas que pueda tener el panel
		super.paint(g);

	}
}