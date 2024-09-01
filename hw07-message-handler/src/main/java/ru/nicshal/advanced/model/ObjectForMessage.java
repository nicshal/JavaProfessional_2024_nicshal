package ru.nicshal.advanced.model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage implements Cloneable {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public ObjectForMessage clone() {
        ObjectForMessage objectForMessage;
        try {
            objectForMessage = (ObjectForMessage) super.clone();
        } catch (CloneNotSupportedException e) {
            objectForMessage = new ObjectForMessage();
        }
        objectForMessage.setData(new ArrayList<>(data));
        return objectForMessage;
    }

}
