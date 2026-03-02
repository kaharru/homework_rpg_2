package com.narxoz.rpg.builder;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class EnemyDirector {
    public Enemy createMinion(EnemyComponentFactory factory) {
        // Factory Method delegation: Director calls builder.build() via interface.
        return new BasicEnemyBuilder()
                .setName(factory.getThemeName() + " Goblin")
                .setHealth(120)
                .setDamage(15)
                .setDefense(5)
                .setSpeed(35)
                .setElement(factory.getElement())
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createElite(EnemyComponentFactory factory) {
        return new BasicEnemyBuilder()
                .setName("Elite " + factory.getThemeName() + " Goblin")
                .setHealth(240)
                .setDamage(30)
                .setDefense(12)
                .setSpeed(40)
                .setElement(factory.getElement())
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .build();
    }

    public Enemy createMiniBoss(EnemyComponentFactory factory) {
        return new BossEnemyBuilder()
                .setName(factory.getThemeName() + " Goblin Champion")
                .setHealth(900)
                .setDamage(80)
                .setDefense(35)
                .setSpeed(35)
                .setElement(factory.getElement())
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .addPhase(1, 70)
                .addPhase(2, 35)
                .build();
    }

    public Enemy createRaidBoss(EnemyComponentFactory factory) {
        return new BossEnemyBuilder()
                .setName(factory.getThemeName() + " Ancient Dragon")
                .setHealth(50000)
                .setDamage(500)
                .setDefense(200)
                .setSpeed(50)
                .setElement(factory.getElement())
                .setAIBehavior(factory.createAIBehavior())
                .setAbilities(factory.createAbilities())
                .setLootTable(factory.createLootTable())
                .addPhase(1, 70)
                .addPhase(2, 40)
                .addPhase(3, 15)
                .build();
    }
}
