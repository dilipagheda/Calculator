package dilip.com.calculator;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    //Variables to hold the operands and operation type

    private Double op2 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.btn0);
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        Button button4 = findViewById(R.id.btn4);
        Button button5 = findViewById(R.id.btn5);
        Button button6 = findViewById(R.id.btn6);
        Button button7 = findViewById(R.id.btn7);
        Button button8 = findViewById(R.id.btn8);
        Button button9 = findViewById(R.id.btn9);
        Button buttondot = findViewById(R.id.btndot);
        Button buttonequals = findViewById(R.id.btnequals);
        Button buttonaddition = findViewById(R.id.btnaddition);
        Button buttonmultiply = findViewById(R.id.btnmultiply);
        Button buttonsubstraction = findViewById(R.id.btnsubstraction);
        Button buttondivision = findViewById(R.id.btndivision);

        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);


        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String n = newNumber.getText().toString();
                if (n.length() > 0) {
                    try {
                        Double d = Double.valueOf(n);
                        performOperation(d, pendingOperation);
                    } catch (Exception e) {
                        newNumber.setText("");
                    }
                }

                pendingOperation = b.getText().toString();
                displayOperation.setText(pendingOperation);
            }
        };
        buttonequals.setOnClickListener(opListener);
        buttondivision.setOnClickListener(opListener);
        buttonmultiply.setOnClickListener(opListener);
        buttonaddition.setOnClickListener(opListener);
        buttonsubstraction.setOnClickListener(opListener);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("pendingOp",pendingOperation);
        if(op2!=null){
            outState.putDouble("op2",op2);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //restore the state

            op2 = savedInstanceState.getDouble("op2");
            pendingOperation = savedInstanceState.getString("pendingOp");
            displayOperation.setText(pendingOperation);
            result.setText(op2.toString());

    }

    private void performOperation(Double number, String operation) {
        if (op2 == null) {
            op2 = number;
        } else {

            switch (operation) {
                case "+":
                    op2 += number;
                    break;
                case "-":
                    op2 -= number;
                    break;
                case "*":
                    op2 *= number;
                    break;
                case "/":
                    if (number == 0.0) {
                        op2 = 0.0;
                    } else {
                        op2 /= number;
                    }

                    break;
                case "=":
                    op2 = number;
                    break;

            }

        }

        result.setText(op2.toString());
        newNumber.setText("");

    }
}
