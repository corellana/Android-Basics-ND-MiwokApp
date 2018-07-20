package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    /** (1) Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    /**
     * (2) This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
               mMediaPlayer.start();
               } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
           }
        }
    };

    /**
     * * This listener gets triggered when the {@link MediaPlayer} has complete playing the audio file.
     */
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //  (1) Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        /*
         * The input into this list is a new Word object which is created in line by using
         * the "new" keyword.
         */
        words.add(new Word("one", "lutti", R.drawable.number_one, R.drawable.ic_play_arrow, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.drawable.ic_play_arrow, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.drawable.ic_play_arrow, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.drawable.ic_play_arrow, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.drawable.ic_play_arrow, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.drawable.ic_play_arrow, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.drawable.ic_play_arrow, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.drawable.ic_play_arrow, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.drawable.ic_play_arrow, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.drawable.ic_play_arrow, R.raw.number_ten));

        // rootView is our parent view.
        //LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

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

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Release the media player if it currently exists because we are about to
                // play a different sound file
                releaseMediaPlayer();
                // Get the {@link Word} object at the given position the user clicked on
                Word word = words.get(position);

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                //mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());
                //REEMPLAZADO por requestAudioFocus ABAJO.

                // (1) Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // Start the audio file
                //mMediaPlayer.start();
                //REEMPLAZADO
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                // We have audio focus now.

                // Setup a listener on the media player, so that we can stop and release the
                // media player once the sound has finished playing.
                //mMediaPlayer.setOnCompletionListener(mCompletionListener);
                //REEMPLAZADO por lo de abajo...

                // Create and setup the {@link MediaPlayer} for the audio resource associate
                // with the current word
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioResourceId());

                // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop(){
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
    // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
    // Regardless of the current state of the media player, release its resources
    // because we no longer need it.
     mMediaPlayer.release();

    // Set the media player back to null. For our code, we've decided that
    // setting the media player to null is an easy way to tell that the media player
    // is not configured to play an audio file at the moment.
     mMediaPlayer = null;

     // (4) Regardless of whether or not we were granted audio focus, abandon it. This also
     // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }







    // ...


    // ...

}

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

