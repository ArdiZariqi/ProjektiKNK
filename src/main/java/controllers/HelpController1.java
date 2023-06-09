package controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.LanguageUtil;


public class HelpController1 {
    @FXML
    private Button closeButton;

    @FXML
    private ComboBox<String> languageID;
    @FXML
    private Label helpn11;
    @FXML
    private Label helpn12;
    @FXML
    private TextArea helpn13;

    @FXML
    private void initialize() {
        closeButton.setOnAction(event -> closeHelpWindow());

        languageID.setItems(FXCollections.observableArrayList("English", "Shqip"));
        languageID.setValue("English");
        languageID.setOnAction(e -> {
            setLanguage();
        });

        setLanguage();
    }

    private void closeHelpWindow(){
        try {

            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/Main/adminDashboard.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.show();

            closeButton.getScene().getWindow().hide();


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLanguage() {
        String selectedLanguage = languageID.getValue();
        LanguageUtil.setLanguage(selectedLanguage);
        helpn11.setText(LanguageUtil.getMessage("helpn11"));
        helpn12.setText(LanguageUtil.getMessage("helpn12"));
        helpn13.setText(LanguageUtil.getMessage("helpn13"));
        closeButton.setText(LanguageUtil.getMessage("closeButton2"));
    }



}
