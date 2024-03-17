package com.smaki.adventure;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdventureController {  

    //Initializing game
    boolean gameOn = false;
    //Initializing objects
    CreaturePicker cPicker = new CreaturePicker();
    Player player = new Player();
    Weapon playerWeapon = new Weapon();

    //Getting randomized creature object from class
    Creature creature = cPicker.randomCreature();
    Weapon creatureWeapon = creature.getWeapon();


    //Mapping to root GET request and showing game info sheet
    @GetMapping("/")
    public String showInfo() {
        return "To play the game: \n" +
        " Requests:\nGET\t/start\tStart game\n" +
        "POST\t/name\t(name=<string>)\tEnter character name\n" +
        "POST\t/weapon\t(weapon=<string>)\tSelect weapon\n" +
        "GET\t/play\tSee what happens next\n";
    }

    //Mapping to game start GET request and starting game
    @GetMapping("/start")
    public String startGame() {
        gameOn = true;
        return "Hello, adventurer! What is your name?";
    }
    
    //Mapping to name input POST request
    @PostMapping("/name")
    public String characterCreation(@RequestParam(required = false) String name) { //handling null param
        TitlePicker tpicker = new TitlePicker(); //randomized title from class

        if(name == null || name.isEmpty()) {
            player.setName("Anonymous");
        } else {
            player.setName(name);
        }
        player.setTitle(tpicker.randomTitle());
            return "Nice to meet you, " + player.getName() + " " + player.getTitle() + "!\n" +
            "Now choose your weapon:\nDagger\nAxe\nSword\nBow\nor Wand?\n"; 
    }

    //Mapping to weapon input POST request
    @PostMapping("/weapon")
    public String weaponAssign(@RequestParam(required = false) String weapon) {

        if (weapon == null || weapon.isEmpty()) {
            weapon="wooden stick";
            playerWeapon.setName(weapon);
        } else {
            playerWeapon.setName(weapon);
            player.setWeapon(playerWeapon);
        }
    //handling weapon choices and setting values        
            switch (weapon.toLowerCase()) {
                case "dagger":
                    player.setMaxHitPoints(15);
                    player.setHitPoints(15);
                    playerWeapon.setDamage(2);
                    playerWeapon.setCriticalHitChance(50);
                    break;
                case "axe":
                    player.setMaxHitPoints(20);
                    player.setHitPoints(20);
                    playerWeapon.setDamage(5);
                    playerWeapon.setCriticalHitChance(10);
                    break;
                case "sword":
                    player.setMaxHitPoints(18);
                    player.setHitPoints(18);
                    playerWeapon.setDamage(4);
                    playerWeapon.setCriticalHitChance(25);
                    break;
                case "bow":
                    player.setMaxHitPoints(13);
                    player.setHitPoints(13);
                    playerWeapon.setDamage(2);
                    playerWeapon.setCriticalHitChance(65);
                    break;
                case "wand":
                    player.setMaxHitPoints(10);    
                    player.setHitPoints(10);
                    playerWeapon.setDamage(8);
                    playerWeapon.setCriticalHitChance(35);
                    break;
                case "wooden stick":
                    player.setMaxHitPoints(35);    
                    player.setHitPoints(35);
                    playerWeapon.setDamage(15);
                    playerWeapon.setCriticalHitChance(0);
                    break;
                default:
                    break;
            }

        return "Your weapon of choice is " +playerWeapon.getName().toLowerCase() +
         ".\nYou see an angry " + creature.getName() + " running towards you wielding a  " 
        + creatureWeapon.getName() + ". You ready your weapon...";
    }

//Mapping to gameplay GET request
    @GetMapping("/play")
    public String play() {
        int playerHealth = 0; //initizing values
        int creatureHealth = 0;
        int playerCriticalHit = 0;
        int creatureCriticalHit = 0;
        boolean playerCrit = false;
        boolean creatureCrit = false;
        int rand = 0;
        Random r = new Random();

        //randomizing crit hit change
        rand = r.nextInt(99);
        if(rand <= playerWeapon.getCriticalHitChance() -1) {
            playerCriticalHit = playerWeapon.getDamage();
            playerCrit = true;
        }
        rand = r.nextInt(99);
        if(rand <= creatureWeapon.getCriticalHitChance() -1) {
            creatureCriticalHit = creatureWeapon.getDamage();
            creatureCrit = true;
        }

        //Game logic
        if(player.getHitPoints() > 0 && creature.getHitPoints() > 0) {
            creature.setHitPoints(creature.getHitPoints() - playerWeapon.getDamage()+playerCriticalHit);
            player.setHitPoints(player.getHitPoints() - creatureWeapon.getDamage()+creatureCriticalHit); 
            playerHealth = (int)(player.getHitPoints() * 100) / player.getMaxHitPoints();
            creatureHealth = (int)(creature.getHitPoints() * 100) / creature.getMaxHitPoints();

            if(player.getHitPoints() <= 0) {
                gameOn = false;
                return "You die...";
            } else if (creature.getHitPoints() <= 0) {
                return "You kill the " + creature.getName() + "! Congrats.";
            } else if  (playerHealth > 50) {
                if(playerCrit) {
                    return "Critical hit! The " +creature.getName()+ " stumbles under your blow...";
                } else if (creatureCrit) {
                    return "The " + creature.getName() + " hits you extra hard! You stumble a bit...";
                } else if (playerCrit && creatureCrit) {
                    return "You both hit each other like champs!";
                } else {
                    return "You attack the "+creature.getName()+ " with your "+playerWeapon.getName()+ 
                    " and " +creature.getName()+ " takes a swing at you!";
                }
            } else if (playerHealth <= 50) {
                if(playerCrit) {
                    return "Critical hit! The " +creature.getName()+ " stumbles under your blow..." +
                    "However you also start to feel like you need a break.";
                } else if (creatureCrit) {
                    return "The " + creature.getName() + " hits you extra hard! You're seeing stars...";
                } else if (playerCrit && creatureCrit) {
                    return "You both hit each other like champs! You're starting to look quite ruffled.";
                } else {
                    return "You attack the "+creature.getName()+ " with your "+playerWeapon.getName()+ 
                    " and " +creature.getName()+ " takes a swing at you! You are looking exhausted...";
                }
            } else if (creatureHealth <= 50) {
                return "The " + creature.getName()+ "gasps its breath..." + 
                "You step aside and prepare yourself to a new attack.";
            }
        }

        return "Game over.";

    }
}
