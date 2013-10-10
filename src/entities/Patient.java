package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jordan Aranda Tejada
 */
public class Patient implements Serializable {

	private static final long	serialVersionUID	= - 7186367905563055140L;

	private int					dni;
	private String				ip;
	private int					port;
	private String				name;
	private String				lastName;
	private Date				birthdate;
	private String				address;
	private int					telephone;
	private String				email;

	/**
	 * Default contructor
	 */
	public Patient()
	{

	}

	/**
	 * @param dni Patient identification number
	 * @param ip Patient ip
	 * @param port Patient port
	 * @param name Patient name
	 * @param lastName Patient last name
	 * @param birthdate Patient birth date
	 * @param address Patient address
	 * @param zipCode Patient zip code
	 * @param telephone Patient telephone number
	 * @param email Patient email
	 */
	public Patient(int dni, String ip, int port, String name, String lastName,
	Date birthdate, String address, int telephone, String email)
	{
		this.dni = dni;
		this.ip = ip;
		this.port = port;
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
	}

	/**
	 * @return Patient identification number
	 */
	public int getDni()
	{
		return dni;
	}

	/**
	 * @param dni Patient new identification number
	 */
	public void setDni(int dni)
	{
		this.dni = dni;
	}

	/**
	 * @return Patient ip
	 */
	public String getIp()
	{
		return ip;
	}

	/**
	 * @param ip Patient new ip
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	/**
	 * @return Patient port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * @param port Patient new port
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * @return Patient name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name Patient new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return Patient last name
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @param lastName Patient new last name
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @return Patient birth date
	 */
	public Date getBirthdate()
	{
		return birthdate;
	}

	/**
	 * @param birthdate Patient new birth date
	 */
	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	/**
	 * @return Patient address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * @param address New patient address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return telephone Patient telephone number
	 */
	public int getTelephone()
	{
		return telephone;
	}

	/**
	 * @param telephone New patient telephone number
	 */
	public void setTelephone(int telephone)
	{
		this.telephone = telephone;
	}

	/**
	 * @return email Patient email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email New patient email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param patient The patient
	 * @return The letter of the patient dni
	 */
	public static char getDniLetter(Patient patient)
	{
		String characters = "TRWAGMYFPDXBNJZSQVHLCKET";
		int module = patient.dni % 23;
		return characters.charAt(module);
	}
}