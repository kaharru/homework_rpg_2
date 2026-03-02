package com.narxoz.rpg.loot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ShadowLootTable implements LootTable {
    private final List<String> items;
    private final int gold;
    private final int xp;

    public ShadowLootTable() {
        this.items = new ArrayList<>();
        items.add("Shadow Gem");
        items.add("Dark Essence");
        items.add("Shadow Rune");
        this.gold = 300;
        this.xp = 650;
    }

    private ShadowLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }
    @Override public LootTable clone() { return new ShadowLootTable(items, gold, xp); }

}
