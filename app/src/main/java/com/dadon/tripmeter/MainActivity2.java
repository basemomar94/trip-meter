package com.dadon.tripmeter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    Double distance, performace, priceofgaz, finalcoast;
    EditText distanceEt, performanceEt;
    TextView pricetoday, cost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button calcuate = findViewById(R.id.button);

        initViews();

        getFuelPrice();

        calcuate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("test");
                try {
                    preparingValues();
                    calculate();
                } catch (Exception e){
                     errorToast();
                }
              // validation();
            }
        });
    }

    void preparingValues() {
        distance = Double.parseDouble(distanceEt.getText().toString());
        performanceEt = findViewById(R.id.performanceET);
        performace = Double.parseDouble(performanceEt.getText().toString());
    }


    void getFuelPrice() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.gaz:
                        priceofgaz = .25;
                        break;
                    case R.id.diesel:
                        priceofgaz = .15;
                }

                updateGazeprice(priceofgaz);


            }
        });
    }
     void errorToast(){
         Toast.makeText(this,"please fill the data",Toast.LENGTH_LONG).show();

     }

    void calculate() {

        Double gasonKm = performace / 100;
        System.out.println(gasonKm);
        finalcoast = (gasonKm * distance * priceofgaz);

        String viewed = "The cost is " + finalcoast.toString() + "$";


        cost.setText(viewed);

    }

    void updateGazeprice(Double price) {
        String viewed = "Price of the feul " + price.toString() + "$";
        pricetoday.setText(viewed);
    }

    void initViews() {
        cost = findViewById(R.id.costTV);
        pricetoday = findViewById(R.id.priceofgaz);
        distanceEt = findViewById(R.id.distanceET);

    }
}