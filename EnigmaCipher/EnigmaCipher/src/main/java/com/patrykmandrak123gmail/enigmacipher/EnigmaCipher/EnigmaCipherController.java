package com.patrykmandrak123gmail.enigmacipher.EnigmaCipher;

class EnigmaCipherController
{
    private String[] firstRotor;
    private String[] firstCable;

    private String[] secondRotor;
    private String[] secondCable;

    private String[] thirdRotor;
    private String[] thirdCable;

    private static final String[] reflecorInCable  = split("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    private static final String[] reflecorOutCable = split("YRUHQSLDPXNGOKMIEBFZCWVJAT");

    EnigmaCipherController(String key)
    {
        char[] keyCharArray = key.toUpperCase().toCharArray();

        this.firstRotor = split("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.firstCable = split("EKMFLGDQVZNTOWYHXUSPAIBRCJ");

        this.secondRotor = split("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.secondCable = split("AJDKSIRUXBLHWTMCQGZNPYFVOE");

        this.thirdRotor = split("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        this.thirdCable = split("BDFHJLCPRTXVZNYEIWGAKMUSQO");

        setAllRotors(keyCharArray);
    }

    private static String[] split(String text) {
        String[] array = new String[text.length()];
        char[] chars = text.toCharArray();
        for (int i = 0 ; i < text.length(); ++i) {
          array[i] = String.valueOf(chars[i]);
        }
        return array;
    }

    private void moveRotorByOne(String[] rotor)
    {
        String temporarySave = rotor[0];
        for(int i=0; i<rotor.length; i++)
        {
            if(i+1<rotor.length)
            {
                rotor[i] = rotor[i+1];
            }
        }
        rotor[rotor.length-1] = temporarySave;
    }

    private void moveFirstRotor()
    {
        moveRotorByOne(this.firstRotor);
        moveRotorByOne(this.firstCable);
    }
    private void moveSecondRotor()
    {
        moveRotorByOne(this.secondRotor);
        moveRotorByOne(this.secondCable);
    }
    private void moveThirdRotor()
    {
        moveRotorByOne(this.thirdRotor);
        moveRotorByOne(this.thirdCable);
    }

    private void setRotor(char keyChar, String rotorNameString)
    {
        int numberOfMoves = keyChar - 65;
        for(int i=0; i<numberOfMoves; i++)
        {
            switch(rotorNameString)
            {
                case "First":
                    moveFirstRotor();
                    break;
                case "Second":
                    moveSecondRotor();
                    break;
                case "Third":
                    moveThirdRotor();
                    break;
            }
        }
    }

    private void setAllRotors(char[] keyCharArray)
    {
        setRotor(keyCharArray[0], "First");
        setRotor(keyCharArray[1], "Second");
        setRotor(keyCharArray[2], "Third");
    }

    private String getSecondRotorFirstIndex()
    {
        return secondRotor[0];
    }
    private String getThirdRotorFirstIndex()
    {
        return thirdRotor[0];
    }

    private static int getIndexOf(String[] stringArray, String toFind)
    {
        for(int i=0; i<stringArray.length; i++)
        {
            if(stringArray[i].equals(toFind))
            {
                return i;
            }
        }
        return 404;
    }

    public void rotorMovementMechanism()
    {
        moveThirdRotor();

        if(getThirdRotorFirstIndex().equals("W"))
            moveSecondRotor();
        else if(getSecondRotorFirstIndex().equals("E"))
            moveSecondRotor();

        if(getSecondRotorFirstIndex().equals("F"))
            moveFirstRotor();
    }

    public void abc()
    {
        moveThirdRotor();

        if(getThirdRotorFirstIndex().equals("W"))
            moveSecondRotor();
        else if(getSecondRotorFirstIndex().equals("E"))
            moveSecondRotor();

        if(getSecondRotorFirstIndex().equals("F"))
            moveFirstRotor();
    }

    public String getEncryptedText(String text)
    {
        char[] textArray = text.toUpperCase().toCharArray();
        String currentLetter;
        int moveBy;
        int index;
        String encryptedText = "";


        for(int i = 0; i<textArray.length; i++)
        {
            abc();
            moveBy = textArray[i] - 65;
            currentLetter = this.thirdCable[moveBy]; // thirdRotor --> ThirdCable

            index = getIndexOf(this.thirdRotor, currentLetter);
            currentLetter = this.secondCable[index]; // secondRotor --> secondCable

            index = getIndexOf(this.secondRotor, currentLetter);
            currentLetter = this.firstCable[index]; // firstRotor --> firstCable

            index = getIndexOf(this.firstRotor, currentLetter);
            currentLetter = EnigmaCipherController.reflecorInCable[index]; // reflectorIn

            index = getIndexOf(this.reflecorInCable, currentLetter);
            currentLetter = EnigmaCipherController.reflecorOutCable[index]; // reflectorOut

            index = getIndexOf(this.reflecorInCable, currentLetter);
            currentLetter = this.firstRotor[index]; // firstCable

            index = getIndexOf(this.firstCable, currentLetter);
            currentLetter = this.firstRotor[index]; // firstRotor

            index = getIndexOf(this.firstRotor, currentLetter);
            currentLetter = this.secondRotor[index]; // secondCable

            index = getIndexOf(this.secondCable, currentLetter);
            currentLetter = this.secondRotor[index]; // secondRotor

            index = getIndexOf(this.secondRotor, currentLetter);
            currentLetter = this.thirdRotor[index]; // thirdCable

            index = getIndexOf(this.thirdCable, currentLetter);
            currentLetter = this.thirdRotor[index]; // ThirdRotor

            index = getIndexOf(this.thirdRotor, currentLetter);
            currentLetter = EnigmaCipherController.reflecorInCable[index]; // Default Alphabet Index Output

            encryptedText += currentLetter;
        }
        return encryptedText;
    }
}