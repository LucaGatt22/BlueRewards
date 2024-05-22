package com.example.bluerewards.backend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.bluerewards.ui.stores.Store;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "test.db";
    private final String _nameStore = StoreContract.StoreEntry.COLUMN_NAME_NAME;
    private final String _location = StoreContract.StoreEntry.COLUMN_NAME_LOCATION;
    private final String _nameUser = UserContract.UserEntry.COLUMN_NAME_NAME;
    private final String _surname = UserContract.UserEntry.COLUMN_NAME_SURNAME;
    private final String _passwordHash = UserContract.UserEntry.COLUMN_NAME_PASSWORDHASH;
    private final String _points = UserContract.UserEntry.COLUMN_NAME_POINTS;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        createTables(db);
        populateStores(db);
        populateUsers(db);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTables());
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE " + StoreContract.StoreEntry.TABLE_NAME + " (" +
                StoreContract.StoreEntry._ID + " INTEGER PRIMARY KEY, " +
                _nameStore + " varchar, " +
                _location + " varchar);\n" );
        db.execSQL(
                "CREATE TABLE "+ UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry._ID + " INTEGER PRIMARY KEY, " +
                _nameUser + " varchar, " +
                _surname + " varchar, " +
                _passwordHash + " varchar, " +
                _points + " integer);" );
    }

    private String dropTables() {
        return "DROP TABLE IF EXISTS " + StoreContract.StoreEntry.TABLE_NAME + ";"+
                "DROP TABLE IF EXISTS " + UserContract.UserEntry.TABLE_NAME + ";";
    }

    private void populateStores(SQLiteDatabase db) {
        ArrayList<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "CompMalta", "Bugibba"));
        stores.add(new Store(2, "IceIceCream", "Hamrun"));
        stores.add(new Store(3, "Bookshelf", "Poala"));
        stores.add(new Store(4, "AGStore", "Tarxien"));

        for (Store store: stores) {
            ContentValues values = new ContentValues();
            values.put(_nameStore, store.getName());
            values.put(_location, store.getLocation());
            db.insert(StoreContract.StoreEntry.TABLE_NAME, null, values);
        }
    }

    private void populateUsers(SQLiteDatabase db) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1, "Jake", "Scerri", "lfTM5/`3#Tt7", 0));
        users.add(new User(2, "Mario", "Farrugia","8%9{!'Bzlnq5", 0));
        users.add(new User(3, "Janica", "Borg", "test", 0));
        users.add(new User(4, "Alan", "Galea", "testAlan", 0));

        for (User user: users) {
            ContentValues values = new ContentValues();
            values.put(_nameUser, user.getName());
            values.put(_surname, user.getSurname());
            values.put(_passwordHash, user.getPasswordHash());
            values.put(_points, user.getPoints());
            db.insert(UserContract.UserEntry.TABLE_NAME, null, values);
        }
    }

    public SQLiteDatabase getDbInstance() {
        return this.getWritableDatabase();
    }

    public long insertStore(Store store) { // no store is inserted by user in this app
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(_nameStore, store.getName());
        values.put(_location, store.getLocation());

        long id = db.insert(StoreContract.StoreEntry.TABLE_NAME, null, values);

        return id;
    }

    public long insertUser(User user) { // no store is inserted by user in this app
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(_nameUser, user.getName());
        values.put(_surname, user.getSurname());
        values.put(_passwordHash, user.getPasswordHash());
        values.put(_points, user.getPoints());

        long id = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

        return id;
    }

    public Store getStoreById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                _nameStore,
                _location
        };

        // Filter results WHERE "id" = condition
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = _location + " ASC";

        Cursor cursor = db.query(
                StoreContract.StoreEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        Store store = null;

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(_nameStore));
            String location = cursor.getString(cursor.getColumnIndexOrThrow(_location));
            store = new Store(id, name, location);
        }
        cursor.close();

        return store;
    }

    public User getUserById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                _nameUser,
                _surname,
                _passwordHash,
                _points
        };

        // Filter results WHERE "id" = condition
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { Long.toString(id) };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = _surname + " ASC";

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        User user = null;

        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(_nameUser));
            String surname = cursor.getString(cursor.getColumnIndexOrThrow(_surname));
            String passwordHash = cursor.getString(cursor.getColumnIndexOrThrow(_passwordHash));
            int points = cursor.getColumnIndexOrThrow(_points);
            user = new User(id, name, surname, passwordHash, points);
        }
        cursor.close();

        return user;
    }

    public int getPointsById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                UserContract.UserEntry.COLUMN_NAME_POINTS
        };

        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        int points = -1; // Default value if ID not found

        if (cursor.moveToFirst()) {
            points = cursor.getInt(cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_POINTS));
        }

        cursor.close();

        return points;
    }

}
