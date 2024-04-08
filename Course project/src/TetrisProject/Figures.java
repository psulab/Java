/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisProject;

import java.awt.Color;

/**
 *
 * @author Gesser
 */
public class Figures {

    private Coordinates[] coord;
    private Color color;
    private int centerRotation;

    /* Создание фируры */
    public Figures(char figure) {
        switch (figure) {
            case 'I': {
                color = new Color(212, 170, 125);
                coord = new Coordinates[]{
                    new Coordinates(1, 3),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5),
                    new Coordinates(1, 6)
                };
                centerRotation = 1;
            }
            break;

            case 'O': {
                color = new Color(202, 207, 133);
                coord = new Coordinates[]{
                    new Coordinates(0, 4),
                    new Coordinates(0, 5),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5)
                };
                centerRotation = -1;
            }
            break;

            case 'L': {
                color = new Color(140, 186, 128);
                coord = new Coordinates[]{
                    new Coordinates(0, 5),
                    new Coordinates(1, 3),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5)
                };
                centerRotation = 2;
            }
            break;

            case 'J': {
                color = new Color(101, 142, 156);
                coord = new Coordinates[]{
                    new Coordinates(0, 3),
                    new Coordinates(1, 3),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5)
                };
                centerRotation = 2;
            }
            break;

            case 'S': {
                color = new Color(77, 83, 130);
                coord = new Coordinates[]{
                    new Coordinates(0, 4),
                    new Coordinates(0, 5),
                    new Coordinates(1, 3),
                    new Coordinates(1, 4)
                };
                centerRotation = 3;
            }
            break;

            case 'Z': {
                color = new Color(81, 70, 99);
                coord = new Coordinates[]{
                    new Coordinates(0, 3),
                    new Coordinates(0, 4),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5)
                };
                centerRotation = 2;
            }
            break;

            case 'T': {
                color = new Color(39, 41, 50);
                coord = new Coordinates[]{
                    new Coordinates(0, 4),
                    new Coordinates(1, 3),
                    new Coordinates(1, 4),
                    new Coordinates(1, 5)
                };
                centerRotation = 2;
            }
            break;
        }
    }

    /* Получить координаты */
    public Coordinates[] getCoordinates() {
        return coord;
    }

    /* Задать координаты */
    public void setCoordinates(Coordinates[] coord) {
        this.coord = coord;
    }

    /* Проверка координат */
    public boolean Check(Coordinates[] coord) {
        /* Проверка диапозона координат */
        if (!((0 <= coord[0].getX() && coord[0].getX() <= 19) && (0 <= coord[0].getY() && coord[0].getY() <= 9))
                || !((0 <= coord[1].getX() && coord[1].getX() <= 19) && (0 <= coord[1].getY() && coord[1].getY() <= 9))
                || !((0 <= coord[2].getX() && coord[2].getX() <= 19) && (0 <= coord[2].getY() && coord[2].getY() <= 9))
                || !((0 <= coord[3].getX() && coord[3].getX() <= 19) && (0 <= coord[3].getY() && coord[3].getY() <= 9))) {
            return false;
        }

        /* Проверка цвета координат */
        Color originalColor = new Color(235, 235, 235);

        return Tetris.arrJPanel[coord[0].getX()][coord[0].getY()].getBackground().equals(originalColor)
                && Tetris.arrJPanel[coord[1].getX()][coord[1].getY()].getBackground().equals(originalColor)
                && Tetris.arrJPanel[coord[2].getX()][coord[2].getY()].getBackground().equals(originalColor)
                && Tetris.arrJPanel[coord[3].getX()][coord[3].getY()].getBackground().equals(originalColor);
    }

    public Coordinates[] Rotation(Coordinates[] coord) {
        if (centerRotation != -1) {
            Coordinates coord0 = coord[centerRotation];

            for (int i = 0; i < coord.length; i++) {
                coord[i] = new Coordinates(
                        coord0.getX() + (coord[i].getX() - coord0.getX()) * (int) Math.cos(Math.toRadians(-90)) - (coord[i].getY() - coord0.getY()) * (int) Math.sin(Math.toRadians(-90)),
                        coord0.getY() + (coord[i].getY() - coord0.getY()) * (int) Math.cos(Math.toRadians(-90)) + (coord[i].getX() - coord0.getX()) * (int) Math.sin(Math.toRadians(-90))
                );
            }
        }

        return coord;
    }

    /* Нарисовать фируру */
    public void Draw() {
        Tetris.arrJPanel[coord[0].getX()][coord[0].getY()].setBackground(color);
        Tetris.arrJPanel[coord[1].getX()][coord[1].getY()].setBackground(color);
        Tetris.arrJPanel[coord[2].getX()][coord[2].getY()].setBackground(color);
        Tetris.arrJPanel[coord[3].getX()][coord[3].getY()].setBackground(color);
    }

    /* Стереть фируру */
    public void Wash() {
        Color originalColor = new Color(235, 235, 235);

        Tetris.arrJPanel[coord[0].getX()][coord[0].getY()].setBackground(originalColor);
        Tetris.arrJPanel[coord[1].getX()][coord[1].getY()].setBackground(originalColor);
        Tetris.arrJPanel[coord[2].getX()][coord[2].getY()].setBackground(originalColor);
        Tetris.arrJPanel[coord[3].getX()][coord[3].getY()].setBackground(originalColor);
    }
}
