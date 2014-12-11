package be.ehb.adrienschautteet.hackthefuture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by adrienschautteet on 11/12/14.
 */
public class DataFacade {

    public MyHelper helper;

    public DataFacade(Context context) {
        helper = new MyHelper(context);
    }

    public long insertCity (long cityID, String name, int zipcode, String province, String alertCode, String kind) {
        SQLiteDatabase database = helper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.CITY_CITY_ID, cityID);
        contentValues.put(helper.CITY_NAME, name);
        contentValues.put(helper.CITY_ZIPCODE, zipcode);
        contentValues.put(helper.CITY_PROVINCE, province);
        contentValues.put(helper.CITY_ALERTCODE, alertCode);
        contentValues.put(helper.CITY_KIND, kind);
        long id = database.insert(helper.TABLE_NAME_CITIES, null, contentValues);
        database.close();
        return id;
    }

    public ArrayList<CityFromDb> getCities() {
        ArrayList<CityFromDb> artists = new ArrayList<CityFromDb>();
        SQLiteDatabase database = helper.getReadableDatabase();

        String [] columns = {helper.CITY_ID, helper.CITY_CITY_ID, helper.CITY_NAME, helper.CITY_ZIPCODE, helper.CITY_PROVINCE, helper.CITY_ALERTCODE, helper.CITY_KIND};
        Cursor cursor = database.query(helper.TABLE_NAME_CITIES, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int index = cursor.getInt(0) - 1;
            int id = cursor.getInt(0);
            long cityId = cursor.getLong(1);
            String name = cursor.getString(2);
            int zipcode = cursor.getInt(3);
            String province = cursor.getString(4);
            String alertCode = cursor.getString(5);
            String kind = cursor.getString(6);


            CityFromDb city = new CityFromDb(id, cityId, name, zipcode, province, alertCode, kind);
            artists.add(index, city);
        }
        return artists;
    }

    static class MyHelper extends SQLiteOpenHelper {
        /**
         * SQLiteOpenHelper takes care of:
         * 1. Opening the database
         * 2. Creating the database if it doesn't exist
         * 3. Upgrading it if necessary
         */

        private static final String DB_NAME = "CityDatabase";

        private static final String TABLE_NAME_CITIES = "CITIES";
        private static final String CITY_ID = "_id";
        private static final String CITY_CITY_ID = "CityId";
        private static final String CITY_NAME = "Name";
        private static final String CITY_ZIPCODE = "Zipcode";
        private static final String CITY_PROVINCE = "Province";
        private static final String CITY_ALERTCODE = "Alertcode";
        private static final String CITY_KIND = "Kind";

        private static final int DB_VERSION = 1;

        private static final String CREATE_TABLE_CITIES = "CREATE TABLE " + TABLE_NAME_CITIES
                + " (" + CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CITY_CITY_ID + " VARCHAR(255), "
                + CITY_NAME + " VARCHAR(255), "
                + CITY_ZIPCODE + " VARCHAR(255), "
                + CITY_PROVINCE + " VARCHAR(255), "
                + CITY_ALERTCODE + " VARCHAR(255), "
                + CITY_KIND + " VARCHAR(255));";

        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_CITIES;

        private Context context;


        public MyHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            this.context = context;
            Log.e(getClass().getName(), CREATE_TABLE_CITIES);
        }

        /**
         * OnCreate is called when the database is created for the first time.
         * Creation of tables and initial data should be put here
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_CITIES);


            } catch (SQLException e) {
                e.printStackTrace();
                Log.e(getClass().getName(), e.getMessage());
            }
        }

        /**
         * Called when the database needs to be upgraded.
         * Dropping tables, adding tables, ... should be put here
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context, "onUpgrade Called", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
