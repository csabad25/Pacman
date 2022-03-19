import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.Math.*;
import static java.lang.Math.round;

public class Blinky implements Ghost{
    private int x,y, direction;  // 1 fel, 2 bal, 3 le, 4 jobb
    private String name;
    private Image img = new ImageIcon("src/images/blinky2.gif").getImage();
    private Image fright = new ImageIcon("src/images/fright.gif").getImage();
    private Image eaten = new ImageIcon("src/images/heart.png").getImage();
    Random rand = new Random();
    private int mode; //1 chase, 2 scatter, 3 frightened, 4 eaten, 5 standby


    public Blinky(int x, int y) {
        this.x = x;
        this.y = y;
        this.name = "Blinky";
        mode = 1;
        direction = 4;
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
            0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0,
            0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,1,1,1,1,
            1,1,1,1,1,0,1,0,0,1,0,0,2,2,0,0,0,0,1,0,0,1,0,1,1,1,1,1,
            0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,
            1,1,1,1,1,0,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,
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
            0,1,1,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1,1,1,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,0,0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
    };

    public void move(){
        // 1 fel, 2 bal, 3 le, 4 jobb
        if(mode == 3) {
            direction = frightened();
        }
        switch (direction) {
            case 1:
                y += -1;
                break;
            case 2:
                x += -1;
                break;
            case 3:
                y += 1;
                break;
            default:
                x += 1;
                break;
        }
    }

    public boolean gotya(int pac_x, int pac_y)
    {
        if(mode == 1 || mode == 2 || mode == 3) {
            if (abs(pac_x - x) < 5 && abs(pac_y - y) < 5) {
                return true;
            }
        }
        return false;
    }

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

    public int chase(int x, int y) {
        int i = this.x/20+1;
        int j = this.y/20+1;
        x/=20;
        x+=2;

        y/=20;
        y+=2;
        Pair array[] = new Pair[4];
        Pair a;
        array[2] = new Pair(3, sqrt((x-i)*(x-i)+(y-j-1)*(y-j-1))); //le
        array[3] = new Pair(4, sqrt((x-i-1)*(x-i-1)+(y-j)*(y-j))); //jobb
        array[0] = new Pair(1, sqrt((x-i)*(x-i)+(y-j+1)*(y-j+1))); //fel
        array[1] = new Pair(2, sqrt((x-i+1)*(x-i+1)+(y-j)*(y-j))); //bal

        boolean igaz[] = new boolean[4];
        igaz[0] = isUp();
        igaz[1] = isLeft();
        igaz[2] = isDown();
        igaz[3] = isRight();

        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 4-k-1; l++) {
                if (array[l].getErt() > array[l + 1].getErt()) {
                    a = array[l];
                    array[l] = array[l + 1];
                    array[l + 1] = a;

                    boolean seged = igaz[l];
                    igaz[l] = igaz[l + 1];
                    igaz[l + 1] = seged;
                }
            }
        }

        for (int k =0;k<4;k++) {
            if (igaz[k]) {
                return array[k].getSorsz();
            }
        }
        return -1;
    }

    @Override
    public int scatter() {

        return 0;
    }

    @Override
    public int frightened() {

        int ind = rand.nextInt(4);
        boolean igaz[] = new boolean[4];
        igaz[0] = isUp();
        igaz[1] = isLeft();
        igaz[2] = isDown();
        igaz[3] = isRight();

        while(true){
            if(igaz[ind]){
                return ind+1;
            }
            ind = rand.nextInt(4);
        }
    }

    @Override
    public void eaten() {
        chase(300,300);
    }


    public boolean isUp(){
        int seg_x = x;
        int seg_y = y;
        if ( direction == 1)
        {
            seg_y = kerekit(seg_y);
        }
        if(direction == 3){
            return false;
        }
        //x = kerekit2(x);
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

        //x = kerekit2(x);
        if(direction == 1) {
            return false;
        }
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
        if (direction == 4) {
            return false;
        }
        int seg_x = x;
        int seg_y = y;
        if(direction == 2)
        {
            seg_x = kerekit(seg_x);
            //System.out.println("miabaj: " + seg_x);
        }
        //y = kerekit2(y);
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
        if (direction == 2){
            return false;
        }
        //y = kerekit2(y);
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
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }


    public Image rajz(){
        switch (mode){
            case 3:
                return fright;
            case 4:
                return eaten;
            default:
                return img;
        }
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    //Returns direction
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
