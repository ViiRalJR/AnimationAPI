package me.viiral.animations.utils;

import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.lang.reflect.Method;
import java.util.function.Supplier;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class Teleport {
    private static final Method[] methods = ((Supplier<Method[]>)() -> {
        try {
            Method getHandle = Class.forName(Bukkit.getServer().getClass().getPackage().getName() + ".entity.CraftEntity").getDeclaredMethod("getHandle", new Class[0]);
            return new Method[]{getHandle, getHandle.getReturnType().getDeclaredMethod("setPositionRotation", Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE)};
        }
        catch (Exception ex) {
            return null;
        }
    }).get();

    public static void tp(LivingEntity entity, Location location) {
        try {
            methods[1].invoke(methods[0].invoke(entity, new Object[0]), location.getX(), location.getY(), location.getZ(), Float.valueOf(location.getYaw()), Float.valueOf(location.getPitch()));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void tp(LivingEntity entity, double x, double y, double z, float yaw, float pitch) {
        try {
            methods[1].invoke(methods[0].invoke(entity, new Object[0]), x, y, z, Float.valueOf(yaw), Float.valueOf(pitch));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void tp(LivingEntity entity, double x, double y, double z) {
        try {
            methods[1].invoke(methods[0].invoke(entity, new Object[0]), x, y, z, 0, 0);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

