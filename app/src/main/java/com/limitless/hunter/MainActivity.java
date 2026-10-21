package com.limitless.hunter;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private final int bg = Color.rgb(13, 10, 24);
    private final int card = Color.rgb(31, 24, 47);
    private final int purple = Color.rgb(177, 126, 255);
    private final int white = Color.rgb(245, 240, 255);
    private final int muted = Color.rgb(177, 165, 199);

    private LinearLayout root;
    private LinearLayout content;
    private LinearLayout navigation;
    private String currentScreen = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showScreen("Home");
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(Gravity.CENTER_VERTICAL);
        t.setPadding(4, 8, 4, 8);
        return t;
    }

    private TextView heading(String value) {
        TextView t = text(value, 23, white);
        t.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        return t;
    }

    private void addCard(String title, String description) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(16, 12, 16, 12);
        box.setBackground(shape(card, 22));

        TextView a = text(title, 17, purple);
        a.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        TextView b = text(description, 14, muted);

        box.addView(a);
        box.addView(b);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
            -1, -2);
        lp.setMargins(0, 8, 0, 8);
        content.addView(box, lp);
    }

    private void addSpace(int height) {
        View v = new View(this);
        content.addView(v, new LinearLayout.LayoutParams(1, height));
    }

    private void showScreen(String screen) {
        currentScreen = screen;

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setPadding(16, 12, 16, 8);

        LinearLayout top = new LinearLayout(this);
        top.setOrientation(LinearLayout.VERTICAL);

        TextView logo = text("◈  LIMITLESS-HUNTER", 21, purple);
        logo.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        top.addView(logo);
        top.addView(text("YOUR REAL-LIFE AWAKENING", 11, muted));
        root.addView(top);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);

        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(0, 14, 0, 14);

        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
            -1, 0, 1));

        navigation = new LinearLayout(this);
        navigation.setOrientation(LinearLayout.HORIZONTAL);
        navigation.setGravity(Gravity.CENTER);
        navigation.setBackground(shape(card, 18));
        navigation.setPadding(2, 6, 2, 6);

        root.addView(navigation, new LinearLayout.LayoutParams(
            -1, -2));

        renderContent(screen);
        renderNavigation();

        setContentView(root);
    }

    private void renderContent(String screen) {
        if (screen.equals("Home")) {
            content.addView(heading("Welcome, Hunter"));
            addCard("HUNTER RANK: E", "Your journey starts here.");
            addCard("LEVEL 1  •  0 XP", "Complete quests to grow stronger.");
            addCard("DAILY QUESTS", "Study • Exercise • Drawing • Focus");
            addCard("REMINDERS", "Reminder scheduling will be added in a later mission.");
            addCard("TODAY'S OBJECTIVE", "Build consistency. Complete one task at a time.");

        } else if (screen.equals("Skills")) {
            content.addView(heading("Skill Tree"));
            addCard("STRENGTH", "Workout training and physical progress.");
            addCard("KNOWLEDGE", "Study, revision and learning.");
            addCard("DISCIPLINE", "Focus, consistency and fewer distractions.");
            addCard("CREATIVITY", "Drawing, art and creative practice.");
            addCard("VITALITY", "Sleep routine and healthy habits.");

        } else if (screen.equals("Stats")) {
            content.addView(heading("Hunter Statistics"));
            addCard("LEVEL", "1");
            addCard("TOTAL XP", "0 XP");
            addCard("QUESTS COMPLETED", "0");
            addCard("CURRENT STREAK", "0 days");
            addCard("PROGRESS HISTORY", "Your progress charts and weekly reports will be added later.");

        } else {
            content.addView(heading("System Settings"));
            addCard("REMINDERS", "Reminder controls will be added later.");
            addCard("NOTIFICATIONS", "Sound, vibration and notification permissions will be configured later.");
            addCard("APPEARANCE", "Dark hunter theme");
            addCard("DATA", "Local progress saving will be added later.");
            addCard("ABOUT", "Limitless-Hunter • Version 1.0");
        }
    }

    private void renderNavigation() {
        String[] names = {"Home", "Skills", "Stats", "Settings"};
        String[] symbols = {"⌂", "⚔", "▥", "⚙"};

        for (int i = 0; i < names.length; i++) {
            final String destination = names[i];

            LinearLayout item = new LinearLayout(this);
            item.setOrientation(LinearLayout.VERTICAL);
            item.setGravity(Gravity.CENTER);
            item.setPadding(2, 4, 2, 4);

            int selected = destination.equals(currentScreen)
                ? purple : muted;

            TextView icon = text(symbols[i], 21, selected);
            icon.setGravity(Gravity.CENTER);

            TextView label = text(names[i], 10, selected);
            label.setGravity(Gravity.CENTER);

            item.addView(icon);
            item.addView(label);
            item.setOnClickListener(v -> showScreen(destination));

            navigation.addView(item, new LinearLayout.LayoutParams(
                0, -2, 1));
        }
    }
  }
                            
