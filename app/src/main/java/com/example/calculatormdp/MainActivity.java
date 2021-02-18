package com.example.calculatormdp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;



public class MainActivity extends AppCompatActivity {
    private String workings;
    private String formula;
    private String tempFormula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        workings = "";
        formula = "";
        tempFormula = "";
    }

    private void SetTextResults(String str)
    {
        TextView resultsTV = (TextView)findViewById(R.id.resultTextView);
        resultsTV.setText(str);
    }
    private void SetTextWorkings(String str)
    {
        TextView workingsTV = (TextView)findViewById(R.id.workingsTextView);
        workingsTV.setText(str);
    }
    private void setWorkings(String givenValue)
    {
        workings += givenValue;
        SetTextWorkings(workings);
    }


    public void equalsOnClick(View view)
    {

        /*Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPowerOf();

        try {
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if(result != null)
            SetTextResults(String.valueOf(result.doubleValue()));*/
        SetTextResults("DICK" + workings);

    }

    private void checkForPowerOf()
    {
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++)
        {
            if (workings.charAt(i) == '^')
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        for(int i = index + 1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
                numberRight += workings.charAt(i);
            else
                break;
        }

        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(workings.charAt(i)))
                numberLeft += workings.charAt(i);
            else
                break;
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        tempFormula = tempFormula.replace(original,changed);
    }

    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view)
    {
        workings = "";
        SetTextWorkings(workings);
        SetTextResults("");
    }

    public void bracketsOnClick(View view)//-------------------------------------------------------------------
    {
        if(workings.isEmpty())
        {
            setWorkings("(");
        }
        else if (workings.charAt(workings.length() - 1) == '('){
            return;
        }
        else if (isNumeric(workings.charAt(workings.length() - 1))) {
            setWorkings(")");
        }
    }

    public void powerOfOnClick(View view) {
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

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
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

    public void DelOneCharOnClick(View view)
    {
        if (!workings.isEmpty()) {
            workings = workings.substring(0, workings.length() - 1);
            SetTextWorkings(workings);
        }
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
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
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
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
        if(workings.isEmpty()) {
            setWorkings("0.");
        }
        else if(!isNumeric(workings.charAt(workings.length() - 1))) {
            setWorkings("0.");
        }
        else {
            setWorkings(".");
        }
    }

    public void zeroOnClick(View view) {
        if(workings.isEmpty()) {
            setWorkings("0.");
        }
        else if(!isNumeric(workings.charAt(workings.length() - 1))) {
            setWorkings("0.");
        }
        else {
            setWorkings("0");
        }
    }

}