package com.narxoz.rpg.loot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class FireLootTable implements LootTable {
    private final List<String> items;
    private final int gold;
    private final int xp;

    public FireLootTable() {
        this.items = new ArrayList<>();
        items.add("Fire Gem");
        items.add("Dragon Scale");
        items.add("Flame Rune");
        this.gold = 250;
        this.xp = 600;
    }

    private FireLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }
    @Override public LootTable clone() { return new FireLootTable(items, gold, xp); }

}
