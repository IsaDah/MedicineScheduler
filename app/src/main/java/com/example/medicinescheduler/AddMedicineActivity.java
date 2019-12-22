package com.example.medicinescheduler;

import android.app.DatePickerDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;

import com.example.medicinescheduler.Database.MedicineDBOperations;
import com.example.medicinescheduler.Model.Medicine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class AddMedicineActivity extends AppCompatActivity
{
    private static final String EXTRA_MEDICINE_ID = "com.example.MId";
    private static final String EXTRA_ADD_UPDATE = "com.example.add_update";
    private static final String DIALOG_DATE = "DialogDate";

    private EditText MedicineNameEditText;
    private EditText MedicineQuantityEditText;

    private EditText TimeEditText;

    private ImageView TimePickerImage;

    private EditText UnitEditText;
    private EditText MealEditText;
    private EditText IntervalEditText;

    private EditText StartDateEditText;
    private EditText EndDateEditText;

    private ImageView StartDatePickerImage;
    private ImageView EndDatePickerImage;

    private Button AddMedicineButton;

    private Medicine newMedicine;
    private Medicine oldMedicine;
    private String mode;
    private long appId;
    private MedicineDBOperations medicineData;

    Calendar medicineTimeCalendar = Calendar.getInstance();
    final Calendar medicineStartDateCalendar = Calendar.getInstance();
    final Calendar medicineEndDateCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        newMedicine = new Medicine();
        oldMedicine = new Medicine();

        MedicineNameEditText = (EditText)findViewById(R.id.edit_text_medicine_name);
        MedicineQuantityEditText = (EditText)findViewById(R.id.edit_text_medicine_quantity);

        TimeEditText = (EditText)findViewById(R.id.edit_text_medicine_time);

        TimePickerImage = (ImageView)findViewById(R.id.image_view_time);

        UnitEditText = (EditText)findViewById(R.id.edit_text_medicine_unit);
        MealEditText = (EditText)findViewById(R.id.edit_text_medicine_meal);
        IntervalEditText = (EditText)findViewById(R.id.edit_text_medicine_interval);

        StartDateEditText = (EditText)findViewById(R.id.edit_text_medicine_from_date);
        EndDateEditText = (EditText)findViewById(R.id.edit_text_medicine_to_date);

        StartDatePickerImage = (ImageView)findViewById(R.id.image_view_date_from);
        EndDatePickerImage = (ImageView)findViewById(R.id.image_view_date_to);

        AddMedicineButton = (Button)findViewById(R.id.button_add_medicine);

        medicineData = new MedicineDBOperations(this);
        medicineData.open();

        ArrayAdapter<CharSequence> adapterUnits = ArrayAdapter.createFromResource(this, R.array.units, R.layout.spinner_item);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner unit = (Spinner) findViewById(R.id.spinner_units);
        unit.setAdapter(adapterUnits);
        unit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String unitText = parent.getItemAtPosition(position).toString();
                UnitEditText.setText(unitText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        ArrayAdapter<CharSequence> adapterMeal = ArrayAdapter.createFromResource(this, R.array.meal, R.layout.spinner_item);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner meal = (Spinner) findViewById(R.id.spinner_meal);
        meal.setAdapter(adapterMeal);
        meal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String mealText = parent.getItemAtPosition(position).toString();
                MealEditText.setText(mealText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        ArrayAdapter<CharSequence> adapterInterval = ArrayAdapter.createFromResource(this, R.array.interval, R.layout.spinner_item);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner interval = (Spinner)findViewById(R.id.spinner_interval);
        interval.setAdapter(adapterInterval);
        interval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int postion, long id)
            {
                String intervalText = parent.getItemAtPosition(postion).toString();
                IntervalEditText.setText(intervalText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        AddMedicineButton.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                    newMedicine.setMedicineName(MedicineNameEditText.getText().toString());
                    newMedicine.setMedicineQuantity(MedicineQuantityEditText.getText().toString());
                    newMedicine.setMedicineUnit(UnitEditText.getText().toString());
                    newMedicine.setMedicineTime(TimeEditText.getText().toString());
                    newMedicine.setMedicineMeal(MealEditText.getText().toString());
                    newMedicine.setMedicineInterval(IntervalEditText.getText().toString());
                    newMedicine.setMedicineStartDate(StartDateEditText.getText().toString());
                    newMedicine.setMedicineEndDate(EndDateEditText.getText().toString());

                    if(MedicineNameEditText.getText().toString().isEmpty() || MedicineQuantityEditText.getText().toString().isEmpty() || UnitEditText.getText().toString().isEmpty() || TimeEditText.getText().toString().isEmpty() || MealEditText.getText().toString().isEmpty() || IntervalEditText.getText().toString().isEmpty() || StartDateEditText.getText().toString().isEmpty() || EndDateEditText.getText().toString().isEmpty())
                    {
                        Toast.makeText(AddMedicineActivity.this,"Field or Fields can't be empty !!!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        medicineData.addMedicine(newMedicine);
                        Toast.makeText(AddMedicineActivity.this, "Medicine Added Successfully",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AddMedicineActivity.this,MainMenuActivity.class);
                        startActivity(i);
                    }
            }
        });

        final DatePickerDialog.OnDateSetListener dateStart = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                // TODO Auto-generated method stub
                medicineStartDateCalendar.set(Calendar.YEAR, year);
                medicineStartDateCalendar.set(Calendar.MONTH, monthOfYear);
                medicineStartDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        final DatePickerDialog.OnDateSetListener dateEnd = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                // TODO Auto-generated method stub
                medicineEndDateCalendar.set(Calendar.YEAR, year);
                medicineEndDateCalendar.set(Calendar.MONTH, monthOfYear);
                medicineEndDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        StartDatePickerImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new DatePickerDialog(AddMedicineActivity.this, dateStart, medicineStartDateCalendar.get(Calendar.YEAR), medicineStartDateCalendar.get(Calendar.MONTH), medicineStartDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        EndDatePickerImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                new DatePickerDialog(AddMedicineActivity.this, dateEnd, medicineEndDateCalendar.get(Calendar.YEAR), medicineEndDateCalendar.get(Calendar.MONTH), medicineEndDateCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TimePickerImage.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int hour = medicineTimeCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = medicineTimeCalendar.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMedicineActivity.this, new TimePickerDialog.OnTimeSetListener()
                {
                   @Override
                   public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                   {
                       TimeEditText.setText(selectedHour + ":" + selectedMinute);
                   }
                },hour,minute,true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void updateLabel()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        StartDateEditText.setText(sdf.format(medicineStartDateCalendar.getTime()));
        EndDateEditText.setText(sdf.format(medicineEndDateCalendar.getTime()));
    }

    private void initializeMedicine(long appId)
    {
        oldMedicine = medicineData.getMedicine(appId);

        MedicineNameEditText.setText(oldMedicine.getMedicineName());
        MedicineQuantityEditText.setText(oldMedicine.getMedicineQuantity());
        UnitEditText.setText(oldMedicine.getMedicineUnit());
        TimeEditText.setText(oldMedicine.getMedicineTime());
        MealEditText.setText(oldMedicine.getMedicineMeal());
        IntervalEditText.setText(oldMedicine.getMedicineInterval());
        StartDateEditText.setText(oldMedicine.getMedicineStartDate());
        EndDateEditText.setText(oldMedicine.getMedicineEndDate());
    }
}
