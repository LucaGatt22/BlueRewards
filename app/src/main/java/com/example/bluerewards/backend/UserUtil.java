package com.example.bluerewards.backend;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

public class UserUtil {
    private SQLiteDatabase db;
    private final String _name = UserContract.UserEntry.COLUMN_NAME_NAME;
    private final String _surname = UserContract.UserEntry.COLUMN_NAME_SURNAME;
    private final String _passwordHash = UserContract.UserEntry.COLUMN_NAME_PASSWORDHASH;
    private final String _points = UserContract.UserEntry.COLUMN_NAME_POINTS;
    public UserUtil(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        String[] projection = {
                BaseColumns._ID,
                _name,
                _surname,
                _passwordHash,
                _points
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = _surname + " ASC";

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        while(cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(_name));
            String surname = cursor.getString(cursor.getColumnIndexOrThrow(_surname));
            String passwordHash = cursor.getString(cursor.getColumnIndexOrThrow(_passwordHash));
            int points = cursor.getInt(cursor.getColumnIndexOrThrow(_points));
            User user = new User(id, name, surname, passwordHash, points);
            users.add(user);
        }
        cursor.close();

        return users;
    }
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }
}
