/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

import MainClasses.MovieCharacter;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class Node {

    /**
     * La infomacion a guardad en el nodo.
     */
    private MovieCharacter tInfo;
    /**
     * El siguiente nodo.
     */
    private Node pNextNode;

    private int idNode;

    /**
     * Constructor de la clase.
     *
     * @param tInfo, la informacion a almacenar en el nodo.
     */
    public Node(MovieCharacter tInfo) {
        this.tInfo = tInfo;
        this.pNextNode = null;
    }

    /**
     * Constructor de la clase.
     */
    public Node() {
        this.tInfo = null;
        this.pNextNode = null;
    }

    /**
     * Getter para acceder a la informacion que guarda el nodo.
     *
     * @return
     */
    public MovieCharacter getTInfo() {
        return this.tInfo;
    }

    /**
     * Setter para modificar la informacion que guarda el nodo.
     *
     * @param tInfo
     */
    public void setTInfo(MovieCharacter tInfo) {
        this.tInfo = tInfo;
    }

    /**
     * Getter para acceder al siguiente nodo enlazado.
     *
     * @return el nodo siguiente que esta enlazado.
     */
    public Node getNextNode() {
        return this.pNextNode;
    }

    /**
     * Setter para nodo siguiente que esta enlazado.
     *
     * @param pNextNode
     */
    public void setNextNode(Node pNextNode) {
        this.pNextNode = pNextNode;
    }

    @Override
    public String toString() {
        return "\n" + this.tInfo.toString();
    }

    /**
     * @return the idNode
     */
    public int getIdNode() {
        return idNode;
    }

    /**
     * @param idNode the idNode to set
     */
    public void setIdNode(int idNode) {
        this.idNode = idNode;
    }

}
