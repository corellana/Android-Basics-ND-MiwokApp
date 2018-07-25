/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.viewpager.CategoryAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CategoryAdapter(MainActivity.this,
                getSupportFragmentManager()));

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

    }
}

//        // Set a click listener on that View
//        numbers.setOnClickListener(new View.OnClickListener() {
//            // The code in this method will be executed when the numbers View is clicked on.
//            @Override
//            public void onClick(View view) {
//                // Create a new intent to open the {@link NumbersActivity}
//                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
//                // Start the new activity
//                startActivity(numbersIntent);
//            }
//        });
//
//        /**
//         * This method is called when the family textView is clicked
//         * @param view text view family
//         */
//        TextView family = (TextView) findViewById(R.id.family);
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent familyIntent = new Intent(MainActivity.this, FamilyMembersActivity.class);
//                startActivity(familyIntent);
//            }
//        });
//
//        /**
//         * This method is called when the colors textView is clicked
//         * @param view text view colors
//         */
//        TextView colors = (TextView) findViewById(R.id.colors);
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(colorsIntent);
//            }
//        });
//
//        /**
//         * This method is called when the phrases is clicked
//         * @param view text view phrases
//         */
//        TextView phrases = (TextView) findViewById(R.id.phrases);
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
//                startActivity(phrasesIntent);
//            }
//        });

