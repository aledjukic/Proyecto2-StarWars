/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import EDD.LinkedList;
import EDD.Queue;
import GUIClasses1.ControlMainUI;
import MainPackage.App;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class Administrator extends Thread {

    private IA ia;
    private final Semaphore mutex;
    private final Movie startrek;
    private final Movie starwars;
    private int numRound = 0;

    public Administrator(IA ia, Semaphore mutex, LinkedList yellowCards1, LinkedList greenCards1, LinkedList redCards1,
            LinkedList yellowCards2, LinkedList greenCards2, LinkedList redCards2) {

        this.ia = ia;
        this.mutex = mutex;
        this.startrek = new Movie("Startrek", "/GUI/Assets/StarTrek",
                yellowCards1, greenCards1, redCards1);
        this.starwars = new Movie("Star Wars", "/GUI/Assets/StarWars",
                yellowCards2, greenCards2, redCards2);
    }

    public void startSimulation() {
//        ControlMainUI.getHome().setVisible(true);

        for (int i = 0; i < 20; i++) {
            getStartrek().createCharacter();
            getStarWars().createCharacter();
        }

        ControlMainUI.getHome().getTvPanelUI1().updateUIQueue(getStartrek().getQueue1(),
                getStartrek().getQueue2(),
                getStartrek().getQueue3(),
                getStartrek().getQueue4()
        );

        ControlMainUI.getHome().getTvPanelUI2().updateUIQueue(getStarWars().getQueue1(),
                getStarWars().getQueue2(),
                getStarWars().getQueue3(),
                getStarWars().getQueue4()
        );

        ControlMainUI.getHome().setVisible(true);

        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.start();
        this.getIa().start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int battleDuration = ControlMainUI.getHome().getBattleDuration().getValue();
                ia.setTime(battleDuration);

                updateReinforcementQueue(this.startrek);
                updateReinforcementQueue(this.starwars);

                if (numRound == 2) {
                    tryCreateCharacters();
                    numRound = 0;
                }

                MovieCharacter StartrekFighter = chooseFighters(this.getStartrek());
                MovieCharacter StarWarsFighter = chooseFighters(this.getStarWars());

                //------------------
                //TODO: Pasarle los fighters a la IA
                // Aca 0j0
                //------------------
                this.getIa().setStartrekFighter(StartrekFighter);
                this.getIa().setStarWarsFighter(StarWarsFighter);

                updateUIqueue();
                mutex.release();
                Thread.sleep(100);
                mutex.acquire();

                this.numRound += 1;
                
                risePriorities(this.getStartrek());
                risePriorities(this.getStarWars());

                updateUIqueue();

            } catch (InterruptedException ex) {
                Logger.getLogger(Administrator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void risePriorities(Movie movie) {
        riseQueue(movie.getQueue2(), movie.getQueue1());
        riseQueue(movie.getQueue3(), movie.getQueue2());
    }

    private void riseQueue(Queue currentLevel, Queue nextLevel) {
        int len = currentLevel.getLength();

        for (int i = 0; i < len; i++) {
            MovieCharacter character = currentLevel.dequeue();
            character.setCounter(character.getCounter() + 1);

            if (character.getCounter() >= 8) {
                character.setCounter(0);
                nextLevel.enqueue(character);
            } else {
                currentLevel.enqueue(character);
            }
        }
    }

    private MovieCharacter chooseFighters(Movie movie) {
        if (movie.getQueue1().isEmpty()) {
            movie.updateQueue1();
            this.updateUIqueue();
        }
        MovieCharacter fighter = movie.getQueue1().dequeue();
        fighter.setCounter(0);
        return fighter;
    }

    public void updateUIqueue() {
        ControlMainUI.updateUIQueue("regularshow",
                this.getStartrek().getQueue1(),
                this.getStartrek().getQueue2(),
                this.getStartrek().getQueue3(),
                this.getStartrek().getQueue4());
        ControlMainUI.updateUIQueue("avatar",
                this.getStarWars().getQueue1(),
                this.getStarWars().getQueue2(),
                this.getStarWars().getQueue3(),
                this.getStarWars().getQueue4());
    }

    private void updateReinforcementQueue(Movie movie) {
        if (!(movie.getQueue4().isEmpty())) {
            double randomNum = Math.random();

            if (randomNum <= 0.4) {
                MovieCharacter character = movie.getQueue4().dequeue();
                character.setCounter(0);
                movie.getQueue1().enqueue(character);
            }else {
            MovieCharacter character = movie.getQueue4().dequeue();
            movie.getQueue4().enqueue(character);
            }
        }
    }

    private void tryCreateCharacters() {
        double randomNum = Math.random();

        if (randomNum <= 0.8) {
            getStartrek().createCharacter();
            getStarWars().createCharacter();
        }
    }

    /**
     * @return the regularShow
     */
    public Movie getStartrek() {
        return startrek;
    }

    /**
     * @return the avatar
     */
    public Movie getStarWars() {
        return starwars;
    }

    /**
     * @return the ia
     */
    public IA getIa() {
        return ia;
    }

    /**
     * @param ia the ia to set
     */
    public void setIa(IA ia) {
        this.ia = ia;
    }

}
