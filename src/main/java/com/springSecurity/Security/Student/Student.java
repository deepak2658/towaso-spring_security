package com.springSecurity.Security.Student;

public class Student {
    private int sdId;
    private String sdName;
    Student(){
    }
    Student(int sdId,String sdName){
        this.sdId = sdId;
        this.sdName = sdName;
    }

    public int getSdId() {
        return sdId;
    }

    public String getSdName() {
        return sdName;
    }

    public void setSdId(int sdId) {
        this.sdId = sdId;
    }

    public void setSdName(String sdName) {
        this.sdName = sdName;
    }
}
