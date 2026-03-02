package com.narxoz.rpg.factory;
import com.narxoz.rpg.combat.*;
import com.narxoz.rpg.loot.IceLootTable;
import com.narxoz.rpg.loot.LootTable;

import java.util.ArrayList;
import java.util.List;
public class IceComponentFactory  implements EnemyComponentFactory{
    @Override public List<Ability> createAbilities() {
        List<Ability> list = new ArrayList<>();
        list.add(new FrostBreath());
        list.add(new IceShield());
        return list;
    }
    @Override public LootTable createLootTable() { return new IceLootTable(); }
    @Override public String createAIBehavior() { return "DEFENSIVE"; }
    @Override public String getThemeName() { return "Ice"; }
    @Override public String getElement() { return "ICE"; }
}
