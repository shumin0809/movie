package com.shumin.movie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shumin.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shumin on 4/3/16.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movie.db";

    // Contacts table name
    private static final String TABLE_OBJECT = "movies";

    // common Columns name
    private static final String KEY_ID = "id";

    // object Table Columns names
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_MOVIE_ID = "movie_id";
    private static final String COLUMN_POSTER = "poster";

    // object table create statement
    private static final String CREATE_OBJECT_TABLE = "CREATE TABLE "
            + TABLE_OBJECT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE
            + " TEXT," + COLUMN_YEAR + " TEXT," + COLUMN_MOVIE_ID + " TEXT," + COLUMN_POSTER + " TEXT" +  ")";


    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_OBJECT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OBJECT);
        // Create tables again
        onCreate(db);
    }

    public void open() throws SQLException {
        this.getWritableDatabase();
    }

    public void close() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    public void addMovie(Movie info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, info.getTitle());
        values.put(COLUMN_YEAR, info.getReleaseYear());
        values.put(COLUMN_MOVIE_ID, info.getMovieId());
        values.put(COLUMN_POSTER, info.getPosterUrl());

        // Inserting Row
        db.insert(TABLE_OBJECT, null, values);
    }

    // Getting All Contacts
    public List<Movie> getAllMovies() {
        List<Movie> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_OBJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Movie contact = new Movie();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                contact.setYear(cursor.getString(2));
                contact.setImdbID(cursor.getString(3));
                contact.setPoster(cursor.getString(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteMovie(Movie contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_OBJECT, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }

    // Getting contacts Count
    public int getMoviesSize() {
        String countQuery = "SELECT  * FROM " + TABLE_OBJECT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}
