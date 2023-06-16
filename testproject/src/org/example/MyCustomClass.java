package org.example;

public class MyCustomClass {
    private int num;//data member declaration
    public int getData(){
        return num;
    }
    public void incrementData(){  //parameter definition
        num++;

    }
    public void setData(int num){
        this.num = num;
    }

    public static void main(String[] args){
        MyCustomClass var1 = new MyCustomClass();
        MyCustomClass var2;
        var1.incrementData();

    }
}
