package com.example.calculatormdp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {
    private String workings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workings = "";
    }

    private void SetTextResults(String str) {
        TextView resultsTV = (TextView)findViewById(R.id.resultTextView);
        resultsTV.setText(str);
    }
    private void SetTextWorkings(String str) {
        TextView workingsTV = (TextView)findViewById(R.id.workingsTextView);
        workingsTV.setText(str);
    }

    private String GetTextResults() {
        TextView resultsTV = (TextView)findViewById(R.id.resultTextView);
        return resultsTV.getText().toString();
    }

    private void setWorkings(String givenValue) {
        workings += givenValue;
        SetTextWorkings(workings);
    }

    public void equalsOnClick(View view)
    {
        int count = 0;
        for(int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '(') {
                count++;
            } else if (workings.charAt(i) == ')') {
                count--;
            }
        }

        for (int i = 0; i < count; i++)
        {
            setWorkings(")");
        }


        Double result;
        Expression expression = new ExpressionBuilder(workings).build();
            try {

                result = expression.evaluate();
                SetTextResults(result.toString());

            } catch (Exception ex) {
                String exception = ex.toString();
                exception = exception.substring(exception.indexOf(":") + 1);
                Toast.makeText(MainActivity.this, exception, Toast.LENGTH_SHORT).show();
            }
    }

    private void ContinueCalculations() {
        if (!GetTextResults().equals("")) {
            workings = GetTextResults().trim();
            SetTextWorkings(workings);
            SetTextResults("");
        }
    }

    private void ContinueCalculationsNumber() {
        if (!GetTextResults().equals("")) {
            workings = "";
            SetTextWorkings(workings);
            SetTextResults("");
        }
    }

    private boolean isNumeric(char c)
    {
        return (c <= '9' && c >= '0') || c == '.';
    }


    public void clearOnClick(View view) {
        workings = "";
        SetTextWorkings(workings);
        SetTextResults("");
    }

    public void bracketsOnClick(View view) {
        ContinueCalculations();
        int count = 0;
        for(int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '(') {
                count++;
            } else if (workings.charAt(i) == ')') {
                count--;
            }
        }

        if(workings.isEmpty()) {
            setWorkings("(");
        } else if (!isNumeric(workings.charAt(workings.length() - 1)) && count == 0) {
            if (workings.charAt(workings.length() - 1) == ')') {
                setWorkings("*(");
            } else {
            setWorkings("(");
            }
        } else if (isNumeric(workings.charAt(workings.length() - 1)) && count == 0) {
            setWorkings("*(");
        } else if (workings.charAt(workings.length() - 1) == '(') {
            setWorkings("(");
        } else if (count != 0) {
            if (workings.charAt(workings.length() - 1) != ')' && !isNumeric(workings.charAt(workings.length() - 1))) {
                setWorkings("(");
            } else {
                setWorkings(")");
            }
        }
    }

    public void powerOfOnClick(View view) {
        ContinueCalculations();
        if(!workings.isEmpty()) {
            if (workings.charAt(workings.length() - 1) == '^' || workings.charAt(workings.length() - 1) == '(') {
                return;
            } else if (workings.charAt(workings.length() - 1) == '-' || workings.charAt(workings.length() - 1) == '+'
                    || workings.charAt(workings.length() - 1) == '*' || workings.charAt(workings.length() - 1) == '/') {
                workings = workings.substring(0, workings.length() - 1);
            }

            setWorkings("^");
        }

    }

    public void divisionOnClick(View view) {
        ContinueCalculations();
        if(!workings.isEmpty()) {
            if (workings.charAt(workings.length() - 1) == '/' || workings.charAt(workings.length() - 1) == '(') {
                return;
            } else if (workings.charAt(workings.length() - 1) == '-' || workings.charAt(workings.length() - 1) == '+'
                    || workings.charAt(workings.length() - 1) == '^' || workings.charAt(workings.length() - 1) == '*') {
                workings = workings.substring(0, workings.length() - 1);
            }

            setWorkings("/");
        }

    }

    private void CheckNumbers() {
        if(!workings.isEmpty()) {
            if (workings.charAt(workings.length() - 1) == ')') {
                setWorkings("*");
            }
        }
    }
    public void sevenOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        ContinueCalculations();
        if(!workings.isEmpty()) {
            if (workings.charAt(workings.length() - 1) == '*' || workings.charAt(workings.length() - 1) == '(') {
                return;
            } else if (workings.charAt(workings.length() - 1) == '-' || workings.charAt(workings.length() - 1) == '+'
                    || workings.charAt(workings.length() - 1) == '^' || workings.charAt(workings.length() - 1) == '/') {
                workings = workings.substring(0, workings.length() - 1);
            }

            setWorkings("*");
        }

    }

    public void DelOneCharOnClick(View view) {
        if (!workings.isEmpty()) {
            workings = workings.substring(0, workings.length() - 1);
            SetTextWorkings(workings);
            if (!GetTextResults().equals("")) {
                SetTextResults("");
            }
        }
    }

    public void fourOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        ContinueCalculations();
        if(!workings.isEmpty()) {

            if (workings.charAt(workings.length() - 1) == '-') {
                return;
            } else if (workings.charAt(workings.length() - 1) == '*' || workings.charAt(workings.length() - 1) == '+'
                    || workings.charAt(workings.length() - 1) == '^' || workings.charAt(workings.length() - 1) == '/') {
                workings = workings.substring(0, workings.length() - 1);
            }
        }

        setWorkings("-");
    }

    public void oneOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        ContinueCalculations();
        if(!workings.isEmpty()) {
            if (workings.charAt(workings.length() - 1) == '+' || workings.charAt(workings.length() - 1) == '(') {
                return;
            } else if (workings.charAt(workings.length() - 1) == '-' || workings.charAt(workings.length() - 1) == '/'
                    || workings.charAt(workings.length() - 1) == '^' || workings.charAt(workings.length() - 1) == '*') {
                workings = workings.substring(0, workings.length() - 1);
            }

            setWorkings("+");
        }
    }

    public void decimalOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        if(workings.isEmpty()) {
            setWorkings("0.");
        } else if(!isNumeric(workings.charAt(workings.length() - 1))) {
            setWorkings("0.");
        } else {
            setWorkings(".");
        }
    }

    public void zeroOnClick(View view) {
        ContinueCalculationsNumber();
        CheckNumbers();
        if(workings.isEmpty()) {
            setWorkings("0.");
        } else if(!isNumeric(workings.charAt(workings.length() - 1))) {
            setWorkings("0.");
        } else {
            setWorkings("0");
        }
    }
}