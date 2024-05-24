package me.viiral.animations.animations.utils.entity;

import org.bukkit.inventory.ItemStack;

public interface AbstractEntityEquipment {
    public AbstractEntityEquipment setItemInMainHand(ItemStack var1);

    public AbstractEntityEquipment setHelmet(ItemStack var1);

    public AbstractEntityEquipment setChestplate(ItemStack var1);

    public AbstractEntityEquipment setLeggings(ItemStack var1);

    public AbstractEntityEquipment setBoots(ItemStack var1);

    public AbstractEntityEquipment getInstance();
}
