package com.patrykmandrak123gmail.enigmacipher.EnigmaCipher;

public class EnigmaCipherInit
{
    public static String enigmaCipher(String key, String text)
    {
        EnigmaCipherController enigma = new EnigmaCipherController(key);

        String encryptedText = enigma.getEncryptedText(text);
        return encryptedText;
    }
}