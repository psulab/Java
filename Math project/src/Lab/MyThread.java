/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lab;

import static java.lang.Math.log;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gesser
 */
public class MyThread implements Runnable {

    @Override
    public void run() {

        do {

            try {

                UdpClient.sem.acquire(); //Занять семафоры

                if (UdpClient.index < (int) UdpClient.upperBound) {
                    UdpClient.res += (1 / log(UdpClient.bottomLine) + 1 / log(UdpClient.bottomLine + UdpClient.step)) * UdpClient.step / 2;
                    UdpClient.index++;
                    UdpClient.bottomLine += UdpClient.step;
                }

                UdpClient.sem.release(); //Освободить семафоры
                Thread.sleep(20);

            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (UdpClient.index < (int) UdpClient.upperBound);
        
    }
}

//        do {
//
//            try {
//
//                Lab1.sem.acquire(); //Занять семафоры
//
//                if (Lab1.index < (int) Lab1.upperBound) {
//                    Lab1.res += (1 / log(Lab1.bottomLine) + 1 / log(Lab1.bottomLine + Lab1.step)) * Lab1.step / 2;
//                    Lab1.index++;
//                    Lab1.bottomLine += Lab1.step;
//                }
//
//                Lab1.sem.release(); //Освободить семафоры
//                Thread.sleep(20);
//
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } while (Lab1.index < (int) Lab1.upperBound);