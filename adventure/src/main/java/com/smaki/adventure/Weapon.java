package com.smaki.adventure;

public class Weapon {
    private String name = "";
    private int damage = 0;
    private int criticalHitChance = 0;

    public Weapon() {}

    public Weapon(String name, int damage, int criticalHitChance) {
        this.name = name;
        this.damage = damage;
        this.criticalHitChance = criticalHitChance;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setCriticalHitChance(int criticalHitChance) {
        this.criticalHitChance = criticalHitChance;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCriticalHitChance() {
        return criticalHitChance;
    }
}


