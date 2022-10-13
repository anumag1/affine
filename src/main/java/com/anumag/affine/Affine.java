package com.anumag.affine;

import java.util.Locale;

import static java.lang.Character.isWhitespace;

public class Affine {
    private final String alphabet = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";

    int gcd(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcd(n2, n1 % n2);
    }

    int modInverse(int A, int M) {
        for (int X = 1; X < M; X++) {
            if (((A % M) * (X % M)) % M == 1) {
                return X;
            }
        }
        return 1;
    }

    public String encrypt(String text, int key1, int key2) { //Y= (aХ + b ) mod n
        StringBuilder encryptedText = new StringBuilder();

        text = text.toLowerCase(Locale.ROOT);
        int newLine = 0;

        if (gcd(key1, alphabet.length()) != 1) {
            return "НСД(a, n)≠1";
        } else {

            for (int i = 0; i < text.length(); i++) {
                newLine++;
                if (isWhitespace(text.charAt(i))) {
                    if (newLine >= 80 && newLine <= 95) {
                        encryptedText.append(" \n");
                        newLine = 0;
                    } else {
                        encryptedText.append(" ");
                    }
                }
                for (int j = 0; j < alphabet.length(); j++) {
                    if (text.charAt(i) == alphabet.charAt(j)) {
                        encryptedText.append((alphabet.charAt((key1 * j + key2) % alphabet.length())));
                    }
                }
            }
        }

        return encryptedText.toString();
    }

    public String decrypt(String text, int key1, int key2) { //X = key1-1(У – key2) mod n, key1-1=A
        StringBuilder decryptedText = new StringBuilder();

        text = text.toLowerCase(Locale.ROOT);
        int newLine = 0;

        for (int i = 0; i < text.length(); i++) {
            newLine++;
            if (isWhitespace(text.charAt(i))) {
                if (newLine >= 80 && newLine <= 95) {
                    decryptedText.append(" \n");
                    newLine = 0;
                } else {
                    decryptedText.append(" ");
                }
            }
            for (int j = 0; j < alphabet.length(); j++) {
                if (text.charAt(i) == alphabet.charAt(j)) {
                    decryptedText.append(alphabet.charAt((modInverse(key1, alphabet.length()) * (j + alphabet.length() - key2) % alphabet.length())));
                }
            }
        }
        return decryptedText.toString();
    }

    public String bruteForce(String text) {
        StringBuilder decryptedText = new StringBuilder();

        text = text.toLowerCase(Locale.ROOT);
        int newLine = 0;

        for (int key1 = 1; key1 <= alphabet.length(); key1++) {
            for (int key2 = 1; key2 <= alphabet.length(); key2++) {
                if (gcd(key1, key2) == 1) {
                    decryptedText.append("keys(").append(key1).append(", ").append(key2).append(") :: ");
                    for (int i = 0; i < text.length(); i++) {
                        newLine++;
                        if (isWhitespace(text.charAt(i))) {
                            if (newLine >= 80 && newLine <= 95) {
                                decryptedText.append(" \n");
                                newLine = 0;
                            } else {
                                decryptedText.append(" ");
                            }
                        }
                        for (int j = 0; j < alphabet.length(); j++) {
                            if (text.charAt(i) == alphabet.charAt(j)) {
                                decryptedText.append(alphabet.charAt((modInverse(key1, alphabet.length()) * (j + alphabet.length() - key2) % alphabet.length())));
                            }
                        }
                    }
                }
                if (gcd(key1, key2) == 1) {
                    newLine = 0;
                    decryptedText.append("\n\n");
                }
            }
        }

        return decryptedText.toString();
    }

    public String frequencyAnalysis(String text) {
        return null;
    }
}
