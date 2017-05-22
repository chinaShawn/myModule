package com.zte.shawn.utiltools.EventMessage;

/**
 * Created by 10192984 on 2017/4/10.
 */
public class DaleTouEvent extends BaseEvent {

    private Object value;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
