
package projet301;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewResultsController implements Initializable {
@FXML
private TableView<Student> classtable; 
@FXML 
private TableColumn <Student,Integer> id;
@FXML 
private TableColumn <Student,String> firstname;
@FXML 
private TableColumn <Student,String> lastname;
@FXML 
private TableColumn <Student,String> username;
@FXML 
private TableColumn <Student,String> GradeColumn;
@FXML 
private TableColumn <Student,String> QuizColumn;
@FXML 
private TextField searchmyClass;
@FXML
private Button SearchBtn;

private Teacher t;

ObservableList<Student> mystudents=FXCollections.observableArrayList();

    public void setTeacher(Teacher t) throws SQLException
    {
        this.t=t;
        TeacherModel M=new TeacherModel();
        mystudents=M.ViewResults(t);
        classtable.setItems(mystudents);
        
    }
    @FXML
    public void Search(ActionEvent e) throws SQLException
    {
        String s=searchmyClass.getText();
        TeacherModel T=new TeacherModel();
        mystudents.clear();
        mystudents.addAll(T.SearchResults(s,t));
        classtable.setItems(mystudents);
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     classtable.setItems(mystudents);
     id.setCellValueFactory(new PropertyValueFactory<Student,Integer>("ID"));
     firstname.setCellValueFactory(new PropertyValueFactory<Student,String>("firstname"));
     lastname.setCellValueFactory(new PropertyValueFactory<Student,String>("lastname"));
     username.setCellValueFactory(new PropertyValueFactory<Student,String>("username"));
     QuizColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("quiz"));
     GradeColumn.setCellValueFactory(new PropertyValueFactory<Student,String>("grades"));
   

        
      
    }    
    
}
