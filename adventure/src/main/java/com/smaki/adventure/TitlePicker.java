package com.smaki.adventure;

import java.util.ArrayList;
import java.util.Collections;

public class TitlePicker {

    private ArrayList<String> titles = new ArrayList<>();

    public String randomTitle() {
        titles.add("The Meek");
        titles.add("The Three-Eyed");
        titles.add("The Monstrous");
        titles.add("The Pompous");
        titles.add("The Stuffed");
        titles.add("The Knackered");

        Collections.shuffle(titles);
        return titles.get(0);
    }
}
