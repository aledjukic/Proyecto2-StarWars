/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIClasses;

import EDD.Queue;

/**
 *
 * @author angel
 */
public class ControlMainUI {

    private static final Home home = new Home();

    public static Home getHome() {
        return home;
    }

    public static void updateUIQueue(String tvShow, Queue queue1, Queue queue2, Queue queue3, Queue queue4) {
        if (tvShow.equalsIgnoreCase("regularshow")) {
            home.getTvPanelUI1().updateUIQueue(queue1, queue2, queue3, queue4);
        } else {
            home.getTvPanelUI2().updateUIQueue(queue1, queue2, queue3, queue4);
        }
    }

}
