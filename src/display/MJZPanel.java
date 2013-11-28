package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class MJZPanel extends JPanel {

	private float	alfa;

	public MJZPanel()
	{
		alfa = 0.5F;
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		AlphaComposite old = (AlphaComposite) g2.getComposite();
		g2.setComposite(AlphaComposite.SrcOver.derive(alfa));
		super.paintComponent(g);
		g2.setComposite(old);
	}

	public float getAlfa()
	{
		return alfa;
	}

	public void setAlfa(float alfa)
	{
		this.alfa = alfa;
	}
}