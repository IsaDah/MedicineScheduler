package com.example.medicinescheduler.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MedicineDBHandler extends SQLiteOpenHelper
{
    private static final String DB_NAME = "medicine.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_MEDICINE = "medicineSchedules";

    public static final String COLUMN_MEDICINE_ID = "medicineID";
    public static final String COLUMN_MEDICINE_NAME = "medicineName";
    public static final String COLUMN_MEDICINE_QUANTITY = "medicineQuantity";
    public static final String COLUMN_MEDICINE_UNIT = "medicineUnit";
    public static final String COLUMN_MEDICINE_TIME = "medicineTime";
    public static final String COLUMN_MEDICINE_MEAL = "medicineMeal";
    public static final String COLUMN_MEDICINE_INTERVAL = "medicineInterval";
    public static final String COLUMN_MEDICINE_START = "medicineStart";
    public static final String COLUMN_MEDICINE_END = "medicineEnd";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_MEDICINE + " (" +
                    COLUMN_MEDICINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MEDICINE_NAME + " TEXT, " +
                    COLUMN_MEDICINE_QUANTITY + " TEXT, " +
                    COLUMN_MEDICINE_UNIT + " TEXT, " +
                    COLUMN_MEDICINE_TIME + " TEXT, " +
                    COLUMN_MEDICINE_MEAL + " TEXT, " +
                    COLUMN_MEDICINE_INTERVAL + " TEXT, " +
                    COLUMN_MEDICINE_START + " TEXT, " +
                    COLUMN_MEDICINE_END + " TEXT " + ") ";

    public MedicineDBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }
}
