package dateex;

import java.util.Calendar;

public class CalendarEx {

	// DB 타입 : 2020-03-18:22:11:05
	public static void printCalender(String msg, Calendar cal) {
		int year = cal.get(Calendar.YEAR); // 2020
		int month = cal.get(Calendar.MONTH) + 1; // 4(0부터 나오기때문에 +1해줘야 함
		// DB 타입에 맞추기 위해 0 붙임.
		String mon = (month < 10) ? "0" + month : "" + month; // 3항 연산자 (DECODE)
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR);
		String h = (hour < 10) ? "0" + hour : "" + hour;
		int minte = cal.get(Calendar.MINUTE);
		String m = (minte < 10) ? "0" + minte : "" + minte;
		int second = cal.get(Calendar.SECOND);
		String s = (second < 10) ? "0" + second : "" + second;
		System.out.println(year + "-" + mon + "-" + day + ":" + hour + ":" + minte + ":" + second);
	}

	public static void main(String[] args) {
		Calendar now = Calendar.getInstance(); // 싱글톤
		printCalender("현재", now);
	}
}
