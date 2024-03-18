package com.csgoempire.automation.utilities;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// A simple class which simply formats today's date in a specific format. In our code
// it is only used for the screenshots' naming, but in general it is still a good practice
// to separate it, since it would most likely grow in real life pretty soon.
public final class TimeService {
	
	public static String getCurrentTimeAsString() {
		return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss"));
	}
	
}
