
package projet301;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class StudentController implements Initializable {
@FXML
private Label welcomeMsg;
private Student s;

@FXML
public void WelcomeStudent (String username) throws SQLException
{
     StudentModel M=new StudentModel();
    this.s=M.getStudent(username);
    welcomeMsg.setText("Welcome "+s.getFirstname());
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
private void ViewAssignements(MouseEvent e) throws IOException, SQLException
{
      FXMLLoader loader=new FXMLLoader(getClass().getResource("assignements.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            AssignementsController T=loader.getController();
            T.setStudent(s);
            stage.setTitle("What to Do");
            stage.show(); 
}






    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
