package components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Jordan Aranda Tejada
 */
public class VibratePanel extends JPanel implements ActionListener {

	private static final long	serialVersionUID	= - 6763178207061081796L;

	private Timer				vibrateTimer;
	private int					posX;
	private int					posY;

	public VibratePanel(int posX, int posY)
	{
		super();
		this.posX = posX;
		System.out.println("PosX: " + posX);
		this.posY = posY;
		vibrateTimer = new Timer(1000, this);
	}

	public void vibrate()
	{
		vibrateTimer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		while (true)
		{
			this.setBounds(this.posX - 10, this.getY(), this.getWidth(),
			this.getHeight());
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e1)
			{
				e1.printStackTrace();
			}
			this.setBounds(this.posX + 10, this.getY(), this.getWidth(),
			this.getHeight());
		}
	}
}
