package me.viiral.animations.animations.utils;

import me.viiral.animations.utils.Teleport;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.EulerAngle;

public class HeadRotatorAnimation extends AbstractAnimations {

    private static final EulerAngle TILTED_EULER_ANGLE = new EulerAngle(-0.7D, 0.0D, 0.0D);
    private static final float DEFAULT_ROTATION = 15.0F;
    private float rotation = 15.0F;
    private int index = 0;

    public HeadRotatorAnimation(EntityAnimator entityAnimator) {
        super(entityAnimator);
    }

    public HeadRotatorAnimation(LivingEntity entity) {
        super(entity);
    }

    public HeadRotatorAnimation setRotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public HeadRotatorAnimation setTilted() {
        if (this.entity instanceof ArmorStand) {
            ((ArmorStand)this.entity).setHeadPose(TILTED_EULER_ANGLE);
        }

        return this;
    }

    public HeadRotatorAnimation setTilted(EulerAngle eulerAngle) {
        if (this.entity instanceof ArmorStand) {
            ((ArmorStand)this.entity).setHeadPose(eulerAngle);
        }

        return this;
    }

    public Location getLocation() {
        return this.entity.getLocation();
    }

    public void teleport(Location location) {
        float yaw = this.rotation * (float)this.index;
        location.setYaw(yaw);
        Teleport.tp(this.entity, location);
        ++this.index;
    }
}
