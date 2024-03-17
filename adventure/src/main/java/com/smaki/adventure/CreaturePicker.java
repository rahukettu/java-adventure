package com.smaki.adventure;

import java.util.ArrayList;
import java.util.Collections;

public class CreaturePicker {

    private ArrayList<Creature> creatures = new ArrayList<>();

    public Creature randomCreature() {
        creatures.add(new Creature("Imp", 12, 12, new Weapon("poison dagger", 3,50)));
        creatures.add(new Creature("Ogre", 25, 25, new Weapon("hammer", 5, 10)));
        creatures.add(new Creature("Goblin", 15, 15, new Weapon("club", 4, 10)));
    
        Collections.shuffle(creatures);
        return creatures.get(0);
    }
}
