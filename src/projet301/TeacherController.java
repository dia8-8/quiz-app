
package projet301;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TeacherController implements Initializable {
@FXML
private Label welcomeMsg;
private Teacher t;
public void setText(String username) throws SQLException
{
    TeacherModel M=new TeacherModel();
    this.t=M.getTeacher(username);
    welcomeMsg.setText("Welcome "+t.getFirstname());
}

@FXML
private void SignOut(MouseEvent e) throws IOException
{
     Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign In");
            stage.show();
             stage=(Stage) welcomeMsg.getScene().getWindow();
            stage.close();
}

@FXML
private void AddQuiz(MouseEvent e) throws IOException, SQLException
{
    FXMLLoader loader=new FXMLLoader(getClass().getResource("quizTitle.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            QuizTitleController T=loader.getController();
            T.setTeacher(t);
            stage.setTitle("Add Quiz");
            stage.show(); 
}

@FXML
private void AddStudent(MouseEvent e) throws IOException, SQLException
{
   FXMLLoader loader=new FXMLLoader(getClass().getResource("AddStudent.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            AddStudentController T=loader.getController();
            T.setTeacher(t);
            stage.setTitle("Add Students");
            stage.show(); 
}
@FXML
private void ViewClass (MouseEvent e) throws IOException, SQLException
{
     FXMLLoader loader=new FXMLLoader(getClass().getResource("Class.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            ClassController T=loader.getController();
            T.setTeacher(t);
            stage.setTitle("Class");
            stage.show(); 
           
}
@FXML
private void resultsView (MouseEvent e) throws IOException, SQLException
{
     FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewResults.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            ViewResultsController T=loader.getController();
            T.setTeacher(t);
            stage.setTitle("Results");
            stage.show(); 
           
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       }    
    
}
