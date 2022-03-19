import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
    private final Image up = new ImageIcon("src/images/up.gif").getImage();
    private final Image down = new ImageIcon("src/images/down.gif").getImage();
    private final Image left = new ImageIcon("src/images/left.gif").getImage();
    private final Image rigth = new ImageIcon("src/images/right.gif").getImage();
    private final Pacman p;

    public Movement(Pacman p) throws HeadlessException, InterruptedException {
        this.p = p;  //new Pacman(280, 340);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println(p.getX() + " " + p.getY());
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if(p.isUp()) {
                    p.setDirection(1);
                    p.setImg(up);
                }
                break;
            case KeyEvent.VK_DOWN:
                if(p.isDown()) {
                    p.setDirection(3);
                    p.setImg(down);
                }
                break;
            case KeyEvent.VK_LEFT:
                if(p.isLeft()) {
                    p.setDirection(2);
                    p.setImg(left);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(p.isRight()) {
                    p.setDirection(4);
                    p.setImg(rigth);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

