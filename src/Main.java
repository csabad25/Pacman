import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame implements ActionListener {
    Movement n;
    View n1;
    private final Blinky b;
    private final Pinky pinky;
    private final Inky inky;
    private final Clyde clyde;
    private final View myV;
    private final Pacman p;
    private final Controll myC;
    private final Movement myM;
    private final Menu myMenu;
    private JPanel contentP;
    private final SoundControl soundControl;
    private Thread t1,t2;
    private CardLayout c1;
    private MenuControll mc;

    public Main() throws HeadlessException, InterruptedException, IOException, FontFormatException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        b = new Blinky(100,100);
        p = new Pacman(280,340);
        pinky = new Pinky(420, 100);
        inky = new Inky(420, 580);
        clyde = new Clyde(100,580);
        myV = new View(b,p,clyde,pinky,inky);
        myC = new Controll(b,myV,p,clyde,pinky,inky,this);
        c1 = new CardLayout();
        soundControl = new SoundControl();

        contentP = new JPanel();
        contentP.setLayout(c1);

        myM = new Movement(p);
        addKeyListener(myM);
        myMenu = new Menu(this);
        setSize(574,700);
        setLocationRelativeTo(null);
        setVisible(true);

        mc = new MenuControll(myMenu);

        contentP.add(myV,"game");
        contentP.add(myMenu,"menu");

        add(contentP);
        showMenu();




    }

    public static void main(String[] args) throws InterruptedException, IOException, FontFormatException {
        Main m = new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showGame() throws InterruptedException {
        p.setX(280);
        p.setY(340);
        p.setDirection(2);
        p.setLife(1);
        myC.setOn_off(true);
        myV.setPoint(0);
        myV.setDead(false);
        myV.repaint();
        b.setY(100);
        b.setX(100);
        b.setDirection(4);
        pinky.setX(420);
        pinky.setY(100);
        pinky.setDirection(2);
        inky.setX(420);
        inky.setY(580);
        inky.setDirection(4);
        clyde.setX(100);
        clyde.setY(580);
        clyde.setDirection(2);
        c1.show(contentP,"game");
        setFocusable(true);
        soundControl.stop();
        soundControl.gameStart();
        requestFocusInWindow();
        t1 = new Thread(myC);
        t1.start();
    }
    public void showMenu(){
        c1.show(contentP,"menu");
        //t1.stop();
        t2 = new Thread(mc);
        t2.start();
        soundControl.menuSound();
    }
    public void exitApp(){
        System.exit(0);
    }
}
