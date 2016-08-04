package testApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * The DateDifference class implements an application that finds the number of
 * days between the two dates(during the years 1901 and 2999, inclusive).
 * 
 * @param: List<DateBean>
 */
public class DateDifference {

	public static void calculateDays(List<DateBean> datestring) throws ParseException {
		DateBean element = new DateBean();// POJO class for storing startDate & EndDate 
		Date dateStart = new Date();
		Date dateEnd = new Date();
		DateFormat f = DateFormat.getDateInstance();
		for (int i = 0; i < datestring.size(); i++) {
			element = datestring.get(i);
			int diffInDays = 0;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"MM,dd,yyyy");
			dateStart = simpleDateFormat.parse(element.getStartDate());
			Calendar calendar1 = Calendar.getInstance();
			calendar1.setTime(dateStart);
			dateEnd = simpleDateFormat.parse(element.getEndDate());
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(dateEnd);
			diffInDays = daysBetween(dateStart, dateEnd);
			if (calendar1.get(Calendar.YEAR) >= 1901
					&& calendar1.get(Calendar.YEAR) <= 2999
					&& calendar2.get(Calendar.YEAR) >= 1901
					&& calendar2.get(Calendar.YEAR) <= 2999) {
				System.out.println("There's " + diffInDays + " days between "
						+ f.format(dateStart.getTime()) + " and "
						+ f.format(dateEnd.getTime()) + ".");
			} else {
				System.out
						.println("Date entered year doesn't lie between 1901 to 2999,Please enter correct date ");
			}
		}
	}

	public static int daysBetween(Date dateStart, Date dateEnd) {

		int diffInDays = 0;
		if (dateStart.before(dateEnd)) {
			diffInDays = (int) ((dateEnd.getTime() - (dateStart.getTime() + (1000 * 60 * 60 * 24))) / (1000 * 60 * 60 * 24));

		} else {
			diffInDays = (int) ((dateStart.getTime() - (dateEnd.getTime() + (1000 * 60 * 60 * 24))) / (1000 * 60 * 60 * 24));

		}
		return diffInDays;
	}

	public static void main(String args[]) throws ParseException {
		List<DateBean> datestring = new ArrayList<DateBean>();
		DateBean element = new DateBean();
		try {
			Scanner stdin = new Scanner(System.in);

			do {
				System.out.println("Add more? (y/n)");
				if (stdin.next().startsWith("y")) {
					System.out.println("Enter start date: ");
					element.setStartDate(stdin.next());
					System.out.println("Enter end date: ");
					element.setEndDate(stdin.next());
					datestring.add(element);
				} else {
					break;
				}
			} while (true);
			if (!datestring.isEmpty()) {
				calculateDays(datestring);
			} else {
				System.out.println("No Output as list was empty");
			}
		} catch (ParseException pe) {
			System.out
					.println("Date Format is incorrect please enter date in given format (mm/dd/yyyy)");
		} catch (Exception e) {
			System.out.println("Please try again with relevant data input");
		}
	}
}
