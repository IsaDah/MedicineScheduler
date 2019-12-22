package com.example.medicinescheduler.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.medicinescheduler.Model.Medicine;
import java.util.ArrayList;
import java.util.List;

public class MedicineDBOperations
{
    public static final String LOGTAG = "APPOINT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns =
            {
                    MedicineDBHandler.COLUMN_MEDICINE_ID,
                    MedicineDBHandler.COLUMN_MEDICINE_NAME,
                    MedicineDBHandler.COLUMN_MEDICINE_QUANTITY,
                    MedicineDBHandler.COLUMN_MEDICINE_UNIT,
                    MedicineDBHandler.COLUMN_MEDICINE_TIME,
                    MedicineDBHandler.COLUMN_MEDICINE_MEAL,
                    MedicineDBHandler.COLUMN_MEDICINE_INTERVAL,
                    MedicineDBHandler.COLUMN_MEDICINE_START,
                    MedicineDBHandler.COLUMN_MEDICINE_END
            };

    public MedicineDBOperations(Context context)
    {
        dbhandler = new MedicineDBHandler(context);
    }

    public void open()
    {
        Log.i(LOGTAG, "Database Opened");
        database = dbhandler.getWritableDatabase();
    }

    public void close()
    {
        Log.i(LOGTAG,"Database Closed");
        dbhandler.close();
    }

    public Medicine addMedicine(Medicine Medicine)
    {
        ContentValues values = new ContentValues();
        values.put(MedicineDBHandler.COLUMN_MEDICINE_NAME, Medicine.getMedicineName());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_QUANTITY, Medicine.getMedicineQuantity());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_UNIT,Medicine.getMedicineUnit());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_TIME,Medicine.getMedicineTime());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_MEAL,Medicine.getMedicineMeal());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_INTERVAL,Medicine.getMedicineInterval());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_START,Medicine.getMedicineStartDate());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_END,Medicine.getMedicineEndDate());
        long insertMedicineID = database.insert(MedicineDBHandler.TABLE_MEDICINE, null, values);
        Medicine.setMedicineID(insertMedicineID);
        return Medicine;
    }

    public Medicine getMedicine(long id)
    {
        Cursor cursor = database.query(MedicineDBHandler.TABLE_MEDICINE, allColumns, MedicineDBHandler.COLUMN_MEDICINE_ID + "=?", new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!= null && cursor.moveToFirst())
            cursor.moveToFirst();
        Medicine e = new Medicine(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        return e;
    }

    public List<Medicine> getAllMedicines()
    {
        Cursor cursor = database.query(MedicineDBHandler.TABLE_MEDICINE,allColumns, null,null,null,null,null);
        List<Medicine> medicines = new ArrayList<>();
        if(cursor.getCount() > 0 && cursor !=null)
        {
            while (cursor.moveToNext())
            {
                Medicine medicine = new Medicine();
                medicine.setMedicineID(cursor.getLong(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_ID)));
                medicine.setMedicineName(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_NAME)));
                medicine.setMedicineQuantity(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_QUANTITY)));
                medicine.setMedicineUnit(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_UNIT)));
                medicine.setMedicineTime(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_TIME)));
                medicine.setMedicineMeal(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_MEAL)));
                medicine.setMedicineInterval(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_INTERVAL)));
                medicine.setMedicineStartDate(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_START)));
                medicine.setMedicineEndDate(cursor.getString(cursor.getColumnIndex(MedicineDBHandler.COLUMN_MEDICINE_END)));

                medicines.add(medicine);
            }
        }
        return medicines;
    }

    public int updateMedicine(Medicine medicine)
    {
        ContentValues values = new ContentValues();

        values.put(MedicineDBHandler.COLUMN_MEDICINE_NAME, medicine.getMedicineName());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_QUANTITY, medicine.getMedicineQuantity());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_UNIT,medicine.getMedicineUnit());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_TIME,medicine.getMedicineTime());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_MEAL,medicine.getMedicineMeal());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_INTERVAL,medicine.getMedicineInterval());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_START,medicine.getMedicineStartDate());
        values.put(MedicineDBHandler.COLUMN_MEDICINE_END,medicine.getMedicineEndDate());

        return database.update(MedicineDBHandler.TABLE_MEDICINE,values,MedicineDBHandler.COLUMN_MEDICINE_ID + "=?", new String[] {String.valueOf(medicine.getMedicineID())});
    }

    public void removeMedicine(Medicine medicine)
    {
        database.delete(MedicineDBHandler.TABLE_MEDICINE, MedicineDBHandler.COLUMN_MEDICINE_ID + "=" + medicine.getMedicineID(), null);
    }
}
