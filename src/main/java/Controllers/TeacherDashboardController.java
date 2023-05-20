package Controllers;
import Models.AbsenceData;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import DBconnection.connectDb;
import Models.courseData;
import Models.getData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;




public class TeacherDashboardController implements Initializable {

    @FXML
    private DatePicker Absence_date;

    @FXML
    private TextField Absence_search1;

    @FXML
    private Button addAbsence_addBtn;

    @FXML
    private ComboBox<?> addAbsence_class;

    @FXML
    private Button addAbsence_clearBtn;

    @FXML
    private TableColumn<?, ?> addAbsence_col_Course;

    @FXML
    private TableColumn<?, ?> addAbsence_col_Course1;

    @FXML
    private TableColumn<?, ?> addAbsence_col_absenceNum;

    @FXML
    private TableColumn<?, ?> addAbsence_col_class;

    @FXML
    private TableColumn<?, ?> addAbsence_col_class1;

    @FXML
    private TableColumn<?, ?> addAbsence_col_date;

    @FXML
    private TableColumn<?, ?> addAbsence_col_firstName;

    @FXML
    private TableColumn<?, ?> addAbsence_col_firstName1;

    @FXML
    private TableColumn<?, ?> addAbsence_col_gender;

    @FXML
    private TableColumn<?, ?> addAbsence_col_gender1;

    @FXML
    private TableColumn<?, ?> addAbsence_col_lastName;

    @FXML
    private TableColumn<?, ?> addAbsence_col_lastName1;

    @FXML
    private TableColumn<?, ?> addAbsence_col_reasonability;

    @FXML
    private TableColumn<?, ?> addAbsence_col_reasonable;

    @FXML
    private TableColumn<?, ?> addAbsence_col_status;

    @FXML
    private TableColumn<?, ?> addAbsence_col_stid;

    @FXML
    private TableColumn<?, ?> addAbsence_col_studentNum;

    @FXML
    private TableColumn<?, ?> addAbsence_col_time;

    @FXML
    private TableColumn<?, ?> addAbsence_col_total;

    @FXML
    private TableColumn<?, ?> addAbsence_col_unreasonable;

    @FXML
    private ComboBox<?> addAbsence_course;

    @FXML
    private Button addAbsence_deleteBtn;

    @FXML
    private TextField addAbsence_search;

    @FXML
    private TextField addAbsence_studentNum;

    @FXML
    private TextField addAbsence_time;

    @FXML
    private Button addAbsence_updateBtn;

    @FXML
    private ComboBox<?> addStudents_Absences;

    @FXML
    private Button addStudents_btn;

    @FXML
    private TextField addStudents_firstName;

    @FXML
    private AnchorPane addStudents_form;

    @FXML
    private ComboBox<?> addStudents_gender;

    @FXML
    private TextField addStudents_lastName;

    @FXML
    private ComboBox<?> addStudents_status;

    @FXML
    private TableView<AbsenceData> addStudents_tableView;

    @FXML
    private TableView<AbsenceData> addStudents_tableView1;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    private AnchorPane studentAbstence_form;

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
    private Label username;


    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;



    public void homeDisplayTotalStudentsAbsence() {

        String sql = "SELECT COUNT(a_id) FROM Absences";

        connect = connectDb.getConnection();

        int countEnrolled = 0;

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countEnrolled = result.getInt("COUNT(a_id)");
            }

            home_totalEnrolled.setText(String.valueOf(countEnrolled));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalFemaleAbsences() {

        String sql = "SELECT COUNT(a_id) FROM Absences WHERE gender = 'Female' and status = 'Enrolled'";

        connect = connectDb.getConnection();

        try {
            int countFemale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countFemale = result.getInt("COUNT(a_id)");
            }

            home_totalFemale.setText(String.valueOf(countFemale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalMaleAbsences() {

        String sql = "SELECT COUNT(a_id) FROM Absences WHERE gender = 'Male' and status = 'Enrolled'";

        connect = connectDb.getConnection();
        try {
            int countMale = 0;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                countMale = result.getInt("COUNT(a_id)");
            }
            home_totalMale.setText(String.valueOf(countMale));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void homeDisplayTotalAbsenceChart() {

        home_totalEnrolledChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

        connect = connectDb.getConnection();

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

    public void homeDisplayFemaleAbsenceChart() {

        home_totalFemaleChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' and gender = 'Female' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

        connect = connectDb.getConnection();

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

    public void homeDisplayAbsenceMaleChart() {

        home_totalMaleChart.getData().clear();

        String sql = "SELECT date_, COUNT(a_id) FROM Absences WHERE status = 'Enrolled' and gender = 'Male' GROUP BY date_ ORDER BY TIMESTAMP(date_) ASC LIMIT 5";

        connect = connectDb.getConnection();

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

    public void AbsencesAdd() {

        String insertData = "INSERT INTO Absences "
                + "(student_id,class_,course_id,time,firstName,lastName,gender,date_,status,reasonability) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = connectDb.getConnection();

        try {
            Alert alert;

            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                // CHECK IF THE STUDENTNUMBER IS ALREADY EXIST
                String checkData = "SELECT a_id FROM Absences WHERE a_id = '"
                        + addAbsence_studentNum.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Student #" + addAbsence_studentNum.getText() + " was already exist!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, addAbsence_studentNum.getText());
                    prepare.setString(2, (String) addAbsence_class.getSelectionModel().getSelectedItem());
                    prepare.setString(3, (String) addAbsence_course.getSelectionModel().getSelectedItem());
                    prepare.setString(3, addAbsence_time.getText());
                    prepare.setString(4, addStudents_firstName.getText());
                    prepare.setString(5, addStudents_lastName.getText());
                    prepare.setString(6, (String) addStudents_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(7, String.valueOf(Absence_date.getValue()));
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8, (String) addStudents_status.getSelectionModel().getSelectedItem());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");
                    prepare.setString(9, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(10, String.valueOf(sqlDate));

                    prepare.executeUpdate();


                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
                    addAbsencesClear();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAbsencesUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String updateData = "UPDATE Absences SET "
                + "' student_id = '" + addAbsence_studentNum.getText()
                + "', class_ = '" + addAbsence_class.getSelectionModel().getSelectedItem()
                + "', course_id = '" + addAbsence_course.getSelectionModel().getSelectedItem()
                + "', time = '" + addAbsence_time.getText()
                + "', firstName = '" + addStudents_firstName.getText()
                + "', lastName = '" + addStudents_firstName.getText()
                + "', gender = '" + addStudents_gender.getSelectionModel().getSelectedItem()
                + "', date_ = '" + Absence_date.getValue()
                + "', status = '" + addStudents_status.getSelectionModel().getSelectedItem()
                + "', reasonability = '" + addStudents_Absences.getSelectionModel().getSelectedItem()+"' where student_id '"
                + addAbsence_studentNum.getText() + "'";

        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Student Absences #" + addAbsence_studentNum.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateData);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
                    addAbsencesClear();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAbsencesDelete() {

        String deleteData = "DELETE FROM Absences WHERE student_id = '"
                + addAbsence_studentNum.getText() + "'";

        connect = connectDb.getConnection();

        try {
            Alert alert;
            if (addAbsence_studentNum.getText().isEmpty()
                    || addAbsence_class.getSelectionModel().getSelectedItem() == null
                    || addAbsence_course.getSelectionModel().getSelectedItem() == null
                    || addAbsence_time.getText().isEmpty()
                    || addStudents_firstName.getText().isEmpty()
                    || addStudents_lastName.getText().isEmpty()
                    || addStudents_gender.getSelectionModel().getSelectedItem() == null
                    || Absence_date.getValue() == null
                    || addStudents_status.getSelectionModel().getSelectedItem() == null
                    || addStudents_Absences.getSelectionModel().getSelectedItem() == null
                    || getData.path == null || getData.path == "") {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Student #" + addAbsence_studentNum.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {

                    statement = connect.createStatement();
                    statement.executeUpdate(deleteData);




                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE THE TABLEVIEW
                    addAbsencesShowListData();
                    // TO CLEAR THE FIELDS
                    addAbsencesClear();

                } else {
                    return;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addAbsencesClear() {
        addAbsence_studentNum.setText("");
        addAbsence_class.getSelectionModel().clearSelection();
        addAbsence_course.getSelectionModel().clearSelection();
        addAbsence_time.setText("");
        addStudents_firstName.setText("");
        addStudents_lastName.setText("");
        addStudents_gender.getSelectionModel().clearSelection();
        Absence_date.setValue(null);
        addStudents_status.getSelectionModel().clearSelection();
        addStudents_Absences.getSelectionModel().clearSelection();

        getData.path = "";
    }

    public void addAbsenceSearch() {

        FilteredList<AbsenceData> filter = new FilteredList<>(addStudentsListD, e -> true);

        addAbsence_search.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateStudentData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateStudentData.getStudent_id().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getClass_().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getCourse_id().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getTime().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getLastName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getDate_().toString().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateStudentData.getReasonability().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<AbsenceData> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(addStudents_tableView.comparatorProperty());
        addStudents_tableView.setItems(sortList);

    }

    public void addAbsencesCourseList() {

        String listCourse = "SELECT * FROM course";

        connect = connectDb.getConnection();

        try {

            ObservableList listC = FXCollections.observableArrayList();

            prepare = connect.prepareStatement(listCourse);
            result = prepare.executeQuery();

            while (result.next()) {
                listC.add(result.getString("course"));
            }
            addAbsence_course.setItems(listC);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   private String[]  classList ={ "Klasa 9","Klasa 10","Klasa 11","Klasa 12"};
    public void addStudentsClassList() {
        List<String> classL = new ArrayList<>();

        for (String data : classList) {
            classL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(classL);
        addStudents_gender.setItems(ObList);
    }



    private String[] genderList = {"Male", "Female"};

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
    private String[] reasonabilityList = {"Reasonable", "Unreasonable"};

    public void AbsenceList() {
        List<String> reasonabilityL = new ArrayList<>();

        for (String data : reasonabilityList) {
            reasonabilityL.add(data);
        }

        ObservableList ObList = FXCollections.observableArrayList(reasonabilityL);
        addStudents_Absences.setItems(ObList);
    }


    public ObservableList<AbsenceData> addAbsencesListData() {

        ObservableList<AbsenceData> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM Absences";

        connect = connectDb.getConnection();

        try {
            AbsenceData studentD;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                studentD = new AbsenceData(result.getInt("student_id"),
                        result.getString("class_"),
                        result.getInt("course_id"),
                        result.getTimestamp("time"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("date_"),
                        result.getString("status"),
                        result.getString("reasonability"));

                listStudents.add(studentD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    private ObservableList<AbsenceData> addStudentsListD;

    public void addAbsencesShowListData() {
        addStudentsListD = addAbsencesListData();

        addAbsence_col_studentNum.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        addAbsence_col_class.setCellValueFactory(new PropertyValueFactory<>("class_"));
        addAbsence_col_Course.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        addAbsence_col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        addAbsence_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        addAbsence_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addAbsence_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        addAbsence_col_date.setCellValueFactory(new PropertyValueFactory<>("date_"));
        addAbsence_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addAbsence_col_reasonability.setCellValueFactory(new PropertyValueFactory<>("reasonability"));

        addStudents_tableView.setItems(addStudentsListD);

    }

    public void addStudentsSelect() {

        AbsenceData studentD = (AbsenceData) addStudents_tableView.getSelectionModel().getSelectedItem();
        int num = addStudents_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addAbsence_studentNum.setText(String.valueOf(studentD.getStudent_id()));
        addStudents_firstName.setText(studentD.getFirstName());
        addStudents_lastName.setText(studentD.getLastName());
        Absence_date.setValue(LocalDate.parse(String.valueOf(studentD.getDate_())));

    }
















    private double x = 0;
    private double y = 0;

    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("AdminPortal.fxml"));
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

    public void displayUsername(){
        username.setText(getData.username);
    }
    // THATS IT FOR THESE VIDEOS, THANKS FOR WATCHING!! SUBSCRIBE AND TURN ON NOTIFICATION
//    TO NOTIF YOU FOR MORE UPCOMING VIDEOS THANKS FOR THE SUPPORT! : )
    public void defaultNav(){
        home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            addStudents_form.setVisible(false);
            studentAbstence_form.setVisible(false);


            home_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

            homeDisplayTotalStudentsAbsence();
            homeDisplayTotalFemaleAbsences();
            homeDisplayTotalMaleAbsences();
            homeDisplayTotalAbsenceChart();
            homeDisplayFemaleAbsenceChart();
            homeDisplayAbsenceMaleChart();

        } else if (event.getSource() == addStudents_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(true);
            studentAbstence_form.setVisible(false);

            addStudents_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            home_btn.setStyle("-fx-background-color:transparent");
            studentAbstence_btn.setStyle("-fx-background-color:transparent");

//            TO BECOME UPDATED ONCE YOU CLICK THE ADD STUDENTS BUTTON ON NAV
            addAbsencesShowListData();
            addAbsenceSearch();
            addStudentsGenderList();
            addStudentsStatusList();
            addAbsencesCourseList();
            addStudentsClassList();
            AbsenceList();

        } else if (event.getSource() == studentAbstence_btn) {
            home_form.setVisible(false);
            addStudents_form.setVisible(false);
            studentAbstence_form.setVisible(true);

            studentAbstence_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
            addStudents_btn.setStyle("-fx-background-color:transparent");
            home_btn.setStyle("-fx-background-color:transparent");


        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    // SORRY ABOUT THAT, I JUST NAMED THE DIFFERENT COMPONENTS WITH THE SAME NAME
    // MAKE SURE THAT THE NAME YOU GAVE TO THEM ARE DIFFERENT TO THE OTHER OKAY?
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        defaultNav();

        homeDisplayTotalStudentsAbsence();
        homeDisplayTotalFemaleAbsences();
        homeDisplayTotalMaleAbsences();
        homeDisplayTotalAbsenceChart();
        homeDisplayFemaleAbsenceChart();
        homeDisplayAbsenceMaleChart();

        // TO SHOW IMMIDIATELY WHEN WE PROCEED TO DASHBOARD APPLICATION FORM
        addAbsencesShowListData();
        addAbsenceSearch();
        addStudentsGenderList();
        addStudentsStatusList();
        addAbsencesCourseList();
        addStudentsClassList();
        AbsenceList();


    }

}