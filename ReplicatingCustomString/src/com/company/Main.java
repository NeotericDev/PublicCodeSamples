package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        MyString myString = new MyString(new char[]{'H','e','l','l','o',' ','W','o','r','l','d','!'});//MyString("Hello World!");
        MyString[] splitData = myString.split('l');
        MyString.print(splitData);
//        System.out.println(myString);
//        for(MyString ms : splitData){
//            System.out.println(ms);
//        }
    }
}
