package com.organazer.web.weborganaizer.model.statistics;

import com.organazer.web.weborganaizer.model.Reminder;

public class LabStatistic {
    private float percent;
    private String color;
    private String name;
    public LabStatistic(Reminder reminder){
        if (!(reminder.getNeedWork() <= 0)){
            percent = (float)reminder.getCloseWork() / reminder.getNeedWork() ;
            if(0 <= percent && percent <= 0.25){
                color = "#bd2525";
            }else if(0.25 < percent && percent <= 0.5){
                color =  "#cb8c22";
            }else if(0.5 < percent && percent <= 0.75){
                color =  "#cccc15";
            }else {
                color =  "#15bd15";
            }
            name = reminder.getLessonName();
            percent*=100;
        }
    }

    public float getPercent() {
        return percent;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
    public String getPercentToString(){
        return (((int) percent) + "%");
    }
}
