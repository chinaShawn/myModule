package com.zte.shawn.lotterycheck.viewmodel;

/**
 * Created by 10192984 on 2017/5/8.
 */

public class viewmodel1 {

    private String firstName;
    private String lastName;

    public viewmodel1(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
