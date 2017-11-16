package com.dsw.calendar.interfaces;

/**
 * @version V1.0
 * @Des
 * @FileName: com.dsw.calendar.interfaces.OnCalendarClickListener.java
 * @author: cl1
 * @date: 2017-11-15 10:31
 */
public interface OnCalendarClickListener {

    /**
     * @param year  年
     * @param month 月
     * @param day   日
     */
    void onDayChange(int year,int month,int day);

    /**
     * @param isPreMonth 是否为上个月
     * @param year       年
     * @param month      月
     */
    void onMonthChange(boolean isPreMonth,int year,int month);


}
