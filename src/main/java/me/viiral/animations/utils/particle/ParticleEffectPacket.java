package me.viiral.animations.utils.particle;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Location;
import org.bukkit.entity.Player;

final class ParticleEffectPacket
{
    ParticleEffect effect;
    Location l;
    float speed;
    int amount;
    float offsetX;
    float offsetY;
    float offsetZ;
    Player p;
    private Packet cachedPacket;

    ParticleEffectPacket(final ParticleEffect pe, final Location l, final float speed, final int amount) {
        this.effect = pe;
        this.l = l;
        this.speed = speed;
        this.amount = amount;
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.0f;
    }

    ParticleEffectPacket(final Player pReciever, final ParticleEffect pe, final Location l, final float speed, final int amount) {
        this.p = pReciever;
        this.effect = pe;
        this.l = l;
        this.speed = speed;
        this.amount = amount;
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.0f;
    }

    ParticleEffectPacket(final Player pReciever, final ParticleEffect pe, final Location l, final float offsetX, final float offsetY, final float offsetZ, final float speed, final int amount) {
        this.p = pReciever;
        this.effect = pe;
        this.l = l;
        this.speed = speed;
        this.amount = amount;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    ParticleEffectPacket(final Player pReciever, final Location location, final Packet cachedParticle) {
        this.p = pReciever;
        this.cachedPacket = cachedParticle;
        this.l = location;
    }

    public ParticleEffect getEffect() {
        return this.effect;
    }

    public Location getL() {
        return this.l;
    }

    public float getSpeed() {
        return this.speed;
    }

    public int getAmount() {
        return this.amount;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public Player getP() {
        return this.p;
    }
}

