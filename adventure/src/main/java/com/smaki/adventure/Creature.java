package com.smaki.adventure;


public class Creature {
    protected String name = "";
    protected int maxHitPoints = 0;
    protected int hitPoints = 0;
    protected Weapon weapon;

    public Creature() {}

    public Creature(String name, int maxHitPoints, int hitPoints, Weapon weapon) {
        this.name = name;
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = hitPoints;
        this.weapon = weapon;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public Weapon getWeapon() {
        return weapon;
    }
}
