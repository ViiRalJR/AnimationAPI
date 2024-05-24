package me.viiral.animations.animations.utils;

import me.viiral.animations.animations.utils.exceptions.UnsupportedEntityType;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;

public abstract class AbstractAnimations {
    protected LivingEntity entity;
    protected float armLength;
    protected float height;

    public AbstractAnimations(EntityAnimator entityAnimator) {
        this.entity = entityAnimator.getEntity();
        this.setup();
    }

    public AbstractAnimations(LivingEntity entity) {
        this.entity = entity;
        this.setup();
    }


    private void setup() {
        switch(this.entity.getType()) {
            case ARMOR_STAND:
                if (((ArmorStand)this.entity).isSmall()) {
                    this.armLength = 0.25F;
                    this.height = 1.2F;
                    return;
                }

                this.armLength = 0.5F;
                this.height = 2.2F;
                return;
            case GIANT:
                this.armLength = 3.5F;
                this.height = 3.0F;
                return;
            default:
                throw new UnsupportedEntityType(this.entity);
        }
    }


    public void delete() {
        this.entity.remove();
    }

    public abstract void teleport(Location var1);

    public abstract Location getLocation();

    public LivingEntity getEntity() {
        return this.entity;
    }

    public float getArmLength() {
        return this.armLength;
    }

    public float getHeight() {
        return this.height;
    }
}
