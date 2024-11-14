/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainPackage;

import FileFunctions.FileFunctions;
import Helpers.HelpersFunctions;
import MainClasses.Administrator;
import MainClasses.IA;
import java.io.File;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Mauricio Duran & Alejandro Djukic
 */
public class App {
    // FIle params

    private static String selectedPath = "test//params.txt";
    private static File selectedFile = new File(selectedPath);
    private static FileFunctions fileFunctions = new FileFunctions();

    private static Semaphore mutex = new Semaphore(1);
    private static int battleDuration = 10;
    private static Administrator admin;
    private static IA ia;

    private static App app;

    /**
     * Devuelve una instancia única de la aplicación.
     *
     * @return La instancia única de la aplicación.
     */
    public static synchronized App getInstance() {
        if (getApp() == null) {
            setApp(new App());
        }
        return getApp();
    }

    public void start() {
        HelpersFunctions.loadParams();
    }

    /**
     * @return the selectedPath
     */
    public static String getSelectedPath() {
        return selectedPath;
    }

    /**
     * @param aSelectedPath the selectedPath to set
     */
    public static void setSelectedPath(String aSelectedPath) {
        selectedPath = aSelectedPath;
    }

    /**
     * @return the selectedFile
     */
    public static File getSelectedFile() {
        return selectedFile;
    }

    /**
     * @param aSelectedFile the selectedFile to set
     */
    public static void setSelectedFile(File aSelectedFile) {
        selectedFile = aSelectedFile;
    }

    /**
     * @return the fileFunctions
     */
    public static FileFunctions getFileFunctions() {
        return fileFunctions;
    }

    /**
     * @param aFileFunctions the fileFunctions to set
     */
    public static void setFileFunctions(FileFunctions aFileFunctions) {
        fileFunctions = aFileFunctions;
    }

    /**
     * @return the battleDuration
     */
    public static int getBattleDuration() {
        return battleDuration;
    }

    /**
     * @param aBattleDuration the battleDuration to set
     */
    public static void setBattleDuration(int aBattleDuration) {
        battleDuration = aBattleDuration;
    }

    /**
     * @return the app
     */
    public static App getApp() {
        return app;
    }

    /**
     * @param aApp the app to set
     */
    public static void setApp(App aApp) {
        app = aApp;
    }

    /**
     * @return the mutex
     */
    public static Semaphore getMutex() {
        return mutex;
    }

    /**
     * @param aMutex the mutex to set
     */
    public static void setMutex(Semaphore aMutex) {
        mutex = aMutex;
    }

    /**
     * @return the admin
     */
    public Administrator getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(Administrator admin) {
        this.admin = admin;
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
