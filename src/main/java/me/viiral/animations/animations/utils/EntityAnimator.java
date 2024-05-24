package me.viiral.animations.animations.utils;

import me.viiral.animations.animations.utils.entity.AbstractEntityEquipment;
import org.bukkit.entity.LivingEntity;

public interface EntityAnimator {
    public EntityAnimator setEntityEquipment(AbstractEntityEquipment var1);
    public AbstractEntityEquipment getEntityEquipment();
    public EntityAnimator getInstance();
    public LivingEntity getEntity();
}
