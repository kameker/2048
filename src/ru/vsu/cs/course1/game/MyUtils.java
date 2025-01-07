package ru.vsu.cs.course1.game;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class MyUtils {
    public static boolean slotIsEmpty(int row, int col, int[][] field) {
        return field[row][col] == 0;
    }
    public static int[] getRandomEmptySlot(int[][] field) {
        Random rnd = new Random();
        int rndRow = rnd.nextInt(field.length);
        int rndCol = rnd.nextInt(field[0].length);
        while (!slotIsEmpty(rndRow, rndCol, field)) {
            rndRow = rnd.nextInt(field.length);
            rndCol = rnd.nextInt(field[0].length);
            if (slotIsEmpty(rndRow, rndCol, field)) {
                return new int[]{rndRow, rndCol};
            }
        }
        return new int[]{rndRow, rndCol};
    }
    public static Color[] getColors(){
        Color[] colors2048 = new Color[16];
        colors2048[0] = new Color(255,200,81);
        colors2048[1] = new Color(255,150,81);
        colors2048[2] = new Color(255,100,90);
        colors2048[3] = new Color(255,100, 50);

        colors2048[4] = new Color(255,100,0);
        colors2048[5] = new Color(255,80,81);
        colors2048[6] = new Color(255,40,40);
        colors2048[7] = new Color(105,50, 81);

        colors2048[8] = new Color( 255, 255,81);
        colors2048[9] = new Color( 205, 156,61);
        colors2048[10] = new Color(155,156, 41);
        colors2048[11] = new Color(105,156, 21);

        colors2048[12] = new Color(100,100,100);
        colors2048[13] = new Color(50,50,50);
        colors2048[14] = new Color(25,25,25);
        colors2048[15] = new Color(10,10,10);
        return colors2048;
    }
    public static double log2(double x){
        return Math.log(x) / Math.log(2);
    }
    public static int getLenOfNum(int number){
        return (int) Math.floor(Math.log10(number) + 1);
    }
    public static boolean existsSlots2048(int[][] field) {
        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field[0].length; j++){
                if(field[i][j] == 0){
                    return true;
                }
            }
        }
        return false;
    }
    public static int[][] rotateMatrixRight(int[][] field){
        int[][] newMatrix = new int[field.length][field[0].length];
        for(int i = 0; i < field[0].length; i++){
            for(int j = 0; j < field.length; j++){
                newMatrix[j][field.length - i - 1] = field[i][j];
            }
        }
        return newMatrix;
    }
    public static int[][] rotateMatrixLeft(int[][] field){
        int row = field.length;
        int col = field[0].length;
        int[][] newMatrix = new int[row][col];
        for(int i = 0; i < col; i++){
            for(int j = 0; j < row; j++){
                newMatrix[i][j] = field[j][col - i - 1];
            }
        }
        return newMatrix;
    }
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0,2,0,0},
                                     {0,0,2,0},
                                     {0,0,0,2},
                                     {0,0,0,2}};
        matrix = rotateMatrixLeft(matrix);
        for (int i = 0 ; i < matrix.length ; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

}
