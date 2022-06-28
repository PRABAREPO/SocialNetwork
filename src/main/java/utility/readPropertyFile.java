package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class readPropertyFile {

	String PropertyValue;

	public String readPropertyData(String propertyKey) throws IOException {

		FileInputStream input = new FileInputStream("./testdata/config.properties");

		Properties prop = new Properties();

		prop.load(input);

		return PropertyValue = prop.getProperty(propertyKey);

	}
}
