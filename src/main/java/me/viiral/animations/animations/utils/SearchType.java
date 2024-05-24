package me.viiral.animations.animations.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public enum SearchType {

    EXPOSED_SURFACE {
        @Override
        public boolean validLocation(Location location) {
            Block block = location.getBlock();
            return !SearchType.isAir(block.getType()) &&
                    (SearchType.isAir(block.getRelative(BlockFace.UP).getType()) ||
                            SearchType.isAir(block.getRelative(BlockFace.SOUTH).getType()) ||
                            SearchType.isAir(block.getRelative(BlockFace.EAST).getType()) ||
                            SearchType.isAir(block.getRelative(BlockFace.NORTH).getType()) ||
                            SearchType.isAir(block.getRelative(BlockFace.WEST).getType()) ||
                            SearchType.isAir(block.getRelative(BlockFace.DOWN).getType()));
        }
    },
    EXPOSED_TOP {
        @Override
        public boolean validLocation(Location location) {
            Block block = location.getBlock();
            return !SearchType.isAir(block.getType()) && SearchType.isAir(block.getRelative(BlockFace.UP).getType());
        }
    };

    private static boolean isAir(Material material) {
        return material == null || material.equals(Material.AIR);
    }

    public abstract boolean validLocation(Location location);
}
