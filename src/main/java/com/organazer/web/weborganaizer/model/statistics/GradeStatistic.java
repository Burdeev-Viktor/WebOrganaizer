package com.organazer.web.weborganaizer.model.statistics;

import com.organazer.web.weborganaizer.model.Grade;

import java.util.List;

public class GradeStatistic {
    private final String name;
    private  float mean;
    private final float percent;
    private final String color;
    public GradeStatistic(List<Grade> grades,String name){
        this.name = name;
        if(grades.size() !=0){
            long sum = grades.stream().mapToLong(Grade::getGrade).sum();
            mean = (float) sum / grades.size();
            percent = mean * 10;
            float scale = (float) Math.pow(10, 2);
            mean = (float) (Math.ceil(mean * scale) / scale);
            if(0 <= mean && mean <= 2.5){
                color = "#bd2525";
            }else if(2.5 < mean && mean <= 5){
                color = "#cb8c22";
            }else if(5 < mean && mean <= 7.5){
                color = "#cccc15";
            }else {
                color = "#15bd15";
            }
        }else {
            mean = 0;
            percent = 0;
            color = "#bd2525";
        }


    }
    public String getName() {
        return name;
    }
    public String getColor() {
        return color;
    }
    public float getMean() {
        return mean;
    }
    public float getPercent() {
        return percent;
    }
}
