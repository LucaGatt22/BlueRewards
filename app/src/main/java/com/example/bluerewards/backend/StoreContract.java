package com.example.bluerewards.backend;

import android.provider.BaseColumns;


// The StoreContract class defines the names of the database table Stores and its fields/columns.
public final class StoreContract {

    private StoreContract() {}

    /* Inner class that defines the table contents */
    public static class StoreEntry implements BaseColumns {
        public static final String TABLE_NAME = "Stores";
        public static final String COLUMN_NAME_NAME = "_name";
        public static final String COLUMN_NAME_LOCATION = "_location";
    }
}
