package me.viiral.animations.animations.utils.entity;


import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class EntityEquipment implements AbstractEntityEquipment {
    private LivingEntity entity;

    public EntityEquipment(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public AbstractEntityEquipment setItemInMainHand(ItemStack item) {
        this.entity.getEquipment().setItemInHand(item);
        return this;
    }

    @Override
    public AbstractEntityEquipment setHelmet(ItemStack item) {
        this.entity.getEquipment().setHelmet(item);
        return this;
    }

    @Override
    public AbstractEntityEquipment setChestplate(ItemStack item) {
        this.entity.getEquipment().setChestplate(item);
        return this;
    }

    @Override
    public AbstractEntityEquipment setLeggings(ItemStack item) {
        this.entity.getEquipment().setLeggings(item);
        return this;
    }

    @Override
    public AbstractEntityEquipment setBoots(ItemStack item) {
        this.entity.getEquipment().setBoots(item);
        return this;
    }

    @Override
    public AbstractEntityEquipment getInstance() {
        return null;
    }
}

