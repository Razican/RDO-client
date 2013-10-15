package display;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import components.IPopupMenu;
import components.Window;

import entities.Patient;

/**
 * @author Jordan Aranda Tejada
 */
public class PatientTablePopup extends IPopupMenu implements ActionListener {

	private static final long	serialVersionUID	= 8850875135238330898L;

	private Patient				patient;

	private JMenuItem			menuEdit;
	private JMenuItem			menuDelete;
	private JMenuItem			menuSendEmail;
	private JMenuItem			menuConnect;

	/**
	 * Create the panel.
	 * 
	 * @param patient Selected patient
	 */
	public PatientTablePopup(Patient patient)
	{
		this.patient = patient;

		menuEdit = new JMenuItem("Modificar");
		menuEdit.addActionListener(this);
		menuEdit.setFont(new Font("Calibri", Font.PLAIN, 18));

		menuDelete = new JMenuItem("Eliminar");
		menuDelete.addActionListener(this);
		menuDelete.setFont(new Font("Calibri", Font.PLAIN, 18));

		menuSendEmail = new JMenuItem("Enviar email");
		menuSendEmail.addActionListener(this);
		menuSendEmail.setFont(new Font("Calibri", Font.PLAIN, 18));

		menuConnect = new JMenuItem("Conectar");
		menuConnect.addActionListener(this);
		menuConnect.setFont(new Font("Calibri", Font.PLAIN, 18));

		add(menuEdit);
		add(menuDelete);
		add(menuSendEmail);
		add(menuConnect);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == menuEdit)
		{
			PatientEditorPanel pep = new PatientEditorPanel(patient);
			Window.getInstance().setContentPane(pep);
			pep.getTextField_dni().requestFocus();
			((JPanel) Window.getInstance().getContentPane()).updateUI();
		}
		else if (e.getSource() == menuDelete)
		{

		}
		else if (e.getSource() == menuSendEmail)
		{

		}
		else if (e.getSource() == menuConnect)
		{

		}
	}
}
