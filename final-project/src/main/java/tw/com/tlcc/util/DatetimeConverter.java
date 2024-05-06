package tw.com.tlcc.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeConverter {
	public static String toString(Date datetime, String format) {
		String result = "";
		try {
			if (datetime != null) {
				result = new SimpleDateFormat(format).format(datetime);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Date parse(String datetime, String format) {
		Date result = new Date();
		try {
			if(datetime != null) {
				result = new SimpleDateFormat(format).parse(datetime);
				return result;
			}else
			result = null;
		} catch (Exception e) {
//			result = new Date();
			result = null;
			e.printStackTrace();
		}
		return result;
	}
}
