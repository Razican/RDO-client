package components;

import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * @author Jordan Aranda Tejada
 */
public class IComboBox extends JComboBox<Object> implements Internationalizable {

	private static final long	serialVersionUID	= 3086341731246410157L;

	/**
	 * Creates a combobox
	 */
	public IComboBox()
	{
		super();
	}

	/**
	 * @param aModel Combobox model
	 */
	public IComboBox(ComboBoxModel<Object> aModel)
	{
		super(aModel);
	}

	/**
	 * @param items Combobox array data
	 */
	public IComboBox(Object[] items)
	{
		super(items);
	}

	/**
	 * @param items Combobox vector data
	 */
	public IComboBox(Vector<Object> items)
	{
		super(items);
	}

	private Vector<Object> getVector()
	{
		Vector<Object> vector = new Vector<Object>();
		for (int i = 0; i < this.getItemCount(); i++)
		{
			vector.add(i, this.getItemAt(i));
		}
		return vector;
	}

	@Override
	public void changeLanguage(String newText)
	{
		Vector<Object> vector = getVector();
		vector.set(0, newText);
		removeAllItems();
		for (int i = 0; i < vector.size(); i++)
		{
			addItem(vector.get(i));
		}
	}
}
