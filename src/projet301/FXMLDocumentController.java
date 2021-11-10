
package projet301;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javax.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

            
    @FXML
    private Button signInBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
     private Label errorMsg;
    
    
    @FXML
    private void SignIn(ActionEvent event) throws SQLException, IOException {
        
           RegisterModel M=new RegisterModel();
            errorMsg.setText(""); 
            String s=M.CheckCredentials(usernameField.getText().toString(), passwordField.getText().toString());
        
        if(s.equals(""))
        {
            errorMsg.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), new CornerRadii(5.0), new Insets(-5.0))));
            errorMsg.setText("Wrong username or password"); 
        }
    
        else  if(s.equals("Student"))
          {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("Student.fxml"));
           Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            StudentController S=loader.getController();
            String username= M.resultat.getString("username");
            S.WelcomeStudent(username);
            stage.setTitle("Home");
            stage.show(); 
            
         stage=(Stage) signInBtn.getScene().getWindow();
            stage.close();
              
          }
          
          
        else 
          {
              FXMLLoader loader=new FXMLLoader(getClass().getResource("Teacher.fxml"));
           Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            TeacherController T=loader.getController();
            String username= M.resultat.getString("username");
            T.setText(username);
            stage.setTitle("Home");
            stage.show(); 
           stage=(Stage) signInBtn.getScene().getWindow();
            stage.close();
              
          }
              
          
     }
    @FXML
   private void SignUp (ActionEvent event) throws Exception
    {
          Parent root=FXMLLoader.load(getClass().getResource("Registration.fxml"));
           Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Registration");
            stage.show();
             stage=(Stage) signUpBtn.getScene().getWindow();
            stage.close();
          
            
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
