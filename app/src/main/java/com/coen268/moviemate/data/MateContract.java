package com.coen268.moviemate.data;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

/**
 * API Contract for the MovieMate app.
 */
public final class MateContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private MateContract() {}

    /**
     * The "Content authority" is a name for the entire content provider, similar to the
     * relationship between a domain name and its website.  A convenient string to use for the
     * content authority is the package name for the app, which is guaranteed to be unique on the
     * device.
     */
    public static final String CONTENT_AUTHORITY = "com.coen.android.moviemate";

    /**
     * Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
     * the content provider.
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * Possible path (appended to base content URI for possible URI's)
     * For instance, content://com.example.android.pets/pets/ is a valid path for
     * looking at pet data. content://com.example.android.pets/staff/ will fail,
     * as the ContentProvider hasn't been given any information on what to do with "staff".
     */
    public static final String PATH_PETS = "pets";

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class MateEntry implements BaseColumns {

        /** The content URI to access the pet data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of user.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single user.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        /** Name of database table for movie */
        public final static String TABLE_NAME_MATE = "mate";
        public final static String TABLE_NAME_MATE_MOVIE = "mate_movie";


        /**
         * Colunm for mate table
         */
        public final static String MATE_ID = BaseColumns._ID;
        public final static String COLUMN_MATE_NAME ="name";
        public final static String COLUMN_MATE_EMAIL = "email";
        public final static String COLUMN_MATE_PASSWORD = "password";

        /**
         * Colunm for mate_movie table
         */
        public final static String MATE_MOVIE_ID = BaseColumns._ID;
        public final static String COLUMN_MOVIE_NAME ="name";
        public final static String COLUMN_MOVIE_ID = "email";

    }

}
