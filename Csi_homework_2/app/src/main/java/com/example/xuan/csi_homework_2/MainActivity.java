package com.example.xuan.csi_homework_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private Button btnsubmit;

    private RadioGroup sizeRadioGroup;
    private RadioGroup tortillaRadioGroup;

    private CheckBox beefCbx;
    private CheckBox riceCbx;
    private CheckBox chickenCbx;
    private CheckBox beansCbx;
    private CheckBox whiteFishCbx;
    private CheckBox picoCbx;
    private CheckBox cheeseCbx;
    private CheckBox guacamoleCbx;
    private CheckBox seaCbx;
    private CheckBox lbtCbx;
    private CheckBox sodaCbx;
    private CheckBox margaritaCbx;
    private CheckBox cervezaCbx;
    private CheckBox tequilaCbx;

    private String orderMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();
        setControls();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();
                orderMessage = getOrderMessage();
                smsManager.sendTextMessage("01269690407", null,
                        orderMessage,
                        null, null);
                Toast.makeText(mContext, "Bạn đã đặt hàng tại Taco Pronto", Toast.LENGTH_SHORT).show();;
            }
        });
    }

    private void setControls() {
        btnsubmit = (Button)findViewById(R.id.btnsubmit);

        sizeRadioGroup = (RadioGroup)findViewById(R.id.sizeRadioGroup);
        tortillaRadioGroup = (RadioGroup)findViewById(R.id.tortillaRadioGroup);

        beefCbx = (CheckBox)findViewById(R.id.beefCbx);
        riceCbx = (CheckBox)findViewById(R.id.riceCbx);
        chickenCbx = (CheckBox)findViewById(R.id.chickenCbx);
        beansCbx = (CheckBox)findViewById(R.id.beansCbx);
        whiteFishCbx = (CheckBox)findViewById(R.id.whiteFishCbx);
        picoCbx = (CheckBox)findViewById(R.id.picoCbx);
        cheeseCbx = (CheckBox)findViewById(R.id.cheeseCbx);
        guacamoleCbx = (CheckBox)findViewById(R.id.guacamoleCbx);
        seaCbx = (CheckBox)findViewById(R.id.seaCbx);
        lbtCbx = (CheckBox)findViewById(R.id.lbtCbx);
        sodaCbx = (CheckBox)findViewById(R.id.sodaCbx);
        margaritaCbx = (CheckBox)findViewById(R.id.margaritaCbx);
        cervezaCbx = (CheckBox)findViewById(R.id.cervezaCbx);
        tequilaCbx = (CheckBox)findViewById(R.id.tequilaCbx);
    }

    private String getOrderMessage() {
        String message = "Pizza Order:";

        String size = getCheckedRadioButtonOnGroup(sizeRadioGroup);
        String tortilla = getCheckedRadioButtonOnGroup(tortillaRadioGroup);

        String beef = getCheckedCheckBox(beefCbx);
        String rice = getCheckedCheckBox(riceCbx);
        String chicken = getCheckedCheckBox(chickenCbx);
        String beans = getCheckedCheckBox(beansCbx);
        String whiteFish = getCheckedCheckBox(whiteFishCbx);
        String pico = getCheckedCheckBox(picoCbx);
        String cheese = getCheckedCheckBox(cheeseCbx);
        String guacamole = getCheckedCheckBox(guacamoleCbx);
        String sea = getCheckedCheckBox(seaCbx);
        String lbt = getCheckedCheckBox(lbtCbx);
        String soda = getCheckedCheckBox(sodaCbx);
        String margarita = getCheckedCheckBox(margaritaCbx);
        String cerveza = getCheckedCheckBox(cervezaCbx);
        String tequila = getCheckedCheckBox(tequilaCbx);

        String[] customerChoice = {size, tortilla, beef, rice, chicken, beans, whiteFish, pico, cheese, guacamole, sea, lbt, soda, margarita, cerveza, sea, tequila};
        for(int i=0; i<customerChoice.length; i++) {
            if(customerChoice[i] != "") {
                message += customerChoice[i] + ",";
            }
        }
        message = message.substring(0, message.length() - 1) + ".";
        return message;
    }

    private String getCheckedRadioButtonOnGroup(RadioGroup radioGroup) {
        String value = "";
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton checkedRadioButton =  (RadioButton)findViewById(checkedRadioButtonId);
        if(checkedRadioButton != null) {
            value = checkedRadioButton.getText().toString();
        }
        return value;
    }

    private String getCheckedCheckBox(CheckBox checkBox) {
        String value = "";
        if(checkBox.isChecked()) {
            value = checkBox.getText().toString();
        }
        return value;
    }
}
