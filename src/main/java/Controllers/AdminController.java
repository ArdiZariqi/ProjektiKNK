package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.Text;
import service.ConnectionUtil;
import service.LanguageUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;


public class AdminController implements Initializable {

    @FXML
    private Button helpButton;

    @FXML
    private Button addStudents_addBtn;
    @FXML
    private DatePicker addStudents_birth;

    @FXML
    private Button addStudents_btn;

    @FXML
    private Button addStudents_clearBtn;

    @FXML
    private TableColumn<studentData, String> addStudents_col_birth;

    @FXML
    private TableColumn<studentData, String> addStudents_col_course;

    @FXML
    private TableColumn<studentData, String> addStudents_col_firstName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_gender;

    @FXML
    private TableColumn<studentData, String> addStudents_col_lastName;

    @FXML
    private TableColumn<studentData, String> addStudents_col_status;

    @FXML
    private TableColumn<studentData, String> addStudents_col_studentNum;

    @FXML
    private TableColumn<studentData, String> addStudents_col_year;

    @FXML
    private ComboBox<?> addStudents_course;

    @FXML
    private Button addStudents_deleteBtn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private ImageView addStudents_imageView;

    @FXML
    private Button addStudents_insertBtn;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private TextField addStudents_search;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private TextField addStudents_studentNum;

    @FXML
    private TableView<studentData> addStudents_tableView;

    @FXML
    private Button addStudents_updateBtn;

    @FXML
    private ComboBox<?> addStudents_year;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;
    @FXML
    private Label studentLabel;

    @FXML
    private Label home_totalEnrolled;

    @FXML
    private BarChart<?, ?> home_totalEnrolledChart;

    @FXML
    private Label home_totalFemale;

    @FXML
    private AreaChart<?, ?> home_totalFemaleChart;

    @FXML
    private Label home_totalMale;

    @FXML
    private LineChart<?, ?> home_totalMaleChart;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button studentAbstence_btn;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_course;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_final;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_firstSem;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_secondSem;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_studentNum;

    @FXML
    private TableColumn<studentData, String> studentAbstence_col_year;

    @FXML
    private AnchorPane studentAbstence_form;

    @FXML
    private TextField studentAbstence_search;

    @FXML
    private TableView<studentData> studentAbstence_tableView;

    @FXML
    private Label username;
    @FXML
    private ComboBox<String> languageID;
    @FXML
    private Label signout;
    @FXML
    private Label home_totalEnrolledLabel;
    @FXML
    private Label home_totalFemaleEnrolled;
    @FXML
    private Label home_totalMaleEnrolled;
    @FXML
    private Label yearLabel;
    @FXML
    private Label courseLabel;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private Label birthDateLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField scheduleLabel;
    @FXML
    private TextField dayLabel;
    @FXML
    private TextField timeLabel;
    @FXML
    private TextField courseLabel1;
    @FXML
    private Button scheduleAdd;
    @FXML
    private Button scheduleUpdate;
    @FXML
    private Button scheduleClear;
    @FXML
    private Button scheduleDelete;
    @FXML
    private TableColumn<scheduleData, String> scheduleId;
    @FXML
    private TableColumn<scheduleData, String> scheduleDay;
    @FXML
    private TableColumn<scheduleData, String> scheduleTime;
    @FXML
    private TableColumn<scheduleData, String> scheduleCourse1;
    @FXML
    private TableView<scheduleData> scheduleTableView;
    @FXML
    private AnchorPane studentSchedule_form;
    @FXML
    private Button studentSchedule_btn;



    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private Image image;


    public void homeDisplayTotalEnrolledStudents() {

        String sql = "SELECT COUNT(id) FROM student";

        connect = ConnectionUtil.getConnection();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("COUNT(id)");
            }

            home_totalEnrolled.setText(String.valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM student WHERE gender = 'Female' and status = 'Enrolled'";

        connect = ConnectionUtil.getConnection();

        try {
            int countFemale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(id)");
            }

            home_totalFemale.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayMaleEnrolled() {

        String sql = "SELECT COUNT(id) FROM student WHERE gender = 'Male' and status = 'Enrolled'";

        connect = ConnectionUtil.getConnection();
        try {
            int countMale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(id)");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalEnrolledChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = ConnectionUtil.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalEnrolledChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayFemaleEnrolledChart() {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = ConnectionUtil.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalFemaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayEnrolledMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM student WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date ORDER BY TIMESTAMP(date) ASC LIMIT 5";

        connect = ConnectionUtil.getConnection();

        try {
            XYChart.Series chart = new XYChart.Series();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
            }

            home_totalMaleChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] yearList = {"First Year", "Second Year", "Third Year", "Fourth Year"};

    public void addStudentsYearList() {

        List<String> yearL = new ArrayList<>();

        for (String data : yearList) {
            yearL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(yearL);
        addStudents_year.setItems(ObList);
    }

    private String[] genderList = {"Male", "Female", "Others"};

    public void addStudentsGenderList() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(genderL);
        addStudents_gender.setItems(ObList);
    }

    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};

    public void addStudentsStatusList() {
        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(statusL);
        addStudents_status.setItems(ObList);
    }

    public void addStudentsCourseList() {

        String listCourse = "SELECT * FROM course";

        connect = ConnectionUtil.getConnection();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addStudents_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsInsertImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            image = new Image(file.toURI().toString(), 120, 149, false, true);
            addStudents_imageView.setImage(image);

            getData.path = file.getAbsolutePath();

        }
    }

    public void addStudentsAdd() {

        String insertData = "INSERT INTO student "
                + "(studentNum,year,course,firstName,lastName,gender,birth,status,image,date) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;

            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST
                String checkData = "SELECT studentNum FROM student WHERE studentNum = '"
                        + addStudents_studentNum.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #" + addStudents_studentNum.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addStudents_studentNum.getText());
                    prepare.setString(2, (String) addStudents_year.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addStudents_course.getSelectionModel().getSelectedItem());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(addStudents_birth.getValue()));
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(9, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(10, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addStudentsShowListData();
                    // TO CLEAR THE FIELDS
                    addStudentsClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsClear() {
        addStudents_studentNum.setText("");
        addStudents_year.getSelectionModel().clearSelection();
        addStudents_course.getSelectionModel().clearSelection();
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        addStudents_birth.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_imageView.setImage(null);

        getData.path = "";
    }

    public void addStudentsUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE student SET "
                + "year = '" + addStudents_year.getSelectionModel().getSelectedItem()
                + "', course = '" + addStudents_course.getSelectionModel().getSelectedItem()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_lastName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', birth = '" + addStudents_birth.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', image = '" + uri + "' WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student #" + addStudents_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    addStudentsClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudentsDelete() {

        String deleteData = "DELETE FROM student WHERE studentNum = '"
                + addStudents_studentNum.getText() + "'";

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;
            if (addStudents_studentNum.getText().isEmpty()
                    || addStudents_year.getSelectionModel().getSelectedItem() == null
                    || addStudents_course.getSelectionModel().getSelectedItem() == null
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || addStudents_birth.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addStudents_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {




                        statement = connect.createStatement();
                        statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    addStudentsShowListData();
                    addStudentsClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<studentData> studentAbstencesListData() {

        ObservableList<studentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student_Abstence";

        connect = ConnectionUtil.getConnection();
        try {
            studentData studentD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getDouble("first_sem"),
                        result.getDouble("second_sem"),
                        result.getDouble("final"));

                listData.add(studentD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<studentData> studentAbstencesList;

    public ObservableList<studentData> addStudentsListData() {

        ObservableList<studentData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        connect = ConnectionUtil.getConnection();

        try {
            studentData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new studentData(result.getInt("studentNum"),
                        result.getString("year"),
                        result.getString("course"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("birth"),
                        result.getString("status"),
                        result.getString("image"));

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }



    public void addStudentsSearch() {

        FilteredList<studentData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addStudents_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudentNum().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getYear().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<studentData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);
    }

    private ObservableList<studentData> addStudentsListD;

    public void addStudentsShowListData() {
        addStudentsListD = addStudentsListData();

        addStudents_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("studentNum"));
        addStudents_col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        addStudents_col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        addStudents_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addStudents_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addStudents_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addStudents_col_birth.setCellValueFactory(new PropertyValueFactory<>("birth"));
        addStudents_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    public void studentScheduleAdd() {

        String insertData = "INSERT INTO schedule (schedule_id, day, time, course) VALUES(?,?,?,?)";

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;

            if (scheduleLabel.getText().isEmpty()
                    || dayLabel.getText().isEmpty()
                    || timeLabel.getText().isEmpty()
                    || courseLabel1.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String checkData = "SELECT schedule_id FROM schedule WHERE schedule_id = '"
                        + scheduleLabel.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Schedule: " + scheduleLabel.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, scheduleLabel.getText());
                    prepare.setString(2, dayLabel.getText());
                    prepare.setString(3, timeLabel.getText());
                    prepare.setString(4, courseLabel1.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();


                    availableScheduleShowListData();
                    studentScheduleClear();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void studentScheduleUpdate() {

        String updateData = "UPDATE schedule_id SET description = '"
                + scheduleLabel.getText() + "', degree = '"
                + dayLabel.getText() + "' WHERE course = '"
                + timeLabel.getText() + "'"
                + courseLabel1.getText();

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;

            if (scheduleLabel.getText().isEmpty()
                    || dayLabel.getText().isEmpty()
                    || timeLabel.getText().isEmpty()
                    || courseLabel1.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Schedule: " + scheduleLabel.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();


                    availableScheduleShowListData();
                    studentScheduleClear();

                } else {
                    return;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void studentScheduleDelete() {

        String deleteData = "DELETE FROM schedule_id WHERE  schedule_id = '"
                + scheduleLabel.getText() + "'";

        connect = ConnectionUtil.getConnection();

        try {
            Alert alert;

            if (scheduleLabel.getText().isEmpty()
                    || dayLabel.getText().isEmpty()
                    || timeLabel.getText().isEmpty()
                    || courseLabel1.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Schedule: " + scheduleLabel.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();


                    availableScheduleShowListData();
                    studentScheduleClear();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void studentScheduleClear() {
        scheduleLabel.setText("");
        dayLabel.setText("");
        timeLabel.setText("");
        courseLabel1.setText("");
    }

    public ObservableList<scheduleData> availableScheduleListData() {

        ObservableList<scheduleData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM schedule";

        connect = ConnectionUtil.getConnection();

        try {
            scheduleData scheduleD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                scheduleD = new scheduleData(result.getString("schedule_id"),
                        result.getString("day"),
                        result.getString("time"),
                        result.getString("course"));

                listData.add(scheduleD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<scheduleData> availableScheduleList;

    public void availableScheduleShowListData() {
        availableScheduleList = availableScheduleListData();

        scheduleId.setCellValueFactory(new PropertyValueFactory<>("schedule_id"));
        scheduleDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        scheduleTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        scheduleCourse1.setCellValueFactory(new PropertyValueFactory<>("course"));

        scheduleTableView.setItems(availableScheduleList);

    }

    public void availableScheduleSelect() {
        scheduleData scheduleD = scheduleTableView.getSelectionModel().getSelectedItem();
        int num = scheduleTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        scheduleLabel.setText(scheduleD.getScheduleId());
        dayLabel.setText(scheduleD.getScheduleDay());
        timeLabel.setText(scheduleD.getScheduleTime());
        courseLabel.setText(scheduleD.getScheduleCourse());

    }

    public void displayUsername() {
        username.setText(Data.username);
    }
//koment
    @FXML
    public void openHelp(ActionEvent event){
        try {
            if (event.getSource() == helpButton ){
                Parent root = null;
                root = FXMLLoader.load(getClass().getResource("/KNK_Projekti/helpn1.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.show();

                helpButton.getScene().getWindow().hide();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            studentSchedule_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            studentSchedule_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalEnrolledStudents();
            homeDisplayMaleEnrolled();
            homeDisplayFemaleEnrolled();
            homeDisplayEnrolledMaleChart();
            homeDisplayFemaleEnrolledChart();
            homeDisplayTotalEnrolledChart();


        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            studentSchedule_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            studentSchedule_btn.setStyle("-fx-background-color:transparent");
            addStudentsShowListData();
            addStudentsYearList();
            addStudentsGenderList();
            addStudentsStatusList();
            addStudentsCourseList();
            addStudentsSearch();
//koment
        } else if (event.getSource() == studentAbstence_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentSchedule_form.setVisible(true);

            studentSchedule_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");
            availableScheduleShowListData();



        }
    }
    private double x=0;
    private double y=0;
    public void logout() {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {


                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addStudentsSelect() {

        studentData studentD = addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addStudents_studentNum.setText(String.valueOf(studentD.getStudentNum()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        addStudents_birth.setValue(LocalDate.parse(String.valueOf(studentD.getBirth())));

        String uri = "file:" + studentD.getImage();

        image = new Image(uri, 120, 149, false, true);
        addStudents_imageView.setImage(image);

        getData.path = studentD.getImage();
    }
    public void close(){
        System.exit(0);
    }
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){

        homeDisplayTotalEnrolledStudents();
        homeDisplayMaleEnrolled();
        homeDisplayFemaleEnrolled();
        homeDisplayEnrolledMaleChart();
        homeDisplayFemaleEnrolledChart();
        homeDisplayTotalEnrolledChart();

        addStudentsShowListData();
        addStudentsYearList();
        addStudentsGenderList();
        addStudentsStatusList();
        addStudentsCourseList();
        availableScheduleShowListData();

        languageID.setItems(FXCollections.observableArrayList("English", "Shqip"));
        languageID.setValue("English");
        languageID.setOnAction(e -> {
            setLanguage();
        });

        setLanguage();
    }

    public void setLanguage() {

        String selectedLanguage = languageID.getValue();
        LanguageUtil.setLanguage(selectedLanguage);

        home_btn.setText(LanguageUtil.getMessage("home.btn"));
        home_totalEnrolledLabel.setText(LanguageUtil.getMessage("home.totalEnrolled"));
        home_totalFemaleEnrolled.setText(LanguageUtil.getMessage("home.totalFemale"));
        home_totalMaleEnrolled.setText(LanguageUtil.getMessage("home.totalMale"));
        home_totalEnrolledChart.setTitle(LanguageUtil.getMessage("tot.enrolled.chart"));
        home_totalFemaleChart.setTitle(LanguageUtil.getMessage("tot.female.enrolled"));
        home_totalMaleChart.setTitle(LanguageUtil.getMessage("tot.male.enrolled"));
        addStudents_btn.setText(LanguageUtil.getMessage("addStudents.btn"));
        addStudents_addBtn.setText(LanguageUtil.getMessage("addStudents.addBtn"));
        addStudents_clearBtn.setText(LanguageUtil.getMessage("addStudents.clearBtn"));
        addStudents_deleteBtn.setText(LanguageUtil.getMessage("addStudents.deleteBtn"));
        addStudents_insertBtn.setText(LanguageUtil.getMessage("addStudents.insertBtn"));
        addStudents_updateBtn.setText(LanguageUtil.getMessage("addStudents.updateBtn"));
        addStudents_col_studentNum.setText(LanguageUtil.getMessage("student.id"));
        addStudents_col_year.setText(LanguageUtil.getMessage("studentAbstence.col_year"));
        addStudents_col_course.setText(LanguageUtil.getMessage("studentAbstence.col_course"));
        addStudents_col_firstName.setText(LanguageUtil.getMessage("student.first.name"));
        addStudents_col_lastName.setText(LanguageUtil.getMessage("student.last.name"));
        addStudents_col_gender.setText(LanguageUtil.getMessage("student.gender"));
        addStudents_col_birth.setText(LanguageUtil.getMessage("student.birth.date"));
        addStudents_col_status.setText(LanguageUtil.getMessage("student.status"));
        studentLabel.setText(LanguageUtil.getMessage("student.id"));
        yearLabel.setText(LanguageUtil.getMessage("studentAbstence.col_year"));
        courseLabel.setText(LanguageUtil.getMessage("studentAbstence.col_course"));
        fNameLabel.setText(LanguageUtil.getMessage("student.first.name"));
        lNameLabel.setText(LanguageUtil.getMessage("student.last.name"));
        genderLabel.setText(LanguageUtil.getMessage("student.gender"));
        birthDateLabel.setText(LanguageUtil.getMessage("student.birth.date"));
        statusLabel.setText(LanguageUtil.getMessage("student.status"));
        addStudents_search.setText(LanguageUtil.getMessage("search.student"));

        signout.setText(LanguageUtil.getMessage("signout"));
    }
}

//help button



