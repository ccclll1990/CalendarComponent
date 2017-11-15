package com.dsw.calendar.interfaces;

/**
 * @version V1.0
 * @Des
 * @FileName: com.dsw.calendar.interfaces.OnCalendarClickListener.java
 * @author: cl1
 * @date: 2017-11-15 10:31
 */
public interface OnCalendarClickListener {

    void onDayChange(int year,int month,int day);

    void onMonthClick(boolean isLeft,int year,int month);


}
