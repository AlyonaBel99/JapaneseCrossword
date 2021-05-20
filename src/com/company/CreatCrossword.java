package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class CreatCrossword {

    private static int[][] pixelArr;
    private static int Width,Height;

    public  CreatCrossword(BufferedImage imageToCrossword){
        // создание массива пикселей и их цвета
        this.Height = imageToCrossword.getHeight();
        this.Width = imageToCrossword.getWidth();
        pixelArr = new int[Height*Width][3];
        int pixel = 0;
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                Color c = new Color(imageToCrossword.getRGB(x, y));
                pixelArr[pixel][0] =  c.getRed();
                pixelArr[pixel][1] = c.getGreen();
                pixelArr[pixel][2] = c.getBlue();
               pixel++;
            }
        }
    }

    public void creatCross() throws IOException{

        int[][] namberHeight = new int[Width][];
        int[][][] colorHeight = new int[Width][][];
        int[][] namberWidth = new int[Height][];
        int[][][] colorWidth = new int[Height][][];
        int namberH = 1, namberW = 1;
        ArrayList<Integer> namX = new ArrayList<>();
        ArrayList<Integer> colorX = new ArrayList<>();
        ArrayList<Integer> namY = new ArrayList<>();
        ArrayList<Integer> colorY = new ArrayList<>();

        // реализация подсчета одинаковых пикселей по столбцам
        int i=0,y=0;
        do {
            int m = (Height -1) + (Height * y);
            if ( (i < pixelArr.length-1)&&(i != m) && pixelArr[i][0] == pixelArr[i + 1][0] && pixelArr[i][1] == pixelArr[i + 1][1] && pixelArr[i][2] == pixelArr[i + 1][2]){
                namberH++;
            }
            else {
                namY.add(namberH);
                colorY.add(pixelArr[i][0]);
                colorY.add(pixelArr[i][1]);
                colorY.add(pixelArr[i][2]);
                namberH = 1;
            }
            if ((i +1) == (m+1) ) {
                namberHeight[y] = new int[namY.size()];
                colorHeight[y] = new int[namY.size()][3];
                //System.out.println(i+"+");
                int a=0;
                for (int j = 0, n = 0; j < namY.size(); j++) {
                    namberHeight[y][j] = namY.get(j);
                    a=a+namY.get(j);
                    colorHeight[y][j][0] = colorY.get(n);
                    colorHeight[y][j][1] = colorY.get(n + 1);
                    colorHeight[y][j][2] = colorY.get(n + 2);
                    n = n + 3;
                    //System.out.println(y + " " + j + " "+namY.get(j) );
                }
               // System.out.println(y+"sm " + a);
                namY.clear();
                colorY.clear();
                y++;
            }
                i++;
        }while (i < pixelArr.length);


        // реализация подсчета одинаковых пикселей по строкам
        int  x = 0;
        i=0;
        do {
            int m = x + (Height * (Width -1));
            //System.out.println(i +"  " + m);
            if ( (i < pixelArr.length-1)&&(i != m) && pixelArr[i][0] == pixelArr[i + Height][0] && pixelArr[i][1]
                    == pixelArr[i + Height][1] && pixelArr[i][2] == pixelArr[i + Height][2]){
                namberW++;
                //System.out.println("+" + i);
            }
            else {
                namX.add(namberW);
                colorX.add(pixelArr[i][0]);
                colorX.add(pixelArr[i][1]);
                colorX.add(pixelArr[i][2]);
                //System.out.println(i +"  -" + namberW);
                namberW = 1;
            }
            if ((i +1) == (m+1) ) {
                namberWidth[x] = new int[namX.size()];
                colorWidth[x] = new int[namX.size()][3];
                //
                int a=0;
                for (int j = 0, n = 0; j < namX.size(); j++) {

                    a=a+namX.get(j);
                    namberWidth[x][j] = namX.get(j);
                    colorWidth[x][j][0] = colorX.get(n);
                    colorWidth[x][j][1] = colorX.get(n + 1);
                    colorWidth[x][j][2] = colorX.get(n + 2);
                    n = n + 3;
                    //System.out.println(y + " " + j + " "+namX.get(j) );
                }
                //System.out.println(x+"sm " + a);
                namX.clear();
                colorX.clear();

                x++;
                if (x < Height) {
                    i = x;
                }
            }
            else {i+=Height;
            }
            //System.out.println(i);
        }while (i < pixelArr.length);

        //сериализация
        SevedCros seve = new SevedCros(namberWidth,namberHeight, colorHeight,colorWidth);

        FileOutputStream fileOutputStream = new FileOutputStream("cross.dat");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(seve);
        objectOutputStream.close();
        System.out.println(seve);


    }
}

