package ru.catalog.book.controllers;

import ru.catalog.book.controllers.enums.YAlertType;

public class YAlert {
    private String ID;
    private YAlertType yAlertType;
    private String type;
    private Boolean showCloseButton;
    private String text;
    private int time = 0;

    public YAlert() {
        setType("alert-info");
    }

    public YAlert(YAlertType yAlertType) {
        setyAlertType(yAlertType);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public YAlertType getyAlertType() {
        return yAlertType;
    }

    public void setyAlertType(YAlertType yAlertType) {
        switch(yAlertType){
            case DANGER:
                setType("alert-danger");
                break;
            case INFO:
                setType("alert-info");
                break;
            case SUCCESS:
                setType("alert-success");
                break;
            case WARNING:
                setType("alert-warning");
                break;
        }
        this.yAlertType = yAlertType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getShowCloseButton() {
        return showCloseButton;
    }

    public void setShowCloseButton(Boolean showCloseButton) {
        this.showCloseButton = showCloseButton;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
