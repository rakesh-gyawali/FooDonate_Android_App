    package com.example.food_donation_dissertation.donate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.food_donation_dissertation.R;
import com.example.food_donation_dissertation.URL;
import com.example.food_donation_dissertation.donate.getCharityDevelopment.CharityBLL;
import com.example.food_donation_dissertation.donate.getCharityDevelopment.CharityResponse;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


    public class DonateFragment extends Fragment {
    private EditText edtDate;
    private EditText edtQuantity;
    private AutoCompleteTextView actvCharity;
    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;
    private Button btnNext;
    private ChipGroup chipGroup;
    private List<CharityResponse> charityResponseList;
    private List<String> nameList;

    public DonateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_donate, container, false);
        edtDate = view.findViewById(R.id.edtDate);
        edtQuantity = view.findViewById(R.id.edtQuantity);
        actvCharity = view.findViewById(R.id.actvCharity);
        chipGroup = view.findViewById(R.id.chipGroup);
        btnNext = view.findViewById(R.id.btnNext);

        //-----date picker-------
        setUpDatePicker();

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });;
        // ------btnNext-------
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                storeRequestDonateValuesInStoredPreference();
                Intent intent = new Intent(getContext(), LocationConfirm.class);
                startActivity(intent);
            }
        });
        charityCall();


        return view;
    }
    private void charityCall() {
        URL.getStrictMode();
        CharityBLL bll = new CharityBLL();
        if (bll.checkGetCharityName()) {
            charityResponseList = bll.returnCharityNames();
            nameList = new ArrayList<>();
            for (CharityResponse charity: charityResponseList) {
                nameList.add(charity.getName());
            }
            if (nameList.size() <= 0) {
                Toast.makeText(getContext(), "No Charity Registered", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_item, nameList);
            actvCharity.setAdapter(arrayAdapter);

        } else {
            Toast.makeText(getContext(), "charityCall Failed ...", Toast.LENGTH_SHORT).show();
        }

    }



//    private void storeRequestDonateValuesInStoredPreference () {
//        getInputValues();
//        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("SIGN_UP", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("firstname", firstname);
//        editor.putString("lastname", lastname);
//        editor.putString("phone", phone);
//        editor.putString("password", password);
//        editor.apply();
//
//    }
//
//    private void getInputValues() {
//        firstname = edtFirstname.getText().toString();
//        lastname = edtLastname.getText().toString();
//        phone = edtPhone.getText().toString();
//        password = edtPassword.getText().toString();
//    }

    private void setUpDatePicker() {
        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtDate.setText(sdf.format(myCalendar.getTime()));
    }


    }