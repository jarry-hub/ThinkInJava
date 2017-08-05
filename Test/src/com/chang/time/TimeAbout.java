package com.chang.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeAbout {
	
	static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws ParseException {
		aboutDate();
		aboutLong();
		aboutCalendar();
		aboutString();
	}
	
	public static void aboutDate() {
		Date currentTime = new Date();
		//dateת��Ϊstring
		String dateString = dateformat.format(currentTime);
		System.out.println("data turn into string: " + dateString);

		//dateת��Ϊlong
		long datesec = currentTime.getTime();
		System.out.println("data turn into long: " + datesec);

		//dateת��Ϊcalendar
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentTime);
		System.out.println("data turn into calendar: " + calendar);
		System.out.println("long turn into calendar.get(calendar.YEAR): " + calendar.get(calendar.YEAR));
		
		System.out.println("-----------------------");
	}
	
	public static void aboutLong() {
		//������1970��1��1��00:00:00GMT�����ĺ�������
		long msec = System.currentTimeMillis();
		System.out.println("long itself: " + msec);

		//longת��Ϊstring
		String msecString = dateformat.format(msec);
		System.out.println("long turn into string: " + msecString);

		//longת��ΪDATE
		Date longToDate= new Date(msec);
		System.out.println("long turn into date: " + longToDate);

		//longת��Ϊcalendar
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(msec);
		System.out.println("long turn into calendar: " + ca);
		System.out.println("long turn into calendar.get(ca.YEAR): " + ca.get(ca.YEAR));
		
		System.out.println("-----------------------");
	}
	
	public static void aboutCalendar() {
		Calendar calendar = Calendar.getInstance();
		/* �������õ�����Ϊ2016��5��1�� */
		calendar.set(2016, 4, 1);  //�ı������գ�
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		//calendarת��Ϊdate
		Date date = calendar.getTime();
		System.out.println("calendar turn into date: " + date);

		//calendarת��Ϊlong
		long time = calendar.getTimeInMillis();
		System.out.println("calendar turn into long: " + time);

		System.out.println("-----------------------");
	}
	
	public static void aboutString() throws ParseException {
		//String ת��Date 
		String str = "2010-5-27";
		SimpleDateFormat dataStr = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = dataStr.parse(str);
		System.out.println("string turn into date: " + birthday);
	}
	
}
