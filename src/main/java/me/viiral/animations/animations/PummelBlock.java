package me.viiral.animations.animations;

import me.viiral.animations.animations.utils.ArmorStandAnimator;
import me.viiral.animations.animations.utils.HeadRotatorAnimation;
import me.viiral.animations.animations.utils.entity.EntityEquipment;
import me.viiral.animations.utils.PVPUtils;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class PummelBlock {

    private final static int MAX_INDEX = 50;
    private final static int TICKS_BETWEEN_BLOCK = 2;

    private final LivingEntity target;
    private final Location location;

    private HeadRotatorAnimation animation;
    private int index = 0;

    public PummelBlock(int index, Location location, LivingEntity target) {
        this.target = target;
        this.index = -(index * TICKS_BETWEEN_BLOCK) - 1;
        this.location = location;
    }

    public void update() {
        index++;
        if (index < 0) return;
        if (index > MAX_INDEX) return;
        if (index == 0) {
            ArmorStandAnimator armorStandAnimator = new ArmorStandAnimator(location);
            armorStandAnimator.setEntityEquipment(new EntityEquipment(armorStandAnimator.getEntity()).setHelmet(new ItemStack(location.getBlock().getType())));
            animation = new HeadRotatorAnimation(armorStandAnimator);
        } else if (index > (MAX_INDEX - 3)) {
            animation.teleport(target.getLocation());
            if (index == MAX_INDEX) {
                PVPUtils.damage(target, 8f);
                animation.delete();
            }
        } else {
            animation.teleport(location.add(0, 0.0, 0));
        }
    }

    public static int getMaxIndex(int amount) {
        return MAX_INDEX + 1 + (5 * (amount - 1));
    }
}
