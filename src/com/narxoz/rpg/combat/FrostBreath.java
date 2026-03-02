package com.narxoz.rpg.combat;

public class FrostBreath implements Ability {
    @Override public String getName() { return "Frost Breath"; }
    @Override public int getDamage() { return 100; }
    @Override public String getDescription() { return "Damage + slow."; }
    @Override public Ability clone() { return new FrostBreath(); }
}
