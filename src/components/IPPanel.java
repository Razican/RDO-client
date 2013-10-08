package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Jordan Aranda Tejada
 */
public class IPPanel extends JPanel {

	private static final long	serialVersionUID	= - 1218654215199884472L;

	private JTextField			textField_1;
	private JTextField			textField_2;
	private JTextField			textField_3;
	private JTextField			textField_4;

	/**
	 * Create the panel.
	 */
	public IPPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 5, 30, 5, 30, 5, 30, 0};
		gridBagLayout.rowHeights = new int[] {10, 0};
		gridBagLayout.columnWeights = new double[] {1.0, 0.0, 1.0, 0.0, 1.0,
		0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[] {0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (textField_1.getText().length() == 3)
				{
					textField_2.requestFocus();
				}
				if (Integer.parseInt(textField_1.getText()) > 256)
				{
					textField_1.setText("256");
				}
			}
		});

		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_1.setForeground(Color.BLACK);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 0;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(2);

		JLabel lbl1 = new JLabel(".");
		lbl1.setForeground(Color.BLACK);
		lbl1.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lblttttt = new GridBagConstraints();
		gbc_lblttttt.fill = GridBagConstraints.VERTICAL;
		gbc_lblttttt.gridx = 1;
		gbc_lblttttt.gridy = 0;
		add(lbl1, gbc_lblttttt);

		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				char car = e.getKeyChar();
				if (textField_2.getText().length() == 3)
				{
					textField_3.requestFocus();
				}
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (Integer.parseInt(textField_2.getText()) > 256)
				{
					textField_2.setText("256");
				}
			}
		});
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_2.setForeground(Color.BLACK);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 0;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(2);

		JLabel lbl2 = new JLabel(".");
		lbl2.setForeground(Color.BLACK);
		lbl2.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lblaa = new GridBagConstraints();
		gbc_lblaa.gridx = 3;
		gbc_lblaa.gridy = 0;
		add(lbl2, gbc_lblaa);

		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				char car = e.getKeyChar();
				if (textField_3.getText().length() == 3)
				{
					textField_4.requestFocus();
				}
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (Integer.parseInt(textField_3.getText()) > 256)
				{
					textField_3.setText("256");
				}
			}
		});
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_3.setForeground(Color.BLACK);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 0;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(2);

		JLabel lbl3 = new JLabel(".");
		lbl3.setForeground(Color.BLACK);
		lbl3.setFont(new Font("Calibri", Font.PLAIN, 20));
		GridBagConstraints gbc_lblaa_1 = new GridBagConstraints();
		gbc_lblaa_1.gridx = 5;
		gbc_lblaa_1.gridy = 0;
		add(lbl3, gbc_lblaa_1);

		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyTyped(KeyEvent e)
			{
				char car = e.getKeyChar();
				if ((car < '0' || car > '9') && car != 127)
				{
					e.consume();
				}
				if (textField_4.getText().length() > 2)
				{
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				if (Integer.parseInt(textField_4.getText().trim()) > 256)
				{
					textField_4.setText("256");
				}
			}
		});
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Calibri", Font.PLAIN, 14));
		textField_4.setForeground(Color.BLACK);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 6;
		gbc_textField.gridy = 0;
		add(textField_4, gbc_textField);
		textField_4.setColumns(2);
	}

	/**
	 * @return IP Address
	 */
	public String getIpAddress()
	{
		String ip = "";

		if (textField_1.getText().equals(""))
		{
			ip += "0.";
		}
		else
		{
			ip += textField_1.getText().trim() + ".";
		}

		if (textField_2.getText().equals(""))
		{
			ip += "0.";
		}
		else
		{
			ip += textField_2.getText().trim() + ".";
		}

		if (textField_3.getText().equals(""))
		{
			ip += "0.";
		}
		else
		{
			ip += textField_3.getText().trim() + ".";
		}

		if (textField_4.getText().equals(""))
		{
			ip += "0";
		}
		else
		{
			ip += textField_4.getText().trim();
		}
		return ip;
	}

	/**
	 * @param ip The new ip
	 */
	public void setIpAddress(String ip)
	{
		int p1 = pointIndex(ip, 0);
		textField_1.setText(ip.substring(0, p1));
		p1++;

		int p2 = pointIndex(ip, p1);
		textField_2.setText(ip.substring(p1, p2));
		p2++;

		int p3 = pointIndex(ip, p2);
		textField_3.setText(ip.substring(p2, p3));
		p3++;

		int p4 = pointIndex(ip, p3);
		textField_4.setText(ip.substring(p3, p4));
	}

	private int pointIndex(String ip, int begin)
	{
		boolean enc = false;
		while ( ! enc && begin < ip.length())
		{
			if (ip.charAt(begin) == '.')
			{
				enc = true;
			}
			else
			{
				begin++;
			}
		}
		return begin;

	}
}