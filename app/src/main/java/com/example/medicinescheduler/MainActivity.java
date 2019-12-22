package com.example.medicinescheduler;

import android.os.Bundle;
import android.app.ListActivity;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;

import com.example.medicinescheduler.Database.MedicineDBOperations;
import com.example.medicinescheduler.Model.Medicine;

import java.util.List;

public class MainActivity extends ListActivity
{
    private MedicineDBOperations medicineOps;
    List<Medicine> medicines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medicineOps = new MedicineDBOperations(this);
        medicineOps.open();
        medicines = medicineOps.getAllMedicines();
        medicineOps.close();

        ArrayAdapter<Medicine> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, medicines);
        setListAdapter(adapter);

    }
}
