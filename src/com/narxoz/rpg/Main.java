package com.narxoz.rpg;
import com.narxoz.rpg.builder.EnemyDirector;
import com.narxoz.rpg.combat.PoisonStab;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.*;
import com.narxoz.rpg.prototype.EnemyRegistry;
/**
 * Main demonstration class for the RPG Enemy System.
 *
 * ============================================================
 * CREATIONAL PATTERNS CAPSTONE
 * ============================================================
 *
 * This demo must showcase ALL FOUR creational design patterns
 * working together in one unified system:
 *
 *   1. ABSTRACT FACTORY — Create themed enemy component families
 *   2. BUILDER          — Construct complex enemies step-by-step
 *   3. FACTORY METHOD   — Embedded in Builder.build() and Director
 *   4. PROTOTYPE        — Clone enemies into variants efficiently
 *
 * The patterns work together in a pipeline:
 *
 *   Abstract Factory (themed components)
 *          |
 *          v
 *   Builder (assembles enemy from components)
 *          |
 *          v  <-- Factory Method: build() produces the Enemy
 *   Prototype (clones built enemy into variants)
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * Your Main.java should demonstrate each pattern clearly,
 * then show them working together. Follow the structure below.
 *
 * Expected output flow:
 *   Part 1: Abstract Factory creates themed components
 *   Part 2: Builder constructs complex enemies
 *   Part 3: Prototype clones enemies into variants
 *   Part 4: Full pipeline — all patterns integrated
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Тест");

        // Abstract Factory
        EnemyComponentFactory fireFactory = new FireComponentFactory();
        EnemyComponentFactory iceFactory = new IceComponentFactory();
        EnemyComponentFactory shadowFactory = new ShadowComponentFactory();

        System.out.println("[Abstract Factory]");
        System.out.println("Fire -> abilities=" + fireFactory.createAbilities().size()
                + ", lootItems=" + fireFactory.createLootTable().getItems().size()
                + ", ai=" + fireFactory.createAIBehavior());

        // Builder (+ Factory Method in build()) via Director
        EnemyDirector director = new EnemyDirector();
        Enemy fireGoblin = director.createMinion(fireFactory);
        Enemy iceGoblin = director.createElite(iceFactory);
        Enemy shadowChampion = director.createMiniBoss(shadowFactory);
        Enemy fireDragon = director.createRaidBoss(fireFactory);

        System.out.println("\n[Builder + Factory Method]");
        fireGoblin.displayInfo();
        iceGoblin.displayInfo();
        shadowChampion.displayInfo();
        fireDragon.displayInfo();

        // Prototype
        EnemyRegistry registry = new EnemyRegistry();
        registry.registerTemplate("goblin_base", fireGoblin);
        registry.registerTemplate("dragon_base", fireDragon);
        registry.registerTemplate("champion_base", shadowChampion);

        System.out.println("\n[Prototype] Templates: " + registry.listTemplates());

        // Deep copy proof
        Enemy goblinClone = registry.createFromTemplate("goblin_base");
        goblinClone.setName("Goblin Clone + Poison");
        goblinClone.addAbility(new PoisonStab());

        System.out.println("\n[Deep Copy Proof]");
        System.out.println("Clone:    ");goblinClone.displayInfo();
        System.out.println("Template: ");fireGoblin.displayInfo();
        System.out.println("abilities template=" + fireGoblin.getAbilities().size()
                + ", clone=" + goblinClone.getAbilities().size());

        // Variants from dragon template
        Enemy baseDragon = registry.createFromTemplate("dragon_base");

        Enemy iceDragon = baseDragon.clone();
        iceDragon.setName("Ice Dragon Variant");
        iceDragon.setElement(iceFactory.getElement());
        iceDragon.setAIBehavior(iceFactory.createAIBehavior());
        iceDragon.setAbilities(iceFactory.createAbilities());
        iceDragon.setLootTable(iceFactory.createLootTable());

        Enemy shadowDragon = baseDragon.clone();
        shadowDragon.setName("Shadow Dragon Variant");
        shadowDragon.setElement(shadowFactory.getElement());
        shadowDragon.setAIBehavior(shadowFactory.createAIBehavior());
        shadowDragon.setAbilities(shadowFactory.createAbilities());
        shadowDragon.setLootTable(shadowFactory.createLootTable());

        System.out.println("\n[Variants from Prototype]");
        System.out.println("Base:   "); baseDragon.displayInfo();
        System.out.println("Ice:    ");iceDragon.displayInfo();
        System.out.println("Shadow: ");shadowDragon.displayInfo();

        System.out.println("Rabotaet i vse proshlo uspeshno. Pervashtan jep qalu bolmady endi.");
    }

}
