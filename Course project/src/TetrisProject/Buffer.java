/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisProject;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Gesser
 */
public class Buffer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int counter;
    private Color[][] arrPlayField;

    public Buffer(int counter, Color[][] arrPlayField) {
        this.counter = counter;
        this.arrPlayField = arrPlayField;
    }

    public int getCounter() {
        return counter;
    }

    public Color[][] getPlayField() {
        return arrPlayField;
    }
}
