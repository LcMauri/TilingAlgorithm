package com.example.tilingalgorithm;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;   // Import the FileWriter class
public class LatexConstructor {

    public LatexConstructor() {
    }

    public static void createLatex(Domino[] doms, float hauteur, float largeur){
        try {
            FileWriter latex = new FileWriter("Results/pavage.latex");
            String scale = "1";
            if(hauteur * largeur > 200 ){
                 scale = "0.5";
            }
            if(hauteur * largeur > 500 ){
                scale = "0.2";
            }
            latex.write("\\documentclass{article}\n" +
                    "\\usepackage{tikz}\n" +
                    "\\begin{document}\n" +
                    "\\begin{tikzpicture}[scale="+scale+"]\n");



            latex.write("\\draw (0,0) -- ("+hauteur+",0) ;\n" +
                    "\\draw (0,0) -- (0,"+largeur+") ; \n");
            for(int i=1; i<=hauteur;i++){
                for(int y=1; y<=largeur;y++){
                    if((Math.abs(y)+Math.abs(i))%2==1){
                        latex.write("\\draw [draw=black, fill=white, opacity=0.5] ("+(i-1)+","+(y-1)+") rectangle ("+i+","+y+");\n");
                    }else{
                        latex.write("\\draw [draw=black, fill=blue, opacity=0.5] ("+(i-1)+","+(y-1)+") rectangle ("+i+","+y+");\n");
                    }
                }
            }

            for(int i=0;i>doms.length;i++){
                String color="purple";

                if(Math.abs(doms[i].points[0].x-doms[i].points[1].x)>1){
                    if((doms[i].points[0].x+doms[i].points[0].y)%2==0){
                        if(doms[i].points[0].x<doms[i].points[1].x){
                            color="red";
                        }else{
                            color="blue";
                        }
                    }else{
                        if(doms[i].points[0].x<doms[i].points[1].x){
                            color="blue";
                        }else{
                            color="red";
                        }
                    }


                }else  if(Math.abs(doms[i].points[0].y-doms[i].points[1].y)>1){
                        if((doms[i].points[0].x+doms[i].points[0].y)%2==0){
                            if(doms[i].points[0].y<doms[i].points[1].y){
                                color="green";
                            }else{
                                color="yellow";
                            }
                        }else{
                            if(doms[i].points[0].y<doms[i].points[1].y){
                                color="yellow";
                            }else{
                                color="green";
                            }
                        }
                }

                latex.write("\\draw [draw=black, fill="+color+", opacity=1] ("+doms[i].points[0].x+","+doms[i].points[0].y+") rectangle ("+doms[i].points[1].x+","+doms[i].points[1].y+");\n");
            }

            latex.write( "\\end{tikzpicture}");
            latex.write( "\\end{document}");
            latex.close();

        } catch (IOException e) {
            System.out.println("Erreur création latex");
            e.printStackTrace();
        }
    }

    public static void createHeighFunctionVisu(Point[][] points, float hauteur, float largeur){
        try {
            FileWriter latex = new FileWriter("Results/pavage.latex");
            String scale = "1";
            if(hauteur * largeur > 200 ){
                scale = "0.5";
            }
            if(hauteur * largeur > 500 ){
                scale = "0.2";
            }
            latex.write("\\documentclass{article}\n" +
                    "\\usepackage{tikz}\n" +
                    "\\begin{document}\n" +
                    "\\begin{tikzpicture}[scale="+scale+"]\n");
            for(int i=-1; i<=largeur+2;i++){
                for(int y=-1; y<=hauteur+2;y++){
                    if((Math.abs(y)+Math.abs(i))%2==1){
                        latex.write("\\draw [draw=black, fill=white, opacity=0.5] ("+(i-1)+","+(y-1)+") rectangle ("+i+","+y+");\n");
                    }else{
                        latex.write("\\draw [draw=black, fill=blue, opacity=0.5] ("+(i-1)+","+(y-1)+")  rectangle ("+i+","+y+");\n");
                    }
                }
            }

            for(int i=0;i<=largeur;i++) {
                for (int y = 0; y <=hauteur; y++) {
                        latex.write("\\draw ("+i+","+y+") node[below,, xshift=0.2cm] {\\normalsize$"+points[i][y].getHauteur()+"$};\n");
                }
            }


            latex.write( "\\end{tikzpicture}");
            latex.write( "\\end{document}");
            latex.close();

        } catch (IOException e) {
            System.out.println("Erreur création latex");
            e.printStackTrace();
        }
    }
}
