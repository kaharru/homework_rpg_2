package com.narxoz.rpg.combat;

public class IceShield implements Ability{
    @Override public String getName() { return "Ice Shield"; }
    @Override public int getDamage() { return 0; }
    @Override public String getDescription() { return "Defensive buff with ice barrier."; }
    @Override public Ability clone() { return new IceShield(); }
}
