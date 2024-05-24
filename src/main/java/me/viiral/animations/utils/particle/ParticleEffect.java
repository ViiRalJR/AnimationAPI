package me.viiral.animations.utils.particle;

import lombok.Getter;
import me.viiral.animations.RecluseAnimationAPI;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
public enum ParticleEffect {
    HUGE_EXPLOSION("hugeexplosion", 0),
    LARGE_EXPLODE("largeexplode", 1),
    FIREWORKS_SPARK("fireworksSpark", 2),
    BUBBLE("bubble", 3),
    SUSPEND("suspend", 4),
    DEPTH_SUSPEND("depthSuspend", 5),
    TOWN_AURA("townaura", 6),
    CRIT("crit", 7),
    MAGIC_CRIT("magicCrit", 8),
    MOB_SPELL("mobSpell", 9),
    MOB_SPELL_AMBIENT("mobSpellAmbient", 10),
    SPELL("spell", 11),
    INSTANT_SPELL("instantSpell", 12),
    WITCH_MAGIC("witchMagic", 13),
    NOTE("note", 14),
    PORTAL("portal", 15),
    ENCHANTMENT_TABLE("enchantmenttable", 16),
    EXPLODE("explode", 17),
    FLAME("flame", 18),
    LAVA("lava", 19),
    FOOTSTEP("footstep", 20),
    SPLASH("splash", 21),
    LARGE_SMOKE("largesmoke", 22),
    CLOUD("cloud", 23),
    RED_DUST("reddust", 24),
    SNOWBALL_POOF("snowballpoof", 25),
    DRIP_WATER("dripWater", 26),
    DRIP_LAVA("dripLava", 27),
    SNOW_SHOVEL("snowshovel", 28),
    SLIME("slime", 29),
    HEART("heart", 30),
    ANGRY_VILLAGER("angryVillager", 31),
    HAPPY_VILLAGER("happyVillager", 32),
    ICONCRACK("iconcrack", 33),
    TILECRACK("tilecrack", 34);

    private String name;
    private int id;
    public static CopyOnWriteArrayList<ParticleEffectPacket> particlePacketQueue;
    public static CopyOnWriteArrayList<QueuedWorldEventPacket> sendPacketNearbyQueue;
    private static final Map<String, ParticleEffect> NAME_MAP;
    private static final Map<Integer, ParticleEffect> ID_MAP;
    static Constructor<?> cachedConstructor;
    static Class<?> cachedPacketClass;
    private static Method getHandle;
    private static Method sendPacketMethod;
    private static Field cachedPlayerConnectionField;

    ParticleEffect(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public static ParticleEffect fromName(String name) {
        if (name == null) {
            return null;
        }
        for (Map.Entry<String, ParticleEffect> e : NAME_MAP.entrySet()) {
            if (!e.getKey().equalsIgnoreCase(name)) continue;
            return e.getValue();
        }
        return null;
    }

    public static ParticleEffect fromId(int id) {
        return ID_MAP.get(id);
    }

    public static void sendWorldEventPacket(PacketPlayOutWorldEvent e, Location l) {
        sendPacketNearbyQueue.add(new QueuedWorldEventPacket(e, l));
    }

    public static void sendToPlayer(ParticleEffect effect, Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) {
        particlePacketQueue.add(new ParticleEffectPacket(player, effect, location, offsetX, offsetY, offsetZ, speed, count));
    }

    public static void sendCachedPacketToLocation(Packet cached, Location location) {
        particlePacketQueue.add(new ParticleEffectPacket(null, location, cached));
    }

    public static void sendCachedPacketToLocation(Packet cached, Player player, Location location) {
        particlePacketQueue.add(new ParticleEffectPacket(player, location, cached));
    }

    public static void sendToLocation(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) {
        particlePacketQueue.add(new ParticleEffectPacket(null, effect, location, offsetX, offsetY, offsetZ, speed, count));
    }

    public static void playEffect(ParticleEffect pe, Location l, float speed, int amount) {
        particlePacketQueue.add(new ParticleEffectPacket(pe, l, speed, amount));
    }

    private static void setValue(Object instance, String fieldName, Object value) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }

    private static Object getEntityPlayer(Player p) throws Exception {
        return (getHandle == null ? (getHandle = p.getClass().getMethod("getHandle", new Class[0])) : getHandle).invoke(p, new Object[0]);
    }

    private static String getPackageName() {
        return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }


    private static void sendPacket(Player p, Object packet) throws Exception {
        Object eplayer = ParticleEffect.getEntityPlayer(p);
        Field playerConnectionField = cachedPlayerConnectionField == null ? (cachedPlayerConnectionField = eplayer.getClass().getField("playerConnection")) : cachedPlayerConnectionField;
        Object playerConnection = playerConnectionField.get(eplayer);
        if (sendPacketMethod == null) {
            for (Method m : playerConnection.getClass().getMethods()) {
                if (!m.getName().equalsIgnoreCase("sendPacket")) continue;
                sendPacketMethod = m;
                break;
            }
        }
        if (sendPacketMethod != null) {
            sendPacketMethod.invoke(playerConnection, packet);
        }
    }

    static {
        particlePacketQueue = new CopyOnWriteArrayList();
        sendPacketNearbyQueue = new CopyOnWriteArrayList();
        NAME_MAP = new HashMap<>();
        ID_MAP = new HashMap<>();
        for (ParticleEffect effect : ParticleEffect.values()) {
            NAME_MAP.put(effect.name, effect);
            ID_MAP.put(effect.id, effect);
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(RecluseAnimationAPI.getInstance(), new Runnable(){
            double radius = Math.pow(48.0, 2.0);

            @Override
            public void run() {
                for (ParticleEffectPacket pep : particlePacketQueue) {
                    try {

                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                particlePacketQueue.clear();
            }
        }, 2L, 2L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(RecluseAnimationAPI.getInstance(), () -> {
            for (QueuedWorldEventPacket data : sendPacketNearbyQueue) {
                Location l = data.getLocation();
                PacketPlayOutWorldEvent particles = data.getPacket();
                ((CraftServer)((Object)Bukkit.getServer())).getServer().getPlayerList().sendPacketNearby(l.getBlockX(), l.getBlockY(), l.getBlockZ(), 16.0, ((CraftWorld)l.getWorld()).getHandle().dimension, particles);
            }
            sendPacketNearbyQueue.clear();
        }, 2L, 2L);
    }
}

