package components;

/**
 * Simple way to change the language of the component.
 * 
 * @author Jordan Aranda Tejada
 */
public interface Internationalizable 
{
	/**
	 * Changes the text of the component to reflect the new language setting.
	 * 
	 * @param newText The new text to set
	 */
	public void changeLanguage(String newText);
}