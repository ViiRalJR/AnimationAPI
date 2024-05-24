package me.viiral.animations.utils;

import org.bukkit.EntityEffect;
import org.bukkit.entity.LivingEntity;

public class PVPUtils {

    public static void damage(LivingEntity attacker, LivingEntity victim, double damage) {
        victim.playEffect(EntityEffect.HURT);
        victim.damage(damage, attacker);
    }

    public static void damage(LivingEntity victim, double damage) {
        victim.playEffect(EntityEffect.HURT);
        victim.damage(damage);
    }

}
