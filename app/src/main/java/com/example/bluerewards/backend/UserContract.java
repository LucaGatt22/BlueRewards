package com.example.bluerewards.backend;

import android.provider.BaseColumns;

public final class UserContract {

    private UserContract() {}

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_NAME_NAME = "_name";
        public static final String COLUMN_NAME_SURNAME = "_surname";
        public static final String COLUMN_NAME_PASSWORDHASH = "_passwordHash";
        public static final String COLUMN_NAME_POINTS = "_points";
    }
}
