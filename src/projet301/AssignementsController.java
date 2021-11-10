
package projet301;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class AssignementsController implements Initializable {

    @FXML
    private Button BackBtn;
          @FXML
    private Button openBtn;
    @FXML
    private ListView<Exam> list;
private Student s;
private ObservableList<Exam> exams=FXCollections.observableArrayList();
public void setStudent(Student s) throws SQLException
{
    this.s=s;
     StudentModel S=new StudentModel();
     exams=S.getExams(s);
    list.setItems(exams);
}
@FXML
public void Open(ActionEvent a) throws IOException
{
   Exam e=list.getSelectionModel().getSelectedItem();
    FXMLLoader loader=new FXMLLoader(getClass().getResource("Resolve.fxml"));
            Stage  stage=new Stage();
            stage.setScene(new Scene(loader.load()));
            ResolveController T=loader.getController();
            T.setExam(e,s);
            stage.setTitle("Quiz");
            stage.show(); 
          stage=(Stage) openBtn.getScene().getWindow();
          stage.close();
    
}
@FXML
public void back(ActionEvent e) throws IOException
{
           Stage stage=new Stage();
           stage=(Stage)BackBtn.getScene().getWindow();
           stage.close();
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       list.setOnMouseClicked(e->{
           openBtn.setDisable(false);
       });
      list.setItems(exams);
    }    
    
}
