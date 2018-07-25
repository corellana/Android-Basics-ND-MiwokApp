package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyMembersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;

    /**
     * (1) Handles audio focus when playing a sound file
     */
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

    public FamilyMembersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //  (1) Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity() .getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        /*
         * The input into this list is a new Word object which is created in line by using
         * the "new" keyword.
         */
        words.add(new Word("father", "әpә", R.drawable.family_father, R.drawable.ic_play_arrow, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.drawable.ic_play_arrow, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.drawable.ic_play_arrow, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.drawable.ic_play_arrow, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.drawable.ic_play_arrow, R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.drawable.ic_play_arrow, R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.drawable.ic_play_arrow, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.drawable.ic_play_arrow, R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.drawable.ic_play_arrow, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.drawable.ic_play_arrow, R.raw.family_grandfather));

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
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_family);

        // We are looking in the view hierarchy for a view with the ID name "list" and the findViewById
        // method returns a view and then we cast it into a list view data type.

        // The casting will work if we found a textview or a imageview. In this case the casting works
        // because we actually did find a list view in the layout.


        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

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
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioResourceId());

                    // Start the audio file
                    mMediaPlayer.start();

                    // Setup a listener on the media player, so that we can stop and release the
                    // media player once the sound has finished playing.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
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
}
