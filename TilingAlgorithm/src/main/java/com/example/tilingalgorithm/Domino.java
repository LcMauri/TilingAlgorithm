package com.example.tilingalgorithm;

public class Domino {
    int height;
    Point[] points;

    public Domino(int height, Point[] points) {
        this.height = height;

        if(points.length>2){
            System.err.println("Nombre de points en entrée d'un domino trop grand");
        }
        this.points = points;
    }

    public Domino(Point point1,Point point2) {
        this.points=new Point[2];


        if(point1.x>point2.x||point1.y>point2.y ){
            this.points[0]=new Point();
            this.points[1]=new Point();
            this.points[0].x=Math.min(point1.x,point2.x);
            this.points[1].x=Math.max(point1.x,point2.x);
            this.points[1].y=Math.max(point1.y,point2.y);
            this.points[0].y=Math.min(point1.y,point2.y);

        }else{
            this.points[0] = point1;
            this.points[1] = point2;
        }
    }
}
