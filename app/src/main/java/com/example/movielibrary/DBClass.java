package com.example.movielibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class DBClass extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;

    public DBClass(Context context) {
        super(context, Movies.TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Movies.CREATE_TABLE);
    }

    public  Movies addMovie (Movies e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Movies.COLUMN_ID, e.getMovieID());
        values.put(Movies.COLUMN_NAME, e.getMovieName());
        values.put(Movies.COLUMN_DESCRIPTION, e.getDescription());
        values.put(Movies.COLUMN_RATING, e.getMovieRating());
        values.put(Movies.COLUMN_STATUS, e.isActive ? 1 : 0);

        db.insert(Movies.TABLE_NAME, null, values);

        return e;
    }

//    public void deleteEmployee(int movieId) {
//        SQLiteDatabase db = getReadableDatabase();
//        db.delete(Movies.TABLE_NAME, Movies.COLUMN_ID + "=?", new String[]{String.valueOf(movieId)});
//    }


    public void updateMovie(int movieId, String movieName, String movieDescription, int movieRating, boolean movieStatus) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Movies.COLUMN_ID, movieId);
        cv.put(Movies.COLUMN_NAME, movieName);
        cv.put(Movies.COLUMN_DESCRIPTION, movieDescription);
        cv.put(Movies.COLUMN_RATING, movieRating);
        cv.put(Movies.COLUMN_STATUS, movieStatus ? 1 : 0);

        db.update(Movies.TABLE_NAME, cv, Movies.COLUMN_ID + "=?", new String[]{String.valueOf(movieId)});

    }


//    public  Movies getMovie (int movieId) {
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor c = db.query(Movies.TABLE_NAME,
//                new String[]{Movies.COLUMN_ID, Movies.COLUMN_NAME,
//                        Movies.COLUMN_DESCRIPTION, Movies.COLUMN_RATING},
//                Movies.COLUMN_STATUS + "=?",
//                new String[]{String.valueOf(movieId)}, null, null, null);
//
//        if (c != null)
//            c.moveToFirst();
//
//        Movies foundMovie = new Movies ();
//        foundMovie.movieID = c.getInt(c.getColumnIndex(Movies.COLUMN_ID));
//        foundMovie.movieName = c.getString(c.getColumnIndex(Movies.COLUMN_NAME));
//        foundMovie.description = c.getString(c.getColumnIndex(Movies.COLUMN_DESCRIPTION));
//        foundMovie.movieRating = c.getInt(c.getColumnIndex(Movies.COLUMN_RATING));
//        foundMovie.isActive = c.getInt(c.getColumnIndex(Movies.COLUMN_STATUS)) !=0 ;
//
//        return foundMovie;
//    }

    public List<Movies> getMovies(boolean status){
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "select * from " + Movies.TABLE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);
        List<Movies> movieList = new ArrayList<>();

        if(c.moveToFirst()) {

            do {
                Movies foundMovie = new Movies ();
                foundMovie.movieID = c.getInt(c.getColumnIndex(Movies.COLUMN_ID));
                foundMovie.movieName = c.getString(c.getColumnIndex(Movies.COLUMN_NAME));
                foundMovie.description = c.getString(c.getColumnIndex(Movies.COLUMN_DESCRIPTION));
                foundMovie.movieRating = c.getInt(c.getColumnIndex(Movies.COLUMN_RATING));
                foundMovie.isActive = c.getInt(c.getColumnIndex(Movies.COLUMN_STATUS)) == 1 ? true : false;

                if (status) {
                    if (foundMovie.isActive) {
                        movieList.add(foundMovie);
                    }
                } else {
                    movieList.add(foundMovie);
                }

            } while (c.moveToNext());
        }

        db.close();

        return movieList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
