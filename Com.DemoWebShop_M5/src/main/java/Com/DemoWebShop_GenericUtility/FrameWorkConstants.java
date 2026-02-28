package Com.DemoWebShop_GenericUtility;

public interface FrameWorkConstants {

	JavaUtility javaUtility = new JavaUtility();

	static final String propertyPath = "./src/test/resources/testData/dataM5.properties";

	static final String excelPath = "./src/test/resources/testData/dataM5.xlsx";

	static final String screenshotPath = "./screenshots/" + javaUtility.dateAndTime() + ".png";

	static final String reportsPath = "./reports/" + javaUtility.dateAndTime() + ".html";

}
