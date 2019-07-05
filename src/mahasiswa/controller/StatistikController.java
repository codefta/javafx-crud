package mahasiswa.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mahasiswa.model.Mahasiswa;
import mahasiswa.model.MahasiswaList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatistikController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Pane paneViews;
    @FXML
    private Label jumlah;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadStatistik();
    }

    public void loadStatistik() {
        MahasiswaList mahasiswa = new MahasiswaList();

        mahasiswa.loadXMLFile();
        int rangeOne = 0;
        int rangeTwo = 0;
        int rangeThree = 0;
        int rangeFour = 0;
        for(Mahasiswa mhs : mahasiswa.get()) {
            String nama = mhs.getNamaMahasiswa();
            Float ipk = mhs.getIpkMahasiswa();
            if( ipk >= 2.0 && ipk <= 2.5 ) {
                rangeOne++;
            } else if( ipk >= 2.6 && ipk <= 3.0) {
                rangeTwo++;
            } else if( ipk >= 3.1 && ipk <= 3.5) {
                rangeThree++;
            } else if(ipk >= 3.6 && ipk <= 4.0) {
                rangeFour++;
            }
        }

        xAxis.setLabel("Range Nilai");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("IPK");

        series.getData().add(new XYChart.Data("2.0 - 2.5", rangeOne));
        series.getData().add(new XYChart.Data("2.6 - 3.0", rangeTwo));
        series.getData().add(new XYChart.Data("3.1 - 3.5", rangeThree));
        series.getData().add(new XYChart.Data("3.6 - 4.0", rangeFour));

        barChart.getData().add(series);
    }

    //switch Scene

    @FXML
    public void changeToMahasiswa(ActionEvent event) throws IOException {
        Parent mahasiswaParent = FXMLLoader.load(getClass().getResource("../view/mahasiswa.fxml"));
        Scene mahasiswaScene = new Scene(mahasiswaParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mahasiswaScene);
        window.show();
    }
}
