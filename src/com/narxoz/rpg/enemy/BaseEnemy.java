package com.narxoz.rpg.enemy;
import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;
import java.util.*;
public abstract class BaseEnemy implements Enemy {
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;
    private String element;
    private String aiBehavior;

    private List<Ability> abilities = new ArrayList<>();
    private LootTable lootTable;
    private Map<Integer, Integer> phases = new LinkedHashMap<>();

    protected BaseEnemy() {}
    @Override
    public abstract Enemy clone();


    protected BaseEnemy(BaseEnemy other) {
        this.name = other.name;
        this.health = other.health;
        this.damage = other.damage;
        this.defense = other.defense;
        this.speed = other.speed;
        this.element = other.element;
        this.aiBehavior = other.aiBehavior;

        // DEEP COPY abilities
        this.abilities = new ArrayList<>();
        for (Ability a : other.abilities) {
            this.abilities.add(a == null ? null : a.clone());
        }

        // DEEP COPY loot
        this.lootTable = other.lootTable == null ? null : other.lootTable.clone();

        // DEEP COPY phases
        this.phases = new LinkedHashMap<>(other.phases);
    }

    @Override public String getName() { return name; }
    @Override public int getHealth() { return health; }
    @Override public int getDamage() { return damage; }
    @Override public int getDefense() { return defense; }
    @Override public int getSpeed() { return speed; }
    @Override public String getElement() { return element; }
    @Override public String getAIBehavior() { return aiBehavior; }

    @Override public List<Ability> getAbilities() { return Collections.unmodifiableList(abilities); }
    @Override public LootTable getLootTable() { return lootTable; }
    @Override public Map<Integer, Integer> getPhases() { return Collections.unmodifiableMap(phases); }

    @Override public void addAbility(Ability ability) { if (ability != null) abilities.add(ability); }
    @Override public void setAbilities(List<Ability> abilities) {
        this.abilities = new ArrayList<>();
        if (abilities != null) this.abilities.addAll(abilities);
    }
    @Override public void setLootTable(LootTable lootTable) { this.lootTable = lootTable; }
    @Override public void addPhase(int phaseNumber, int healthThreshold) { phases.put(phaseNumber, healthThreshold); }

    @Override public void setName(String name) { this.name = name; }
    @Override public void setHealth(int health) { this.health = health; }
    @Override public void setDamage(int damage) { this.damage = damage; }
    @Override public void setDefense(int defense) { this.defense = defense; }
    @Override public void setSpeed(int speed) { this.speed = speed; }
    @Override public void setElement(String element) { this.element = element; }
    @Override public void setAIBehavior(String ai) { this.aiBehavior = ai; }

}
