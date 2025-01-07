package ru.vsu.cs.course1.game;

import java.util.Arrays;
import java.util.Random;

import static ru.vsu.cs.course1.game.MyUtils.getRandomEmptySlot;

/**
 * Класс, реализующий логику игры
 */
public class Game {
    /**
     * объект Random для генерации случайных чисел
     * (можно было бы объявить как static)
     */
    private final Random rnd = new Random();

    /**
     * двумерный массив для хранения игрового поля
     * (в данном случае цветов, 0 - пусто; создается / пересоздается при старте игры)
     */
    private int[][] field = null;

    /**
     * Максимальное кол-во цветов
     */
    private int colorCount = 0;

    public Game() {
    }

    public boolean isWIn() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                if (field[x][y] == 2048) return true;
            }
        }
        return false;
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        // создаем поле
        field = new int[rowCount][colCount];
        this.colorCount = colorCount;
        //field[0][3] = 2;
        //field[0][0] = 2;
        //field[0][1] = 2;
        //field[0][2] = 2;

        for (int i = 0; i < 2; i++) {
            int[] rowAndCol = getRandomEmptySlot(this.field);
            field[rowAndCol[0]][rowAndCol[1]] = 2;
        }
    }

    public void generateTwoInFreeSlot() {
        if (MyUtils.existsSlots2048(this.field)) {
            int[] rowAndCol = getRandomEmptySlot(this.field);
            field[rowAndCol[0]][rowAndCol[1]] = 2;
            return;
        }
    }



    public static boolean rowIsDoneRight(int[] row) {
        for (int x = 0; x < 3; x++) {
            if (row[x] != 0 && row[x + 1] == 0) {
                return false;
            } else if (row[x] == row[x + 1] && (row[x] != 0 && row[x+1] != 0)) {

                return false;
            }
        }
        return true;
    }
    public void moveLeft() {
        this.field = MyUtils.rotateMatrixRight(this.field);
        this.field = MyUtils.rotateMatrixRight(this.field);
        moveRight();
        this.field = MyUtils.rotateMatrixLeft(this.field);
        this.field = MyUtils.rotateMatrixLeft(this.field);
        System.out.println("Left");
    }
    public void moveRight() {
        for (int x = 0; x < 4; x++) {
            while (!rowIsDoneRight(field[x])) {
                for (int y = 0; y < 3; y++) {
                    if (field[x][y] != 0 && field[x][y + 1] == 0) {
                        field[x][y+1] = field[x][y];
                        field[x][y] = 0;
                    } else if (field[x][y] == field[x][y + 1]) {
                        field[x][y+1] = field[x][y] * 2;
                        field[x][y] = 0;
                    }

                }
            }
        }
        System.out.println("Right");
        generateTwoInFreeSlot();
    }

    public void moveUp() {
        this.field = MyUtils.rotateMatrixRight(this.field);
        moveRight();
        this.field = MyUtils.rotateMatrixLeft(this.field);
        System.out.println("Up");
    }

    public void moveDown() {
        this.field = MyUtils.rotateMatrixLeft(this.field);
        moveRight();
        this.field = MyUtils.rotateMatrixRight(this.field);
        System.out.println("Down");
    }

    public int getRowCount() {
        return field == null ? 0 : field.length;
    }

    public int getColCount() {
        return field == null ? 0 : field[0].length;
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getCell(int row, int col) {
        return (row < 0 || row >= getRowCount() || col < 0 || col >= getColCount()) ? 0 : field[row][col];
    }


}
