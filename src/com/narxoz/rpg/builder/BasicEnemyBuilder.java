package com.narxoz.rpg.builder;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
public class BasicEnemyBuilder implements EnemyBuilder {
    private String name;
    private Integer health;
    private Integer damage;
    private Integer defense;
    private Integer speed;
    private String element;
    private String aiBehavior;
    private List<Ability> abilities = new ArrayList<>();
    private LootTable loot;

    @Override public EnemyBuilder setName(String name) { this.name = name; return this; }
    @Override public EnemyBuilder setHealth(int health) { this.health = health; return this; }
    @Override public EnemyBuilder setDamage(int damage) { this.damage = damage; return this; }
    @Override public EnemyBuilder setDefense(int defense) { this.defense = defense; return this; }
    @Override public EnemyBuilder setSpeed(int speed) { this.speed = speed; return this; }
    @Override public EnemyBuilder setElement(String element) { this.element = element; return this; }

    @Override public EnemyBuilder addAbility(Ability ability) { if (ability != null) abilities.add(ability); return this; }
    @Override public EnemyBuilder setAbilities(List<Ability> abilities) {
        this.abilities = new ArrayList<>();
        if (abilities != null) this.abilities.addAll(abilities);
        return this;
    }

    // миньоны phases игнорируют
    @Override public EnemyBuilder addPhase(int phaseNumber, int healthThreshold) { return this; }

    @Override public EnemyBuilder setLootTable(LootTable loot) { this.loot = loot; return this; }
    @Override public EnemyBuilder setAIBehavior(String aiBehavior) { this.aiBehavior = aiBehavior; return this; }

    @Override
    public Enemy build() {
        // FACTORY METHOD: build() creates the Product (Enemy).
        // Different builders create different concrete enemies.
        if (name == null || name.trim().isEmpty()) throw new IllegalStateException("Name required");
        if (health == null || health <= 0) throw new IllegalStateException("Health must be positive");
        if (loot == null) throw new IllegalStateException("LootTable required");

        if (damage == null) damage = 10;
        if (defense == null) defense = 0;
        if (speed == null) speed = 30;
        if (element == null) element = "NONE";
        if (aiBehavior == null) aiBehavior = "BASIC";



        Goblin e = new Goblin();
        e.setName(name);
        e.setHealth(health);
        e.setDamage(damage);
        e.setDefense(defense);
        e.setSpeed(speed);
        e.setElement(element);
        e.setAIBehavior(aiBehavior);
        e.setLootTable(loot);
        e.setAbilities(abilities);
        return e;
    }
}
