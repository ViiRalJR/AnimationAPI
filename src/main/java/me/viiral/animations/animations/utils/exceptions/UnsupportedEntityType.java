package me.viiral.animations.animations.utils.exceptions;

import org.bukkit.entity.Entity;

public class UnsupportedEntityType
        extends RuntimeException {
    private final Entity entity;

    public UnsupportedEntityType(Entity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "Unsupported Entity Type! " + this.entity.getType() + " is not supported for animations.";
    }
}
