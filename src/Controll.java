import javax.swing.*;
import java.awt.*;

import static java.lang.Math.sqrt;

public class Controll implements Runnable {
    private boolean on_off;
    private final Blinky myB;
    private final Clyde myC;
    private final Pacman myP;
    private final Pinky myPinky;
    private final View myN;
    private final Inky inky;
    private final Main main;
    private final SoundControl sc;
    private boolean fright = false;
    private Image death = new ImageIcon("src/images/pacman-death.gif").getImage();
    private int cord = 0;

    public Controll(Blinky myB, View myN, Pacman p, Clyde myC, Pinky myPinky, Inky inky, Main main){
        this.myC = myC;
        this.myB = myB;
        this.myN = myN;
        this.myP = p;
        this.myPinky = myPinky;
        this.inky = inky;
        this.on_off = true;
        this.main = main;
        this.sc = new SoundControl();
    }

    public boolean isOn_off() {
        return on_off;
    }

    public void setOn_off(boolean on_off) {
        this.on_off = on_off;
    }

    @Override
    public void run() {
        int i = 0;
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (on_off) {
            myB.move();
            myP.move();
            myC.move();
            myPinky.move();
            inky.move();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Thread.sleep error!");
            }
            myN.repaint();
            i++;
            int ind = myP.checkRec();
            if(ind != -1)
            {
                sc.eat();
                myN.setLevel((short) 10,ind);
                myN.setPoint(myN.getPoint()+10);
            }
            int ind2 = myP.checkCirc();
            if(ind2 != -1)
            {
                sc.eatCirc();
                myN.setLevel((short)10, ind2);
                myN.setPoint(myN.getPoint()+40);
                myB.setMode(3);
                myC.setMode(3);
                myPinky.setMode(3);
                inky.setMode(3);
                fright = true;
                cord = i;
            }
            if(fright && i-cord>500)
            {
                fright = false;
                myB.setMode(1);
                myC.setMode(1);
                myPinky.setMode(1);
                inky.setMode(1);
            }
            if(myB.gotya(myP.getX(),myP.getY()) && myB.getMode() == 3){
                myB.setMode(4);
                System.out.println("AAAAAALMMMMMAAAAAA\n");
            }
            else if(myB.gotya(myP.getX(),myP.getY()) || myPinky.gotya(myP.getX(),myP.getY()) || myC.gotya(myP.getX(),myP.getY()) || inky.gotya(myP.getX(),myP.getY()))
            {
                System.out.println(myP.getLife());

                if(myP.getLife() > 1){
                    myP.setLife(myP.getLife()-1);
                    myP.setX(280);
                    myP.setY(340);
                    myP.setDirection(2);
                    myB.setY(100);
                    myB.setX(100);
                    myB.setDirection(4);
                    myPinky.setX(420);
                    myPinky.setY(100);
                    myPinky.setDirection(2);
                    inky.setX(420);
                    inky.setY(580);
                    inky.setDirection(4);
                    myC.setX(100);
                    myC.setY(580);
                    myC.setDirection(2);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    on_off = false;
                    myN.setDead(true);
                    try {
                        sc.Gameover();
                        Thread.sleep(2500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if(myN.getPoint() >= 3120)
            {
                myN.setWon(true);
                on_off = false;
                sc.gameEnd();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (i % 20 == 0) {
                myPinky.setDirection(myPinky.chase2(myP.getX(),myP.getY(),myP.getDirection()));
                if(myB.getMode() == 1) {
                    myB.setDirection(myB.chase(myP.getX(), myP.getY()));
                }
                inky.setDirection(inky.chase(myP.getX(),myP.getY(),myP.getDirection(),myB.getX(),myB.getY()));

                double gyok = sqrt(((myP.getX()/20)-(myC.getX()/20))*((myP.getX()/20)-(myC.getX())/20) + ((myP.getY()/20)-(myC.getY())/20)*((myP.getY()/20)-(myC.getY()/20)));
                if(gyok > 10) {
                    myC.setDirection(myC.chase(myP.getX(), myP.getY()));
                }else
                {
                    myC.setDirection(myC.scatter());
                }

            }
        }
        main.showMenu();
    }
}
