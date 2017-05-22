package com.zte.shawn.lotterycheck.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by 10192984 on 2016/10/31.
 */
@Entity
public class MyDaLeTou implements Serializable{

    private static final long serialVersionUID = 0L;

    @Id(autoincrement = false)
    @Index(unique = true)
    private int dateNum;

    @NotNull
    private int read1;
    @NotNull
    private int read2;
    @NotNull
    private int read3;
    @NotNull
    private int read4;
    @NotNull
    private int read5;
    @NotNull
    private int green1;
    @NotNull
    private int green2;


    @NotNull
    private int times;

    @Generated(hash = 1473809445)
    public MyDaLeTou(int dateNum, int read1, int read2, int read3, int read4,
            int read5, int green1, int green2, int times) {
        this.dateNum = dateNum;
        this.read1 = read1;
        this.read2 = read2;
        this.read3 = read3;
        this.read4 = read4;
        this.read5 = read5;
        this.green1 = green1;
        this.green2 = green2;
        this.times = times;
    }

    @Generated(hash = 2005991524)
    public MyDaLeTou() {
    }

    public int getRead1() {
        return this.read1;
    }

    public void setRead1(int read1) {
        this.read1 = read1;
    }

    public int getRead2() {
        return this.read2;
    }

    public void setRead2(int read2) {
        this.read2 = read2;
    }

    public int getRead3() {
        return this.read3;
    }

    public void setRead3(int read3) {
        this.read3 = read3;
    }

    public int getRead4() {
        return this.read4;
    }

    public void setRead4(int read4) {
        this.read4 = read4;
    }

    public int getRead5() {
        return this.read5;
    }

    public void setRead5(int read5) {
        this.read5 = read5;
    }

    public int getGreen1() {
        return this.green1;
    }

    public void setGreen1(int green1) {
        this.green1 = green1;
    }

    public int getGreen2() {
        return this.green2;
    }

    public void setGreen2(int green2) {
        this.green2 = green2;
    }

    public int getDateNum() {
        return this.dateNum;
    }

    public void setDateNum(int dateNum) {
        this.dateNum = dateNum;
    }

    public int getTimes() {
        return this.times;
    }

    public void setTimes(int times) {
        this.times = times;
    }


   public MyDaLeTou getClone(){
       try {
           return (MyDaLeTou) this.clone();
       } catch (CloneNotSupportedException e) {
           e.printStackTrace();
       }
       return null;
   }


}
