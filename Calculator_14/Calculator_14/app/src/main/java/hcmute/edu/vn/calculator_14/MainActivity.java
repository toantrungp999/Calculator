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
                Global.Integer=true;
                Global.Type="";
                break;
        }
    }
    private void CalculationClick(String type)
    {
        Equal();
        Global.Type=type;
        Global.Pressed=true;
        Global.Integer=true;
        Global.Made=false;
        Global.Count =0;
    }

    private void Load(){
        Global.Integer=true;
        Global.Made=false;
        Global.Pressed=false;
        Global.Total =0;
        Global.Type ="";
        Global.Count =0;
        textView.setText("0");
    }
    private void Equal(){
        if(Global.Pressed)
            return;;
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
        if(Global.Made){
            Load();
        }
        if(Global.Pressed)
            textView.setText("");
        Global.Pressed=false;
        Button btn = (Button) view;
        String buttonNumberText = btn.getText().toString();
        if(textView.getText().toString()=="0"){
            if(buttonNumberText == "0" && Global.Integer == true)
                return;
            textView.setText("");
        }
        textView.append(buttonNumberText);
        if(Global.Integer) {

            //tang 5 so kiem tra doi font
            if (Global.Count % 5 == 0) {
                double value = Double.parseDouble(textView.getText().toString());
                //do something
                textView.setText(String.valueOf(value));
            }
            Global.Count++;
        }
    }
    public void DocClick(View view){
        if(Global.Integer){
            Global.Integer=false;
            textView.append(".");
        }
    }
}
