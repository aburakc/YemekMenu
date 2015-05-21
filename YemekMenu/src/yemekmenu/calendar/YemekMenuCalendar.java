package yemekmenu.calendar;

import java.util.Date;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.CompatibilityHints;

public class YemekMenuCalendar {
	private static Calendar calendar;

	public static Calendar getInstance() {
		if (getCalendar() == null){
			setCalendar(new Calendar());
			CompatibilityHints.setHintEnabled("KEY_RELAXED_UNFOLDING", true);
		}

		return getCalendar();
	}

	public static void cleanCalendar(){
		getInstance().getComponents().clear();
	}
	
	public static void addMenu(Date date, String[] menu) {
		java.util.Calendar newCal = java.util.Calendar.getInstance();
		newCal.setTime(date);
		newCal.add(java.util.Calendar.DAY_OF_MONTH, 1);
		VEvent menuEvent = new VEvent(new net.fortuna.ical4j.model.Date(newCal.getTime()),
				"TUBITAK YEMEK MENUSU");
		menuEvent.getProperties().getProperty(Property.DTSTART).getParameters()
				.add(Value.DATE);

		Uid uid = new Uid(date.getTime() + "TUBITAK");
		menuEvent.getProperties().add(uid);
		StringBuilder sb = new StringBuilder();
		for (String str : menu) {
			sb.append(str);
			sb.append("\r\n");
		}
		Description description = new Description(sb.toString());
		menuEvent.getProperties().add(description);
		getInstance().getComponents().add(menuEvent);
	}

	public static Calendar getCalendar() {
		return calendar;
	}

	public static void setCalendar(Calendar calendar) {
		YemekMenuCalendar.calendar = calendar;
	}

}
