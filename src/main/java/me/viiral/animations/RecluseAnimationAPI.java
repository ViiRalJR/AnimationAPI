package me.viiral.animations;

import org.bukkit.plugin.java.JavaPlugin;

public final class RecluseAnimationAPI extends JavaPlugin {

    public static RecluseAnimationAPI instance;
    public static RecluseAnimationAPI getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
    }
}
