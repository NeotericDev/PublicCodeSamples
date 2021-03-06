package com.asker.messages;

import java.io.Serializable;

public class AddMessage implements MySerializable {
    private int op1;
    private int op2;
    private String str = "Default";

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public AddMessage(int op1, int op2) {
        this.op1 = op1;
        this.op2 = op2;
    }

    public int getOp1() {
        return op1;
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return op2;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }
}
