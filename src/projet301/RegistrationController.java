
package projet301;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class RegistrationController implements Initializable {

@FXML
private TextField firstname;
@FXML
private TextField lastname;
@FXML
private TextField newusername;
@FXML
private PasswordField newpassword;
@FXML
private PasswordField confirmpassword;
@FXML
private Label errorsignup;
@FXML
private Button createaccount;
@FXML
ToggleGroup group1;
@FXML
private Button BackBtn;
@FXML
private AnchorPane Signup;


@FXML
private void CreateAccount (ActionEvent event) throws SQLException, IOException
{
    String role="";
     errorsignup.setText("");
     try{
    RadioButton SelectedRadioBtn =(RadioButton) group1.getSelectedToggle();
        role=SelectedRadioBtn.getText();
        }catch(NullPointerException e)
        {
            e.printStackTrace();
            errorsignup.setText("Please choose your job");
        }
   
    if(firstname.getText().equals("") || lastname.getText().equals("") || newusername.getText().equals("") || newpassword.getText().equals("")||role.equals(""))
    {
         errorsignup.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), new CornerRadii(5.0), new Insets(-5.0))));
        errorsignup.setText("Please fill all required fields");
    }
    else if(!newpassword.getText().equals(confirmpassword.getText()))
    {
         errorsignup.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 80, 0.7), new CornerRadii(5.0), new Insets(-5.0))));
         errorsignup.setText("Please confirm your password!");
         confirmpassword.setText("");
    }
    else{
         RegisterModel M=new RegisterModel();
    if(M.AddAccount(firstname.getText(), lastname.getText(), newusername.getText(), newpassword.getText(),role))
    {
        Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Stage  stage=new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Sign in");
            stage.show(); 
           stage=(Stage) createaccount.getScene().getWindow();
            stage.close();
            
    }
    else
    {
        errorsignup.setText("Username already taken!");
    }  
    
    }
    
}
@FXML
private void GoBack(ActionEvent e) throws Exception{
    
    Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
     Stage stage=new Stage();
    stage.setScene(new Scene(root));
    stage.setTitle("Sign in");
    stage.show();
    stage=(Stage) BackBtn.getScene().getWindow();
    stage.close();
}




    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
