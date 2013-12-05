package display.components;

/**
 * @author Jordan Aranda Tejada
 */
public interface Loader {

	/**
	 * @param loadable Loadable component
	 */
	public void addLoadable(Loadable loadable);

	/**
	 * @param loadable Loadable component
	 */
	public void deleteLoadable(Loadable loadable);

	/**
	 * Method to notify loadable components.
	 * 
	 * @param object The object to notify to components
	 */
	public void notifyLoadables(Object object);
}
