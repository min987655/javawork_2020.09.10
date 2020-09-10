package dateex;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

// (구버전 자바 1.7 이하)날짜 패키지들
// java.sql.Date 
// java.sql.Time (X) 
// java.sql.Timestamp
// java.util.Calender (X)
// java.util.Date

// 신버전 자바 1.8 이상
// java.time.LocalDate
// java.time.LocalDateTime
// java.time.LocalTime

public class TimeEx01 {

	public static void main(String[] args) {
		// 1. Calendar 안씀
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String today = formater.format(cal.getTime());
		System.out.println("cal.getTime() : " + cal.getTime());
		System.out.println("today : " + today);

		// 2. java.util.Date 안씀
		// Date는 1970.01.01 기준으로 경과된 밀리초를 계산
		Date time2 = new Date();
		System.out.println("time2 : " + time2);
		System.out.println("time2.getTime() : " + time2.getTime());
//		System.out.println("time2.getDay() : "+time2.getDay()); // 안씀

		// 3. java.sql.Date 안씀
		java.sql.Date time3 = new java.sql.Date(24 * 3600 * 1000);
		System.out.println("time3 : " + time3);

		// 4. java.sql.Timestamp : DB에도 있기때문에 연동 쉬움.
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date();
		String timestamp = sf.format(d.getTime());
		Timestamp ts = Timestamp.valueOf(timestamp);
		System.out.println("ts : " + ts);

		// 5. LocalDateTime : DB는 안들고있음 -> LocalDate를 TimeStamp에 담아서 디비에 넘김
		// (LocalDateTime to Timestamp)
		Timestamp nowDate = Timestamp.valueOf(LocalDateTime.now()); // LocalDate --> TimeStamp
		System.out.println("nowDate : " + nowDate);

		// ((Timestamp to LocalDateTime)
		LocalDateTime ldt = nowDate.toLocalDateTime();
		System.out.println("ldt : " + ldt);

		System.out.println("LocalDateTime : " + LocalDateTime.now());
		System.out.println("LocalDate : " + LocalDate.now());
		System.out.println("LocalTime : " + LocalTime.now());
	}

}
