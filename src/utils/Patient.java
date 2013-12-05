package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import display.components.Loadable;
import display.components.Loader;
import entities.Sensor;
import entities.User;

/**
 * @author Jordan Aranda Tejada
 */
public class Patient implements Serializable, Loader {

	private static final long			serialVersionUID	= - 7186367905563055140L;

	private static Patient				currentPatient;
	private static ArrayList<Loadable>	loadables			= new ArrayList<Loadable>();

	private Patient()
	{

	}

	/**
	 * Gets the sensors from the server
	 * 
	 * @param loadable The loadable component
	 */
	public void getSensors(final Loadable loadable)
	{
		//@formatter:off
		(new Thread()
		{

			@Override
			public void run()
			{
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
				notifyLoadables(loadable, vSensors);
				// menu.notify(vSensors);
			}
		}).start();
		//@formatter:on
	}

	/**
	 * @param loadable The loadable component
	 * @param idSensor The sensor id
	 */
	public void getHistoric(final Loadable loadable, final int idSensor)
	{
		//@formatter:off
		(new Thread()
		{

			@Override
			public void run()
			{
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
				notifyLoadables(loadable, vHistoric);
				// return vHistoric;
				//@formatter:on
			}
		}).start();
	}

	/**
	 * @param loadable The loadable component
	 * @param idSensor The sensor
	 * @param enable The new status
	 * @return The response code
	 */
	public void setSensorStatus(final Loadable loadable, final int idSensor,
	final boolean enable)
	{
		//@formatter:off
		(new Thread()
		{

			@Override
			public void run()
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
				int code = User.getCurrent().getClient().getInputCode(response);
				notifyLoadables(loadable, code);
			}
		}).start();
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
	 * @param loadable The loadable component
	 */
	public void getFoto(Loadable loadable)
	{
		//@formatter:off
		(new Thread()
		{

			@Override
			public void run()
			{
				User.getCurrent().getClient().sendData("GET_FOTO");
				User.getCurrent().getClient().getInputFile();
			}
		}).start();
		//@formatter:on
	}

	/**
	 * @return The current patient
	 */
	public static Patient getCurrent()
	{
		if (currentPatient == null)
		{
			currentPatient = new Patient();
		}
		return currentPatient;
	}

	@Override
	public void addLoadable(Loadable loadable)
	{
		loadables.add(loadable);
	}

	@Override
	public void deleteLoadable(Loadable loadable)
	{
		loadables.remove(loadable);
	}

	@Override
	public void notifyLoadables(Loadable loadable, Object object)
	{
		loadable.update(object);
	}
}