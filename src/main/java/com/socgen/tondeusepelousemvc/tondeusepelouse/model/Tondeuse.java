package com.socgen.tondeusepelousemvc.tondeusepelouse.model;

import com.socgen.tondeusepelousemvc.tondeusepelouse.common.Constantes;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Tondeuse {
    private Position position;

    public void executerDeplacement(String instructions, int maxX, int maxY) {
        for (char instruction : instructions.toCharArray()) {
            switch (instruction) {
                case Constantes.GAUCHE -> position.setOrientation(position.getOrientation().tournerAGauche());
                case Constantes.DROITE -> position.setOrientation(position.getOrientation().tournerADroite());
                case Constantes.AVANCER -> avancer(maxX, maxY);
                default -> throw new IllegalArgumentException("Cette action n'est pas possible : " + instruction);
            }
        }
    }

    private void avancer(int maxX, int maxY) {
        int newX = position.getX();
        int newY = position.getY();
        switch (position.getOrientation()) {
            case N -> newY++;
            case E -> newX++;
            case S -> newY--;
            case W -> newX--;
        }
        if (newX >= 0 && newX <= maxX && newY >= 0 && newY <= maxY) {
            position.setX(newX);
            position.setY(newY);
        }
    }
}
