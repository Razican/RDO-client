package display;

import interfaces.Loadable;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import utils.Lang;
import utils.Patient;
import display.components.IPanel;

/**
 * @author Jordan Aranda Tejada
 */
public class GPSPanel extends IPanel implements Loadable {

	private static final long	serialVersionUID	= 3058995416278746348L;
	private JButton				btnEnable;
	private boolean				gpsEnable;
	private boolean				init;

	/**
	 * Create the panel.
	 */
	public GPSPanel()
	{
		this.gpsEnable = true;
		this.init = false;

		setBackgroundImage(new ImageIcon("img/background.jpg"));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0};
		gridBagLayout.rowHeights = new int[] {0, 0, 0};
		gridBagLayout.columnWeights = new double[] {1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {5.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		Patient.getCurrent().setGPSStatus(true, this);

		JLabel lblNewLabel = new JLabel(new ImageIcon("img/gps-icon.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		btnEnable = new JButton();
		btnEnable.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Patient.getCurrent().setGPSStatus( ! gpsEnable, GPSPanel.this);
			}
		});
		btnEnable.setBackground(Color.BLACK);
		btnEnable.setFocusPainted(false);
		btnEnable.setBorderPainted(false);
		btnEnable.setForeground(Color.GREEN);
		btnEnable.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 1;
		add(btnEnable, gbc_btnNewButton);
	}

	@Override
	public void update(Object object)
	{
		if (object instanceof Integer)
		{
			int code = (Integer) object;
			if (code == 315 && ! init)
			{
				Patient.getCurrent().setGPSStatus(false, this);
				gpsEnable = false;
				init = true;
			}
			else if ( ! init)
			{
				btnEnable.setText(Lang.getLine("sensor_panel_btn_enable_off"));
				init = true;
			}
			else if (code == 315)
			{
				btnEnable.setText(Lang.getLine("sensor_panel_btn_enable_off"));
				this.gpsEnable = true;
			}
			else if (code == 316)
			{
				btnEnable.setText(Lang.getLine("sensor_panel_btn_enable_on"));
				this.gpsEnable = false;
			}
		}
	}
}