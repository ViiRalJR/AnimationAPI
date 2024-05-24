package me.viiral.animations.utils;

public abstract class AbstractProbability {
    double random = Math.random();
    abstract boolean chance(float var1);
    abstract int getInt(int var1, int var2);
    abstract double getNumber(double var1, double var2);
    abstract float getNumber(float var1, float var2);
    protected double generateNumber(double range1, double range2) {
        double base = range1 < range2 ? range1 : range2;
        return (Math.abs(range2 - range1) + 1.0) * this.random + base;
    }
}
