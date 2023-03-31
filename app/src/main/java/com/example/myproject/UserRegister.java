package com.example.myproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UserRegister extends AppCompatActivity  {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    RadioButton r1,r2,r3;
    MultiAutoCompleteTextView ad1;
    EditText name,phone,city,education,course,otherlanguage;
    CheckBox tamil,english,hindi;
    LocalDate dob1;
    String email,gender;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    DatabaseReference usersRef;
    ArrayList<String> arrayList;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        r1=findViewById(R.id.male);
        r2=findViewById(R.id.female);
        r3=findViewById(R.id.trans);
        ad1=findViewById(R.id.skill);
        Intent i=getIntent();
        email=i.getStringExtra("Email");
        name=findViewById(R.id.username_input);
        phone=findViewById(R.id.phone);
        city=findViewById(R.id.city);
        education=findViewById(R.id.edu1);
        course=findViewById(R.id.course1);
        tamil=findViewById(R.id.tamil);
        english=findViewById(R.id.english);
        hindi=findViewById(R.id.hindi);
        otherlanguage=findViewById(R.id.other);
        usersRef = ref.child("interview/user");
         arrayList= new ArrayList<>();
        String[] str=getResources().getStringArray(R.array.key_skills);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(UserRegister.this, android.R.layout.simple_list_item_1,str);
        ad1.setAdapter(arrayAdapter);
        ad1.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


    }


    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        dob1=LocalDate.of(year,month,day);


        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void checked(View view) {
       // boolean check=((RadioButton) view).isChecked();

        switch (view.getId())
        {
            case R.id.male:
                    r1.setChecked(true);
                    r2.setChecked(false);
                    r3.setChecked(false);
                    gender="Male";
                    break;
            case R.id.female:
                gender="Female";
                r1.setChecked(false);
                r2.setChecked(true);
                r3.setChecked(false);
                break;
            case R.id.trans:
                gender="Transgender";
                r1.setChecked(false);
                r2.setChecked(false);
                r3.setChecked(true);
                break;
        }
    }


    public void resumePage(View view) {
        //Variable names
        Toast.makeText(this, "Please select gender1", Toast.LENGTH_SHORT).show();
        String na,cit,langua,educa,cour,keysk;
        Toast.makeText(this, "Please select gender11", Toast.LENGTH_SHORT).show();
        String phoe="9025376162";
        Toast.makeText(this, "Please select gender12", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Please select gender13", Toast.LENGTH_SHORT).show();
        na=name.getText().toString();
        cit=city.getText().toString();
        Toast.makeText(this, "Please select gender14", Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Please select gender25", Toast.LENGTH_SHORT).show();
        if(tamil.isChecked())
            arrayList.add("Tamil");
        if(english.isChecked())
            arrayList.add("English");
        if(hindi.isChecked())
            arrayList.add("Hindi");
        if(!(otherlanguage.getText().toString().isEmpty()))
            arrayList.add(otherlanguage.getText().toString());
        String lan[]= (String[]) arrayList.toArray();
        langua=String.join(",",lan);
        educa=education.getText().toString();
        cour=course.getText().toString();
        keysk=ad1.getText().toString();
        Toast.makeText(this, "Please select gender3", Toast.LENGTH_SHORT).show();

        if(na.isEmpty())
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
        else if(gender.isEmpty())
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
        else if(phoe.length()!=10)
            phone.setError("Enter a valid number");
        else if(cit.isEmpty())
            city.setError("This field must not be empty");
        else if(langua.isEmpty())
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
        else if(keysk.isEmpty())
            ad1.setError("This field must not be empty");
        else if(educa.isEmpty())
            education.setError("This field must not be empty");
        else if(cour.isEmpty())
            course.setError("This field must not be empty");
        else {
            usersRef.child(email).setValue(new user(na,gender,cit,langua,educa,cour,keysk,phoe,dob1.toString()));
            Intent i = new Intent(getApplicationContext(), resumeUpload.class);
            startActivity(i);
            finish();
        }
    }
}