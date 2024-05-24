package me.viiral.animations.utils.particle;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class WorldUtil {
    public static void sendWorldEffect(final Block b, final int effectID) {
        BlockPosition position = new BlockPosition(b.getX(), b.getY(), b.getZ());
        final PacketPlayOutWorldEvent particles = new PacketPlayOutWorldEvent(effectID, position, b.getTypeId(), false);
        ParticleEffect.sendWorldEventPacket(particles, b.getLocation());
    }

    public static void sendWorldEffect(Block b, int materialID, int effectID) {
        BlockPosition position = new BlockPosition(b.getX(), b.getY(), b.getZ());
        PacketPlayOutWorldEvent particles = new PacketPlayOutWorldEvent(effectID, position, materialID, false);
        ParticleEffect.sendWorldEventPacket(particles, b.getLocation());
    }

    public static void sendBlockBreakAnimation(Block b, int breaks, List<Player> players) {
        BlockPosition position = new BlockPosition(b.getX(), b.getY(), b.getZ());
        PacketPlayOutBlockBreakAnimation animation = new PacketPlayOutBlockBreakAnimation(0, position, breaks);
        for (Player pl : players) {
            ((CraftPlayer)pl).getHandle().playerConnection.sendPacket(animation);
        }
    }

    public static void sendWorldBreakPacket(Block b) {
        BlockPosition position = new BlockPosition(b.getX(), b.getY(), b.getZ());
        PacketPlayOutWorldEvent particles = new PacketPlayOutWorldEvent(2001, position, b.getTypeId(), false);
        ParticleEffect.sendWorldEventPacket(particles, b.getLocation());
    }
}
