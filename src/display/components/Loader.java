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
	 * @param loadable The loadable component
	 * @param object The object to notify to component
	 */
	public void notifyLoadables(Loadable loadable, Object object);
}
