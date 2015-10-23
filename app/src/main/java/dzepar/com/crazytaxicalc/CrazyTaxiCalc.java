package dzepar.com.crazytaxicalc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;


public class CrazyTaxiCalc extends AppCompatActivity {


    private static final String KM_START = "KM_START";
    private static final String KM_END = "KM_END";
    private static final String KM_TOTAL = "KM_TOTAL";
    private static final String PRICE = "PRICE";

    private double kmStart;
    private double kmEnd;
    private double kmTotal;
    private double priceTotal;


    EditText kmStartET;
    EditText kmEndET;
    TextView kmTotalET;
    TextView priceTotalET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazy_taxi_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            kmStart = 0.0;
            kmEnd = 0.0;
            kmTotal = 0.0;
            priceTotal = 0.0;
        } else {

            kmStart = savedInstanceState.getDouble(KM_START);
            kmEnd = savedInstanceState.getDouble(KM_END);
            kmTotal = savedInstanceState.getDouble(KM_TOTAL);
            priceTotal = savedInstanceState.getDouble(PRICE);

        }

        kmStartET = (EditText) findViewById(R.id.kmStartEditText);
        kmEndET = (EditText) findViewById(R.id.kmEndEditText);
        kmTotalET = (TextView) findViewById(R.id.kmTotalEditText);
        priceTotalET = (TextView) findViewById(R.id.priceEditTextView);

        kmStartET.addTextChangedListener(kmStartListener);
        kmEndET.addTextChangedListener(kmEndListener);

    }

    private TextWatcher kmStartListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                kmStart = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {

                kmStart = 0.0;
            }

            updateKmTotalAmount();

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher kmEndListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                kmEnd = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {

                kmEnd = 0.0;
            }

            updateKmTotalAmount();

        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void updateKmTotalAmount() {

        double kmTotal = kmEnd - kmStart;
        double priceTotal = kmTotal * 30;

        kmTotalET.setText(String.format("%.02f", kmTotal));
        priceTotalET.setText(String.format("%.02f", priceTotal));

        }

        protected void onSaveInstanceState(Bundle outState) {

            kmTotalET.onSaveInstanceState();

            outState.putDouble(KM_TOTAL, kmTotal);
            outState.putDouble(KM_END, kmEnd);
            outState.putDouble(KM_START, kmStart);
            outState.putDouble(PRICE, priceTotal);

        }


    }
