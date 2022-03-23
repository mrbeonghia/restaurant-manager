package com.salon.custom.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.salon.custom.util.Constant.TEN_MINUTES_BLOCK;

public class DateUtils {

    /*public static String getDayOfWeek(Date date) {
        return new SimpleDateFormat("EEE").format(date);
    }*/

    public static Date convertStringToDate(Date date, String time, Integer minutes) {
        if (addTime(time, minutes) != null) {
            return convertToDate(date, addTime(time, minutes));
        } else {
            return null;
        }
    }

    public static Date get5AMTomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 5);

        return cal.getTime();
    }

    public static Date addTimeEnd(Date date, Integer minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date convertStringToDate(String date, String time) {
        try {
            Date dateNew = new SimpleDateFormat("yyyy/MM/dd").parse(date);
            return convertToDate(dateNew, time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStrToDate(String date) {
        try {

            return new SimpleDateFormat("yyyy/MM/dd").parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date convertStringDate(String date, String time) {
        try {
            Date dateNew = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            return convertToDate(dateNew, time);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToDate(String date) {
        try {
            Date dateNew = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            return getStartTimeOfDay(dateNew);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToFullDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertStringToDateJapan(String date) {
        try {
            Date dateNew = new SimpleDateFormat("MM/dd/yyyy").parse(date);
            return getStartTimeOfDay(dateNew);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date convertDateOfYear(Integer dayOfYear, Integer year, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        calendar.set(Calendar.YEAR, year);
        return convertToDate(calendar.getTime(), time);
    }


    public static String convertDateToString(Date date) {
        if (date == null) return null;
        String pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String convertDateToStringJapan(Date date) {
        if (date == null) return null;
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatDateToString(Date date) {
        String pattern = "yyyy/MM/dd HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatTimeToString(Date date) {
        if (date == null) return null;
        String pattern = "HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatDateJapanToString(Date date) {
        if (date == null) {
            return null;
        }
        String pattern = "MM/dd/yyyy HH:mm";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatToDateString(Date date) {
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static String formatDateToDateTimeString(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public static Date convertToDate(Date date, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] arrayTime = time.split(":");
        if (arrayTime.length > 1) {
            calendar.set(Calendar.MINUTE, Integer.parseInt(arrayTime[1]));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arrayTime[0]));
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    //staff can work in store after 24:00
    public static Date convertEndTimeWorkStore(Date date, String time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] arrayTime = time.split(":");
        if (arrayTime.length > 1) {
            int hour = Integer.parseInt(arrayTime[0]);
            if (hour <= 5) {
                calendar.add(Calendar.DATE, 1);
            }
            calendar.set(Calendar.MINUTE, Integer.parseInt(arrayTime[1]));
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        } else {
            return null;
        }
    }

    public static String getDateString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(date);
    }

    private static String addTime(String time, Integer minutes) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date d = df.parse(time);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, minutes);
            return df.format(cal.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static String convert(Date date) {
        String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);
        return simpleDateFormat.format(date);
    }

    public static LocalDate covertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date covertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getStartTimeOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    public static Date getEndTimeOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    private static Calendar getFrom(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;
    }

    public static Date getTimeEndOfDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 0);
        c.set(Calendar.MILLISECOND, 999);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.HOUR_OF_DAY, 23);

        return c.getTime();
    }

    public static int get(int calendarConst, Date date) {
        Calendar c = getFrom(date);
        return c.get(calendarConst);
    }

    public static Date getStartTimeOfStore(Date date) {
        if (date == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 5);

        return c.getTime();
    }

    public static Date getEndTimeOfStore(Date date) {
        Calendar c = getFrom(date);
        c.add(Calendar.DATE, 1);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 5);

        return c.getTime();
    }

    public static Date getFirstDayOfMonth(Integer month, Integer year) {
        Date date = new Date(0);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.YEAR, year - c.get(Calendar.YEAR));
        c.add(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTime();
    }

    public static Date getLastDayOfMonth(Integer month, Integer year) {
        Date date = new Date(0);
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.YEAR, year - c.get(Calendar.YEAR));
        c.add(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, 0);
        c.set(Calendar.DATE, 0);
        c.set(Calendar.MILLISECOND, 999);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.HOUR_OF_DAY, 23);
        return c.getTime();
    }

    public static Date getMondayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    public static Date getMondayNextTwoWeeks(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.DATE, 14);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    public static Date getDateNextWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 7);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }


    public static Date getDateFromDayOfWeek(Date date, Integer dayOfWeek) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_WEEK, dayOfWeek);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    public static Integer getDayOfWeekFromDate(Date date) {
        if (date == null) return null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // first day in DayOfWeek is Monday but Calendar is Sunday
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    public static Date get30DaysAgo(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, -30);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);

        return c.getTime();
    }

    public static Date roundSecondOfTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }

    public static Integer getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    public static Integer getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static Date addMinutesToDate(Date date, int minutes) {
        if (date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return c.getTime();
    }

    public static long getMinutesBetweenTwoDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) return 0;
        long milliseconds = Math.abs(startDate.getTime() - endDate.getTime());
        return TimeUnit.MILLISECONDS.toMinutes(milliseconds);
    }

    public static Date getTimeCanCancelBooking(Date date) {
        if (date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, -2);
        return c.getTime();
    }

    public static Date getEndTimeBooking(Date date) {
        if (date == null) return null;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, 3);
        return c.getTime();
    }

    public static int totalMinutes(Date date1, Date date2) {
        if (date1.after(date2)) {
            return (int) TimeUnit.MILLISECONDS.toMinutes(date1.getTime() - date2.getTime());
        } else if (date1.before(date2)) {
            return (int) TimeUnit.MILLISECONDS.toMinutes(date2.getTime() - date1.getTime());
        } else {
            return 0;
        }
    }

    public static int subtractMinutes(Date date1, Date date2) {
        if (date1 == null || date2 == null) return 0;
        if (date1.before(date2)) {
            return (int) TimeUnit.MILLISECONDS.toMinutes(date2.getTime() - date1.getTime());
        } else {
            return 0;
        }
    }

    public static Date sallowCopy(Date date) {
        return getFrom(date).getTime();
    }

    /**
     * set Hour and Minute to Date with Pattern of HourAndMinute is HH:mm
     *
     * @param date
     * @param hourAndMinutes
     * @return
     */
    public static Date setHourAndMinute(Date date, String hourAndMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] strings = hourAndMinutes.split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strings[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(strings[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * round `time` to `closest time` satisfy interval of 2 time smaller 15 minutes
     * and must longer than 5 minutes (5min < interval < 15min)
     *
     * @param date
     * @return
     */
    public static Date round(Date date) {
        int PADDING_MINUTES_MIN_UNTIL_START = 5;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);
        int resultMinutes;
        int downRoundMinutes = minutes / TEN_MINUTES_BLOCK * TEN_MINUTES_BLOCK;

        if (minutes - downRoundMinutes <= PADDING_MINUTES_MIN_UNTIL_START) {
            resultMinutes = downRoundMinutes + TEN_MINUTES_BLOCK;
        } else {
            resultMinutes = downRoundMinutes + 2 * TEN_MINUTES_BLOCK;
        }

        calendar.set(Calendar.MINUTE, resultMinutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date roundTo0or5Minutes(Date date) {
        int block5Minutes = 5;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minutes = calendar.get(Calendar.MINUTE);

        int downRoundMinutes = minutes / block5Minutes * block5Minutes;
        int resultMinutes = downRoundMinutes + block5Minutes;

        calendar.set(Calendar.MINUTE, resultMinutes);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static Date addMinutes(Date date, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, number);

        return calendar.getTime();
    }

    /**
     * calendarConst is MONTH, DATE, ...
     * usage example: Calendar.MONTH
     *
     * @param date
     * @param calendarConst
     * @param number
     * @return
     */
    public static Date plus(Date date, int calendarConst, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarConst, number);

        return calendar.getTime();
    }

    public static Date minus(Date date, int calendarConst, int number) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarConst, -number);

        return calendar.getTime();
    }

    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }



    public static boolean isToday(Date date) {
        Date today = getStartTimeOfDay(new Date());
        Date date1 = getStartTimeOfDay(date);

        return today.equals(date1);
    }

    public static void main(String[] args) {
        Calendar c = getFrom(new Date());
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
    }


    public static boolean beforeOrEquals(Date date1, Date date2) {
        return date1.before(date2) || date1.equals(date2);
    }

    public static boolean afterOrEquals(Date date1, Date date2) {
        return date1.after(date2) || date1.equals(date2);
    }

    public static boolean isBetween(Date origin, Date date1, Date date2) {
        return (afterOrEquals(origin, date1) && beforeOrEquals(origin, date2)) //
                || (afterOrEquals(origin, date2) && beforeOrEquals(origin, date1));
    }

    public static Date removeSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static boolean isInterSect(Date start1, Date end1, Date start2, Date end2) {
        return isBetween(start2, start1, end1)
                || isBetween(end2, start1, end1)
                || isBetween(start1, start2, end2);
    }

    public static Date getFirstDayOfNextMonth() {
        Calendar c = getFrom(new Date());
        int currentMonth = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, currentMonth + 1);
        c.set(Calendar.DATE, 1);
        resetToStartOfDay(c);

        return c.getTime();
    }

    public static Date getLastDayOfNextMonth() {
        Calendar c = getFrom(new Date());
        int currentMonth = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, currentMonth + 2);
        c.set(Calendar.DATE, 1);
        resetToStartOfDay(c);
        c.add(Calendar.DATE, -1);

        return c.getTime();
    }

    private static void resetToStartOfDay(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }

    public static Integer getDayOfWeekOfNextDay(Integer currentDayOfWeek) {
        if(currentDayOfWeek < 7) {
            return currentDayOfWeek + 1;
        } else {
            return 1;
        }
    }

    public static Date getFirstDayOfNext2Month() {
        Calendar c = getFrom(new Date());
        int currentMonth = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, currentMonth + 2);
        c.set(Calendar.DATE, 1);
        resetToStartOfDay(c);

        return c.getTime();
    }
}
