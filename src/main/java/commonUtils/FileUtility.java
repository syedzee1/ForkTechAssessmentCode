package commonUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author Syed Zeeshan
 *
 */
public class FileUtility {
	/**
	 * its is used get common data from property file based on the key which you have specified as a argument
	 * @return
	 */
	public String getPropertValue(String key){
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(ConstantData.filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties pres=new Properties();
		try {
			pres.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = pres.getProperty(key);
		return value;
	}
}
