package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Example basic enemy implementation — a simple Goblin.
 *
 * This is provided as a REFERENCE to show enemy structure.
 * Study this implementation, then create more enemies.
 *
 * Notice:
 * - Simple stats (low health, low damage)
 * - Basic constructor (only a few parameters — no Builder needed!)
 * - This is intentionally simple to contrast with DragonBoss.java
 *
 * ============================================================
 * IMPORTANT OBSERVATION:
 * ============================================================
 *
 * A Goblin is simple: name, health, damage, defense — done.
 * A regular constructor works fine here:
 *     new Goblin("Forest Goblin")
 *
 * But look at DragonBoss.java... THAT'S where Builder shines!
 * Simple objects don't need Builder. Complex objects do.
 * Knowing WHEN to use a pattern is as important as knowing HOW.
 *
 * ============================================================
 * PROTOTYPE PATTERN NOTE:
 * ============================================================
 *
 * Goblin is a GREAT candidate for Prototype pattern!
 * Imagine you need 50 goblins for a dungeon. Instead of:
 *     new Goblin("Goblin 1"), new Goblin("Goblin 2"), ...
 *
 * You can:
 *     Goblin template = new Goblin("Goblin");
 *     Enemy goblin1 = template.clone();  // Fast!
 *     Enemy goblin2 = template.clone();  // Fast!
 *
 * And for variants:
 *     Enemy elite = template.clone();
 *     // modify elite's stats to 2x
 *
 * TODO: Implement the clone() method with DEEP COPY.
 * TODO: Create similar basic enemies: Skeleton, Orc, etc.
 * TODO: Consider what needs deep vs shallow copy here.
 *   - Primitive stats (health, damage) → shallow copy is fine
 *   - Ability list → MUST be deep copied!
 *   - LootTable → MUST be deep copied!
 */
public class Goblin implements Enemy {

    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    private String element;
    private String aiBehavior;

    private List<Ability> abilities;
    private LootTable lootTable;

    private Map<Integer, Integer> phases;

    public Goblin() {
        this("Goblin");
    }


    public Goblin(String name) {
        this.name = name;
        this.health = 100;
        this.damage = 15;
        this.defense = 5;
        this.speed = 35;

        this.element = "NONE";
        this.aiBehavior = "BASIC";

        this.abilities = new ArrayList<>();
        this.phases = new LinkedHashMap<>();
    }

    // gett
    @Override public String getName() { return name; }
    @Override public int getHealth() { return health; }
    @Override public int getDamage() { return damage; }
    @Override public int getDefense() { return defense; }
    @Override public int getSpeed() { return speed; }

    @Override public String getElement() { return element; }
    @Override public String getAIBehavior() { return aiBehavior; }

    @Override public List<Ability> getAbilities() { return abilities; }
    @Override public LootTable getLootTable() { return lootTable; }
    @Override public Map<Integer, Integer> getPhases() { return phases; }
    // sett
    @Override public void setName(String name) { this.name = name; }
    @Override public void setHealth(int health) { this.health = health; }
    @Override public void setDamage(int damage) { this.damage = damage; }
    @Override public void setDefense(int defense) { this.defense = defense; }
    @Override public void setSpeed(int speed) { this.speed = speed; }

    @Override public void setElement(String element) { this.element = element; }
    @Override public void setAIBehavior(String aiBehavior) { this.aiBehavior = aiBehavior; }

    @Override
    public void addAbility(Ability ability) {
        if (ability == null) return;
        abilities.add(ability);
    }

    @Override
    public void setAbilities(List<Ability> abilities) {
        this.abilities = new ArrayList<>();
        if (abilities != null) this.abilities.addAll(abilities);
    }

    @Override public void setLootTable(LootTable lootTable) { this.lootTable = lootTable; }

    @Override
    public void addPhase(int phaseNumber, int healthThreshold) {
        phases.put(phaseNumber, healthThreshold);
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Goblin) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);
        System.out.println("Abilities: " + abilities.size());
        System.out.println("Loot: " + (lootTable == null ? "none" : lootTable.getItems()));
    }

    @Override
    public Enemy clone() {
        Goblin copy = new Goblin(this.name);
        copy.health = this.health;
        copy.damage = this.damage;
        copy.defense = this.defense;
        copy.speed = this.speed;

        copy.element = this.element;
        copy.aiBehavior = this.aiBehavior;


        copy.abilities = new ArrayList<>();
        for (Ability a : this.abilities) {
            copy.abilities.add(a == null ? null : a.clone());
        }

        copy.lootTable = (this.lootTable == null) ? null : this.lootTable.clone();


        copy.phases = new LinkedHashMap<>(this.phases);

        return copy;
    }


    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) return;
        this.health = (int) Math.round(this.health * multiplier);
        this.damage = (int) Math.round(this.damage * multiplier);
        this.defense = (int) Math.round(this.defense * multiplier);
        this.speed = (int) Math.round(this.speed * multiplier);
    }
    }

    // TODO: Implement clone() for Prototype pattern
    // This is CRITICAL! You must deep copy:
    //   - The abilities list (create new list, clone each ability)
    //   - The loot table (clone it)
    //   - Primitive fields can be copied directly
    //
    // Example skeleton:
    // public Enemy clone() {
    //     Goblin copy = new Goblin(this.name);
    //     copy.health = this.health;
    //     copy.damage = this.damage;
    //     copy.defense = this.defense;
    //     copy.speed = this.speed;
    //     copy.abilities = ???  // DEEP COPY! Not just = this.abilities!
    //     copy.lootTable = ???  // DEEP COPY!
    //     return copy;
    // }

    // TODO: Add helper methods for Prototype variant creation
    // Consider methods like:
    // - void multiplyStats(double multiplier) — for Elite/Champion variants
    // - void addAbility(Ability ability) — for enhanced variants
    // - void setElement(String element) — for elemental variants

