package com.dsw.calendarcomponent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.interfaces.OnCalendarClickListener;
import com.dsw.calendar.views.GridCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GridCalendarActivity extends Activity {
    private static final String TAG = GridCalendarActivity.class.getSimpleName();
    private GridCalendarView gridCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_calendar_view);
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        int currMonth = calendar.get(Calendar.MONTH) + 1;
        List<CalendarInfo> list = new ArrayList<>();
        list.add(new CalendarInfo(currYear,currMonth,4,"￥1200"));
        list.add(new CalendarInfo(currYear,currMonth,6,"￥1200"));
        list.add(new CalendarInfo(currYear,currMonth,12,"￥1200"));
        list.add(new CalendarInfo(currYear,currMonth,16,"￥1200"));
        list.add(new CalendarInfo(currYear,currMonth,28,"￥1200"));
        list.add(new CalendarInfo(currYear,currMonth,1,"￥1200",1));
        list.add(new CalendarInfo(currYear,currMonth,11,"￥1200",1));
        list.add(new CalendarInfo(currYear,currMonth,19,"￥1200",2));
        list.add(new CalendarInfo(currYear,currMonth,21,"￥1200",1));
        gridCalendarView = (GridCalendarView)findViewById(R.id.gridMonthView);
        gridCalendarView.setCalendarInfos(list);

        gridCalendarView.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onDayChange(int year,int month,int day){

                Toast.makeText(GridCalendarActivity.this,"点击了" + year + "-" + month + "-" + day,Toast.LENGTH_SHORT).show();
                Log.e(TAG,"点击了" + year + "-" + month + "-" + day);
            }

            @Override
            public void onMonthChange(boolean isPreMonth,int year,int month){
                Toast.makeText(GridCalendarActivity.this,(isPreMonth ? "上月" : "下月") + ", 现在月份 " + year + "-" + month,Toast.LENGTH_SHORT).show();

                Log.e(TAG,(isPreMonth ? "上月" : "下月") + ", 现在月份 " + year + "-" + month);

            }
        });


    }
}
