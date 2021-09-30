package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class DataHandlers implements RelativePaths
{
public static String getDataFromPropertyFile(String key) {
		
		String data = null;
		try {
			File f = new File(config_properties_path);
			FileInputStream fis = new FileInputStream(f);
			Properties prop = new Properties();
			prop.load(fis);
			data = (String)prop.get(key);
			
		}catch (Exception e) {
			
			System.out.println("Exception while reading data from properties file " + e.getMessage());
			e.printStackTrace();

		}
		return data;
	}

}
