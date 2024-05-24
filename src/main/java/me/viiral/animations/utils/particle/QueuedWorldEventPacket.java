package me.viiral.animations.utils.particle;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;
import org.bukkit.Location;

final class QueuedWorldEventPacket
{
    PacketPlayOutWorldEvent packet;
    Location location;

    QueuedWorldEventPacket(final PacketPlayOutWorldEvent p, final Location l) {
        this.packet = p;
        this.location = l;
    }

    public PacketPlayOutWorldEvent getPacket() {
        return this.packet;
    }

    public Location getLocation() {
        return this.location;
    }
}