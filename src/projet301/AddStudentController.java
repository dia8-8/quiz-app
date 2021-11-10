
 
package projet301;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;




public class AddStudentController implements Initializable {
    
    @FXML
    private ListView<String> ListStudent;
    @FXML
    private Button Search;
    @FXML
    private TextField usernameSearch;
    private Teacher t;
   
    
    ObservableList<String> students=FXCollections.observableArrayList();
    
    
    public void setTeacher(Teacher t) throws SQLException
    {
        this.t=t;
        TeacherModel T=new TeacherModel();
        students=T.Display();
        ListStudent.setItems(students);
    }
    @FXML
    private void Search(ActionEvent e) throws SQLException
    {
        String s=usernameSearch.getText();
        TeacherModel T=new TeacherModel();
        students.clear();
        ListStudent.getItems().addAll(T.SearchStudent(s,t));
    }
    @FXML 
    private void AddStudent(ActionEvent e) throws SQLException
    {
        String name;
        name=ListStudent.getSelectionModel().getSelectedItem().substring(0,ListStudent.getSelectionModel().getSelectedItem().indexOf(':'));
        TeacherModel M=new TeacherModel();
        Student s=M.AddStudent(name, t);
        students=M.Display();
          ListStudent.setItems(students);
        t.AddStudent(s);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ListStudent.setItems(students);
               
              // ListStudent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
               
    }    
    
}
