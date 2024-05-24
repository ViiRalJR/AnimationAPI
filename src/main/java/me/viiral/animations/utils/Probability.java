package me.viiral.animations.utils;

public class Probability extends AbstractProbability {
    @Override
    public boolean chance(float chance) {
        return this.random <= (double)(chance / 100.0f);
    }

    @Override
    public int getInt(int range1, int range2) {
        return (int)super.generateNumber(range1, range2);
    }

    @Override
    public double getNumber(double range1, double range2) {
        return super.generateNumber(range1, range2);
    }

    @Override
    public float getNumber(float range1, float range2) {
        return (float)super.generateNumber(range1, range2);
    }
}
