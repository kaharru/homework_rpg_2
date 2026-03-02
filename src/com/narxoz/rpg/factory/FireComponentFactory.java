package com.narxoz.rpg.factory;
import com.narxoz.rpg.combat.*;
import com.narxoz.rpg.loot.FireLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
public class FireComponentFactory  implements EnemyComponentFactory{
    @Override public List<Ability> createAbilities() {
        List<Ability> list = new ArrayList<>();
        list.add(new FlameBreath());
        list.add(new FireShield());
        return list;
    }
    @Override public LootTable createLootTable() { return new FireLootTable(); }
    @Override public String createAIBehavior() { return "AGGRESSIVE"; }
    @Override public String getThemeName() { return "Fire"; }
    @Override public String getElement() { return "FIRE"; }
}
