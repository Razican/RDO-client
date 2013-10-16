package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

	/**
	 * Create the panel.
	 * 
	 * @param patient Selected patient
	 */
	public PatientTablePopup(Patient patient)
	{
		this.patient = patient;

		menuEdit = new JMenuItem("Modificar");
		menuEdit.setIconTextGap(10);
		menuEdit.setHorizontalAlignment(SwingConstants.LEFT);
		menuEdit.setForeground(Color.BLACK);
		menuEdit.setMargin(new Insets(5, 5, 5, 5));
		menuEdit.setIcon(new ImageIcon("img/popup_icons/edit-icon.png"));
		menuEdit.addActionListener(this);
		menuEdit.setFont(new Font("Calibri", Font.PLAIN, 16));

		menuDelete = new JMenuItem("Eliminar");
		menuDelete.setIconTextGap(10);
		menuDelete.setHorizontalAlignment(SwingConstants.LEFT);
		menuDelete.setForeground(Color.BLACK);
		menuDelete.setMargin(new Insets(5, 5, 5, 5));
		menuDelete.setIcon(new ImageIcon("img/popup_icons/delete-icon.png"));
		menuDelete.addActionListener(this);
		menuDelete.setFont(new Font("Calibri", Font.PLAIN, 16));

		menuSendEmail = new JMenuItem("Enviar email");
		menuSendEmail.setIconTextGap(10);
		menuSendEmail.setHorizontalAlignment(SwingConstants.LEFT);
		menuSendEmail.setForeground(Color.BLACK);
		menuSendEmail.setMargin(new Insets(5, 5, 5, 5));
		menuSendEmail.setIcon(new ImageIcon(
		"img/popup_icons/send-email-icon.png"));
		menuSendEmail.addActionListener(this);
		menuSendEmail.setFont(new Font("Calibri", Font.PLAIN, 16));

		add(menuEdit);
		add(menuDelete);
		add(menuSendEmail);
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
			int option = JOptionPane.showOptionDialog(Window.getInstance(),
			"¿Estas seguro de querer borrar el paciente: " + patient.getName()
			+ " " + patient.getLastName() + "?", "Eliminar paciente",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
			new Object[] {"Si", "No"}, "Si");
			if (option == 0)
			{
				// Borrar paciente
			}

		}
		else if (e.getSource() == menuSendEmail)
		{

		}
	}
}
