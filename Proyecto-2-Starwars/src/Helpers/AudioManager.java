/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Helpers;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.FloatControl;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class AudioManager {

    private Clip clip;

    public void playMusic(String path) {
        try {
            if (clip != null && clip.isRunning()) {
                clip.stop(); // Detiene cualquier música que se esté reproduciendo
            }
            // Carga el archivo de audio como un recurso del sistema
            URL soundURL = getClass().getResource(path);
            if (soundURL == null) {
                throw new RuntimeException("El archivo de audio no se ha encontrado en " + path);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range / 1.5f) + gainControl.getMinimum();
            gainControl.setValue(gain / 1.5f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void resumeMusic() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }
    
    public void playSoundEffect(String path, float volume) {
        try {
            // Carga el archivo de audio para el efecto de sonido
            URL soundURL = getClass().getResource(path);
            if (soundURL == null) {
                throw new RuntimeException("El archivo de audio no se ha encontrado en: " + path);
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip soundClip = AudioSystem.getClip();
            soundClip.open(audioIn);
            soundClip.start();
            FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float)(Math.log(volume) / Math.log(10) * 20); 
            gainControl.setValue(dB);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
