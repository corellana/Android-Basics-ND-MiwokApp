package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word.
 */

public class Word {

    /*
     * STATE
     * m indicate they are variables of the class
     */

    /**
     * Default translation for the word
     */
    private String mDefaultTranslation;

    /**
     * Miwok translation for the word
     */
    private String mMiwokTranslation;

    /**
     * Image Resource ID for the word
     */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;

    /**
     * Image Player with the audio for the word
     */
    private int mImagePlayer;

    /**
     * Audio for ech word.
     */
    private int mAudioResourceId;

    /**
     * CONSTRUCTOR
     * The name has to match the class name and it has no return type.
     */

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user
     * @param miwokTranslation   is the word in the Miwok language
     * @param imagePlayer        is the drawable for the audio player.
     * @param audioResourceId    is the audio file for each word.
     *
     */
    public Word(String defaultTranslation, String miwokTranslation, int imagePlayer, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.mImagePlayer = imagePlayer;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user
     * @param miwokTranslation   is the word in the Miwok language
     * @param imageResourceId   is the drawable resource ID for the image asset.
     * @param imagePlayer        is the drawable for the audio player.
     */
    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int imagePlayer, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mImagePlayer = imagePlayer;
        mAudioResourceId = audioResourceId;
    }
    /**
     * METHODS
     */

    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the image of the word.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Get the image for the player of the word.
     */
    public int getImagePlayer() {return mImagePlayer; }

    /**
     * Get the audio for each word.
     */
    public int getAudioResourceId() {return mAudioResourceId; }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}

