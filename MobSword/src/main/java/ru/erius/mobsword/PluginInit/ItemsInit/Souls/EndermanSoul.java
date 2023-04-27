package ru.erius.mobsword.PluginInit.ItemsInit.Souls;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class EndermanSoul extends Soul {

    private final static int ID = 4;
    private final static String NAME = "enderman_soul";
    private final static EntityType ENTITY_TYPE = EntityType.ENDERMAN;

    public EndermanSoul() {
        super(ID, NAME, ENTITY_TYPE);
    }

    @Override
    public void onConsumed(Player player) {

    }
}
