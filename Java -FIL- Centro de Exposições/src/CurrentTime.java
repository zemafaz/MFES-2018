
import java.util.*;
import java.util.Date;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class CurrentTime {
  public static Number getDay() {

	  	Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today); // don't forget this if date is arbitrary
		int day = cal.get(Calendar.DAY_OF_MONTH);
		return day;
  }

  public static Number getMonth() {

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today); // don't forget this if date is arbitrary
		int month = cal.get(Calendar.MONTH);

		return month+1;
  }

  public static Number getYear() {

		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today); // don't forget this if date is arbitrary
		int year = cal.get(Calendar.YEAR);

		return year;
  }

  public CurrentTime() {}

  public String toString() {

    return "CurrentTime{}";
  }
}
