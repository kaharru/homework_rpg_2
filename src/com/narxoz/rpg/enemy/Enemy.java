package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import java.util.Map;
import java.util.List;

/**
 * Base interface for all enemies in the RPG system.
 *
 * Every enemy — from a lowly Goblin to an Ancient Dragon — shares
 * certain characteristics: they have stats, abilities, and loot.
 * But HOW they are created varies dramatically.
 *
 * ============================================================
 * WHY THIS INTERFACE MATTERS FOR DESIGN PATTERNS:
 * ============================================================
 *
 * Builder Pattern:
 *   Complex enemies have many fields (stats, abilities, phases, loot, AI).
 *   The Builder pattern constructs enemies step-by-step instead of
 *   cramming everything into one monstrous constructor.
 *   → Think: Should Enemy be immutable once built? (Hint: YES!)
 *
 * Prototype Pattern:
 *   This interface includes a clone() method. Enemies must be CLONABLE
 *   so we can create variants efficiently:
 *     Base Goblin → Elite Goblin → Goblin Champion → Goblin King
 *   → Think: What needs DEEP copying? What can be SHALLOW copied?
 *
 * Factory Method:
 *   The Builder's build() method IS a factory method — it produces
 *   Enemy objects. Different builders produce different enemy types.
 *
 * Abstract Factory:
 *   Enemy components (abilities, loot) come from themed factories.
 *   A FireComponentFactory guarantees all components match the fire theme.
 *
 * ============================================================
 * YOUR TASKS:
 * ============================================================
 *
 * TODO: Decide — should this be an interface or abstract class?
 *   - Interface: If implementations are very different
 *   - Abstract class: If you want shared fields (name, health, etc.)
 *   Hint: An abstract class with shared stat fields might be cleaner.
 *
 * TODO: Define the core enemy contract.
 *   Every enemy should provide:
 *   - Basic stats (health, damage, defense, speed)
 *   - Abilities they can use
 *   - Loot they drop when defeated
 *   - Information display (for the demo)
 *   - Clone method (for Prototype pattern)
 *
 * TODO: Think about immutability.
 *   - Once built by the Builder, should enemy stats change?
 *   - Should clone() return a mutable or immutable copy?
 *   - How do you allow Prototype to modify cloned stats?
 */
public interface Enemy {
    String getName();
    int getHealth();
    int getDamage();
    int getDefense();
    int getSpeed();

    String getElement();
    String getAIBehavior();

    List<Ability> getAbilities();
    LootTable getLootTable();
    Map<Integer, Integer> getPhases();

    void setName(String name);
    void setHealth(int health);
    void setDamage(int damage);
    void setDefense(int defense);
    void setSpeed(int speed);

    void setElement(String element);
    void setAIBehavior(String aiBehavior);

    void addAbility(Ability ability);
    void setAbilities(List<Ability> abilities);

    void setLootTable(LootTable lootTable);

    void addPhase(int phaseNumber, int healthThreshold);

    Enemy clone();

    void displayInfo();

}
