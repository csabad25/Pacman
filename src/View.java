import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.awt.Transparency.TRANSLUCENT;

public class View extends JPanel {
    private Blinky b;
    private Pacman p;
    private Clyde c;
    private Pinky pinky;
    private Inky inky;
    private boolean dead = false;
    private boolean won = false;
    private Font customFont;
    private int point;
    JTextArea textArea, pointArea;
    private final short levelData[] = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,
            0,4,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,4,0,
            0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,0,0,0,3,3,0,0,0,1,0,0,1,0,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,1,1,1,1,1,0,2,2,2,2,2,2,0,1,1,1,1,1,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,2,2,2,2,2,2,0,1,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,0,
            0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,
            0,0,0,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,
            0,4,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,4,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
    };

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public View(Blinky b, Pacman p, Clyde c, Pinky pinky, Inky inky) throws InterruptedException {
        this.b = b;
        this.p = p;
        this.c = c;
        this.pinky = pinky;
        this.inky = inky;
        point = 0;
        textArea = new JTextArea("game over");
        pointArea = new JTextArea(String.valueOf(point));

        try {
            //create the font to use. Specify the size!
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/font/pac_font.ttf")).deriveFont(12f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public void drawMaze(Graphics2D g2d)
    {
        for (int i = 0; i < 32; i++){
            for (int j = 0; j < 28; j++){
                if(levelData[i*28+j] == 0)
                {
                    g2d.setColor(new Color(4, 38, 129));
                    g2d.drawRect(j*20,i*20,20,20);
                }else if (levelData[i*28+j] == 2){
                    g2d.setColor(new Color(86, 81, 15));
                    g2d.fillRect(j*20,i*20,20,20);
                }else if(levelData[i*28+j] == 4){
                    g2d.setColor(Color.black);
                    g2d.fillRect(j*20,i*20,20,20);
                    g2d.setColor(Color.yellow);
                    g2d.fillOval(j*20+5,i*20+5,10,10);
                }else if (levelData[i*28+j] == 1) {
                    g2d.setColor(Color.black);
                    g2d.fillRect(j*20,i*20,20,20);
                    g2d.setColor(Color.yellow);
                    g2d.fillRect(j*20+8,i*20+8,4,4);
                }else {
                    g2d.setColor(Color.black);
                    g2d.fillRect(j*20,i*20,20,20);
                }

            }

        }
        g2d.drawImage(b.rajz(),b.getX(),b.getY(),this);
        g2d.drawImage(c.rajz(),c.getX(),c.getY(),this);
        g2d.drawImage(pinky.rajz(),pinky.getX(), pinky.getY(), this);
        g2d.drawImage(inky.rajz(), inky.getX(),inky.getY(),this);
        g2d.drawImage(p.getImg(),p.getX(),p.getY(),this);

        pointArea.setText(String.valueOf(point));
        pointArea.setBounds(500,640,100,18);
        pointArea.setOpaque(false);
        pointArea.setVisible(true);
        pointArea.setForeground(Color.red);
        pointArea.setFont(new Font("Arial",12,18));
        add(pointArea);
        textArea.setVisible(false);
        if(dead)
        {
            textArea.setBounds(240,343,100,15);
            textArea.setBackground(Color.black);
            textArea.setVisible(true);
            textArea.setForeground(Color.red);
            textArea.setFont(customFont);
            add(textArea);
        }
        if(won)
        {
            textArea.setBounds(240,343,100,15);
            textArea.setBackground(Color.black);
            textArea.setVisible(true);
            textArea.setForeground(Color.green);
            textArea.setText("Congrat");
            textArea.setFont(customFont);
            add(textArea);
        }
    }
    public void drawPacman(Graphics2D g2d){
        g2d.drawImage(p.getImg(),p.getX(),p.getY(),this);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawMaze(g2d);
        int lifes = p.getLife();
        for (int i = 0; i < lifes; i++){
            g.drawImage(p.getLife_icon().getImage(), i * 30 + 10, 640,null);
        }

    }
    public void setLevel(short pont, int index) {
        levelData[index] = pont;
    }

}
