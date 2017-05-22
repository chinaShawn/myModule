package com.zte.shawn.lotterycheck.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by 10192984 on 2016/12/28.
 */
public class Test {

    public class Point {
        public int score;
        public String color;
        public float y;
        public float z;//当前点到上面点的距离差
        public int year;
    }

    float H = 500;
    List<Point> blue=new ArrayList<>();
    List<Point> green = new ArrayList<>();
    List<Point> BigToSmall = new ArrayList<>();
    List<Point> SmallToBig = new ArrayList<>();
    float hPerScore;
    float bSame;//相同线之间的比例。
    float bNotSame = 0.2f;//不同线之间的比例。
    int max;
    int min;


    public  void  Calculate(List<Point> blue, List<Point> green){
        this.blue = blue;
        this.green = green;
        calculateHPerScore();
        cacluatePosititon();
        calculateB1AndB2();
        calculateRealPosition();
        calculateZUOBiaoXi();
    }

    public void calculateHPerScore(){
        BigToSmall.addAll(blue);
        BigToSmall.addAll(green);

        SmallToBig.addAll(blue);
        SmallToBig.addAll(green);

        Collections.sort(BigToSmall, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return rhs.score-lhs.score;
            }
        });
        max = BigToSmall.get(0).score;

        Collections.sort(SmallToBig, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return lhs.score-rhs.score;
            }
        });
        min = SmallToBig.get(0).score;

        hPerScore = (float)H/(float)(max-min);

    }



    public void cacluatePosititon(){
        for (int i = 0; i < BigToSmall.size(); i++) {
            Point point = BigToSmall.get(i);
            if(i==0){
                point.y = 0;
                point.z = 0;
            }else{
                point.y = (max-point.score)*hPerScore;
                point.z = point.y-BigToSmall.get(i-1).y;
            }
        }
    }

    public  void calculateB1AndB2(){
        float ZSame = 0;
        float ZNotSame = 0;
        for (int i = 0; i < BigToSmall.size(); i++) {
            Point point = BigToSmall.get(i);
            if(i+1==BigToSmall.size()){
               continue;
            }else{
                if(point.color.equals(BigToSmall.get(i+1).color)){
                    ZSame += BigToSmall.get(i+1).z;
                }else{
                    ZNotSame += BigToSmall.get(i+1).z;
                }
            }
        }
        bSame = (H-ZNotSame*bNotSame)/ZSame;
    }


    public void calculateRealPosition(){

        for (int i = 0; i < BigToSmall.size(); i++) {
            Point point = BigToSmall.get(i);
            if(i!=0){
                if(point.color.equals(BigToSmall.get(i-1).color)){
                    point.y = BigToSmall.get(i-1).y + point.z*bSame;
                }else{
                    point.y = BigToSmall.get(i-1).y + point.z*bNotSame;
                }
            }
        }
    }

    public void calculateZUOBiaoXi(){
        float add = BigToSmall.get(BigToSmall.size()-1).y;
        for (Point point : BigToSmall) {
            point.y=Math.abs(point.y-add);
        }
    }

    public List<Point> getBuleAnswer(){
        List<Point> list = new ArrayList<>();

        for (Point point : BigToSmall) {
            if(point.color.equals("blue")){
                list.add(point);
            }
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return (lhs.year<rhs.year?1:-1);
            }
        });
        return list;
    }

    public List<Point> getGreenAnswer(){
        List<Point> list = new ArrayList<>();

        for (Point point : BigToSmall) {
            if(point.color.equals("green")){
                list.add(point);
            }
        }
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return (lhs.year<rhs.year?1:-1);
            }
        });
        return list;
    }

}
