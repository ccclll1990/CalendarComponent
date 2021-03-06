package com.dsw.calendar.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.dsw.calendar.entity.CalendarInfo;
import com.dsw.calendar.theme.DefaultDayTheme;

/**
 * Created by Administrator on 2016/7/31.
 */
public class GridMonthView extends MonthView {

    public GridMonthView(Context context,AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public void drawLines(Canvas canvas,int rowsCount){
        int rightX = getWidth();
        int BottomY = getHeight();
        int rowCount = rowsCount;
        int columnCount = 7;
        Path path = new Path();
        float startX = 0;
        float startY;
        float endX = rightX;
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(theme.colorLine());
        for (int row = 1; row <= rowCount; row++) {
            startY = row * rowSize;
            path.moveTo(startX,startY);
            path.lineTo(endX,startY);
            canvas.drawPath(path,paint);
            path.reset();
        }

        startY = 0;
        float endY = BottomY;
        for (int column = 1; column < columnCount; column++) {
            startX = column * columnSize;
            path.moveTo(startX,startY);
            path.lineTo(startX,endY);
            canvas.drawPath(path,paint);
            path.reset();
        }
    }

    @Override
    public void drawBG(Canvas canvas,int column,int row,int day){
        if (day == selDay) {
            //绘制背景色矩形
            float startRecX = columnSize * column + 1;
            float startRecY = rowSize * row + 1;
            float endRecX = startRecX + columnSize - 2 * 1;
            float endRecY = startRecY + rowSize - 2 * 1;
            paint.setColor(theme.colorSelectBG());
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(startRecX,startRecY,endRecX,endRecY,paint);
        }
    }

    @Override
    public void drawDecor(Canvas canvas,int column,int row,int year,int month,int day){
        if (calendarInfos != null && calendarInfos.size() > 0) {
            if (TextUtils.isEmpty(iscalendarInfo(year,month,day))) return;
            paint.setColor(theme.colorDecor());
            paint.setStyle(Paint.Style.FILL);
            float circleX = (float)(columnSize * column + columnSize * 0.8);
            float circleY = (float)(rowSize * row + rowSize * 0.2);
            canvas.drawCircle(circleX,circleY,theme.sizeDecor(),paint);
        }
    }

    @Override
    public void drawRest(Canvas canvas,int column,int row,int year,int month,int day){
        if (calendarInfos != null && calendarInfos.size() > 0) {
            Path path = new Path();
            for (CalendarInfo calendarInfo : calendarInfos) {
                if (calendarInfo.day == day && calendarInfo.year == year && calendarInfo.month == month + 1) {
                    float pointX0 = columnSize * column + 1;
                    float pointY0 = rowSize * row - 1;
                    float pointX1 = (float)(columnSize * column + rowSize * 0.5);
                    float pointY1 = rowSize * row + 1;
                    float pointX2 = columnSize * column + 1;
                    float pointY2 = (float)(rowSize * row + rowSize * 0.5);
                    path.moveTo(pointX0,pointY0);
                    path.lineTo(pointX1,pointY1);
                    path.lineTo(pointX2,pointY2);
                    path.close();
                    paint.setStyle(Paint.Style.FILL);
                    if (calendarInfo.rest == 2) {//班
                        paint.setColor(theme.colorWork());
                        canvas.drawPath(path,paint);

                        paint.setTextSize(theme.sizeDesc());
                        paint.setColor(theme.colorSelectDay());
                        paint.measureText("班");
                        canvas.drawText("班",pointX0 + 5,pointY0 + paint.measureText("班"),paint);
                    } else if (calendarInfo.rest == 1) {//休息
                        paint.setColor(theme.colorRest());
                        canvas.drawPath(path,paint);
                        paint.setTextSize(theme.sizeDesc());
                        paint.setColor(theme.colorSelectDay());
                        canvas.drawText("休",pointX0 + 5,pointY0 + paint.measureText("休"),paint);
                    }
                    path.reset();
                }
            }
        }
    }

    @Override
    public void drawText(Canvas canvas,int column,int row,int year,int month,int day){
        paint.setTextSize(theme.sizeDay());
        float startX = columnSize * column + (columnSize - paint.measureText(day + "")) / 2;
        float startY = rowSize * row + rowSize / 2 - (paint.ascent() + paint.descent()) / 2;
        paint.setStyle(Paint.Style.STROKE);
        String des = iscalendarInfo(year,month,day);
        if (day == selDay) {//日期为选中的日期
            if (!TextUtils.isEmpty(des)) {//desc不为空的时候
                int dateY = (int)(startY - 10);
                paint.setColor(theme.colorSelectDay());
                canvas.drawText(day + "",startX,dateY,paint);

                paint.setTextSize(theme.sizeDesc());
                int priceX = (int)(columnSize * column + (columnSize - paint.measureText(des)) / 2);
                int priceY = (int)(startY + 15);
                canvas.drawText(des,priceX,priceY,paint);
            } else {//des为空的时候
                paint.setColor(theme.colorSelectDay());
                canvas.drawText(day + "",startX,startY,paint);
            }
        } else if (day == currDay && currDay != selDay && currMonth == selMonth && currYear == selYear) { //今日的颜色，不是选中的时候
            //正常月，选中其他日期，则今日为红色
            int dateY = (int)(startY - 10);
            paint.setColor(theme.colorToday());
            canvas.drawText(day + "",startX,dateY,paint);

            paint.setTextSize(theme.sizeDesc());
            paint.setColor(theme.colorDesc(day) == 0 ? theme.colorDesc() : theme.colorDesc(day));
            int priceX = (int)(columnSize * column + Math.abs((columnSize - paint.measureText(des)) / 2));
            int priceY = (int)(startY + 15);
            canvas.drawText(des,priceX,priceY,paint);

        } else {
            //没选中
            // desc不为空
            if (!TextUtils.isEmpty(des)) {
                int dateY = (int)(startY - 10);
                paint.setColor(theme.colorWeekday(day) == 0 ? theme.colorWeekday() : theme.colorWeekday(day));
                canvas.drawText(day + "",startX,dateY,paint);

                paint.setTextSize(theme.sizeDesc());
                paint.setColor(theme.colorDesc(day) == 0 ? theme.colorDesc() : theme.colorDesc(day));
//                paint.setColor(theme.colorDesc());
                int priceX = (int)(columnSize * column + Math.abs((columnSize - paint.measureText(des)) / 2));
                int priceY = (int)(startY + 15);
                canvas.drawText(des,priceX,priceY,paint);
            } else {
                //des为空
                paint.setColor(theme.colorWeekday(day));
                canvas.drawText(day + "",startX,startY,paint);
            }
        }
    }

    @Override
    public void createTheme(){
        theme = new DefaultDayTheme();
    }
}
