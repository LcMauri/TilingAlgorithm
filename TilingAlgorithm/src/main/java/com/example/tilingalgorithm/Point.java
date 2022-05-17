package com.example.tilingalgorithm;

public class Point implements Comparable<Point>{


    int hauteur;
    float x;
    float y;
    boolean modif;


    Point(float x, float y){
        this.x=x;
        this.y=y;
        modif=false;
    }
    Point(){

    }


    Point(float x, float y, int hauteur){
        this.x=x;
        this.y=y;
        this.hauteur=hauteur;
        modif=false;
    }

    public boolean isModif() {
        return modif;
    }

    public void setModif(boolean modif) {
        this.modif = modif;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
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

    @Override
    public int compareTo(Point o) {
        return Integer.compare(getHauteur(), o.getHauteur());
    }

    @Override
    public boolean equals(Object o){
       if(o instanceof Point){
           Point a= (Point) o;
           if((a.x==this.x&&a.y==this.y)){
               return true;
           }else{
               return false;
           }
       }
        return false;
    }
}
