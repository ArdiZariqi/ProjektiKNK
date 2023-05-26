package Repository.Interfaces;
import Models.AbsenceData;
import javafx.scene.chart.XYChart;

import java.sql.SQLException;

public interface TeacherUserInterface {
        int getTotalStudentsAbsenceCount() throws SQLException;
        int getTotalFemaleAbsenceCount() throws SQLException;
        int getTotalMaleAbsenceCount() throws SQLException;
        XYChart.Series<String, Integer> getTotalAbsenceChartData() throws SQLException;
        XYChart.Series<String, Integer> getFemaleAbsenceChartData() throws SQLException;
        XYChart.Series<String, Integer> getMaleAbsenceChartData() throws SQLException;
        void AbsencesAdd(AbsenceData absenceData) throws SQLException;
        void addAbsencesUpdate(AbsenceData absenceData) throws SQLException;
        void addAbsencesDelete(AbsenceData absenceData) throws SQLException;
}