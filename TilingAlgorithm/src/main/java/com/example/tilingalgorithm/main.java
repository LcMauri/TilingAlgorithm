package com.example.tilingalgorithm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
public class main {
    // Pour l'instant l'input sera sous forme de HAUTEUR LARGEUR v1 V2
    public static void main(String[] args) {
        Algorithm.pavageMaximumCalculDistance(new Vector2(150,0),new Vector2(0,100),  60, 20,false);
        Algorithm.pavageMinimumCalculDistance(new Vector2(150,0),new Vector2(0,100),  60, 20,false);

    }

    public static void csv(int hauteur, int largeur){
        int hor;
        int ver;


        hor=0;
        ver=0;






        try{
            FileWriter writer = new FileWriter("test.csv");
            StringBuilder sb = new StringBuilder();
            sb.append('x');
            sb.append(',');
            sb.append('y');
            sb.append('\n');
            while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, 0,false)){
                ver=0;
                while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, ver*4,false)){
                    sb.append(ver+"");
                    sb.append(',');
                    sb.append(hor+"");
                    sb.append('\n');
                    ver++;
                }
                hor++;
            }

            hor=0;
            ver=0;
            while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, 0,false)){
                ver=0;
                while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, ver*4,false)){
                    sb.append(ver+"");
                    sb.append(',');
                    sb.append(hor+"");
                    sb.append('\n');
                    ver--;
                }
                hor--;
            }


            hor=0;
            ver=0;
            while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, 0,false)){
                ver=0;
                while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, ver*4,false)){
                    sb.append(ver+"");
                    sb.append(',');
                    sb.append(hor+"");
                    sb.append('\n');
                    ver--;
                }
                hor++;
            }


            hor=0;
            ver=0;
            while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, 0,false)){
                ver=0;
                while(Algorithm.pavageMaximumCalculDistance(new Vector2(hauteur,0),new Vector2(0,largeur),  hor*4, ver*4,false)){
                    sb.append(ver+"");
                    sb.append(',');
                    sb.append(hor+"");
                    sb.append('\n');
                    ver++;
                }
                hor--;
            }
            writer.write(sb.toString());

            System.out.println("done!");

            writer.flush();
            writer.close();
        }catch(IOException e){
            System.out.println(e);
        }


    }
}