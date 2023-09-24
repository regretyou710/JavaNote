```
@Test
public void test() throws ParseException {
    System.out.println(System.currentTimeMillis());//1695283270326

    Date date = new Date();
    System.out.println(date.getTime());//1695282989551
    System.out.println(date);//Thu Sep 21 15:56:29 CST 2023

    java.sql.Date date1 = new java.sql.Date(date.getTime());
    System.out.println(date1);//2023-09-21

    Calendar calendar = Calendar.getInstance();
    System.out.println(calendar.getTime());//Thu Sep 21 15:57:27 CST 2023
    System.out.println(calendar);//java.util.GregorianCalendar[time=1695283047607,areFieldsSet=true,areAllFieldsSet=true,lenient=true,zone=sun.util.calendar.ZoneInfo[id="Asia/Taipei",offset=28800000,dstSavings=0,useDaylight=false,transitions=42,lastRule=null],firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,YEAR=2023,MONTH=8,WEEK_OF_YEAR=38,WEEK_OF_MONTH=4,DAY_OF_MONTH=21,DAY_OF_YEAR=264,DAY_OF_WEEK=5,DAY_OF_WEEK_IN_MONTH=3,AM_PM=1,HOUR=3,HOUR_OF_DAY=15,MINUTE=57,SECOND=27,MILLISECOND=607,ZONE_OFFSET=28800000,DST_OFFSET=0]

    SimpleDateFormat sdf = new SimpleDateFormat();
    System.out.println(sdf.format(date));//2023/9/21 下午3:58

    LocalTime localTime = LocalTime.now();
    LocalDate localDate = LocalDate.now();
    LocalDateTime localDateTime = LocalDateTime.now();

    System.out.println(localTime);//16:00:23.303958700
    System.out.println(localDate);//2023-09-21
    System.out.println(localDateTime);//2023-09-21T16:00:23.304954300

    Instant instant = Instant.now();
    System.out.println(instant);//2023-09-21T08:03:06.133692700Z
    System.out.println(instant.toEpochMilli());//1695283386133
    System.out.println(Instant.ofEpochMilli(1695283270326L));//2023-09-21T08:01:10.326Z

    DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    System.out.println(dtf.format(localDateTime));//2023-09-21T16:06:23.5565559
}
```