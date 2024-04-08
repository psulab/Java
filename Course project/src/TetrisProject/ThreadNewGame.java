/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisProject;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Gesser
 */
public class ThreadNewGame extends Thread {

    private boolean isServer;

    /* Сервер */
    private void Server() {
        try (ServerSocket server = new ServerSocket(3345)) {

            server.setSoTimeout(30000);
            Socket client = server.accept();

            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

            /* Генератор фигур */
            Random random = new Random();
            char[] arrFigure = {'I', 'O', 'L', 'J', 'S', 'Z', 'T'};
            int moveCounter = 0;

            while (true) {

                /* Создание фигуры (начальные координаты + цвет) */
                Figures figure = new Figures(arrFigure[random.nextInt(arrFigure.length)]);
                Buffer b;

                /* Если чётное значение - ход сервера, иначе клиента */
                while (moveCounter % 2 != 0) {
                    /* Чтение */
                    b = (Buffer) ois.readObject();

                    moveCounter = b.getCounter();
                    setColor(b.getPlayField());
                }

                /* Проверка создания фигуры */
                if (!figure.Check(figure.getCoordinates())) {
                    try {

                        /* Запись */
                        b = new Buffer(++moveCounter, getColor());
                        oos.writeObject(b);

                        Tetris.gameOver.setVisible(true);
                        Thread.sleep(2000);
                        Tetris.gameOver.setVisible(false);
                        Tetris.playingField.setVisible(false);
                        Tetris.mainMenu.setVisible(true);

                        /* Очистить игровое поле */
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 10; j++) {
                                Tetris.arrJPanel[i][j].setBackground(new Color(235, 235, 235));
                            }
                        }

                        break;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadNewGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ClearPlayingField();
                }

                while (true) {
                    try {
                        figure.Draw();

                        /* Запись */
                        b = new Buffer(moveCounter, getColor());
                        oos.writeObject(b);

                        /* Обработчик клавиатуры */
                        for (int i = 0; i < 50; i++) {

                            if (Tetris.buttonName.equals("Down")) {
                                Tetris.buttonName = new String();
                                Thread.sleep(20);
                                break;
                            }

                            switch (Tetris.buttonName) {
                                case "Left": {
                                    Coordinates[] coord = new Coordinates[]{
                                        new Coordinates(figure.getCoordinates()[0].getX(), figure.getCoordinates()[0].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[1].getX(), figure.getCoordinates()[1].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[2].getX(), figure.getCoordinates()[2].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[3].getX(), figure.getCoordinates()[3].getY() - 1),};

                                    figure.Wash();

                                    if (figure.Check(coord)) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;

                                case "Right": {
                                    Coordinates[] coord = new Coordinates[]{
                                        new Coordinates(figure.getCoordinates()[0].getX(), figure.getCoordinates()[0].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[1].getX(), figure.getCoordinates()[1].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[2].getX(), figure.getCoordinates()[2].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[3].getX(), figure.getCoordinates()[3].getY() + 1),};

                                    figure.Wash();

                                    if (figure.Check(coord)) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;

                                case "Space": {
                                    Coordinates[] coord = figure.getCoordinates().clone();

                                    figure.Wash();

                                    if (figure.Check(figure.Rotation(coord))) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;
                            }

                            Tetris.buttonName = new String();
                            Thread.sleep(20);
                        }

                        Coordinates[] coord = new Coordinates[]{
                            new Coordinates(figure.getCoordinates()[0].getX() + 1, figure.getCoordinates()[0].getY()),
                            new Coordinates(figure.getCoordinates()[1].getX() + 1, figure.getCoordinates()[1].getY()),
                            new Coordinates(figure.getCoordinates()[2].getX() + 1, figure.getCoordinates()[2].getY()),
                            new Coordinates(figure.getCoordinates()[3].getX() + 1, figure.getCoordinates()[3].getY()),};

                        figure.Wash();

                        if (figure.Check(coord)) {
                            figure.setCoordinates(coord);
                        } else {
                            figure.Draw();

                            /* Запись */
                            b = new Buffer(++moveCounter, getColor());
                            oos.writeObject(b);

                            break;
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadNewGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            oos.close();
            ois.close();

            client.close();

        } catch (SocketTimeoutException ex) {
            return;
        } catch (ClassNotFoundException ex) {
            return;
        } catch (IOException ex) {
            return;
        }
    }

    /* Клиент */
    private void Client() {
        try (Socket socket = new Socket("localhost", 3345)) {

            isServer = false;

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            /* Генератор фигур */
            Random random = new Random();
            char[] arrFigure = {'I', 'O', 'L', 'J', 'S', 'Z', 'T'};
            int moveCounter = 0;

            while (true) {
                /* Создание фигуры (начальные координаты + цвет) */
                Figures figure = new Figures(arrFigure[random.nextInt(arrFigure.length)]);
                Buffer b;

                /* Если не чётное значение - ход клиента, иначе сервера */
                while (moveCounter % 2 == 0) {
                    /* Чтение */
                    b = (Buffer) ois.readObject();

                    moveCounter = b.getCounter();
                    setColor(b.getPlayField());
                }

                /* Проверка создания фигуры */
                if (!figure.Check(figure.getCoordinates())) {
                    try {

                        /* Запись */
                        b = new Buffer(++moveCounter, getColor());
                        oos.writeObject(b);

                        Tetris.gameOver.setVisible(true);
                        Thread.sleep(2000);
                        Tetris.gameOver.setVisible(false);
                        Tetris.playingField.setVisible(false);
                        Tetris.mainMenu.setVisible(true);

                        /* Очистить игровое поле */
                        for (int i = 0; i < 20; i++) {
                            for (int j = 0; j < 10; j++) {
                                Tetris.arrJPanel[i][j].setBackground(new Color(235, 235, 235));
                            }
                        }

                        break;
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadNewGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    ClearPlayingField();
                }

                while (true) {
                    try {
                        figure.Draw();

                        /* Запись */
                        b = new Buffer(moveCounter, getColor());
                        oos.writeObject(b);

                        /* Обработчик клавиатуры */
                        for (int i = 0; i < 50; i++) {

                            if (Tetris.buttonName.equals("Down")) {
                                Tetris.buttonName = new String();
                                Thread.sleep(20);
                                break;
                            }

                            switch (Tetris.buttonName) {
                                case "Left": {
                                    Coordinates[] coord = new Coordinates[]{
                                        new Coordinates(figure.getCoordinates()[0].getX(), figure.getCoordinates()[0].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[1].getX(), figure.getCoordinates()[1].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[2].getX(), figure.getCoordinates()[2].getY() - 1),
                                        new Coordinates(figure.getCoordinates()[3].getX(), figure.getCoordinates()[3].getY() - 1),};

                                    figure.Wash();

                                    if (figure.Check(coord)) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;

                                case "Right": {
                                    Coordinates[] coord = new Coordinates[]{
                                        new Coordinates(figure.getCoordinates()[0].getX(), figure.getCoordinates()[0].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[1].getX(), figure.getCoordinates()[1].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[2].getX(), figure.getCoordinates()[2].getY() + 1),
                                        new Coordinates(figure.getCoordinates()[3].getX(), figure.getCoordinates()[3].getY() + 1),};

                                    figure.Wash();

                                    if (figure.Check(coord)) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;

                                case "Space": {
                                    Coordinates[] coord = figure.getCoordinates().clone();

                                    figure.Wash();

                                    if (figure.Check(figure.Rotation(coord))) {
                                        figure.setCoordinates(coord);
                                    }

                                    figure.Draw();

                                    /* Запись */
                                    b = new Buffer(moveCounter, getColor());
                                    oos.writeObject(b);
                                }
                                break;
                            }

                            Tetris.buttonName = new String();
                            Thread.sleep(20);
                        }

                        Coordinates[] coord = new Coordinates[]{
                            new Coordinates(figure.getCoordinates()[0].getX() + 1, figure.getCoordinates()[0].getY()),
                            new Coordinates(figure.getCoordinates()[1].getX() + 1, figure.getCoordinates()[1].getY()),
                            new Coordinates(figure.getCoordinates()[2].getX() + 1, figure.getCoordinates()[2].getY()),
                            new Coordinates(figure.getCoordinates()[3].getX() + 1, figure.getCoordinates()[3].getY()),};

                        figure.Wash();

                        if (figure.Check(coord)) {
                            figure.setCoordinates(coord);
                        } else {
                            figure.Draw();

                            /* Запись */
                            b = new Buffer(++moveCounter, getColor());
                            oos.writeObject(b);

                            break;
                        }

                    } catch (InterruptedException ex) {
                        Logger.getLogger(ThreadNewGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            oos.close();
            ois.close();

        } catch (ClassNotFoundException ex) {
            return;
        } catch (IOException ex) {
            return;
        }
    }

    /* Очистить и перерисовать игровое поле */
    private void ClearPlayingField() {
        Color originalColor = new Color(235, 235, 235);
        ArrayList<ArrayList<Color>> al = new ArrayList<>();

        /* Запись строк с пробелами */
        for (int i = 19; i >= 0; i--) {

            ArrayList<Color> tempAl = new ArrayList<>();
            int colorCounter = 0;

            for (int j = 0; j < 10; j++) {
                if (!Tetris.arrJPanel[i][j].getBackground().equals(originalColor)) {
                    colorCounter++;
                }

                tempAl.add(Tetris.arrJPanel[i][j].getBackground());
                Tetris.arrJPanel[i][j].setBackground(originalColor);
            }

            if (colorCounter != 0 && colorCounter != 10) {
                al.add(tempAl);
            }
        }

        /* Вывод на экран строк с пробелами */
        int index = 20 - al.size();

        for (int i = al.size() - 1; i >= 0; i--, index++) {
            for (int j = 0; j < 10; j++) {
                Tetris.arrJPanel[index][j].setBackground(al.get(i).get(j));
            }
        }
    }

    /* Задать цвет игрового поля */
    private void setColor(Color[][] c) {

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                Tetris.arrJPanel[i][j].setBackground(c[i][j]);
            }
        }

    }

    /* Получить цвет игрового поля */
    private Color[][] getColor() {

        Color[][] c = new Color[20][10];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                c[i][j] = Tetris.arrJPanel[i][j].getBackground();
            }
        }

        return c;
    }

    @Override
    public void run() {

        isServer = true;

        Client();

        if (isServer) {
            Server();
        }

    }
}
