package com.example.bluerewards.backend;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.bluerewards.ui.stores.Store;

import java.util.ArrayList;

public class StoreUtil {
    private SQLiteDatabase db;
    private final String _name = StoreContract.StoreEntry.COLUMN_NAME_NAME;
    private final String _location = StoreContract.StoreEntry.COLUMN_NAME_LOCATION;

    public StoreUtil(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Store> getStores() {
        ArrayList<Store> stores = new ArrayList<>();

        String[] projection = {
                BaseColumns._ID,
                _name,
                _location
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder = _name + " ASC";

        Cursor cursor = db.query(
                StoreContract.StoreEntry.TABLE_NAME,   // The table to query
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
            String location = cursor.getString(cursor.getColumnIndexOrThrow(_location));
            Store store = new Store(id, name, location);
            stores.add(store);
        }
        cursor.close();

        return stores;
    }
}
