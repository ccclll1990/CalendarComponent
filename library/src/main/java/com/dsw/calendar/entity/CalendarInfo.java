package com.dsw.calendar.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/7/30.
 */
public class CalendarInfo implements Parcelable {

    /**
     * 年
     */
    public int year;
    /**
     * 月
     */
    public int month;
    /**
     * 日
     */
    public int day;
    /**
     * 描述词
     */
    public String des;
    /**
     * 是否为休、班。。1为休，2为班，默认为普通日期
     */
    public int rest;


    public int state;

    public CalendarInfo(){

    }

    /**
     * 构造函数
     *
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     */
    public CalendarInfo(int year,int month,int day,String des){
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
    }

    /**
     * 构造函数
     *
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期号
     * @param des   事务描述
     * @param rest  是否为休、班。。1为休，2为班，默认为普通日期
     */
    public CalendarInfo(int year,int month,int day,String des,int rest){
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
        this.rest = rest;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getMonth(){
        return month;
    }

    public void setMonth(int month){
        this.month = month;
    }

    public int getDay(){
        return day;
    }

    public void setDay(int day){
        this.day = day;
    }

    public String getDes(){
        return des;
    }

    public void setDes(String des){
        this.des = des;
    }

    public int getRest(){
        return rest;
    }

    public void setRest(int rest){
        this.rest = rest;
    }

    public int getState(){
        return state;
    }

    public void setState(int state){
        this.state = state;
    }

    @Override
    public String toString(){
        return "CalendarInfo{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", des='" + des + '\'' +
                ", rest=" + rest +
                ", state=" + state +
                '}';
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest,int flags){
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
        dest.writeString(this.des);
        dest.writeInt(this.rest);
        dest.writeInt(this.state);
    }

    protected CalendarInfo(Parcel in){
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.des = in.readString();
        this.rest = in.readInt();
        this.state = in.readInt();
    }

    public static final Parcelable.Creator<CalendarInfo> CREATOR = new Parcelable.Creator<CalendarInfo>() {
        @Override
        public CalendarInfo createFromParcel(Parcel source){
            return new CalendarInfo(source);
        }

        @Override
        public CalendarInfo[] newArray(int size){
            return new CalendarInfo[size];
        }
    };
}
