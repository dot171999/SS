package com.dot.ss;

import java.io.Serializable;

public class PacketModel implements Serializable {
    private static final long serialVersionUID = 5950169519310163575L;
    String key;
    float Float;
    int Int,X,Y;


    public PacketModel(String key, float aFloat, int anInt) {
        this.key = key;
        Float = aFloat;
        Int = anInt;
    }

    public PacketModel(String key,int X,int Y) {
        this.key = key;
        this.X=X;
        this.Y=Y;
    }

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public float getFloat() {
        return Float;
    }

    public void setFloat(float aFloat) {
        Float = aFloat;
    }

    public int getInt() {
        return Int;
    }

    public void setInt(int anInt) {
        Int = anInt;
    }
}
