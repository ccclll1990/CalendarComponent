package com.dsw.calendar.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dsw.calendar.R;
import com.dsw.calendar.component.CircleMonthView;
import com.dsw.calendar.component.WeekView;
import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.interfaces.OnCalendarClickListener;
import com.dsw.calendar.theme.IDayTheme;
import com.dsw.calendar.theme.IWeekTheme;

import java.util.List;

/**
 * Created by Administrator on 2016/8/7.
 */
public class CircleCalendarView extends LinearLayout implements View.OnClickListener {
    private WeekView weekView;
    private CircleMonthView circleMonthView;
    private TextView textViewYear, textViewMonth;

    public CircleCalendarView(Context context,AttributeSet attrs){
        super(context,attrs);
        setOrientation(LinearLayout.VERTICAL);
        LayoutParams llParams =
                new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.display_grid_date,null);
        weekView = new WeekView(context,null);
        circleMonthView = new CircleMonthView(context,null);
        addView(view,llParams);
        addView(weekView,llParams);
        addView(circleMonthView,llParams);

        view.findViewById(R.id.left).setOnClickListener(this);
        view.findViewById(R.id.right).setOnClickListener(this);
        textViewYear = (TextView)view.findViewById(R.id.year);
        textViewMonth = (TextView)view.findViewById(R.id.month);

    }

    public void setOnCalendarClickListener(final OnCalendarClickListener calendarClickListener){
        circleMonthView.setOnCalendarClickListener(new OnCalendarClickListener() {
            @Override
            public void onDayChange(int year,int month,int day){

                calendarClickListener.onDayChange(year,month,day);
            }

            @Override
            public void onMonthClick(boolean isLeft,int year,int month){
                textViewYear.setText(year + "年");
                textViewMonth.setText(month + "月");
                calendarClickListener.onMonthClick(isLeft,year,month);
            }
        });
    }

    /**
     * 设置星期的形式
     *
     * @param weekString 默认值	"日","一","二","三","四","五","六"
     */
    public void setWeekString(String[] weekString){
        weekView.setWeekString(weekString);
    }

    public void setCalendarInfos(List<CalendarInfo> calendarInfos){
        circleMonthView.setCalendarInfos(calendarInfos);
        textViewYear.setText(circleMonthView.getSelYear() + "年");
        textViewMonth.setText((circleMonthView.getSelMonth() + 1) + "月");
    }

    public void setDayTheme(IDayTheme theme){
        circleMonthView.setTheme(theme);
    }

    public void setWeekTheme(IWeekTheme weekTheme){
        weekView.setWeekTheme(weekTheme);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.left) {
            circleMonthView.onLeftClick();
        } else {
            circleMonthView.onRightClick();
        }
    }
}
