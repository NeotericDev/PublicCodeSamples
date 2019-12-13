package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyString myString = new MyString("Hello World!");
        MyString[] splitData = myString.split('l');
        System.out.println(myString);
        for(MyString ms : splitData){
            System.out.println(ms);
        }
    }
}
