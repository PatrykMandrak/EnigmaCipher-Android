package com.patrykmandrak123gmail.enigmacipher.EnigmaCipher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnigmaCipherActivity extends AppCompatActivity {
    Button clearButton;

    TextView outputMessage;
    EditText inputMessage;

    Button firstRottorUp;
    Button firstRottorDown;
    TextView firstRottorLetter;

    Button secondRottorUp;
    Button secondRottorDown;
    TextView secondRottorLetter;

    Button thirdRottorUp;
    Button thirdRottorDown;
    TextView thirdRottorLetter;

    EnigmaRottorLogic firstRottor;
    EnigmaRottorLogic secondRottor;
    EnigmaRottorLogic thirdRottor;

    String currentInputMessage ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enigma_cipher);

        clearButton = findViewById(R.id.clearButoon);

        outputMessage = findViewById(R.id.outputMessage);
        inputMessage = findViewById(R.id.inputMessage);


        firstRottorUp = findViewById(R.id.firstRottorUp);
        firstRottorDown = findViewById(R.id.firstRottorDown);
        firstRottorLetter = findViewById(R.id.firstRottorLetter);

        secondRottorUp = findViewById(R.id.secondRottorUp);
        secondRottorDown = findViewById(R.id.secondRottorDown);
        secondRottorLetter = findViewById(R.id.secondRottorLetter);

        thirdRottorUp = findViewById(R.id.thirdRottorUp);
        thirdRottorDown = findViewById(R.id.thirdRottorDown);
        thirdRottorLetter = findViewById(R.id.thirdRottorLetter);

        firstRottor = new EnigmaRottorLogic();
        secondRottor = new EnigmaRottorLogic();
        thirdRottor = new EnigmaRottorLogic();

        firstRottorLetter.setText(String.valueOf(firstRottor.getLetter()));
        secondRottorLetter.setText(String.valueOf(secondRottor.getLetter()));
        thirdRottorLetter.setText(String.valueOf(thirdRottor.getLetter()));


        inputMessage.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                currentInputMessage = inputMessage.getText().toString();
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

                if(s.length() != 0 || currentInputMessage.length() == 1){
                    if(inputMessage.length()>currentInputMessage.length()) {
                        String key = String.valueOf(firstRottor.getLetter())+
                                     String.valueOf(secondRottor.getLetter())+
                                     String.valueOf(thirdRottor.getLetter());
                        String text = String.valueOf(inputMessage.getText().charAt(inputMessage.length()-1));
                        String encryptedLetter = EnigmaCipherInit.enigmaCipher(key, text);
                        outputMessage.append(encryptedLetter);
                        rotorMovementMechanism();
                    }
                    else{
                        String text = outputMessage.getText().toString();
                        outputMessage.setText(text.substring(0, text.length() - 1));
                        reverseRotorMovement();
                    }
                }
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Toast toast = Toast.makeText(EnigmaCipherActivity.this,"Cleared", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                inputMessage.setText("");
                outputMessage.setText("");
                firstRottorLetter.setText("A");
                secondRottorLetter.setText("A");
                thirdRottorLetter.setText("A");
                firstRottor.setLetter('A');
                secondRottor.setLetter('A');
                thirdRottor.setLetter('A');
            }
        });


        firstRottorUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                firstRottor.moveUp();
                String letter = String.valueOf(firstRottor.getLetter());
                firstRottorLetter.setText(letter);
            }
        });

        firstRottorDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                firstRottor.moveDown();
                String letter = String.valueOf(firstRottor.getLetter());
                firstRottorLetter.setText(letter);
            }
        });

        secondRottorUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                secondRottor.moveUp();
                String letter = String.valueOf(secondRottor.getLetter());
                secondRottorLetter.setText(letter);
            }
        });

        secondRottorDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                secondRottor.moveDown();
                String letter = String.valueOf(secondRottor.getLetter());
                secondRottorLetter.setText(letter);
            }
        });

        thirdRottorUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                thirdRottor.moveUp();
                String letter = String.valueOf(thirdRottor.getLetter());
                thirdRottorLetter.setText(letter);
            }
        });

        thirdRottorDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                thirdRottor.moveDown();
                String letter = String.valueOf(thirdRottor.getLetter());
                thirdRottorLetter.setText(letter);
            }
        });




    }

    public void rotorMovementMechanism()
    {
        String letter;
        thirdRottor.moveUp();
        letter = String.valueOf(thirdRottor.getLetter());
        thirdRottorLetter.setText(letter);

        if(thirdRottor.getLetter()=='W') {
            secondRottor.moveUp();
            letter = String.valueOf(secondRottor.getLetter());
            secondRottorLetter.setText(letter);
        }
        else if(secondRottor.getLetter()=='E') {
            secondRottor.moveUp();
            letter = String.valueOf(secondRottor.getLetter());
            secondRottorLetter.setText(letter);

        }
        if(secondRottor.getLetter() =='F' && thirdRottor.getLetter()=='X') {
            firstRottor.moveUp();
            letter = String.valueOf(firstRottor.getLetter());
            firstRottorLetter.setText(letter);
        }
    }

    public void reverseRotorMovement(){
        String letter;
        thirdRottor.moveDown();
        letter = String.valueOf(thirdRottor.getLetter());
        thirdRottorLetter.setText(letter);

        if(thirdRottor.getLetter() == 'V') {
            secondRottor.moveDown();
            letter = String.valueOf(secondRottor.getLetter());
            secondRottorLetter.setText(letter);
        }
        else if(secondRottor.getLetter() == 'D') {
            secondRottor.moveDown();
        }

        if(secondRottor.getLetter() == 'E' && thirdRottor.getLetter()=='W') {
            firstRottor.moveDown();
            letter = String.valueOf(firstRottor.getLetter());
            firstRottorLetter.setText(letter);
        }
    }
}
