import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SchoolApp extends Application {

    private SchoolDataAccess dbAccess;
    private ListView teacherListView;
    private ObservableList<Teacher> dataTeacher;
    private TextField teacherIDTxt;
    private TextField teacherNameTxt;
    private TextField teacherSurnameTxt;
    private TextField teacherEmailTxt;
    private ListView teachesClassesListView;
    private int teacherIDForClasses;

    @Override
    public void init() {
        try {
            dbAccess = new SchoolDataAccess();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void stop() {
        try {
            dbAccess.closeDb();
        }
        catch (Exception e) {

            displayException(e);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("School Statistics of Teachers");


        Label teacherLbl = new Label("Teachers");
        teacherListView = new ListView<>();
        teacherListView.setPrefHeight(175);
        teacherListView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListenerTeacher());
        dataTeacher = getDbDataTeachers();
        teacherListView.setItems(dataTeacher);
        VBox vBoxList = new VBox(teacherLbl, teacherListView);
        vBoxList.setSpacing(5);
        vBoxList.setPadding(new Insets(20, 20, 20, 20));

        Label thisTeacherLbl = new Label("this teacher");
        Label teacherIDLbl = new Label("ID");
        teacherIDTxt = new TextField();
        HBox hBoxTeacherID = new HBox(teacherIDLbl, teacherIDTxt);
        hBoxTeacherID.setPadding(new Insets(20,0,0,0));
        hBoxTeacherID.setSpacing(62);

        Label teacherNameLbl = new Label("Name");
        teacherNameTxt = new TextField();
        HBox hBoxTeacherName = new HBox(teacherNameLbl, teacherNameTxt);
        hBoxTeacherName.setPadding(new Insets(20,0,0,0));
        hBoxTeacherName.setSpacing(42);

        Label teacherSurnameLbl = new Label("surname");
        teacherSurnameTxt = new TextField();
        HBox hBoxTeacherSurname = new HBox(teacherSurnameLbl, teacherSurnameTxt);
        hBoxTeacherSurname.setPadding(new Insets(20,0,0,0));
        hBoxTeacherSurname.setSpacing(29);

        Label teacherEmailLbl = new Label("eMail");
        teacherEmailTxt = new TextField();
        HBox hBoxTeacherEmail = new HBox(teacherEmailLbl, teacherEmailTxt);
        hBoxTeacherEmail.setPadding(new Insets(20,0,0,0));
        hBoxTeacherEmail.setSpacing(45);

        VBox vBoxTextfields = new VBox(thisTeacherLbl, hBoxTeacherID, hBoxTeacherName,
                hBoxTeacherSurname, hBoxTeacherEmail);
        vBoxTextfields.setPadding(new Insets(20, 20, 20, 20));

        Label teachesClassesLbl = new Label("teaches this classes");
        teachesClassesListView = new ListView();
        teachesClassesListView.setPrefHeight(175);
        VBox vBoxTeachesList = new VBox(teachesClassesLbl, teachesClassesListView);
        vBoxTeachesList.setSpacing(5);
        vBoxTeachesList.setPadding(new Insets(20, 20, 20, 20));

        HBox hBox = new HBox(vBoxList, vBoxTextfields,vBoxTeachesList);
        hBox.setPadding(new Insets(10, 20,80,20));

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
    private class ListSelectChangeListenerTeacher implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= dataTeacher.size())) {
                return; // invalid data
            }

            // set fields for the selected teacher (ID, name, surname, eMail
            // set ID for reference to the classes in classesListView, and set classesListView
            Teacher teacher = dataTeacher.get(new_val.intValue());
            teacherIDTxt.setText(Integer.toString(teacher.getTeacherID()));
            teacherNameTxt.setText(teacher.getTeacherName());
            teacherSurnameTxt.setText(teacher.getTeacherSurname());
            teacherEmailTxt.setText(teacher.getTeacherEmail());
            teacherIDForClasses = teacher.getTeacherID();
            teachesClassesListView.setItems(getDbDataClasses(teacherIDForClasses));
        }
    }

    private ObservableList<Teacher> getDbDataTeachers() {

        List<Teacher> list = null;
        try {
            list = dbAccess.getAllRowsTeacher();
        }
        catch (Exception e) {

            displayException(e);
        }
        ObservableList<Teacher> dbDataTeacher = FXCollections.observableList(list);
        return dbDataTeacher;
    }

    private ObservableList<SchoolClass> getDbDataClasses(int teacherID) {

        List<SchoolClass> list = null;
        try {
            list = dbAccess.getAllRowsClass(teacherID);
            System.out.println(list);
        }
        catch (Exception e) {

            displayException(e);
        }
        ObservableList<SchoolClass> dbDataClass = FXCollections.observableList(list);
        return dbDataClass;
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
