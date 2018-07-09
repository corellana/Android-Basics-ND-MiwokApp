package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();

        /*
         * The input into this list is a new Word object which is created in line by using
         * the "new" keyword.
         */
        words.add(new Word("one", "lutti", R.drawable.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten));

        // rootView is our parent view.
//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        // First we create an array adapter object using the "new" keyword and the constructor with
        // three inputs.

        // We are storing it in a new variable, the recalling itemsAdapter. Where the ArrayAdapter
        // is its data type.

        // First Parameter: The reason we need a context is because we are creating views. Since the numberActivity is
        // a context, we specify the word "this" to refer the numberActivity.

        // Second Parameter is the list item layout file. Normally, we refer to the layout files in
        // the form of R.Layout for layouts we've defined, followed by the name of the layout file.
        // In this case, we specify android.R.layout because the Android framework predefined an XML
        // layout called, simple_list_item_1.

        // Third Parameter: The last printer is a list of objects which will be the data source for
        // the array adapters (words).

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
//        ArrayAdapter<Word> itemsAdapter = new ArrayAdapter<Word>(this, R.layout.list_item, words);
        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);

        // We are looking in the view hierarchy for a view with the ID name "list" and the findViewById
        // method returns a view and then we cast it into a list view data type.

        // The casting will work if we found a textview or a imageview. In this case the casting works
        // because we actually did find a list view in the layout.


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // And we can call methods on it and interact with it while the app is running.

        // We are calling the setAdapter method on the ListView object we just defined in the previous line.
        // And then, we're associating the array adapter with a list view.
        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        //LOOP WITH WHILE LOOP
        // Create a variable to keep track of the current index position
//        int count = 0;
//
//        while (words.size() > count){
//
//            // Create a new TextView
//            TextView wordView = new TextView(this);
//
//            // Set the text to be word at the current index
//            wordView.setText(words.get(count));
//
//            // Add this TextView as another child to the root view of this layout
//            rootView.addView(wordView);
//
//            System.out.println(words.get(count));
//
//            // Increment the index variable by 1
//            count++;
//        }

        // LOOP WITH FOR LOOP

        //  for loop which created a new TextView and added it to the layout for each word in the list.
//        for (int count = 0; count < words.size(); count++) {
//
//            // Create a new TextView
//            TextView wordView = new TextView(this);
//
//            // Set the text to be word at the current index
//            wordView.setText(words.get(count));
//
//            // Add this TextView as another child to the root view of this layout
//            rootView.addView(wordView);
//
//            Log.v("NumbersActivity", "Index:" + count + " Value:" + words.get(count));
//        }


        //First approach
//
//        // rootView is our parent view.
//        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);
//        // To Create a TextView object, that is store in a variable called wordView (child view), whose data type is a TextView
//        TextView wordView = new TextView(this);
//        // To change the text that is displayed on screen, we call the setText method on the wordView object.
//        // And we pass in one input which is the desired text.
//        wordView.setText(words.get(0));
//        // We are going to add the view as a child to the parent view called rootView, using the addView method to the parent view, rootView.
//        rootView.addView(wordView);
//
//        // Another TextView called wordView2
//        TextView wordView2 = new TextView(this);
//        wordView2.setText(words.get(1));
//        rootView.addView(wordView2);
//
//        // Another TextView called wordView3
//        TextView wordView3 = new TextView(this);
//        wordView3.setText(words.get(2));
//        rootView.addView(wordView3);
    }
}

