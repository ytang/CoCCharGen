package com.example.cocchargen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {
    private final Random rand = new Random();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.button).setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        generateCharacter();
                    }
                }
        );
    }

    int roll(int numDices, int numFaces, int adjustment) {
        int sum = 0;
        for (int i = 0; i < numDices; ++i) {
            sum += rand.nextInt(numFaces) + 1;
        }
        sum += adjustment;
        return sum;
    }

    int lookupDamageBonus(int key) {
        if (key <= 12) {
            return -roll(1, 6, 0);
        } else if (key <= 16) {
            return -roll(1, 4, 0);
        } else if (key <= 24) {
            return 0;
        } else if (key <= 32) {
            return roll(1, 4, 0);
        } else if (key <= 40) {
            return roll(1, 6, 0);
        } else if (key <= 56) {
            return roll(2, 6, 0);
        } else if (key <= 72) {
            return roll(3, 6, 0);
        } else {
            return roll(4, 6, 0);
        }
    }

    void generateCharacter() {
        int strength = roll(3, 6, 0);
        int constitution = roll(3, 6, 0);
        int power = roll(3, 6, 0);
        int dexterity = roll(3, 6, 0);
        int appearance = roll(3, 6, 0);
        int size = roll(2, 6, 6);
        int intelligence = roll(2, 6, 6);
        int education = roll(3, 6, 3);
        int sanity = power * 5;
        int idea = intelligence * 5;
        int luck = power * 5;
        int know = education * 5;
        int damageBonus = lookupDamageBonus(strength + size);
        int hp = (constitution + size + 1) / 2;
        int mp = power;
        int income = roll(1, 10, 0) - 1;
        String[] income1890s = {"500", "1000", "1500", "2000", "2500", "3000", "4000", "5000", "5000", "10,000"};
        String[] income1920s = {"1500", "2500", "3500", "3500", "4500", "5500", "6500", "7500", "10,000", "20,000"};
        String[] incomePresent = {"15,000", "25,000", "35,000", "45,000", "55,000", "75,000", "100,000", "200,000", "300,000", "500,000"};
        int occupation = education * 20;
        int interest = intelligence * 10;

        ArrayList<String> characteristics = new ArrayList<String>();
        characteristics.add("STR  " + strength);
        characteristics.add("CON  " + constitution);
        characteristics.add("SIZ  " + size);
        characteristics.add("DEX  " + dexterity);
        characteristics.add("APP  " + appearance);
        characteristics.add("SAN  " + sanity);
        characteristics.add("INT  " + intelligence);
        characteristics.add("POW  " + power);
        characteristics.add("EDU  " + education);
        characteristics.add("Idea " + idea);
        characteristics.add("Luck " + luck);
        characteristics.add("Know " + know);
        characteristics.add("Damage Bonus      " + damageBonus);
        characteristics.add("Magic Points      " + mp);
        characteristics.add("Hit Points        " + hp);
        characteristics.add("Occupation Skills " + occupation);
        characteristics.add("Interest Skills   " + interest);
        characteristics.add("Income (1890s)    " + income1890s[income]);
        characteristics.add("Income (1920s)    " + income1920s[income]);
        characteristics.add("Income (present)  " + incomePresent[income]);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.characteristic, characteristics);
        ((ListView) findViewById(R.id.listView)).setAdapter(adapter);
    }
}
