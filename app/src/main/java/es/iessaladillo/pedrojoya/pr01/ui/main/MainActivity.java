package es.iessaladillo.pedrojoya.pr01.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import es.iessaladillo.pedrojoya.pr01.R;
import es.iessaladillo.pedrojoya.pr01.bmi.BmiCalculator;
import es.iessaladillo.pedrojoya.pr01.utils.SoftInputUtils;

public class MainActivity extends AppCompatActivity {

    private EditText txtWeight;
    private EditText txtHeight;
    private Button btnReset;
    private Button btnCalculate;
    private ImageView imgBmi;
    private TextView lblResult;
    private BmiCalculator b = new BmiCalculator();
    private BmiCalculator.BmiClasification w;
    private float bmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupViews();
        // TODO
    }

    private void setupViews() {
        lblResult=ActivityCompat.requireViewById(this, R.id.lblResult);
        txtWeight=ActivityCompat.requireViewById(this, R.id.txtWeight);
        txtHeight=ActivityCompat.requireViewById(this,R.id.txtHeight);
        btnReset=ActivityCompat.requireViewById(this,R.id.btnReset);
        btnCalculate=ActivityCompat.requireViewById(this,R.id.btnCalculate);
        imgBmi=ActivityCompat.requireViewById(this,R.id.imgBmi);
        btnCalculate.setOnClickListener(view1 -> calculateBmi(view1));
        btnReset.setOnClickListener(view -> reset());
    }

    private void reset() {
        txtWeight.setText("");
        txtHeight.setText("");
        lblResult.setText("");
        txtWeight.setError(null);
        txtHeight.setError(null);
        changeImage(R.drawable.bmi);
    }

    private void calculateBmi(View view) {
        SoftInputUtils.hideKeyboard(view);
        checktxtHeight();
        checktxtWeight();
        if(checktxtWeight()&&checktxtHeight()){
            bmi = b.calculateBmi(Float.parseFloat(txtWeight.getText().toString()), Float.parseFloat(txtHeight.getText().toString()));
            w =  b.getBmiClasification(bmi);

            switch(w){
                case LOW_WEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_underweight)));
                    changeImage(R.drawable.underweight);
                    break;
                case NORMAL_WEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_normal)));
                    changeImage(R.drawable.normal_weight);
                    break;
                case OVERWWEIGHT:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_overweight)));
                    changeImage(R.drawable.overweight);
                    break;
                case OBESITY_GRADE_1:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_obeseI)));
                    changeImage(R.drawable.obesity1);
                    break;
                case OBESITY_GRADE_2:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_obeseII)));
                    changeImage(R.drawable.obesity2);
                    break;
                case OBESITY_GRADE_3:
                    lblResult.setText(getString(R.string.main_bmi, bmi,getString(R.string.main_obeseIII)));
                    changeImage(R.drawable.obesity3);
                    break;
            }

        }
    }

    private void changeImage(int id) {
        imgBmi.setImageResource(id);
    }

    private boolean checktxtHeight() {
        if (txtHeight.getText().toString().equals("")){
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        if (txtHeight.getText().toString().equals(".")){
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else if(Float.parseFloat(txtHeight.getText().toString())>=3){
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else if (Float.parseFloat(txtHeight.getText().toString())<0.3){
            txtHeight.setError(getString(R.string.main_invalid_height));
            return false;
        }
        else {
            return true;
        }

    }

    private boolean checktxtWeight() {
        if (txtWeight.getText().toString().equals("")){
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else if (txtWeight.getText().toString().equals(".")){
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else if(Float.parseFloat(txtWeight.getText().toString())>=500){
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else if (Float.parseFloat(txtWeight.getText().toString())<1){
            txtWeight.setError(getString(R.string.main_invalid_weight));
            return false;
        }
        else {
            return true;
        }
    }

    // TODO

}
