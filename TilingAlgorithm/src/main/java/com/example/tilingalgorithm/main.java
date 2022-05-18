package com.example.tilingalgorithm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
public class main {
    // Pour l'instant l'input sera sous forme de HAUTEUR LARGEUR v1 V2
    public static void main(String[] args) {


        Vector2 vertical= new Vector2(0,16);
        Vector2 horizontal = new Vector2(10,0);
        int poidVertical= 1;
        int poidHorizontal=1;


        int determinent= Math.abs((int) vertical.determinent(vertical,horizontal));
        if(vertical.determinent(vertical,horizontal)%2!=0){
            System.out.println("impavable car air est impair");
            System.exit(-1);
        }

        float hauteur =vertical.y;
        float largeur=horizontal.x;
        int nbPointhauteur;
        int nbPointLargueur=(int) largeur+1;
        int nbPointHauteur=(int) hauteur+1;
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((int) (nbPointLargueur*nbPointHauteur),new PointComparator());


        Domino[] doms= new Domino[determinent];
        Point[][] points= new Point[nbPointLargueur][nbPointHauteur];

        for(int i=0;i<nbPointLargueur;i++){
            for(int y=0;y<nbPointHauteur;y++){
                points[i][y]=new Point(i,y);
            }
        }
        points[(int)vertical.x][nbPointHauteur-1].setHauteur(poidVertical);
        points[nbPointLargueur-1][(int )horizontal.y].setHauteur(poidHorizontal);
        points[nbPointLargueur-1][(int )horizontal.y].setModif(true);
        points[(int)vertical.x][nbPointHauteur-1].setModif(true);


        points[nbPointLargueur-1][nbPointHauteur-1].setHauteur(poidVertical);
        points[0][0].setHauteur(poidHorizontal);
        points[0][0].setModif(true);
        points[nbPointLargueur-1][nbPointHauteur-1].setModif(true);
      /*  System.out.println((int)horizontal.x);
        System.out.println((int)horizontal.y);
        System.out.println((int)vertical.x);
        System.out.println((int)vertical.y);
        System.out.println(points[(int)horizontal.x][(int )horizontal.y].getHauteur());
        System.out.println(points[(int)vertical.x][(int )vertical.y].getHauteur()); */

        priorityQueue.add( points[nbPointLargueur-1][(int )horizontal.y]);
        priorityQueue.add(points[(int)vertical.x][nbPointHauteur-1]);
        priorityQueue.add( points[0][0]);
        priorityQueue.add(points[nbPointLargueur-1][nbPointHauteur-1]);

        // CALCUL DU MINIMUM
        Point tmp;
        Point[] voisin;
        while(!priorityQueue.isEmpty()){
            tmp= priorityQueue.poll();
            voisin=getVoisin(tmp, nbPointHauteur, nbPointLargueur);
            for(int i=0; i<voisin.length;i++){
                if(voisin[i]!=null){
                    if(priorityQueue.contains(voisin[i])){
                        if(points[(int) voisin[i].x][(int) voisin[i].y].hauteur<(tmp.hauteur-1)){
                            System.out.println("La forme n'est pas pavable");
                            System.out.println(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                            System.out.println(tmp.hauteur-1);
                            System.exit(-2);
                        }
                    }else{
                        if(!points[(int) voisin[i].x][(int) voisin[i].y].modif){
                            points[(int) voisin[i].x][(int) voisin[i].y].setHauteur(tmp.hauteur-1);
                            points[(int) voisin[i].x][(int) voisin[i].y].setModif(true);
                            priorityQueue.add(points[(int) voisin[i].x][(int) voisin[i].y]);


                            if((int) voisin[i].x==0){

                                points[nbPointLargueur-1][(int) voisin[i].y].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[nbPointLargueur-1][(int) voisin[i].y].setModif(true);
                                priorityQueue.add(points[nbPointLargueur-1][(int) voisin[i].y]);

                            }
                            if((int) voisin[i].y==0){
                                points[(int) voisin[i].x][nbPointHauteur-1].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[(int) voisin[i].x][nbPointHauteur-1].setModif(true);
                                priorityQueue.add(points[(int) voisin[i].x][nbPointHauteur-1]);
                            }
                            if((int) voisin[i].y==0&&(int) voisin[i].x==0){
                                points[nbPointLargueur-1][nbPointHauteur-1].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[nbPointLargueur-1][nbPointHauteur-1].setModif(true);
                                priorityQueue.add(points[nbPointLargueur-1][nbPointHauteur-1]);
                            }




                            if((int) voisin[i].x==nbPointLargueur-1){

                                points[0][(int) voisin[i].y].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[0][(int) voisin[i].y].setModif(true);
                                priorityQueue.add(points[0][(int) voisin[i].y]);

                            }
                            if((int) voisin[i].y==nbPointHauteur-1){
                                points[(int) voisin[i].x][0].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[(int) voisin[i].x][0].setModif(true);
                                priorityQueue.add(points[(int) voisin[i].x][0]);
                            }
                            if((int) voisin[i].y==0&&(int) voisin[i].x==0){
                                points[0][0].setHauteur(points[(int) voisin[i].x][(int) voisin[i].y].hauteur);
                                points[0][0].setModif(true);
                                priorityQueue.add(points[0][0]);
                            }



                        }

                    }
                }
            }
        }


        ArrayDeque<Point> deque = new ArrayDeque<>();
        for(int i=0;i<nbPointLargueur;i++){
            for(int y=0;y<nbPointHauteur;y++){
                deque.add(points[i][y]);
            }
        }

        int compteur=0;
        while(!deque.isEmpty()&&compteur<determinent){
            tmp=deque.poll();
            if(tmp.x<nbPointLargueur-1){
                if(Math.abs(points[(int) tmp.x+1][(int) tmp.y].hauteur-tmp.hauteur)==3){
                    doms[compteur]=new Domino(new Point(tmp.x, tmp.y-1),new Point(tmp.x+1,tmp.y+1));
                    deque.remove(points[(int) tmp.x+1][(int) tmp.y]);
                    compteur++;
                }
            }


            if(tmp.x>0){
                if(Math.abs(points[(int) tmp.x-1][(int) tmp.y].hauteur-tmp.hauteur)==3){
                    doms[compteur]=new Domino(new Point(tmp.x, tmp.y+1),new Point(tmp.x-1,tmp.y-1));
                    deque.remove(points[(int) tmp.x-1][(int) tmp.y]);
                    compteur++;
                }
            }

            if(tmp.y<nbPointHauteur-1) {
                if (Math.abs(points[(int) tmp.x][(int) tmp.y + 1].hauteur - tmp.hauteur) == 3) {
                    doms[compteur] = new Domino(new Point(tmp.x - 1, tmp.y), new Point(tmp.x + 1, tmp.y + 1));
                    deque.remove(points[(int) tmp.x][(int) tmp.y+1]);
                    compteur++;

                }
            }

            if(tmp.y>0){
                if(Math.abs(points[(int) tmp.x][(int) tmp.y-1].hauteur-tmp.hauteur)==3){
                    doms[compteur]=new Domino(new Point(tmp.x+1, tmp.y),new Point(tmp.x-1,tmp.y-1));

                    deque.remove(points[(int) tmp.x][(int) tmp.y-1]);
                    compteur++;


                }
            }

        }
        LatexConstructor.createHeighFunctionVisu(points,hauteur,largeur);
        LatexConstructor.createLatex(doms,hauteur,largeur);

    }


    // Le premier points est le positif l'autre négatif
    public static Point[] getVoisin(Point point, int hauteur, int largeur){
        Point[] points=new Point[2];
        if((point.x+point.y)%2==0){
            points[0]=new Point(point.x+1,point.y);
            points[1]=new Point(point.x-1,point.y);
            if(point.x>=largeur-1){
                points[0]=null;
            }
            if(point.x<=0){
                points[1]=null;
            }
        }else{
            points[0]=new Point(point.x,point.y+1);
            points[1]=new Point(point.x,point.y-1);
            if(point.y<=0){
                points[1]=null;
            }
            if(point.y>=hauteur-1){
                points[0]=null;
            }
        }
        return points;
    }

}