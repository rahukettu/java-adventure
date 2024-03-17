package com.smaki.adventure;

public class Player extends Creature {

    private String title = "";

    public Player() {}

    public Player(String name, int maxHitPoints, int hitPoints, String title, Weapon weapon) {
        this.name = name;
        this.maxHitPoints = maxHitPoints;
        this.hitPoints = hitPoints;
        this.title = title;
        this.weapon = weapon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
