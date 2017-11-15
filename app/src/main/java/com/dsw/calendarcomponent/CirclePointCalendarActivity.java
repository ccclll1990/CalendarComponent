package com.dsw.calendarcomponent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.interfaces.OnCalendarClickListener;
import com.dsw.calendar.views.CirclePointCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CirclePointCalendarActivity extends Activity {
    private CirclePointCalendarView circleCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circlepoint_calendar_view);
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        List<CalendarInfo> list = new ArrayList<CalendarInfo>();
        list.add(new CalendarInfo(currYear,currMonth,4,"￥120"));
        list.add(new CalendarInfo(currYear,currMonth,6,"￥120"));
        list.add(new CalendarInfo(currYear,currMonth,12,"￥120"));
        list.add(new CalendarInfo(currYear,currMonth,16,"￥120"));
        list.add(new CalendarInfo(currYear,currMonth,28,"￥120"));
        list.add(new CalendarInfo(currYear,currMonth,1,"￥120",1));
        list.add(new CalendarInfo(currYear,currMonth,11,"￥120",1));
        list.add(new CalendarInfo(currYear,currMonth,19,"￥120",2));
        list.add(new CalendarInfo(currYear,currMonth,21,"￥120",1));
        circleCalendarView = (CirclePointCalendarView)findViewById(R.id.circleMonthView);
        circleCalendarView.setCalendarInfos(list);
        circleCalendarView.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onDayChange(int year,int month,int day){
                Toast.makeText(CirclePointCalendarActivity.this,"点击了" + year + "-" + month + "-" + day,Toast.LENGTH_SHORT).show();
                Log.e("TAG","点击了" + year + "-" + month + "-" + day);
            }

            @Override
            public void onMonthClick(boolean isLeft,int year,int month){
                Toast.makeText(CirclePointCalendarActivity.this,(isLeft ? "上月" : "下月") + ", 现在月份 " + year + "-" + month,Toast.LENGTH_SHORT).show();

                Log.e("TAG",(isLeft ? "上月" : "下月") + ", 现在月份 " + year + "-" + month);

            }
        });
    }
}
