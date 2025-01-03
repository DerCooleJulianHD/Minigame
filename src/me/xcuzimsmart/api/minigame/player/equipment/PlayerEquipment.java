package me.xcuzimsmart.minigame.player.equipment;

import me.xcuzimsmart.minigame.player.equipment.utils.EquipmentType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface PlayerEquipment {

    Player player();
    EquipmentType type();

    ItemStack helmet();
    ItemStack jacket();
    ItemStack pants();
    ItemStack boots();

    List<ItemStack> list();
}
