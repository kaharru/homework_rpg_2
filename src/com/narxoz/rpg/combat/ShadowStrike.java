package com.narxoz.rpg.combat;

public class ShadowStrike implements Ability {
    @Override public String getName() { return "Shadow Strike"; }
    @Override public int getDamage() { return 140; }
    @Override public String getDescription() { return "High single-target damage + blind."; }
    @Override public Ability clone() { return new ShadowStrike(); }
}
