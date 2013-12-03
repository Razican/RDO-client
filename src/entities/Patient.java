package entities;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 * @author Jordan Aranda Tejada
 */
public class Patient implements Serializable {

	private static final long	serialVersionUID	= - 7186367905563055140L;

	/**
	 * @return The vector with sensors
	 */
	public static Vector<Sensor> getSensors()
	{
		//@formatter:off
		Vector<Sensor> vSensors = new Vector<Sensor>();
		
		User.getCurrent().getClient().sendData("LISTSENSOR");
		String sensorString = User.getCurrent().getClient().getInputData("322 OK Lista finalizada.");
		System.out.println(sensorString);
		
		String [] sensors = sensorString.split("#");
		for (int i = 1; i < sensors.length; i++)
		{
			System.out.println("Sensor " + i +":" + sensors[i]);
			String [] attributes = sensors[i].split(";");
			vSensors.add(new Sensor(Integer.parseInt(attributes[0]), attributes[1], attributes[2].equals("ON")));
		}
		return vSensors;
		//@formatter:on
	}

	/**
	 * @param idSensor The sensor id
	 * @return The vector with historic lines (the first position has the
	 *         response code).
	 */
	public static Vector<String> getHistoric(int idSensor)
	{
		//@formatter:off
		Vector<String> vHistoric = new Vector<String>();

		User.getCurrent().getClient().sendData("HISTORICO " + idSensor);
		String historicString = User.getCurrent().getClient().getInputData("322 OK Lista finalizada.");
		System.out.println(historicString);
		
		String[] historicLines = historicString.split("#");
		for (int i = 0; i < historicLines.length; i++)
		{
			System.out.println(i + ":" + historicLines[i]);
			vHistoric.add(historicLines[i]);
		}
		return vHistoric;
		//@formatter:on
	}

	/**
	 * @param idSensor The sensor
	 * @param enable The new status
	 * @return The response code
	 */
	public static int setSensorStatus(int idSensor, boolean enable)
	{
		if (enable)
		{
			User.getCurrent().getClient().sendData("ON " + idSensor);
		}
		else
		{
			User.getCurrent().getClient().sendData("OFF " + idSensor);
		}
		String response = User.getCurrent().getClient().getInputData();
		System.out.println(response);
		return User.getCurrent().getClient().getInputCode(response);
	}

	/**
	 * @param enable The new status
	 * @return The response code
	 */
	public static int setGPSStatus(boolean enable)
	{
		if (enable)
		{
			User.getCurrent().getClient().sendData("ONGPS");
		}
		else
		{
			User.getCurrent().getClient().sendData("OFFGPS");
		}
		String response = User.getCurrent().getClient().getInputData();
		System.out.println(response);
		return User.getCurrent().getClient().getInputCode(response);
	}

	/**
	 * @param idSensor The sensor
	 * @return The response data
	 */
	public static String getSensorValue(int idSensor)
	{
		User.getCurrent().getClient().sendData("GET_VALACT " + idSensor);
		return User.getCurrent().getClient().getInputData();
	}

	/**
	 * @return The foto
	 */
	public static ImageIcon getFoto()
	{
		return null;
	}
}