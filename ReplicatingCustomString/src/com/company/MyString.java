package com.company;

import java.util.ArrayList;

public class MyString implements Comparable {
    private char[] characterSequence;
    private int defaultSize = 10;

    public MyString(){
        initWithDefaultSize();
    }

    public MyString(char[] chars){
        if(chars != null)
            characterSequence = chars;
        else
            initWithDefaultSize();
    }

    public MyString(String string){
        if(string != null)
            characterSequence = string.toCharArray();
        else
            initWithDefaultSize();
    }

    private void initWithDefaultSize(){
        characterSequence = new char[defaultSize];
    }

    // H e l l o w o r l d !
    public MyString[] split(char c){
        StringBuilder builder = new StringBuilder();
        ArrayList<MyString> myStringList = new ArrayList<>();
        for(int i = 0; i < characterSequence.length; i++){
            if(characterSequence[i] != c){
                builder.append(characterSequence[i]);
            }
            else{
                if(builder.length() != 0){
                    myStringList.add(new MyString(builder.toString()));
                    builder = new StringBuilder();
                }
            }
        }
        if(builder.length() != 0){
            myStringList.add(new MyString(builder.toString()));
        }
        return myStringList.toArray(new MyString[]{});
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if(characterSequence != null){
            for(char c : characterSequence){
                builder.append(c).append(",");
            }
        }
        builder.replace(builder.length()  - 1, builder.length() , "]");
        return builder.toString();
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
