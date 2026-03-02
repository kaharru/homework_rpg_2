package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Example complex boss enemy — THE REASON BUILDER PATTERN EXISTS.
 *
 * ============================================================
 * READ THIS CAREFULLY — THIS IS THE CORE LEARNING MOMENT!
 * ============================================================
 *
 * Look at this constructor. REALLY look at it.
 * Count the parameters. Imagine using it in Main.java.
 * Imagine a teammate trying to understand what each parameter means.
 *
 * This is called the "Telescoping Constructor" anti-pattern.
 * It's the #1 problem that the Builder pattern solves.
 *
 * YOUR MISSION:
 * After studying this horror, you will create an EnemyBuilder
 * that constructs DragonBoss (and other complex enemies)
 * step-by-step, with clear and readable code.
 *
 * Compare:
 *
 *   BEFORE (Telescoping Constructor — current code):
 *   new DragonBoss("Fire Dragon", 50000, 500, 200, 50, "FIRE",
 *       abilities, 30000, 15000, 5000, loot, "AGGRESSIVE",
 *       true, true, 20);
 *   // What does 'true, true, 20' mean? Nobody knows!
 *
 *   AFTER (Builder Pattern — your implementation):
 *   new BossEnemyBuilder()
 *       .setName("Fire Dragon")
 *       .setHealth(50000)
 *       .setDamage(500)
 *       .setDefense(200)
 *       .setSpeed(50)
 *       .setElement("FIRE")
 *       .addAbility(new FlameBreath())
 *       .addAbility(new WingBuffet())
 *       .addPhase(1, 50000)
 *       .addPhase(2, 30000)
 *       .addPhase(3, 15000)
 *       .setLootTable(fireLoot)
 *       .setAI("AGGRESSIVE")
 *       .build();
 *   // Every parameter is labeled! Readable! Maintainable!
 *
 * ============================================================
 * TODO: After implementing your Builder, REFACTOR this class!
 * ============================================================
 * - Remove (or simplify) the telescoping constructor
 * - Make DragonBoss constructable ONLY through a Builder
 * - Make the built DragonBoss IMMUTABLE (no setters!)
 * - The Builder handles all the complexity
 */
public class DragonBoss implements Enemy {
    // Basic Stats
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

    //  Theme / AI
    private String element;      // e.g., FIRE / ICE / SHADOW
    private String aiBehavior;   // e.g., AGGRESSIVE / DEFENSIVE / TACTICAL

    // Components
    private List<Ability> abilities;
    private LootTable lootTable;

    // Boss Phases
    private Map<Integer, Integer> phases; // phase -> hp threshold

    //  Special Properties
    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;

    /**
     * Telescoping constructor (legacy).
     * Builder should ideally be the only place that calls this.
     */
    public DragonBoss(String name,
                      int health, int damage, int defense, int speed,
                      String element,
                      List<Ability> abilities,
                      int phase1Threshold, int phase2Threshold, int phase3Threshold,
                      LootTable lootTable,
                      String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;

        this.element = element;
        this.aiBehavior = aiBehavior;

        this.abilities = (abilities == null) ? new ArrayList<>() : new ArrayList<>(abilities);
        this.lootTable = lootTable;

        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);

        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

    //  Enemy getters
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

    //  Enemy setters / modifiers
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
        if (abilities == null) abilities = new ArrayList<>();
        abilities.add(ability);
    }

    @Override
    public void setAbilities(List<Ability> abilities) {
        this.abilities = new ArrayList<>();
        if (abilities != null) this.abilities.addAll(abilities);
    }

    @Override
    public void setLootTable(LootTable lootTable) {
        this.lootTable = lootTable;
    }

    @Override
    public void addPhase(int phaseNumber, int healthThreshold) {
        if (phases == null) phases = new HashMap<>();
        phases.put(phaseNumber, healthThreshold);
    }

    // Extra helpers (optional but useful for variants)
    public void multiplyStats(double multiplier) {
        if (multiplier <= 0) return;
        health  = (int) Math.round(health  * multiplier);
        damage  = (int) Math.round(damage  * multiplier);
        defense = (int) Math.round(defense * multiplier);
        speed   = (int) Math.round(speed   * multiplier);
    }

    public void displayInfo() {
        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("HP: " + health + " DMG: " + damage + " DEF: " + defense + " SPD: " + speed);
        System.out.println("Element: " + element + " | AI: " + aiBehavior);
        System.out.println("CanFly: " + canFly + " | Breath: " + hasBreathAttack + " | Wingspan: " + wingspan);

        System.out.println("Abilities (" + (abilities == null ? 0 : abilities.size()) + "):");
        if (abilities != null) {
            for (Ability a : abilities) {
                System.out.println(" - " + a.getName() + " (" + a.getDamage() + "): " + a.getDescription());
            }
        }

        System.out.println("Phases (" + (phases == null ? 0 : phases.size()) + "): " + phases);

        System.out.println("Loot: " + (lootTable == null ? "none" : lootTable.getItems()));
    }

    //Prototype: deep clone
    @Override
    public Enemy clone() {
        // Deep copy abilities
        List<Ability> abilitiesCopy = new ArrayList<>();
        if (this.abilities != null) {
            for (Ability a : this.abilities) {
                abilitiesCopy.add(a == null ? null : a.clone());
            }
        }

        // Deep copy loot
        LootTable lootCopy = (this.lootTable == null) ? null : this.lootTable.clone();

        // Copy phases
        Map<Integer, Integer> phasesCopy = new HashMap<>();
        if (this.phases != null) phasesCopy.putAll(this.phases);

        int p1 = phasesCopy.getOrDefault(1, 0);
        int p2 = phasesCopy.getOrDefault(2, 0);
        int p3 = phasesCopy.getOrDefault(3, 0);

        DragonBoss copy = new DragonBoss(
                this.name,
                this.health, this.damage, this.defense, this.speed,
                this.element,
                abilitiesCopy,
                p1, p2, p3,
                lootCopy,
                this.aiBehavior,
                this.canFly, this.hasBreathAttack, this.wingspan
        );

        copy.phases = new HashMap<>(phasesCopy);

        return copy;
    }

    public DragonBoss() {
        this.abilities = new ArrayList<>();
        this.phases = new HashMap<>();
    }

}
