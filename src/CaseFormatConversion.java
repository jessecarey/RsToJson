import com.google.common.base.CaseFormat;
import com.google.common.base.CaseFormat.*;

public class CaseFormatConversion {
	
	public CaseFormatConversion(){
		;
	}
	public static void main(String[] args){
		String testString = "testStringTwo";
		String loweCam = testString;
		String upperCam;
		String lowerHyph;
		String lowerUnder;
		String upperUnder;
		
		lowerHyph = (CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, testString));
		lowerUnder = (CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, testString));
		upperCam = (CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, testString));
		upperUnder = (CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, testString));
		
		System.out.println(loweCam +"," + upperCam +"," + lowerHyph +"," + lowerUnder +"," + upperUnder);
		
	}
	
	public static String toReadable(String input){
		String readable = new String();
		readable = input.replace("_", " ");

		
		return readable;
	}

}
