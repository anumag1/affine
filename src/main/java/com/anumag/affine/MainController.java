package com.anumag.affine;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class MainController {
    @FXML
    TextArea textInputField;
    @FXML
    TextArea firstKeyInputField;
    @FXML
    TextArea secondKeyInputField;
    @FXML
    TextArea outputField;

    public void encrypt() {
        Affine a = new Affine();
        outputField.setText(a.encrypt(textInputField.getText(), Integer.parseInt(firstKeyInputField.getText()), Integer.parseInt(secondKeyInputField.getText())));
    }

    public void decrypt() {
        Affine a = new Affine();
        outputField.setText(a.decrypt(textInputField.getText(), Integer.parseInt(firstKeyInputField.getText()), Integer.parseInt(secondKeyInputField.getText())));
    }

    public void bruteForce() {
        Affine a = new Affine();
        outputField.setText(a.bruteForce(textInputField.getText()));
    }

    public void analysis() {
        Affine a = new Affine();
        outputField.setText(a.frequencyAnalysis(textInputField.getText()));
    }
}