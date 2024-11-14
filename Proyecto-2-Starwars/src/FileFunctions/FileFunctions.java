/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileFunctions;

import EDD.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import MainClasses.MovieCharacter;

public class FileFunctions {

    // Definimos arrays para cada categoría
    private LinkedList yellowStartrek = new LinkedList();
    private LinkedList greenStartrek = new LinkedList();
    private LinkedList redStartrek = new LinkedList();

    private LinkedList yellowStarWars = new LinkedList();
    private LinkedList greenStarWars = new LinkedList();
    private LinkedList redStarWars = new LinkedList();

    public void read(File file) {
        String line;

        // Rellenar los arrays con las instancias de Character
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String currentShow = null;

            while ((line = br.readLine()) != null) {
                if (!(line.isEmpty())) {
                    if (line.startsWith("[")) {
                        currentShow = line.substring(1, line.length() - 1);

                    } else {
                        String[] parts = line.split(",");

                        MovieCharacter character = new MovieCharacter(
                                "",
                                parts[0],
                                Integer.parseInt(parts[1]),
                                Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]),
                                parts[4],
                                parts[5].split(";")[0]
                        );
 
                        // Clasificar el personaje en la linkedList correspondiente
                        if (line.contains("yellow.png") && "Startrek".equals(currentShow)) {
                            character.setPriorityLevel(1);
                            this.getYellowStartrek().addEnd(character);
                        } else if (line.contains("green.png") && "Startrek".equals(currentShow)) {
                            character.setPriorityLevel(2);
                            this.getGreenStartrek().addEnd(character);
                        } else if (line.contains("red.png") && "Startrek".equals(currentShow)) {
                            character.setPriorityLevel(3);
                            this.getRedStartrek().addEnd(character);
                        } else if (line.contains("yellow.png") && "StarWars".equals(currentShow)) {
                            character.setPriorityLevel(1);
                            this.getYellowStarWars().addEnd(character);
                        } else if (line.contains("green.png") && "StarWars".equals(currentShow)) {
                            character.setPriorityLevel(2);
                            this.getGreenStarWars().addEnd(character);
                        } else if (line.contains("red.png") && "StarWars".equals(currentShow)) {
                            character.setPriorityLevel(3);
                            this.getRedStarWars().addEnd(character);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the yellowStartrek
     */
    public LinkedList getYellowStartrek() {
        return yellowStartrek;
    }

    /**
     * @param yellowStartrek the yellowStartrek to set
     */
    public void setYellowStartrek(LinkedList yellowStartrek) {
        this.yellowStartrek = yellowStartrek;
    }

    /**
     * @return the greenStartrek
     */
    public LinkedList getGreenStartrek() {
        return greenStartrek;
    }

    /**
     * @param greenStartrek the greenStartrek to set
     */
    public void setGreenStartrek(LinkedList greenStartrek) {
        this.greenStartrek = greenStartrek;
    }

    /**
     * @return the redStartrek
     */
    public LinkedList getRedStartrek() {
        return redStartrek;
    }

    /**
     * @param redStartrek the redStartrek to set
     */
    public void setRedStartrek(LinkedList redStartrek) {
        this.redStartrek = redStartrek;
    }

    /**
     * @return the yellowStarWars
     */
    public LinkedList getYellowStarWars() {
        return yellowStarWars;
    }

    /**
     * @param yellowStarWars the yellowStarWars to set
     */
    public void setYellowStarWars(LinkedList yellowStarWars) {
        this.yellowStarWars = yellowStarWars;
    }

    /**
     * @return the greenStarWars
     */
    public LinkedList getGreenStarWars() {
        return greenStarWars;
    }

    /**
     * @param greenStarWars the greenStarWars to set
     */
    public void setGreenStarWars(LinkedList greenStarWars) {
        this.greenStarWars = greenStarWars;
    }

    /**
     * @return the redStarWars
     */
    public LinkedList getRedStarWars() {
        return redStarWars;
    }

    /**
     * @param redStarWars the redStarWars to set
     */
    public void setRedStarWars(LinkedList redStarWars) {
        this.redStarWars = redStarWars;
    }

}
