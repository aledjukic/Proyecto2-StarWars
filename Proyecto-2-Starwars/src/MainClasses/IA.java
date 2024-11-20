/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import GUIClasses.ControlMainUI;
import Helpers.AudioManager;
import Helpers.ImageUtils;
import MainPackage.App;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 * 
 */
public class IA extends Thread {

    private Administrator administrator;
    private MovieCharacter startrekFighter;
    private MovieCharacter starwarsFighter;
    private int victoriesStartrek = 0;
    private int victoriesStarWars = 0;

    private final Semaphore mutex;

    private long time;
    private int round;

    public IA() {
        this.administrator = App.getApp().getAdmin();
        this.mutex = App.getApp().getMutex();
        this.time = App.getApp().getBattleDuration();
        this.round = 0;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.mutex.acquire();

                this.round += 1;

                ControlMainUI.getHome().getWinnerLabelID().setText("");
                ControlMainUI.getHome().getIaStatusLabel().setText("Determinando el resultado del combate...");
                updateCardsUI(getStartrekFighter(), getStarWarsFighter());

                ControlMainUI.getHome().getRoundLabel().setText("Round: " + String.valueOf(round));

                Thread.sleep((long) (this.getTime() * 1000 * 0.7));

                double aux = Math.random();
                AudioManager audioManager = new AudioManager(); 

                if (aux <= 0.4) {
                    ControlMainUI.getHome().getIaStatusLabel().setText("¡Hay un ganador!");
                    MovieCharacter winner = getWinnerCharacter(this.startrekFighter, this.starwarsFighter);
                    ControlMainUI.getHome().getWinnerLabelID().setText(winner.getCharacterId());
                    audioManager.playSoundEffect("/GUI/Assests/victory.wav", 2.0f);
                    Thread.sleep((long) ((getTime() * 1000 * 0.3) * 0.6));

                } else if (aux > 0.40 && aux <= 0.67) {
                    ControlMainUI.getHome().getIaStatusLabel().setText("¡El combate termina en empate!");
                    audioManager.playSoundEffect("/GUI/Assests/tie.wav", 2.0f);
                    Thread.sleep((long) ((getTime() * 1000 * 0.3) * 0.6));
                    

                    this.getAdministrator().getStartrek().getQueue1().enqueue(this.startrekFighter);
                    this.getAdministrator().getStarWars().getQueue1().enqueue(this.starwarsFighter);
                } else {
                    ControlMainUI.getHome().getIaStatusLabel().setText("El combate no se llevará a cabo.");
                    audioManager.playSoundEffect("/GUI/Assests/fail.wav", 2.0f);
                    Thread.sleep((long) ((getTime() * 1000 * 0.3) * 0.6));

                    this.getAdministrator().getStartrek().getQueue4().enqueue(this.startrekFighter);
                    this.getAdministrator().getStarWars().getQueue4().enqueue(this.starwarsFighter);
                }

                clearFightersUI();

                Thread.sleep((long) ((getTime() * 1000 * 0.3) * 0.4));
                this.mutex.release();
                Thread.sleep(100);

            } catch (InterruptedException ex) {
                Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void clearFightersUI() {
        ControlMainUI.getHome().getIaStatusLabel().setText("Esperando nuevos personajes...");
        ControlMainUI.getHome().getWinnerLabelID().setText("");
        ControlMainUI.getHome().getStartrekFighter().clearFightersLabels();
        ControlMainUI.getHome().getStarWarsFighter().clearFightersLabels();
    }

    private MovieCharacter getWinnerCharacter(MovieCharacter startrekFighter, MovieCharacter starwarsFighter) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + getTime() * 1000; // Convierte tiempo de combate a milisegundos
        boolean combatEnd = false;

        // Determina quién ataca primero basado en la velocidad inicialmente
        boolean isStartrekTurn = startrekFighter.getSpeedVelocity() >= starwarsFighter.getSpeedVelocity();

        while (System.currentTimeMillis() < endTime && !combatEnd) {
            int damage;
            if (isStartrekTurn) {
                // Regular Show ataca
                ControlMainUI.getHome().getStartrekFighter().getStatusLabel().setText("Enviando daño");
                ControlMainUI.getHome().getStarWarsFighter().getStatusLabel().setText("Recibiendo daño");
                damage = calculateDamage(startrekFighter, starwarsFighter);
                starwarsFighter.takeDamage(damage);
                ControlMainUI.getHome().getStarWarsFighter().getHPLabel().setText(String.valueOf(starwarsFighter.getHitPoints()));
                if (starwarsFighter.getHitPoints() <= 0) combatEnd = true;
            } else {
                // Avatar ataca
                ControlMainUI.getHome().getStarWarsFighter().getStatusLabel().setText("Enviando daño");
                ControlMainUI.getHome().getStartrekFighter().getStatusLabel().setText("Recibiendo daño");
                damage = calculateDamage(starwarsFighter, startrekFighter);
                startrekFighter.takeDamage(damage);
                ControlMainUI.getHome().getStartrekFighter().getHPLabel().setText(String.valueOf(startrekFighter.getHitPoints()));
                if (startrekFighter.getHitPoints() <= 0) combatEnd = true;
            }

            // Alterna el turno para el próximo ciclo
            isStartrekTurn = !isStartrekTurn;
            ControlMainUI.getHome().getStarWarsFighter().getHPLabel().setText(String.valueOf(starwarsFighter.getHitPoints()));
            ControlMainUI.getHome().getStartrekFighter().getHPLabel().setText(String.valueOf(startrekFighter.getHitPoints()));

            // Simula una pausa por ronda
            try {
                Thread.sleep(1000); // Ajusta según necesidad para permitir actualización de UI
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (combatEnd) break; // Salir del bucle si el combate terminó.
        }
        
        if (!combatEnd) {
        // Aquí se decide el ganador basado en quién tiene más HP.
            if (startrekFighter.getHitPoints() > starwarsFighter.getHitPoints()) {
                this.victoriesStartrek++;
                ControlMainUI.getHome().getTvPanelUI1().getVictoriesLabel().setText(String.valueOf(this.victoriesStartrek));
                return startrekFighter;
            } else if (startrekFighter.getHitPoints() < starwarsFighter.getHitPoints()) {
                this.victoriesStarWars++;
                ControlMainUI.getHome().getTvPanelUI2().getVictoriesLabel().setText(String.valueOf(this.victoriesStarWars));
                return starwarsFighter;
            } else {
                // En caso de empate por HP
                return starwarsFighter;
            }
        }

        // Determinar ganador basado en HP restante.
        if (startrekFighter.getHitPoints() > 0) {
            this.victoriesStartrek++;
            ControlMainUI.getHome().getTvPanelUI1().getVictoriesLabel().setText(String.valueOf(this.victoriesStartrek));
            return startrekFighter;
        } else if (starwarsFighter.getHitPoints() > 0) {
            this.victoriesStarWars++;
            ControlMainUI.getHome().getTvPanelUI2().getVictoriesLabel().setText(String.valueOf(this.victoriesStarWars));
            return starwarsFighter;
        } else {
            return null; // Manejo de empate
        }
    }


    private int calculateDamage(MovieCharacter attacker, MovieCharacter defender) {
        // Daño base con la lógica que el ataque no puede ser completo porque sino lo matariamos de one.
         int baseDamage = (attacker.getSpeedVelocity() + (attacker.getAgility() / 2)) / 2;

         // Inicializamos el daño
         int damage = baseDamage;

         switch (attacker.getHability()) {
             case "Ataque Crítico":
                 //INCREMENTE EL DAÑO BASE DE X1.5
                 damage *= 1.5;
                 break;
             case "Curación":
                 // RECUPERA EN VIDA LA MITAD DE LO QUE LO ATACARÍA 
                 int healAmount = baseDamage / 2;
                 attacker.heal(healAmount);
                 damage = (attacker.getSpeedVelocity() + (attacker.getAgility() / 2)) / 4;
                 break;
             case "Parálisis":
                 // Se le disminuye la agilidad al enemigo en un 50%
                 defender.setAgility(defender.getAgility() / 2);
                 break;
             case "Congelamiento":
                 // Se le disminuye la velocidad al enemigo en un 50%
                 defender.setSpeedVelocity(defender.getSpeedVelocity() / 2);
                 break;
             default:
                 // No special ability
                 break;
         }

         return damage;
     }

    private void updateCardsUI(MovieCharacter StartrekCharacter, MovieCharacter StarWarsCharacterTv) {
        ImageUtils imageUtils = new ImageUtils();

        ImageIcon StartrekCardIA = imageUtils.loadScaledImage(
                StartrekCharacter.getUrlSource(), 150, 200
        );

        ImageIcon StarWarsCardIA = imageUtils.loadScaledImage(
                StarWarsCharacterTv.getUrlSource(), 150, 200
        );

        ControlMainUI.getHome().getStartrekFighter().getFighterCard().setIcon(StartrekCardIA);
        ControlMainUI.getHome().getStartrekFighter().getCharacterIDLabel().setText(StartrekCharacter.getCharacterId());
        ControlMainUI.getHome().getStartrekFighter().getHPLabel().setText(String.valueOf(StartrekCharacter.getHitPoints()));

        ControlMainUI.getHome().getStarWarsFighter().getFighterCard().setIcon(StarWarsCardIA);
        ControlMainUI.getHome().getStarWarsFighter().getCharacterIDLabel().setText(StarWarsCharacterTv.getCharacterId());
        ControlMainUI.getHome().getStarWarsFighter().getHPLabel().setText(String.valueOf(StarWarsCharacterTv.getHitPoints()));
    }

    /**
     * @return the startrekFighter
     */
    public MovieCharacter getStartrekFighter() {
        return startrekFighter;
    }

    /**
     * @param startrekFighter the startrekFighter to set
     */
    public void setStartrekFighter(MovieCharacter startrekFighter) {
        this.startrekFighter = startrekFighter;
    }

    /**
     * @return the starwarsFighter
     */
    public MovieCharacter getStarWarsFighter() {
        return starwarsFighter;
    }

    /**
     * @param starwarsFighter the starwarsFighter to set
     */
    public void setStarWarsFighter(MovieCharacter starwarsFighter) {
        this.starwarsFighter = starwarsFighter;
    }

    /**
     * @return the administrator
     */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
     * @param administrator the administrator to set
     */
    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    /**
     * @return the time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

}
