package com.patrykmandrak123gmail.enigmacipher.EnigmaCipher;

public class EnigmaRottorLogic {

    private char letter;

    EnigmaRottorLogic() {
        this.letter = 'A';
    }

    public void moveUp() {
        letter += 1;
        if(letter > 90)
            letter = 65;

    }

    public void moveDown() {
        letter -= 1;
        if(letter < 65)
            letter = 90;
    }

    public char getLetter() {
        return letter;
    }
    public void setLetter(char newLetter) { this.letter = newLetter;}
}
