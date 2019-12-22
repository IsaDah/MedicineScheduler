package com.example.medicinescheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicinescheduler.Database.MedicineDBHandler;
import com.example.medicinescheduler.Database.MedicineDBOperations;

public class MainMenuActivity extends AppCompatActivity
{
    private Button GoToAddMedicineButton;
    private Button RemoveMedication;
    private Button ViewAllMedicineButton;

    private MedicineDBOperations medicineDBOperations;

    private static final String EXTRA_MEDICINE_ID = "com.example.MId";
    private static final String EXTRA_ADD_UPDATE = "com.example.add_update";

    private static final String TAG = "Medicine Exits";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        GoToAddMedicineButton = (Button)findViewById(R.id.button_go_to_add_medicine);
        RemoveMedication = (Button)findViewById(R.id.button_remove_medicine);
        ViewAllMedicineButton = (Button)findViewById(R.id.button_view_all_medications);

        RemoveMedication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getMedicineIDAndRemoveMedicine();
            }
        });
    }

    public void goToAddMedicine(View view)
    {
        GoToAddMedicineButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainMenuActivity.this, AddMedicineActivity.class);
                startActivity(i);
            }
        });
    }

    public void goToViewAllMedicine(View view)
    {
        ViewAllMedicineButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean check_existance(String medicine_ID)
    {
        SQLiteOpenHelper db = new MedicineDBHandler(this);
        SQLiteDatabase database = db.getWritableDatabase();

        String select = "SELECT * FROM medicineSchedules WHERE medicineID =" + medicine_ID;

        Cursor c = database.rawQuery(select, null);

        if (c.moveToNext())
        {
            Log.d(TAG, "Medicine Exists");
            return true;
        }
        if (c != null)
        {
            c.close();
        }

        database.close();
        return false;
    }

    public void getMedicineIDAndUpdateMedicine()
    {
        LayoutInflater li = LayoutInflater.from(this);
        final View getMedicineIDView = li.inflate(R.layout.dialog_get_medicine_id, null);
        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(this);

        alerDialogBuilder.setView(getMedicineIDView);

        final EditText userInput = (EditText)getMedicineIDView.findViewById(R.id.editTextDialogUserInput);

        alerDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if(userInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainMenuActivity.this,"User Input is Invalid", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(check_existance(userInput.getText().toString()) == true)
                    {
                        Intent i = new Intent (MainMenuActivity.this, AddMedicineActivity.class);
                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
                        i.putExtra(EXTRA_MEDICINE_ID, Long.parseLong(userInput.getText().toString()));
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainMenuActivity.this, "Input is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).create().show();
    }


    public void getMedicineIDAndRemoveMedicine()
    {
        LayoutInflater li = LayoutInflater.from(this);
        final View getMedicineIDView = li.inflate(R.layout.dialog_get_medicine_id, null);
        AlertDialog.Builder alerDialogBuilder = new AlertDialog.Builder(this);

        alerDialogBuilder.setView(getMedicineIDView);

        final EditText userInput = (EditText)getMedicineIDView.findViewById(R.id.editTextDialogUserInput);

        alerDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                if(userInput.getText().toString().isEmpty())
                {
                    Toast.makeText(MainMenuActivity.this,"User Input is Invalid", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(check_existance(userInput.getText().toString()) == true)
                    {
                        medicineDBOperations.removeMedicine(medicineDBOperations.getMedicine(Long.parseLong(userInput.getText().toString())));
                        Toast.makeText(MainMenuActivity.this, "Medicine has been removed successfully", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(MainMenuActivity.this, "Input is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).create().show();
    }

    @Override
    protected  void onResume()
    {
        super.onResume();
        medicineDBOperations = new MedicineDBOperations(MainMenuActivity.this);
        medicineDBOperations.open();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        medicineDBOperations.close();
    }
}
