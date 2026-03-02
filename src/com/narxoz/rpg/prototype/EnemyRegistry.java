package com.narxoz.rpg.prototype;
import com.narxoz.rpg.enemy.Enemy;
import java.util.*;

public class EnemyRegistry {
    private final Map<String, Enemy> templates = new LinkedHashMap<>();

    public void registerTemplate(String key, Enemy template) {
        if (key == null || key.trim().isEmpty()) throw new IllegalArgumentException("key");
        if (template == null) throw new IllegalArgumentException("template");
        templates.put(key, template);
    }

    public Enemy createFromTemplate(String key) {
        Enemy template = templates.get(key);
        if (template == null) throw new IllegalArgumentException("Unknown template: " + key);
        return template.clone(); // ALWAYS clone
    }

    public List<String> listTemplates() {
        return new ArrayList<>(templates.keySet());
    }
}
