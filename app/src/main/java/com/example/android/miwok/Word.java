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

    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Image Resource ID for the word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;


    /**
     * CONSTRUCTOR
     * The name has to match the class name and it has no return type.
     */

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user
     * @param miwokTranslation is the word in the Miwok language
     */
    public Word(String defaultTranslation, String miwokTranslation) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the word in a language that the user
     * @param miwokTranslation is the word in the Miwok language
     * @param mImageResourceId is the drawable resource ID for the image asset.
     */
    public Word(String defaultTranslation, String miwokTranslation, int mImageResourceId) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        this.mImageResourceId = mImageResourceId;

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
         public int getImageResourceId(){
             return mImageResourceId;
         }

    /**
     * Returns whether or not there is an image for this word.
     */
    public boolean hasImage(){
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}