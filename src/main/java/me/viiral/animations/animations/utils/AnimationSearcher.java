package me.viiral.animations.animations.utils;

import me.viiral.animations.utils.Probability;
import org.bukkit.Location;

import java.util.function.Predicate;

public class AnimationSearcher {

    private final int RECURSION_MAXIMUM_ATTEMPTS = 3;

    private Location location;
    private SearchType searchType;
    private int checkHeight = 5;
    private int radius = 12;
    private Predicate<Location> conditions;

    private int recursionAttempts = 0;

    public AnimationSearcher(Location location) {
        this.location = location;
    }

    public AnimationSearcher setSearchType(SearchType searchType) {
        this.searchType = searchType;
        return this;
    }

    public AnimationSearcher setLocation(Location location) {
        this.location = location;
        return this;
    }

    public AnimationSearcher setCheckHeight(int height) {
        this.checkHeight = height;
        return this;
    }

    public AnimationSearcher setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public AnimationSearcher setCondition(Predicate<Location> conditions) {
        this.conditions = conditions;
        return this;
    }

    public Location searchLocation() {
        final Location searched = location.clone().add(0, checkHeight, 0).add(new Probability().getInt(-radius, radius), 0, new Probability().getInt(-radius, radius));
        for (int y = checkHeight; y >= -checkHeight; y--) {
            searched.add(0, -1, 0);
            if (searchType.validLocation(searched)) {
                if (conditions != null && !conditions.test(searched)) continue;
                recursionAttempts = 0;
                return searched;
            }
        }
        if (recursionAttempts >= RECURSION_MAXIMUM_ATTEMPTS) {
            recursionAttempts = 0;
            return null;
        }
        recursionAttempts++;
        return searchLocation();
    }

}
