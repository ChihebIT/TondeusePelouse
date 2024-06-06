package com.socgen.tondeusepelousemvc.tondeusepelouse.model;


public enum OrientationEnum {
    N, E, S, W;

    public OrientationEnum tournerAGauche() {
        return values()[(ordinal() + 3) % 4];
    }

    public OrientationEnum tournerADroite() {
        return values()[(ordinal() + 1) % 4];
    }
}
