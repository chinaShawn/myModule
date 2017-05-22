package com.zte.shawn.lotterycheck.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 10192984 on 2016/10/29.
 */
@Entity
public class DaLeTou implements Parcelable {

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

    private String opentime;

    @Generated(hash = 1916118724)
    public DaLeTou(int dateNum, int read1, int read2, int read3, int read4,
                   int read5, int green1, int green2, String opentime) {
        this.dateNum = dateNum;
        this.read1 = read1;
        this.read2 = read2;
        this.read3 = read3;
        this.read4 = read4;
        this.read5 = read5;
        this.green1 = green1;
        this.green2 = green2;
        this.opentime = opentime;
    }

    @Generated(hash = 2081552091)
    public DaLeTou() {
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


    public DaLeTou getClone() {
        try {
            return (DaLeTou) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getOpentime() {
        return this.opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(dateNum);
        dest.writeInt(read1);
        dest.writeInt(read2);
        dest.writeInt(read3);
        dest.writeInt(read4);
        dest.writeInt(read5);
        dest.writeInt(green1);
        dest.writeInt(green2);
        dest.writeString(opentime);
    }

    public static final Parcelable.Creator<DaLeTou> CREATOR = new Creator<DaLeTou>() {
        @Override
        public DaLeTou createFromParcel(Parcel source) {
            return new DaLeTou(source);
        }

        @Override
        public DaLeTou[] newArray(int size) {
            return new DaLeTou[size];
        }
    };

    private DaLeTou(Parcel source) {
        dateNum = source.readInt();
        read1 = source.readInt();
        read2 = source.readInt();
        read3 = source.readInt();
        read4 = source.readInt();
        read5 = source.readInt();
        green1 = source.readInt();
        green2 = source.readInt();
        opentime = source.readString();
    }
}
