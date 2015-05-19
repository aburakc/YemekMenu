package yemekmenu.servlet;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import yemekmenu.calendar.YemekMenuCalendar;

public class CalendarTest {

	@Test
	public void test() {
		Calendar cal = Calendar.getInstance();
		cal.set(2015, 4, 19);
		
		YemekMenuCalendar.addMenu(cal.getTime(), new String[]{"1","2","3"});
		
		System.out.println(YemekMenuCalendar.getInstance().toString());
	}

}
