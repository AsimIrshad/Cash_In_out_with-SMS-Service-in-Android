package com.example.asimirshad.home_page_design;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Receiving_screen extends AppCompatActivity {

    Spinner company,depositor,category1;
    Button save;
    String [] dep_num = {"03008828341","03364110924","03088400975","03130454950","03099410157","03044487274","03081201371","03234008069","03094710566","03126471605","03068160048","03060497068"};
    String [] owner_num = {"03008828341","03457541259"};
    String [] companies = { "Syed Brothers","Royal Cookies"};
    String [] depositors ={"Irshad Ali Shah","Asim Irshad","A.R. Khan","A.R.Siyal","Nouman","Waheed ul Hassan","Ahsan Ali Shah","Shuja Kazmi sb","Haris","Rehman","Gujjar","M. Waqas"};
    String [] categories = {"Sale Amount", "Ghatta Amount","Credit ","Stock Return","Fuel","Refreshment","Maintenance","Advance","Salary","Sheet Amount","Bank Payment","Other"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_screen);

        save=(Button)findViewById(R.id.R_btn);
        company = (Spinner) findViewById(R.id.R_company1);
        depositor = (Spinner) findViewById(R.id.R_depositor);
        category1 = (Spinner) findViewById(R.id.R_category);

        //capital_textView = (TextView) findViewById(R.id.capital);

        //value to be shown in the spinner


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, companies);
        company.setAdapter(adapter);



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, depositors);
        depositor.setAdapter(adapter1);


         ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        category1.setAdapter(adapter2);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_sms(view);
            }
        });

    }
    public void make_sms(View view){

    String temp_dep=depositor.getSelectedItem().toString();
    EditText amount=(EditText) findViewById(R.id.R_amunt_payment);
    EditText decr=(EditText) findViewById(R.id.R_description);

        String dep_message="Dear Mr "+temp_dep+" \n You have submitted "+amount.getText()+" to Syed Brother Distribution. \n\n Amount Collector: Asim Irshad \n Category: "+category1.getSelectedItem().toString()+"\n Company: "+company.getSelectedItem().toString()+"\n Description :"+decr.getText().toString()+"\n\n Thanks for your cooperation";
        String owner_message=amount.getText().toString()+" Rs  has been received from "+temp_dep+"\n \n Category : "+category1.getSelectedItem().toString()+" \n Description: "+decr.getText().toString();
        String manag_message="Received amount: "+amount.getText()+"\n Depositor : "+temp_dep+"\n Category: "+category1.getSelectedItem().toString()+"\n Description :"+decr.getText().toString()+"\n";






            sendSMS("+923364110924",manag_message);

            String dep_num=getdepositor_num(temp_dep);
            sendSMS(dep_num,dep_message);

            String owner_num=getowner_num(company.getSelectedItem().toString());
            sendSMS(owner_num,owner_message);




        //String dep_num=getdepositor_num(temp_dep);


        System.out.println("In Message box");
       //new Sendsms(Receiving_screen.this).execute("03364110924",message);

    }

    private String getowner_num(String company2) {
        for(int i=0;i<companies.length;i++){
            if(company2.equals(companies[i])){
                return owner_num[i];
            }
        }

        return null;

    }

    private String getdepositor_num(String temp_dep) {

        for(int i=0;i<depositors.length;i++){
            if(temp_dep.equals(depositors[i])){
                return dep_num[i];
            }
        }
        return null;
    }

    private void sendSMS(String phoneNumber, String message)
    {
        System.out.println("In sent box "+message);
        PendingIntent pi = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);
        SmsManager sms = SmsManager.getDefault();
        ArrayList<String> parts = sms.divideMessage(message);
        sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);
        this.finish();
    }


}

