package me.viiral.animations.animations.utils;

import me.viiral.animations.animations.utils.entity.AbstractEntityEquipment;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class ArmorStandAnimator implements EntityAnimator {
    private AbstractEntityEquipment entityEquipment;
    private ArmorStand animator;

    public ArmorStandAnimator(Location location) {
        this.animator = (ArmorStand)location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.animator.setGravity(false);
        this.animator.setVisible(false);
        this.animator.setBasePlate(false);
    }

    public ArmorStandAnimator setSmall() {
        this.animator.setSmall(true);
        return this;
    }

    public boolean isSmall() {
        return this.animator.isSmall();
    }

    public EntityAnimator setEntityEquipment(AbstractEntityEquipment entityEquipment) {
        this.entityEquipment = entityEquipment;
        return this;
    }

    public AbstractEntityEquipment getEntityEquipment() {
        return this.entityEquipment;
    }

    public EntityAnimator getInstance() {
        return this;
    }

    public ArmorStand getEntity() {
        return this.animator;
    }
}
