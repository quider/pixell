package pl.quider.pixell;

import java.io.*;
import java.util.Properties;

/**
 * Register class hanldes properties for this programe.
 * 
 * @author akozlowski
 *
 */
public class Register {
	private static Register instance = null;
	private Properties p;

	private Register() {
		p = new Properties();
		File propertiesFile = new File("properties.props");
		try {
			if (!propertiesFile.exists()) {
				propertiesFile.createNewFile();
			}
			p.load(new FileReader(propertiesFile));
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * get Singleton instance of Register
	 * @return
	 */
	public static Register getInstance(){
		if(instance == null){
			instance = new Register();
		}
		return instance;
	}
	
	/**
	 * Gets property given in name parameter. If property doesn't exist create new
	 * with default value
	 * @param name key
	 * @param def default value
	 * @return if property exists returns value otherwise returns def
	 */
	public String getProperty(String name, String def){
		if(p.containsKey(name)){
			return p.get(name).toString();
		} else {
			if(def!=null)
				setProperty(name, def);
			return def;
		}
	}
	
	/**
	 * Sets property
	 * @param name
	 * @param value
	 */
	public void setProperty(String name, String value){
		p.setProperty(name, value);
	}
	
	/**
	 * Persists properties to file.
	 */
	public void save(){
		try {
			p.store(new FileWriter(new File("properties.props")), "W�a�ciwo�ci");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
