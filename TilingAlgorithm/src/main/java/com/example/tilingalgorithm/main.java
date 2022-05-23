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
      double hor =(Math.random()*60);
        double ver =(Math.random()*60);
        System.out.println((int) hor*4);
        System.out.println((int) ver*4);
        /*  int i=0;
        while(!Algorithm.pavageMaximum(new Vector2(150,0),new Vector2(0,150),(int) 4,(int) -192,false)){
            i++;
            System.out.println(i);
        }*/
        hor=0;
        ver=0;
        Algorithm.pavageMininum(new Vector2(4,0),new Vector2(0,4), (int) hor*4, (int)ver*4,false);
        Algorithm.pavageMaximum(new Vector2(4,0),new Vector2(0,4), (int) hor*4, (int)ver*4,false);
    }

}