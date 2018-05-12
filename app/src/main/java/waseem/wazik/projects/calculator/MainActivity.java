package waseem.wazik.projects.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageButton btn1, btn2, btn3,  btn5, btn6, btn7, btn8, btn9, btn0,  btnsub,btnp,  btndiv, btnc, btneq,btndot,btn4,btnadd,btnmul,btncan;
    TextView tvres;
    ArrayList<Double> ai = new ArrayList<>();
    ArrayList<Character> ac = new ArrayList<>();
    double c = 0,check;
    String display = "",s;
    int d=1,dot,e,flagminus,sub,flag = 0,flageq = 0,flagmul = 0,flagbtn=0;;
    String TAG="position";
    char last = 'g';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvres = findViewById(R.id.tvresult);
        btn0 = findViewById(R.id.btn0);
        btnp=findViewById(R.id.btnp);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btncan=findViewById(R.id.btncan);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btnmul = findViewById(R.id.btnmul);
        btndiv = findViewById(R.id.btndiv);
        btnc = findViewById(R.id.btnc);
        btneq = findViewById(R.id.btneq);
        btndot=findViewById(R.id.btndot);
        btncan.setOnClickListener(this);
        btnp.setOnClickListener(this);
        btndot.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnc.setOnClickListener(this);
        btneq.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        check=c;

        //Setting limit for the input number not to exceed 10000000000000
        if (v.getId()==R.id.btn1||v.getId()==R.id.btn2||v.getId()==R.id.btn3||v.getId()==R.id.btn4||v.getId()==R.id.btn5||v.getId()==R.id.btn6||v.getId()==R.id.btn7||v.getId()==R.id.btn8||v.getId()==R.id.btn9||v.getId()==R.id.btn0)
        {
            if (check>=100000000000000.0)
            {
                return;
            }
        }

        //Saving last character of (display) the String that is displayed on the screen to check the last character
        if (display.length() >= 1 ) {
            last = display.charAt(display.length() - 1);

        }
        else
        {
            last='g';
        }

        //Removing equal sign from display String if any mathematical operation is pressed
        if ((v.getId()==R.id.btnadd ||v.getId()==R.id.btnsub ||v.getId()==R.id.btnmul||v.getId()==R.id.btndiv )&& display.isEmpty()==false && display.charAt(0)=='=')
        {
            display = display.substring(1);
        }

        //Adding 0 to the number Arraylist if any Mathematical operator is pressed
        if (ai.isEmpty() && (v.getId()==R.id.btnadd ||v.getId()==R.id.btnsub ||v.getId()==R.id.btnmul||v.getId()==R.id.btndiv)&& c==0)
        {
            display ="0";
            ai.add(c);
        }

        //Calculating percentage and displaying it
        if(v.getId()==R.id.btnp)
        {
            if(ai.size()==0  && c!=0)
            {
                c=c/100;
                if(c%1==0) {
                    display =String.valueOf((long)c);
                }

                else
                {

                    display =String.valueOf((float)c);
                }
                tvres.setText(display);
            }

            return;
        }

        try {
//Swith condition on the button pressed
            switch (v.getId()) {
                case R.id.btncan:
                    if(flageq==1)
                    {
                        return;
                    }
                    if(display.length()==0)
                    {
                        display ="0";
                        tvres.setText("0");
                        return;
                    }

                    if(last=='*'||last=='+'||last=='-'||last=='÷') {
                        if (last=='-' && (display.charAt(display.length()-2)=='*'|| display.charAt(display.length()-2)=='÷'))
                        {
                            display = display.substring(0, display.length() - 1);
                            flagminus=0;
                            if (display.length() == 0) {
                                tvres.setText("0");
                            } else {
                                tvres.setText(display);
                            }
                        }
                        else {

                            ac.remove(ac.size() - 1);
                            display = display.substring(0, display.length() - 1);
                            if (display.length() == 0) {
                                tvres.setText("0");
                            } else {
                                tvres.setText(display);
                            }
                        }
                    }
                    else {
                        if (flagbtn == 0) {
                            if( ai.size()==1 && (ai.get(0)%1)!=0 && c==0)
                            {
                                c=0;
                                ai.clear();
                                ac.clear();
                                tvres.setText("0");

                            }
                            else {
                                if (ai.size()==1 && c==0)
                                {
                                    double cr= ai.get(0);
                                    cr = cr / 10;
                                    cr = cr - cr % 1;
                                    ai.set(0,cr);

                                }

                                else {
                                    c = c / 10;
                                    c = c - c % 1;
                                }
                                display = display.substring(0, display.length() - 1);
                                if (display.length() == 0) {
                                    tvres.setText("0");
                                } else {
                                    tvres.setText(display);
                                }
                            }
                        } else {
                            if (last=='.')
                            {
                                flagbtn=0;

                            }
                            else {
                                c = c * d;
                                c = c - c % 10;
                                c = c / d;
                                d = d / 10;
                            }
                            display = display.substring(0, display.length() - 1);
                            if (display.length() == 0) {
                                tvres.setText("0");
                            } else {
                                tvres.setText(display);
                            }
                        }
                    }
                    return;
                case R.id.btndot:
                    if(flagbtn==1)
                    {
                        return;
                    }
                    if(last=='.')
                    {
                        return;
                    }
                    else {
                        if (flageq == 0) {
                            flagbtn = 1;
                            display = display + ".";
                            tvres.setText(display);
                        } else {
                            flagbtn = 1;
                            flageq=0;
                            c=0;
                            ai.remove(0);
                            display = ".";
                            tvres.setText(display);
                        }
                    }

                    return;
                case R.id.btnadd:
                    if (last == '+') {
                        return;
                    } else if (last == '-' || last == '*' || last == '÷') {
                        ac.remove(ac.size() - 1);
                        ac.add('+');
                        display = display.substring(0, display.length() - 1);
                        display = display + "+";
                        tvres.setText(display);
                        return;
                    }
                    ac.add('+');
                    if(flageq==0) {
                        if (c != 0) {
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }

                        }
                    }
                    else
                    {
                        flageq=1;
                    }

                    display = display + "+";

                    c = 0;
                    flag = 1;
                    break;
                case R.id.btnsub:
                    if (last == '-') {
                        return;
                    } else if (last == '+') {
                        ac.remove(ac.size() - 1);
                        ac.add('-');
                        display = display.substring(0, display.length() - 1);
                        display = display + "-";
                        tvres.setText(display);
                        return;
                    }
                    else if ( last == '*' || last == '÷') {
                        display = display + "-";
                        flagminus=1;
                        tvres.setText(display);
                        return;
                    }
                    ac.add(('-'));

                    if (display =="0")
                    {
                        display ="-";
                    }
                    else {
                        display = display + "-";
                    }
                    if(flageq==0) {
                        if (c != 0) {
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }
                        }
                    }
                    else
                    {
                        flageq=1;
                    }
                    c = 0;
                    flag = 1;
                    break;
                case R.id.btnmul:
                    if (last == '*') {
                        return;
                    } else if (last == '-' || last == '+' || last == '÷') {
                        ac.remove(ac.size() - 1);
                        ac.add('*');
                        display = display.substring(0, display.length() - 1);
                        display = display + "*";
                        tvres.setText(display);
                        return;
                    }
                    ac.add('*');
                    display = display + "*";
                    if(flageq==0) {
                        if (c != 0) {
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }
                        }
                    }
                    else
                    {
                        flageq=1;
                    }
                    c=0;
                    flag = 1;
                    flagmul = 1;
                    break;
                case R.id.btndiv:
                    if (last == '÷') {
                        return;
                    } else if (last == '-' || last == '*' || last == '+') {
                        ac.remove(ac.size() - 1);
                        ac.add('÷');
                        display = display.substring(0, display.length() - 1);
                        display = display + "÷";
                        tvres.setText(display);
                        return;
                    }
                    ac.add('÷');

                    display = display + "÷";
                    if(flageq==0) {
                        if (c != 0) {
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }
                        }
                    }
                    else
                    {
                        flageq=1;
                    }
                    c = 0;
                    flag = 1;
                    break;
            }
            if (flag == 1) {
                flageq = 0;
                flagbtn=0;
                d=1;
                flag = 0;
                if (display.length() > 15) {
                    display = display.substring(1);
                }
                tvres.setText(display);
                return;
            }
            if(flageq==0) {
                if (flagbtn == 0) {
                    switch (v.getId()) {

                        case R.id.btn0:

                            if (last == '÷') {
                                return;
                            } else {
                                c = c * 10 + 0;
                                display = display + "0";
                            }
                            break;
                        case R.id.btn1:
                            c = c * 10 + 1;
                            display = display + "1";
                            break;
                        case R.id.btn2:
                            c = c * 10 + 2;
                            display = display + "2";
                            break;
                        case R.id.btn3:
                            c = c * 10 + 3;
                            display = display + "3";
                            break;
                        case R.id.btn4:
                            c = c * 10 + 4;
                            display = display + "4";
                            break;
                        case R.id.btn5:
                            c = c * 10 + 5;
                            display = display + "5";
                            break;
                        case R.id.btn6:
                            c = c * 10 + 6;
                            display = display + "6";
                            break;
                        case R.id.btn7:
                            c = c * 10 + 7;
                            display = display + "7";
                            break;
                        case R.id.btn8:
                            c = c * 10 + 8;
                            display = display + "8";
                            break;
                        case R.id.btn9:
                            c = c * 10 + 9;
                            display = display + "9";
                            break;
                        case R.id.btnc:
                            c = 0;
                            display = "";
                            //fi = 0;
                            ai.clear();
                            ac.clear();
                            flageq = 0;
                            tvres.setText("0");
                            return;
                        case R.id.btneq:
                            if(last=='*'||last=='+'||last=='-'||last=='÷')
                            {
                                return;
                            }
                            flagbtn=0;
                            flageq = 1;
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }
                            flagminus=0;
                            c = 0;
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '÷') {
                                    ai.set(i, (ai.get(i) / ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);
                                } else {
                                    i++;
                                }
                            }
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '*') {
                                    ai.set(i, (ai.get(i) * ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }

                            }

                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '-') {
                                    ai.set(i, (ai.get(i) - ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }

                            }
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '+') {

                                    ai.set(i, (ai.get(i) + ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }
                            }

                            double db = ai.get(0);
                            if (db == 0) {
                                tvres.setText("= 0");
                                display = "";
                                return;

                            }
                            else if(db<9999999999999.0)
                            {
                                s=String.valueOf(db);
                                if (db % 1 == 0) {
                                    long fn = (long) db;
                                    display = "= "+String.valueOf(fn);
                                }
                                else {
                                    if(db>0.0009999999999 || db< -0.0009999999999) {
                                        float f = (float) (db);
                                        display = "= " + String.valueOf(f);
                                    }
                                    else
                                    {
                                        for(int i=0;i<s.length();i++)
                                        {
                                            if (s.charAt(i)=='.')
                                            {
                                                dot=i;
                                            }
                                            else if (s.charAt(i)=='E')
                                            {
                                                e=i;
                                            }

                                        }
                                        sub=Integer.valueOf(s.substring(e+2));
                                        if(sub<=7) {
                                            StringBuilder sb = new StringBuilder();
                                            if (db>0) {
                                                sb.append("0.");
                                                for (int i = 0; i < sub - dot; i++) {
                                                    sb.append('0');
                                                }
                                            }
                                            else {
                                                sb.append("-0.");

                                                for (int i = 0; i < sub - dot + 1; i++) {
                                                    sb.append('0');
                                                }
                                            }
                                            if(e-dot<3) {
                                                if(db<0)
                                                {
                                                    display = "= " + sb.toString() + s.substring(1, dot) + s.substring(dot + 1, e);
                                                }
                                                else {
                                                    display = "= " + sb.toString() + s.substring(0, dot) + s.substring(dot + 1, e);
                                                }
                                            }
                                            else
                                            {
                                                if (db<0) {
                                                    display = "= " + sb.toString() + s.substring(1, dot) + s.substring(dot + 1, dot + 3);
                                                }else {
                                                    display = "= " + sb.toString() + s.substring(0, dot) + s.substring(dot + 1, dot + 3);
                                                }
                                            }
                                            sb.delete(0,sb.length());
                                        }
                                        else
                                        {
                                            display = "= " + String.valueOf((float)db);
                                        }

                                    }
                                }
                            }else
                            {
                                s=String.valueOf(db);
                                if (s.charAt(s.length()-2)=='E')
                                {
                                    display ="= "+String.valueOf(db);
                                }
                                else {

                                    display ="= "+s.substring(0,7)+s.substring(s.length()-3);
                                }
                            }
                            break;
                    }
                }
                else
                {
                    d=d*10;
                    switch (v.getId()) {

                        case R.id.btn0:

                            if (last == '÷') {
                                return;
                            }
                            else {
                                c = c * d + 0;
                                c=c/d;
                                display = display + "0";

                            }
                            break;
                        case R.id.btn1:
                            c = c * d + 1;
                            c=c/d;
                            display = display + "1";
                            break;
                        case R.id.btn2:
                            c = c * d + 2;
                            c=c/d;
                            display = display + "2";
                            break;
                        case R.id.btn3:
                            c = c * d + 3;
                            c=c/d;
                            display = display + "3";
                            break;
                        case R.id.btn4:
                            c = c * d + 4;
                            c=c/d;
                            display = display + "4";
                            break;
                        case R.id.btn5:
                            c = c * d + 5;
                            c=c/d;
                            display = display + "5";
                            break;
                        case R.id.btn6:
                            c = c * d + 6;
                            c=c/d;
                            display = display + "6";
                            break;
                        case R.id.btn7:
                            c = c * d + 7;
                            c=c/d;
                            display = display + "7";
                            break;
                        case R.id.btn8:
                            c = c * d + 8;
                            c=c/d;
                            display = display + "8";
                            break;
                        case R.id.btn9:
                            c = c * d + 9;
                            c=c/d;
                            display = display + "9";
                            break;
                        case R.id.btnc:
                            c = 0;
                            display = "";
                            d=1;
                            flagbtn=0;
                            ai.clear();
                            ac.clear();
                            flageq = 0;
                            tvres.setText("0");
                            return;
                        case R.id.btneq:
                            //doing calculations and then printing result
                            if(last=='*'||last=='+'||last=='-'||last=='÷')
                            {
                                return;
                            }
                            flageq = 1;
                            flagbtn=0;
                            d=1;
                            if (flagminus==0) {
                                ai.add(c);
                            }
                            else
                            {
                                c=c*(-1);
                                ai.add(c);
                                flagminus=0;
                            }
                            c = 0;
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '÷') {
                                    ai.set(i, (ai.get(i) / ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);
                                } else {
                                    i++;
                                }
                            }
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '*') {
                                    ai.set(i, (ai.get(i) * ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }

                            }

                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '-') {
                                    ai.set(i, (ai.get(i) - ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }

                            }
                            for (int i = 0; i < ac.size(); ) {
                                if (ac.get(i) == '+') {

                                    ai.set(i, (ai.get(i) + ai.get(i + 1)));
                                    ai.remove(i + 1);
                                    ac.remove(i);

                                } else {
                                    i++;
                                }
                            }
                            double db = ai.get(0);
                            if (db == 0) {
                                tvres.setText("= 0");
                                display = "";
                                return;

                            }
                            else if(db<9999999999999.0)
                            {

                                s=String.valueOf(db);
                                if (db % 1 == 0) {
                                    long fn = (long) db;
                                    display = "= "+String.valueOf(fn);
                                }
                                else {
                                    if(db>0.0009999999999 || db< -0.0009999999999) {
                                        float f = (float) (db);
                                        display = "= " + String.valueOf(f);
                                    }
                                    else
                                    {
                                        for(int i=0;i<s.length();i++)
                                        {
                                            if (s.charAt(i)=='.')
                                            {
                                                dot=i;
                                            }
                                            else if (s.charAt(i)=='E')
                                            {
                                                e=i;
                                            }

                                        }
                                        sub=Integer.valueOf(String.valueOf(s.charAt(s.length()-1)));
                                        if(sub<=7) {
                                            StringBuilder sb = new StringBuilder();

                                            if (db > 0) {
                                                sb.append("0.");
                                                for (int i = 0; i < sub - dot; i++) {
                                                    sb.append('0');
                                                }
                                            } else {
                                                sb.append("-0.");

                                                for (int i = 0; i < sub - dot+1; i++) {
                                                    sb.append('0');
                                                }
                                            }
                                            if(e-dot<3) {
                                                if(db<0)
                                                {
                                                    display = "= " + sb.toString() + s.substring(1, dot) + s.substring(dot + 1, e);
                                                }
                                                else {
                                                    display = "= " + sb.toString() + s.substring(0, dot) + s.substring(dot + 1, e);
                                                }
                                            }
                                            else
                                            {
                                                if (db<0) {
                                                    display = "= " + sb.toString() + s.substring(1, dot) + s.substring(dot + 1, dot + 3);
                                                }else {
                                                    display = "= " + sb.toString() + s.substring(0, dot) + s.substring(dot + 1, dot + 3);
                                                }
                                            }
                                            sb.delete(0, sb.length());
                                        }
                                        else
                                        {
                                            display = "= " + String.valueOf((float)db);
                                        }

                                    }
                                }
                            }
                            else
                            {
                                s=String.valueOf(db);
                                if (s.charAt(s.length()-2)=='E')
                                {
                                    display ="= "+String.valueOf(db);
                                }
                                else {

                                    display ="= "+s.substring(0,7)+s.substring(s.length()-3);
                                }
                            }


                            break;
                    }

                }

            }
            else {

                switch (v.getId()) {

                    case R.id.btn0:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        if (last == '÷') {
                            return;
                        }
                        else {
                            c = c * 10 + 0;
                            display = display + "0";
                        }
                        break;
                    case R.id.btn1:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 1;
                        display = display + "1";
                        break;
                    case R.id.btn2:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 2;
                        display = display + "2";
                        break;
                    case R.id.btn3:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 3;
                        display = display + "3";
                        break;
                    case R.id.btn4:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 4;
                        display = display + "4";
                        break;
                    case R.id.btn5:
                        flageq = 0;

                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 5;
                        display = display + "5";
                        break;
                    case R.id.btn6:
                        flageq = 0;
                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 6;
                        display = display + "6";
                        break;
                    case R.id.btn7:
                        flageq = 0;

                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 7;
                        display = display + "7";
                        break;
                    case R.id.btn8:
                        flageq = 0;

                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 8;
                        display = display + "8";
                        break;
                    case R.id.btn9:
                        flageq = 0;

                        ai.clear();
                        ac.clear();
                        display = "";
                        c = c * 10 + 9;
                        display = display + "9";
                        break;
                    case R.id.btnc:
                        c = 0;
                        display = "";
                        ai.clear();
                        ac.clear();
                        flageq = 0;
                        tvres.setText("0");
                        return;
                    case R.id.btneq:
                        if(last=='*'||last=='+'||last=='-'||last=='÷')
                        {
                            return;
                        }
                        flageq=1;
                        break;
                }

            }
            if (display.length() > 15) {
                display = display.substring(1);
            }
            tvres.setText(display);


        } catch(NumberFormatException | ArithmeticException e){
            Toast.makeText(this, "Wrong input or operation", Toast.LENGTH_SHORT).show();
        }
    }
}
