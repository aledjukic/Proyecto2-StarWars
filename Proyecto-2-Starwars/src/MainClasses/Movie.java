/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import EDD.LinkedList;
import EDD.Node;
import EDD.Queue;
import Helpers.HelpersFunctions;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class Movie {

    private LinkedList yellowCards;
    private LinkedList greenCards;
    private LinkedList redCards;

    private Queue queue1 = new Queue();
    private Queue queue2 = new Queue();
    private Queue queue3 = new Queue();
    private Queue queue4 = new Queue();
    private String packageImg;
    private String logoUrl;
    private String movie;

    public Movie(String tvShow, String packageImg, LinkedList yellowCards, LinkedList greenCards, LinkedList redCards) {
        this.yellowCards = yellowCards;
        this.greenCards = greenCards;
        this.redCards = redCards;

        this.packageImg = packageImg;
        this.logoUrl = this.packageImg + "/logo.png";

    }

    private void createAndEnqueueCharacter(Node characterNode) {
        characterNode.setIdNode(characterNode.getIdNode() + 1);

        MovieCharacter character = characterNode.getTInfo();

        int priorityLevel = character.getPriorityLevel();

        String characterId = character.getNameCharacter() + "-"
                + HelpersFunctions.priority[priorityLevel - 1] + "-"
                + characterNode.getIdNode();

        String nameCharacter = character.getNameCharacter();
        int hitPoints = character.getHitPoints();
        int speedVelocity = character.getSpeedVelocity();
        int agility = character.getAgility();
        String hability = character.getHability();
        String urlSource = character.getUrlSource();

        MovieCharacter newCharacter = new MovieCharacter(
                characterId,
                nameCharacter,
                hitPoints,
                speedVelocity,
                agility,
                hability,
                urlSource
        );

        newCharacter.setPriorityLevel(priorityLevel);

        if (priorityLevel == 1) {
            this.queue1.enqueue(newCharacter);
        } else if (priorityLevel == 2) {
            this.queue2.enqueue(newCharacter);
        } else {
            this.queue3.enqueue(newCharacter);
        }
    }

    public void createCharacter() {
        int quality = 0;
        double randomValueHP = Math.random();
        double randomValueSpeed = Math.random();
        double randomValueHability = Math.random();
        double randomValueAgility = Math.random();

        quality = (randomValueHability <= 0.6) ? quality + 1 : quality;
        quality = (randomValueHP <= 0.7) ? quality + 1 : quality;
        quality = (randomValueSpeed <= 0.5) ? quality + 1 : quality;
        quality = (randomValueAgility <= 0.4) ? quality + 1 : quality;

        Node node = new Node();

        if (quality == 4) {
            node = yellowCards.getRandomNode();
        } else if (quality == 3 || quality == 2) {
            node = greenCards.getRandomNode();
        } else {
            node = redCards.getRandomNode();
        }

        createAndEnqueueCharacter(node);
    }

    public void updateQueue1() {
        if (!(this.queue2.isEmpty())) {
            MovieCharacter aux = this.queue2.dequeue();
            aux.setCounter(0);
            this.queue1.enqueue(aux);
        } else {
            MovieCharacter aux = this.queue3.dequeue();
            aux.setCounter(0);
            this.queue1.enqueue(aux);
        }
    }

    /**
     * @return the yellowCards
     */
    public LinkedList getYellowCards() {
        return yellowCards;
    }

    /**
     * @param yellowCards the yellowCards to set
     */
    public void setYellowCards(LinkedList yellowCards) {
        this.yellowCards = yellowCards;
    }

    /**
     * @return the greenCards
     */
    public LinkedList getGreenCards() {
        return greenCards;
    }

    /**
     * @param greenCards the greenCards to set
     */
    public void setGreenCards(LinkedList greenCards) {
        this.greenCards = greenCards;
    }

    /**
     * @return the redCards
     */
    public LinkedList getRedCards() {
        return redCards;
    }

    /**
     * @param redCards the redCards to set
     */
    public void setRedCards(LinkedList redCards) {
        this.redCards = redCards;
    }

    /**
     * @return the queue1
     */
    public Queue getQueue1() {
        return queue1;
    }

    /**
     * @param queue1 the queue1 to set
     */
    public void setQueue1(Queue queue1) {
        this.queue1 = queue1;
    }

    /**
     * @return the queue2
     */
    public Queue getQueue2() {
        return queue2;
    }

    /**
     * @param queue2 the queue2 to set
     */
    public void setQueue2(Queue queue2) {
        this.queue2 = queue2;
    }

    /**
     * @return the queue3
     */
    public Queue getQueue3() {
        return queue3;
    }

    /**
     * @param queue3 the queue3 to set
     */
    public void setQueue3(Queue queue3) {
        this.queue3 = queue3;
    }

    /**
     * @return the queue4
     */
    public Queue getQueue4() {
        return queue4;
    }

    /**
     * @param queue4 the queue4 to set
     */
    public void setQueue4(Queue queue4) {
        this.queue4 = queue4;
    }

    /**
     * @return the packageImg
     */
    public String getPackageImg() {
        return packageImg;
    }

    /**
     * @param packageImg the packageImg to set
     */
    public void setPackageImg(String packageImg) {
        this.packageImg = packageImg;
    }

    /**
     * @return the logoUrl
     */
    public String getLogoUrl() {
        return logoUrl;
    }

    /**
     * @param logoUrl the logoUrl to set
     */
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public String toString() {
        String ListInfo = "\n\n\nListas:\n"
                + "-YellowCards:" + this.getYellowCards().toString() + "\n\n"
                + "-GreenCards:" + this.getGreenCards().toString() + "\n\n"
                + "-RedCards:" + this.getRedCards().toString() + "\n\n";

        String queueInfo = "\n\nColas:\n"
                + "Prioridad 1: " + this.getQueue1().toString() + "\n\n"
                + "Prioridad 2: " + this.getQueue2().toString() + "\n\n"
                + "Prioridad 3: " + this.getQueue3().toString() + "\n\n"
                + "Prioridad 4: " + this.getQueue4().toString() + "\n\n";

        return ListInfo + queueInfo;
    }

}
