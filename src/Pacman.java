import javax.swing.*;
import java.awt.*;
import java.io.File;

import static java.lang.Math.round;

public class Pacman {
    private int x,y, direction;  // 1 fel, 2 bal, 3 le, 4 jobb
    private Image img;
    private File chomp_file;
    private int life;
    private ImageIcon life_icon;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public ImageIcon getLife_icon() {
        return life_icon;
    }

    public void setLife_icon(ImageIcon life_icon) {
        this.life_icon = life_icon;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Pacman(int x, int y) {
        this.x = x;
        this.y = y;
        img = new ImageIcon("src/images/left.gif").getImage();
        life_icon = new ImageIcon("src/images/life.png");
    }

    private final short levelData[] = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,
            0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,
            0,5,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,5,0,
            0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,0,0,0,2,2,0,0,0,1,0,0,1,0,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,
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
            0,5,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,5,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    };
    public int kerekit(int szam)
    {
        int alma = szam%10;
        int alma2 = szam%100 - alma;
        int terit = szam;
        if(alma != 0)
        {
            terit = szam + 20 - alma;
        }
        if(alma2%20 != 0)
        {
            return terit + 10;
        }
        return terit;
    }

    public int kerekit2(int szam)
    {
        int alma = szam%10;
        int alma2 = szam%100 - alma;

        if (alma2%20 == 0)
        {
            if(alma < 7)
            {
                return szam - alma;
            }
        }else
        {
            if(alma > 3)
            {
                return szam - alma + 10;
            }
        }
        return szam;
    }
    public int checkCirc(){
        int i = y/20;
        int j = x/20;

        if(levelData[i*28+j] == 5)
        {
            levelData[i*28+j] = 3;
            return i*28+j;
        }
        return -1;

    }
    public int checkRec(){
        int i = y/20;
        int j = x/20;

        if(levelData[i*28+j] == 1)
        {
            levelData[i*28+j] = 3;
            return i*28+j;
        }
        return -1;
    }

    public boolean isUp(){
        int seg_x = x;
        int seg_y = y;
        if ( direction == 1)
        {
            seg_y = kerekit(seg_y);
        }
        x = kerekit2(x);
        if(x%20!=0)
        {
            return false;
        }
        int j = round(seg_x/20);
        int i = round(seg_y/20);
        //System.out.println("Fel: " + x + " " + y);
        if((levelData[(i-1)*28+j] & 1) != 0)
        {
            return true;
        }
        return false;
    }
    public boolean isDown(){

        x = kerekit2(x);

        if(x%20!=0)
        {
            return false;
        }

        int j = round(x/20);
        int i = round(y/20);
        //System.out.println("Le: " + x + " " + y);
        if((levelData[(i+1)*28+j] & 1) != 0)
        {
            return true;
        }
        return false;
    }
    public boolean isLeft(){
        int seg_x = x;
        int seg_y = y;
        if(direction == 2)
        {
            seg_x = kerekit(seg_x);
            //System.out.println("miabaj: " + seg_x);
        }
        y = kerekit2(y);
        if(y%20 != 0)
        {
            return false;
        }
        int j = round(seg_x/20);
        int i = round(seg_y/20);
        //System.out.println("Bal: " + x + " " + y);
        if((levelData[(i)*28+(j-1)] & 1) != 0)
        {
            return true;
        }
        return false;
    }
    public boolean isRight(){
        y = kerekit2(y);
        if(y%20 != 0)
        {
            return false;
        }
        int j = round(x/20);
        int i = round(y/20);
        //System.out.println("Jobb: " + x + " " + y);
        if((levelData[i*28+j+1] & 1) != 0)
        {
            return true;
        }
        return false;
    }
    public void move(){
        int a[] = {0,0};
        // 1 fel, 2 bal, 3 le, 4 jobb
        switch (direction) {
            case 1:
                if(isUp())
                    y += -1;
                break;
            case 2:
                if(isLeft())
                    x += -1;
                break;
            case 3:
                if(isDown())
                    y += 1;
                break;
            default:
                if(isRight())
                    x += 1;
                break;
        }
    }
}
