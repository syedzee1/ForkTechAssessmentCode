package commonUtils;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author Syed Zeeshan
 *
 */
public class JavaUtility {
	/**
	 * its used to generate a random number
	 * @return
	 */
	public int getRandomNum() {
		Random random=new Random();
		int randNum = random.nextInt(1000);
		return randNum;
	}
	
}

