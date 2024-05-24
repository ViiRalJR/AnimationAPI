package me.viiral.animations.animations.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public final class AnimationDisplayer {

    private AnimationDisplayer() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void animateForNearby(Location location, float radius, Consumer<Player> consumer) {
        final float r = radius * radius;
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.getLocation().distanceSquared(location) <= r) consumer.accept(player);
        });
    }

}
