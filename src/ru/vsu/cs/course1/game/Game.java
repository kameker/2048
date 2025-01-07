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
    public int[][] field = null;
    public int[][] fieldCopy = null;

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
    public boolean checkDuplicates(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (i < array.length - 1 && array[i][j] == array[i + 1][j]) {
                    return false;
                }
                if (j < array[0].length - 1 && array[i][j] == array[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isLose() {
        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field[x].length; y++) {
                if (field[x][y] == 0) return false;
            }
        }

        return checkDuplicates(field);
    }

    public void newGame(int rowCount, int colCount, int colorCount) {
        field = new int[rowCount][colCount];
        this.colorCount = colorCount;
        for (int i = 0; i < 2; i++) {
            int[] rowAndCol = getRandomEmptySlot(this.field);
            field[rowAndCol[0]][rowAndCol[1]] = 2;
        }
        fieldCopy = getField();
    }

    public void generateNumInFreeSlot() {
        if (MyUtils.existsSlots2048(this.field)) {
            int[] rowAndCol = getRandomEmptySlot(this.field);
            if (MyUtils.myRandom(1, 10)) {
                field[rowAndCol[0]][rowAndCol[1]] = 4;
            } else {
                field[rowAndCol[0]][rowAndCol[1]] = 2;
            }
            return;
        }
    }


    public static boolean rowIsDoneRight(int[] row) {
        for (int x = 0; x < 3; x++) {
            if (row[x] != 0 && row[x + 1] == 0) {
                return false;
            } else if (row[x] == row[x + 1] && (row[x] != 0 && row[x + 1] != 0)) {

                return false;
            }
        }
        return true;
    }

    public void reField() {
        int[][] gfc = fieldCopy.clone();
        fieldCopy = field.clone();
        field = gfc.clone();

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
        int firstNum;
        int secondNum;
        for (int i = 0; i < field.length; i++) {
            int[] newCol = new int[field[i].length];
            int k = field[i].length - 1;
            for (int j = field[i].length - 1; j >= 0; j--) {
                firstNum = field[i][j];
                if (firstNum == 0) {
                    continue;
                } else {
                    secondNum = 0;
                    int p = 0;
                    for (int m = j - 1; m >= 0; m--) {
                        secondNum = field[i][m];
                        if (secondNum != 0) {
                            p = m;
                            break;
                        }
                    }
                    if (firstNum == secondNum) {
                        newCol[k] = firstNum * 2;
                        field[i][p] = 0;
                    } else {
                        newCol[k] = firstNum;
                    }
                    k--;
                }

            }
            field[i] = newCol;
        }
        System.out.println("Right");
        generateNumInFreeSlot();
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


    public int[][] getFieldCopy() {
        return fieldCopy;
    }

    public int[][] getField() {
        return field;
    }
}
