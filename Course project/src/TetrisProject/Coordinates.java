/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TetrisProject;

/**
 *
 * @author Gesser
 */
public class Coordinates {

    private int x;
    private int y;

    /* Инициализация */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Получить координату х */
    public int getX() {
        return x;
    }

    /* Получить координату у */
    public int getY() {
        return y;
    }

    /* Задать координату х */
    public void setX(int x) {
        this.x = x;
    }

    /* Задать координату у */
    public void setY(int y) {
        this.y = y;
    }
}
