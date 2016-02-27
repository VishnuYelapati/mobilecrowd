package android.com.mobilecrowdlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by srinu on 2/25/2016.
 */
public class DataBaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "MobileCrowdLocDataBase.db";

    // Contacts table name
    public static final String TABLE_CROWDLOCATION = "CrowdLocation";
    public static final String CROWDLOC_ID = "crowdloc_id";
    public static final String CROWDLOC_NAME = "crowdloc_Name";
    public static final String CROWDLOC_ADDRESS = "crowdloc_address";
    public static final String CROWDLOC_LATITUDE = "crowdloc_latitude";
    public static final String CROWDLOC_LONGITUDE= "crowdloc_longitude";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String MAIN_LOCATION_TABLE = " CREATE TABLE " +TABLE_CROWDLOCATION
                + " (" + CROWDLOC_ID +" INTEGER PRIMARY KEY ,"
                + CROWDLOC_NAME + " TEXT,"
                + CROWDLOC_ADDRESS + " TEXT,"
                + CROWDLOC_LATITUDE + " TEXT ,"
                + CROWDLOC_LONGITUDE + " TEXT"

                + ");";
        db.execSQL(MAIN_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CROWDLOCATION);

        // Create tables again
        onCreate(db);
    }

    void addLocationInfo(LocationData locationdata) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CROWDLOC_ID,locationdata.crowdloc_id);
        values.put(CROWDLOC_NAME, locationdata.crowdloc_name); // Contact Name
        values.put(CROWDLOC_ADDRESS, locationdata.crowdloc_address); // Contact Phone
        values.put(CROWDLOC_LATITUDE, locationdata.crowdloc_latitude); //Contact phone no
        values.put(CROWDLOC_LONGITUDE, locationdata.crowdloc_longitude); //Contact phone no


        // Inserting Row
        db.insert(TABLE_CROWDLOCATION, null, values);
        db.close(); // Closing database connection
    }

    LocationData getLocationData(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        LocationData data=null;


        try {
            Cursor cursor = db.query(TABLE_CROWDLOCATION, new String[]{CROWDLOC_ID,
                            CROWDLOC_NAME, CROWDLOC_ADDRESS, CROWDLOC_LATITUDE, CROWDLOC_LONGITUDE}, CROWDLOC_NAME + "=?",
                    new String[]{String.valueOf(name)}, null, null, null, null);
            if (cursor != null)
            {
                cursor.moveToFirst();

            data = new LocationData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));

            // return contact
            return data;
        }
        }
        catch (Exception e)
        {
            Log.d("ExceptionMessage", e.getMessage());
            return null;
        }
        return data;
    }

    public List<LocationData> getAllDetails() {
        List<LocationData> dataList = new ArrayList<LocationData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CROWDLOCATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LocationData locationdata = new LocationData();
                locationdata.setCrowdloc_id(cursor.getInt(0));
                locationdata.setCrowdloc_name(cursor.getString(1));
                locationdata.setCrowdloc_address(cursor.getString(2));
                locationdata.setCrowdloc_latitude(cursor.getDouble(3));
                locationdata.setCrowdloc_longitude(cursor.getDouble(4));
                dataList.add(locationdata);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Getting  Count
    public int getLocationDataCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_CROWDLOCATION;
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

    public int updatelocationDetails(LocationData locationdata,String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CROWDLOC_ID, locationdata.getCrowdloc_id());
        values.put(CROWDLOC_NAME, locationdata.getCrowdloc_name());
        values.put(CROWDLOC_ADDRESS, locationdata.getCrowdloc_address());
        values.put(CROWDLOC_LATITUDE, locationdata.getCrowdloc_latitude());
        values.put(CROWDLOC_LONGITUDE, locationdata.getCrowdloc_longitude());


        // updating row
        return db.update(TABLE_CROWDLOCATION, values, CROWDLOC_NAME + " = ?",
                new String[] { String.valueOf(name) });
    }

}
