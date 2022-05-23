package com.example.tilingalgorithm;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class Algorithm {


    public static boolean pavageMaximum(Vector2 horizontal, Vector2 vertical, int poidHorizontal, int poidVertical, boolean affichageGrille){
        int determinent= Math.abs((int) vertical.determinent(vertical,horizontal));

      Algorithm.checkPreCondition(horizontal,vertical,poidHorizontal,poidVertical,determinent);
        float hauteur =vertical.y;
        float largeur=horizontal.x;
        int nbPointLargueur=(int) largeur+1;
        int nbPointHauteur=(int) hauteur+1;
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(nbPointLargueur*nbPointHauteur,new PointComparatorInferior());


        Domino[] doms;
        Point[][] points= new Point[nbPointLargueur][nbPointHauteur];

        for(int i=0;i<nbPointLargueur;i++){
            for(int y=0;y<nbPointHauteur;y++){
                points[i][y]=new Point(i,y,-500);
            }
        }

        points[(int)vertical.x][nbPointHauteur-1].setHauteur(poidVertical);
        points[nbPointLargueur-1][(int )horizontal.y].setHauteur(poidHorizontal);

        points[nbPointLargueur-1][(int )horizontal.y].setModif(true);
        points[(int)vertical.x][nbPointHauteur-1].setModif(true);


       points[nbPointLargueur-1][nbPointHauteur-1].setHauteur(poidVertical+poidHorizontal);
        points[nbPointLargueur-1][nbPointHauteur-1].setModif(true);
        points[0][0].setHauteur(0);
        points[0][0].setModif(true);
        priorityQueue.add(points[0][0]);
          priorityQueue.add( points[nbPointLargueur-1][(int )horizontal.y]);
        priorityQueue.add(points[(int)vertical.x][nbPointHauteur-1]);

        priorityQueue.add(points[nbPointLargueur-1][nbPointHauteur-1]);

        // CALCUL DU MAXIMUM
        Point v;
        Point[] voisin;
        while(!priorityQueue.isEmpty()){
            v = priorityQueue.poll();
            voisin=getVoisinMax(v, nbPointHauteur, nbPointLargueur);
            for (Point w : voisin) {
                if (w != null) {

                        if ((points[(int) w.x][(int) w.y].hauteur > (v.hauteur+1))) {
                            System.out.println("La forme avec les poids demandé n'est pas pavable");
                            System.out.println(points[(int) w.x][(int) w.y].hauteur);
                            System.out.println(v.hauteur);
                            System.out.println("Position point origine : "+ v.x+" "+ v.y);
                            System.out.println("Position voisin : "+points[(int) w.x][(int) w.y].x+" "+points[(int) w.x][(int) w.y].y);

                               return false;
                        }

                        if (!points[(int) w.x][(int) w.y].modif) {
                            points[(int) w.x][(int) w.y].setHauteur(v.hauteur + 1);
                            points[(int) w.x][(int) w.y].setModif(true);
                            priorityQueue.add(points[(int) w.x][(int) w.y]);
                        }
                }
            }
        }

        doms=creationTableauDomino(points,nbPointHauteur,nbPointLargueur,determinent);
        LatexConstructor.createHeighFunctionVisu(points,nbPointHauteur,nbPointLargueur,"Results/Max/functionMax.latex");
        LatexConstructor.createLatex(doms,nbPointHauteur,nbPointLargueur,affichageGrille,"Results/Max/pavageMax.latex");
        return true;
    }

    public static boolean pavageMininum(Vector2 horizontal, Vector2 vertical, int poidHorizontal, int poidVertical, boolean affichageGrille){
        int determinent= Math.abs((int) vertical.determinent(vertical,horizontal));

        Algorithm.checkPreCondition(horizontal,vertical,poidHorizontal,poidVertical,determinent);
        float hauteur =vertical.y;
        float largeur=horizontal.x;
        int nbPointLargueur=(int) largeur+1;
        int nbPointHauteur=(int) hauteur+1;
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>(nbPointLargueur*nbPointHauteur,new PointComparatorSuperior());


        Domino[] doms;
        Point[][] points= new Point[nbPointLargueur][nbPointHauteur];

        for(int i=0;i<nbPointLargueur;i++){
            for(int y=0;y<nbPointHauteur;y++){
                points[i][y]=new Point(i,y,500);
            }
        }

        points[(int)vertical.x][nbPointHauteur-1].setHauteur(poidVertical);
        points[nbPointLargueur-1][(int )horizontal.y].setHauteur(poidHorizontal);

        points[nbPointLargueur-1][(int )horizontal.y].setModif(true);
        points[(int)vertical.x][nbPointHauteur-1].setModif(true);


        points[nbPointLargueur-1][nbPointHauteur-1].setHauteur(poidVertical+poidHorizontal);
        points[nbPointLargueur-1][nbPointHauteur-1].setModif(true);
        points[0][0].setHauteur(0);
        points[0][0].setModif(true);
        priorityQueue.add(points[0][0]);
        priorityQueue.add( points[nbPointLargueur-1][(int )horizontal.y]);
        priorityQueue.add(points[(int)vertical.x][nbPointHauteur-1]);

        priorityQueue.add(points[nbPointLargueur-1][nbPointHauteur-1]);

        // CALCUL DU MAXIMUM
        Point v;
        Point[] voisin;
        while(!priorityQueue.isEmpty()){
            v = priorityQueue.poll();
            voisin=getVoisinMin(v, nbPointHauteur, nbPointLargueur);
            for (Point w : voisin) {
                if (w != null) {

                    if ((points[(int) w.x][(int) w.y].hauteur < (v.hauteur-1))) {
                        System.out.println("La forme avec les poids demandé n'est pas pavable");
                        System.out.println(points[(int) w.x][(int) w.y].hauteur);
                        System.out.println(v.hauteur);
                        System.out.println("Position point origine : "+ v.x+" "+ v.y);
                        System.out.println("Position voisin : "+points[(int) w.x][(int) w.y].x+" "+points[(int) w.x][(int) w.y].y);

                        return false;
                    }

                    if (!points[(int) w.x][(int) w.y].modif) {
                        points[(int) w.x][(int) w.y].setHauteur(v.hauteur - 1);
                        points[(int) w.x][(int) w.y].setModif(true);
                        priorityQueue.add(points[(int) w.x][(int) w.y]);
                    }
                }
            }
        }

        doms=creationTableauDomino(points,nbPointHauteur,nbPointLargueur,determinent);
        LatexConstructor.createHeighFunctionVisu(points,nbPointHauteur,nbPointLargueur,"Results/Min/functionHMin.latex");
        LatexConstructor.createLatex(doms,nbPointHauteur,nbPointLargueur,affichageGrille,"Results/Min/pavageMin.latex");
        return true;
    }


    // Le premier points est le positif l'autre négatif
    private static Point[] getVoisinMax(Point point, int hauteur, int largeur){
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

    private static Point[] getVoisinMin(Point point, int hauteur, int largeur){
        Point[] points=new Point[2];
        if((point.x+point.y)%2==0){
            points[0]=new Point(point.x,point.y+1);
            points[1]=new Point(point.x,point.y-1);
            if(point.y>=hauteur-1){
                points[0]=null;
            }
            if(point.y<=0){
                points[1]=null;
            }
        }else{
            points[0]=new Point(point.x+1,point.y);
            points[1]=new Point(point.x-1,point.y);
            if(point.x<=0){
                points[1]=null;
            }
            if(point.x>=largeur-1){
                points[0]=null;
            }
        }
        return points;
    }


    private static Point[] getAllVoisin(Point point, int hauteur, int largeur){
        Point[] points=new Point[4];


        points[0]=new Point(point.x,point.y+1);
        points[1]=new Point(point.x+1,point.y);
        points[2]=new Point(point.x,point.y-1);
        points[3]=new Point(point.x-1,point.y);
        if(point.x<=0){
            points[3]=null;

        }
        if(point.y<=0){
            points[2]=null;

        }

        if(point.x>=largeur-1){
            points[1]=null;

        }
        if(point.y>=hauteur-1){
            points[0]=null;

        }

        return points;
    }

    private static Domino[] creationTableauDomino(Point[][] points,int nbPointHauteur, int nbPointLargueur, int determinent){


        Point tmp;
        Domino[] doms = new Domino[determinent];
        ArrayDeque<Point> deque = new ArrayDeque<>();
        for(int i=0;i<nbPointLargueur;i++){
            for(int y=0;y<nbPointHauteur;y++){
                deque.add(points[i][y]);
            }
        }



        int compteur=0;
        Point[] voisins;
        while(!deque.isEmpty()&&compteur<determinent){
            tmp=deque.poll();
            voisins=getAllVoisin(tmp,nbPointHauteur,nbPointLargueur);
            for(Point voisin : voisins){
               if(voisin!=null){
                   if(Math.abs(points[(int) voisin.x][(int) voisin.y].hauteur-tmp.hauteur)==3){
                       if(voisin.x==tmp.x){
                           doms[compteur]=new Domino(new Point(tmp.x+1,tmp.y),new Point(voisin.x-1,voisin.y));
                           deque.remove(points[(int)voisin.x][(int)voisin.y]);
                           compteur++;
                           break;

                       }
                       if(voisin.y==tmp.y){

                           doms[compteur]=new Domino(new Point(tmp.x,tmp.y+1),new Point(voisin.x,voisin.y-1));
                            deque.remove(points[(int)voisin.x][(int)voisin.y]);
                            compteur++;
                            break;
                       }
                   }
               }
            }
        }

       /* while(compteur>0){

            compteur--;
        }

        System.out.println(points[(int)4][(int) 5].hauteur);
        System.out.println(points[(int) 0][(int) 4].hauteur);
        System.out.println(points[(int) 0][(int) 3].hauteur);
        System.out.println(points[(int) 0][(int) 2].hauteur);
        System.out.println(points[(int) 0][(int) 1].hauteur);
        System.out.println(points[(int) 0][(int) 0].hauteur);*/

        return doms;
    }

    private static boolean checkPreCondition(Vector2 horizontal, Vector2 vertical, int poidHorizontal,int poidVertical, int determinent){

        if(vertical.determinent(vertical,horizontal)%2!=0){
            System.out.println("impavable car air est impair");
           return false;
        }
        if((horizontal.x+horizontal.y)%2!=0){
            System.out.println("impavable car v1 mène vers une case blanche");
           return false;
        }

        if((vertical.x+vertical.y)%2!=0){
            System.out.println("impavable car v2 mène vers une case blanche");
           return false;
        }

        if(((vertical.x%2==0)&&(vertical.y%2==0))&&poidVertical%4!=0){
            System.out.println("Impavable car le poids vertical n'est pas égale a 0 mod 4");
           return false;
        }
        if(((vertical.x%2!=0)&&(vertical.y%2!=0))&&poidVertical%4!=2){
            System.out.println("Impavable car le poids vertical n'est pas égale a 2 mod 4");
           return false;
        }
        if(((vertical.x%2!=0)&&(vertical.y%2==0))&&poidVertical%4!=1){
            System.out.println("Impavable car le poids vertical n'est pas égale a 1 mod 4");
           return false;
        }
        if(((vertical.x%2==0)&&(vertical.y%2!=0))&&poidVertical%4!=3){
            System.out.println("Impavable car le poids vertical n'est pas égale a 3 mod 4");
           return false;
        }

        if(((horizontal.x%2==0)&&(horizontal.y%2==0))&&poidHorizontal%4!=0){
            System.out.println("Impavable car le poids horizontal n'est pas égale a 0 mod 4");
           return false;
        }
        if(((horizontal.x%2!=0)&&(horizontal.y%2!=0))&&poidHorizontal%4!=2){
            System.out.println("Impavable car le poids horizontal n'est pas égale a 2 mod 4");
           return false;
        }
        if(((horizontal.x%2!=0)&&(horizontal.y%2==0))&&poidHorizontal%4!=1){
            System.out.println("Impavable car le poids horizontal n'est pas égale a 1 mod 4");
           return false;
        }
        if(((horizontal.x%2==0)&&(horizontal.y%2!=0))&&poidHorizontal%4!=2){
            System.out.println("Impavable car le poids horizontal n'est pas égale a 2 mod 4");
           return false;
        }


        return true;
    }

    private static boolean checkPavageBordure(Point[][] points,int nbPointHauteur, int nbPointLargueur){
        for(int i=0;i<nbPointLargueur-1;i++){
            if(points[i][0].hauteur-points[i+1][0].hauteur!=points[i][nbPointHauteur-1].hauteur-points[i+1][nbPointHauteur-1].hauteur){
                return false;
            }
        }
        for(int y=0;y<nbPointHauteur-1;y++){
            if(points[0][y].hauteur-points[0][y+1].hauteur!=points[nbPointLargueur-1][y].hauteur-points[nbPointLargueur-1][y+1].hauteur){
                return false;
            }
        }
        return true;
    }
}
