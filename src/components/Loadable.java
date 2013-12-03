package components;

import java.util.Vector;

import entities.Sensor;

/**
 * Simple way to change the data of the component.
 * 
 * @author Jordan Aranda Tejada
 */
public interface Loadable {

	/**
	 * @param sensors The new sensors to set
	 */
	public void notifySensors(Vector<Sensor> sensors);

	/**
	 * @param sensors The new text to set
	 */
	public void notifyHistoric(Vector<String> historic);
}
