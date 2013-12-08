package interfaces;

/**
 * @author Jordan Aranda Tejada
 */
public interface Loader {

	/**
	 * Method to notify loadable components.
	 * 
	 * @param loadable The loadable component
	 * @param object The object to notify to component
	 */
	public void notifyLoadables(Loadable loadable, Object object);
}