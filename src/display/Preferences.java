package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SkinInfo;

import utils.Lang;

/**
 * @author Razican (Iban Eguia)
 */
public class Preferences extends JPanel {

	private static final long	serialVersionUID	= - 9082799207563983259L;
	private final JComboBox<String>	langCombo, lookNFeelCombo;
	private HashMap<String, String>	lookNFeelHashMap;
	private String					currentLookAndFeel;

	/**
	 * Create the panel.
	 */
	public Preferences()
	{
		setLayout(new BorderLayout(0, 0));

		final JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		final GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 78, 32, 0, 0};
		gbl_panel.rowHeights = new int[] {10, 24, 0, 10, 0};
		gbl_panel.columnWeights = new double[] {1.0, 0.0, 1.0, 1.0,
		Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[] {1.0, 0.0, 0.0, 1.0,
		Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		final JLabel lblLanguage = new JLabel(Lang.getLine("pref_lang"));
		lblLanguage.setForeground(Color.BLACK);
		lblLanguage.setFont(new Font("Calibri", Font.PLAIN, 14));
		final GridBagConstraints gbc_lblLanguage = new GridBagConstraints();
		gbc_lblLanguage.anchor = GridBagConstraints.WEST;
		gbc_lblLanguage.insets = new Insets(0, 0, 5, 5);
		gbc_lblLanguage.gridx = 1;
		gbc_lblLanguage.gridy = 1;
		panel.add(lblLanguage, gbc_lblLanguage);

		langCombo = new JComboBox<>(Lang.getCombableLocales());
		langCombo.setForeground(Color.BLACK);
		langCombo.setFont(new Font("Calibri", Font.PLAIN, 14));
		langCombo.setSelectedIndex(Lang.getCurrentLocaleKey());
		final GridBagConstraints gbc_langCombo = new GridBagConstraints();
		gbc_langCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_langCombo.insets = new Insets(0, 0, 5, 5);
		gbc_langCombo.anchor = GridBagConstraints.NORTH;
		gbc_langCombo.gridx = 2;
		gbc_langCombo.gridy = 1;
		panel.add(langCombo, gbc_langCombo);

		final JLabel lbLookandfeel = new JLabel(
		Lang.getLine("pref_look_and_feel"));
		lbLookandfeel.setForeground(Color.BLACK);
		lbLookandfeel.setFont(new Font("Calibri", Font.PLAIN, 14));
		final GridBagConstraints gbc_lbLookandfeel = new GridBagConstraints();
		gbc_lbLookandfeel.anchor = GridBagConstraints.WEST;
		gbc_lbLookandfeel.insets = new Insets(0, 0, 5, 5);
		gbc_lbLookandfeel.gridx = 1;
		gbc_lbLookandfeel.gridy = 2;
		panel.add(lbLookandfeel, gbc_lbLookandfeel);

		lookNFeelCombo = new JComboBox<>(getAvailableLF());
		lookNFeelCombo.setForeground(Color.BLACK);
		lookNFeelCombo.setFont(new Font("Calibri", Font.PLAIN, 14));
		lookNFeelCombo.setSelectedItem(currentLookAndFeel);
		final GridBagConstraints gbc_lfCombo = new GridBagConstraints();
		gbc_lfCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lfCombo.insets = new Insets(0, 0, 5, 5);
		gbc_lfCombo.gridx = 2;
		gbc_lfCombo.gridy = 2;
		panel.add(lookNFeelCombo, gbc_lfCombo);
	}

	/**
	 * @return Current selected locale's index
	 */
	public int getLocaleIndex()
	{
		return langCombo.getSelectedIndex();
	}

	/**
	 * @return Current selected LookAndFeelInfo
	 */
	public String getLookAndFeel()
	{
		return lookNFeelHashMap.get(lookNFeelCombo.getSelectedItem());
	}

	private Vector<String> getAvailableLF()
	{
		final LookAndFeelInfo lfs[] = UIManager.getInstalledLookAndFeels();

		lookNFeelHashMap = new HashMap<>(lfs.length);
		final Vector<String> v = new Vector<>(lfs.length);

		for (final LookAndFeelInfo lf2: lfs)
		{
			lookNFeelHashMap.put(lf2.getName(), lf2.getClassName());
			v.add(lf2.getName());
			if (utils.Properties.getLookAndFeel().equals(lf2.getClassName()))
			{
				currentLookAndFeel = lf2.getName();
			}
		}

		// SUBSTANCE LookAndFeels
		TreeMap<String, SkinInfo> treemap = (TreeMap<String, SkinInfo>) SubstanceLookAndFeel
		.getAllSkins();
		Iterator<Entry<String, SkinInfo>> it = treemap.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<String, SkinInfo> e = it.next();
			v.add(e.getKey());
			lookNFeelHashMap.put(e.getKey(), e.getValue().getClassName());
			if (utils.Properties.getLookAndFeel().equals(
			e.getValue().getClassName()))
			{
				currentLookAndFeel = e.getKey();
			}
		}
		return v;
	}
}