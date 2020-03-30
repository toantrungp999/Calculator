package hcmute.edu.vn.calculator_14;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import MyClasses.Global;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView textView;
    private Button btnClear;
    private Button btnDivide;
    private Button btnMultiply;
    private Button btnPlus;
    private Button btnSub;
    private Button btnEqual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id._textView);
        btnClear=(Button)findViewById(R.id._btnClear);
        btnClear.setOnClickListener(this);
        btnDivide=(Button)findViewById(R.id._btnDivide);
        btnDivide.setOnClickListener(this);
        btnMultiply=(Button)findViewById(R.id._btnMultiply);
        btnMultiply.setOnClickListener(this);
        btnPlus=(Button)findViewById(R.id._btnPlus);
        btnPlus.setOnClickListener(this);
        btnSub=(Button)findViewById(R.id._btnSub);
        btnSub.setOnClickListener(this);
        btnEqual=(Button)findViewById(R.id._btnEqual);
        btnEqual.setOnClickListener(this);
        Load();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._btnClear:
                Load();
                break;
            case R.id._btnDivide:
                CalculationClick("/");
                break;
            case R.id._btnMultiply:
                CalculationClick("*");
                break;
            case R.id._btnPlus:
                CalculationClick("+");
                break;
            case R.id._btnSub:
                CalculationClick("-");
                break;
            case R.id._btnEqual:
                Equal();
                Global.Made=true;
                Global.StringValue=new StringBuilder(String.valueOf(Global.Total));
                Global.Integer=true;
                Global.Type="";
                break;
        }
    }
    private void CalculationClick(String type)
    {
        Equal();
        Global.StringValue=new StringBuilder();
        Global.Type=type;
        Global.Integer=true;
        Global.Made=false;
        Global.Count =0;
    }

    private void Load(){
        Global.Integer=true;
        Global.Made=false;
        Global.Total =0;
        Global.Type ="";
        Global.Count =0;
        Global.StringValue=new StringBuilder();
        textView.setText("0");
    }
    private void Equal(){
        String str=textView.getText().toString();
        if(str.isEmpty())
            return;
        Double Value = Double.parseDouble(str);
        if(Global.Type==""){
            Global.Total=Value;
            return;
        }
        if(Global.Type=="+"){
            Global.Total += Value;
        }
        else if(Global.Type=="-"){
            Global.Total -= Value;
        }
        else if(Global.Type=="*"){
            Global.Total *= Value;
        }
        else if(Global.Type=="/"){
            Global.Total /= Value;
        }
        textView.setText(""+Global.Total);
    }

    public void Click(View view) {
        Button btn = (Button) view;
        String buttonText = btn.getText().toString();
        if(Global.StringValue.length() == 0 && buttonText == "0" && Global.Integer==true) {
           return;
        }
        if(Global.Made){
            Load();
        }
        Global.StringValue.append(buttonText);
        if(Global.Integer) {
            Global.Count++;
            if (Global.Count % 5 == 0) {
                double value = Double.parseDouble(Global.StringValue.toString());
                Global.StringValue = new StringBuilder(String.valueOf(value));
            }
        }
        textView.setText(Global.StringValue);
    }
    public void DocClick(View view){
        if(Global.Integer){
            Global.Integer=false;
        }
    }
}
