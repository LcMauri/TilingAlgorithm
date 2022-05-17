package com.example.tilingalgorithm;

public class Vector2 {
    float x;
    float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float determinent(Vector2 v1, Vector2 v2){
        return (v1.x*v2.y)-(v2.x*v1.y);
    }
}
