package com.narxoz.rpg.factory;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;

/**
 * Abstract Factory interface for creating themed enemy components.
 *
 * ============================================================
 * THE PROBLEM THIS SOLVES (Abstract Factory Pattern):
 * ============================================================
 *
 * Enemies in our RPG have themed components that MUST match:
 * - A Fire Dragon must have fire abilities, fire loot, and aggressive AI
 * - An Ice Dragon must have ice abilities, ice loot, and defensive AI
 * - You CANNOT mix Fire abilities with Ice loot — that breaks the theme!
 *
 * Without Abstract Factory, students might write:
 *     Ability a = new FlameBreath();
 *     LootTable l = new IceLootTable();  // MISMATCH! Fire ability + Ice loot!
 *
 * Abstract Factory guarantees consistency: ONE factory creates ALL components
 * for a single theme. If you use FireComponentFactory, you get:
 *     fire abilities + fire loot + fire AI behavior (guaranteed match!)
 *
 * ============================================================
 * CONNECTION TO HOMEWORK 1:
 * ============================================================
 *
 * Remember Abstract Factory from HW1?
 * - HW1: EquipmentFactory created matching Weapon + Armor families
 *   (MedievalFactory -> Iron Sword + Plate Armor)
 * - HW2: EnemyComponentFactory creates matching Ability + Loot + AI families
 *   (FireFactory -> Flame Breath + Fire Loot + Aggressive AI)
 *
 * Same pattern, new context! You're reinforcing what you learned.
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * TODO: Implement at least 3 concrete component factories:
 *   - FireComponentFactory   (fire abilities, fire loot, aggressive AI)
 *   - IceComponentFactory    (ice abilities, ice loot, defensive AI)
 *   - ShadowComponentFactory (shadow abilities, shadow loot, tactical AI)
 *
 * TODO: Each factory must create a CONSISTENT family of components.
 *   - The abilities must match the theme
 *   - The loot must match the theme
 *   - The AI behavior must match the theme
 *
 * TODO: Your Builder will use these factories to get themed components:
 *   EnemyComponentFactory factory = new FireComponentFactory();
 *   Enemy dragon = new BossEnemyBuilder()
 *       .setName("Fire Dragon")
 *       .setAbilities(factory.createAbilities())
 *       .setLootTable(factory.createLootTable())
 *       .build();
 *
 * Think: How is this similar to HW1's EquipmentFactory?
 * Think: What ensures a Fire enemy never gets Ice loot?
 * Think: How easy is it to add a new theme (e.g., NatureComponentFactory)?
 */
public interface EnemyComponentFactory {
    List<Ability> createAbilities();
    LootTable createLootTable();
    String createAIBehavior();


    String getThemeName();
    String getElement();

}
