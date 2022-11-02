package properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import log.Log;

public class PropertiesClass {
	
	public static Properties properties = new Properties();
	
	public static void getProperties() {
		try {
			properties.load(new FileInputStream(new File("properties/conf.properties")));
		} catch (FileNotFoundException e) {
			Log.logger.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
        	Log.logger.log(Level.SEVERE, e.getMessage());
        }
	}
}
