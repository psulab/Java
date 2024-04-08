/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gesser
 */
public class UdpClient implements Runnable {
    public static Semaphore sem = new Semaphore(1);
    public static int index;
    public static double upperBound;
    public static double bottomLine;
    public static double step;
    public static double res;
    
    @Override
    public void run(){
        try(DatagramSocket clientSocket = new DatagramSocket(50001)){
            
            double[] arrIntermediateValues = new double[3];
            
            for (int i = 0; i < 3; i++) {
                /* Получение данных от сервера */
                byte[] buffer = new byte[256];
                DatagramPacket dp = new DatagramPacket(buffer, 0, buffer.length);
                clientSocket.receive(dp);

                String strValue = new String(dp.getData(), 0, dp.getLength());
                arrIntermediateValues[i] = Double.parseDouble(strValue);
            }
            
            index = 0;
            upperBound = arrIntermediateValues[0];
            bottomLine = arrIntermediateValues[1];
            step = arrIntermediateValues[2];
            res = 0.0;

            upperBound = (upperBound - bottomLine) / step;
            
            /* Создание потоков */
            Thread[] arrThread = new Thread[7];

            for (int j = 0; j < 7; j++) {
                arrThread[j] = new Thread(new MyThread(), "MyThread");
                arrThread[j].start();
            }

            /* Ожидание завершения всех потоков */
            for (int j = 0; j < 7; j++) {
                try {
                    arrThread[j].join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            /* Отправка данных серверу */
            String strRes = String.valueOf(res);
            DatagramPacket dp = new DatagramPacket(strRes.getBytes(), strRes.length(), InetAddress.getLocalHost(), 50000);
            clientSocket.send(dp);
       
            clientSocket.close();
            
        } catch (SocketException ex) {
            Logger.getLogger(UdpClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UdpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
