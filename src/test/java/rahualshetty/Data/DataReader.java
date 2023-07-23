package rahualshetty.Data;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class DataReader {

	public void getJsonData() throws Throwable {

		FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "\\src\\test\\java\\rahualshetty\\Data\\PurchaseOrder.json"));
	}
}
