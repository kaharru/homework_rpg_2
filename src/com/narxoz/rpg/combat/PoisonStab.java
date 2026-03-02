package com.narxoz.rpg.combat;

public class PoisonStab implements Ability {
    @Override public String getName() { return "Poison Stab"; }
    @Override public int getDamage() { return 25; }
    @Override public String getDescription() { return "Demo ability for deep copy proof."; }
    @Override public Ability clone() { return new PoisonStab(); }
}
