package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import entities.Patient;

/**
 * @author Jordan Aranda Tejada
 */
public class PatientTablePopup extends JPopupMenu implements ActionListener {

	private static final long	serialVersionUID	= 8850875135238330898L;

	/**
	 * Create the panel.
	 * 
	 * @param patient Selected patient
	 */
	public PatientTablePopup(Patient patient)
	{

		JMenuItem mntmEditar = new JMenuItem("Editar");
		add(mntmEditar);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}
}
