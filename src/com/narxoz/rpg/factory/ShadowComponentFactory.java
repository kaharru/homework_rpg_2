package com.narxoz.rpg.factory;
import com.narxoz.rpg.combat.*;
import com.narxoz.rpg.loot.LootTable;
import com.narxoz.rpg.loot.ShadowLootTable;

import java.util.ArrayList;
import java.util.List;
public class ShadowComponentFactory implements EnemyComponentFactory{
    @Override public List<Ability> createAbilities() {
        List<Ability> list = new ArrayList<>();
        list.add(new ShadowStrike());
        list.add(new Vanish());
        return list;
    }
    @Override public LootTable createLootTable() { return new ShadowLootTable(); }
    @Override public String createAIBehavior() { return "TACTICAL"; }
    @Override public String getThemeName() { return "Shadow"; }
    @Override public String getElement() { return "SHADOW"; }
}
