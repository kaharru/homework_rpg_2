package com.narxoz.rpg.loot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IceLootTable implements LootTable {
    private final List<String> items;
    private final int gold;
    private final int xp;

    public IceLootTable() {
        this.items = new ArrayList<>();
        items.add("Ice Gem");
        items.add("Frost Scale");
        items.add("Ice Rune");
        this.gold = 220;
        this.xp = 580;
    }

    private IceLootTable(List<String> items, int gold, int xp) {
        this.items = new ArrayList<>(items);
        this.gold = gold;
        this.xp = xp;
    }

    @Override public List<String> getItems() { return Collections.unmodifiableList(items); }
    @Override public int getGoldDrop() { return gold; }
    @Override public int getExperienceDrop() { return xp; }
    @Override public LootTable clone() { return new IceLootTable(items, gold, xp); }

}
