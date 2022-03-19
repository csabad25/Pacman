import javax.sound.sampled.AudioSystem;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class SoundControl {
    private Clip clip;
    private final String recEaten;
    private final String backgroundMenu, backgroundGame;
    private String OnOff;
    private final String gameover;
    private final String score;
    private final String game_start;
    private final String circ;
    private final String gameend;
    public SoundControl() {
        recEaten = "src/sounds/Pac-Man/credit.wav";
        game_start = "src/sounds/Pac-Man/game_start.wav";
        backgroundMenu = "src/sounds/menumusic.wav";
        backgroundGame = "./sounds/Game.wav";
        gameover = "src/sounds/game_over.wav";
        circ = "src/sounds/Pac-Man/power_pellet.wav";
        score = "src/sounds/Pac-Man/munch_1.wav";
        gameend = "src/sounds/game_end.wav";
        OnOff = "off";
    }

    public void setFile(String soundFileName) {

        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(new File(soundFileName));
            clip = AudioSystem.getClip();
            clip.open(sound);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public void menuSound() {
        if(OnOff == "On")
        {
            stop();
        }
        setFile(backgroundMenu);
        play();
        OnOff = "On";
    }
    public void gameStart(){
        setFile(game_start);
        play();
    }
    public void eat(){
        setFile(recEaten);
        play();
    }
    public void eatCirc(){
        setFile(circ);
        play();
    }
    public void Gameover(){
        setFile(gameover);
        play();
    }
    public void gameEnd(){
        setFile(gameend);
        play();
    }
}
