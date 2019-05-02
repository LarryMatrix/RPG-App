package tz.co.matrixhub.systems.rpg;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView generatedTxt;
    private Button generateBtn;
    private Button ungenerateBtn;
    private Button copyButton;
    private String saltStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generatedTxt = (TextView) findViewById(R.id.generatedTxt);
        generateBtn = (Button) findViewById(R.id.generateBtn);
        copyButton = (Button) findViewById(R.id.copyBtn);
        ungenerateBtn = (Button) findViewById(R.id.unGenerateBtn);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatedTxt.setVisibility(View.VISIBLE);
                copyButton.setVisibility(View.VISIBLE);
                ungenerateBtn.setVisibility(View.VISIBLE);
                generatedTxt.setText(getSaltString());
            }
        });

        ungenerateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatedTxt.setVisibility(View.INVISIBLE);
                copyButton.setVisibility(View.INVISIBLE);
                generatedTxt.setVisibility(View.INVISIBLE);
                ungenerateBtn.setVisibility(View.INVISIBLE);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Text Copied Successfully", saltStr);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getApplicationContext(), "Copied Successful!", Toast.LENGTH_LONG).show();
            }
        });

    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890=!?@*&";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 16) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        saltStr = salt.toString();
        return saltStr;

    }

}
