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
import java.util.Arrays;
import java.util.ResourceBundle;

public class StatistikController implements Initializable {
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Pane paneViews;
    @FXML
    private Label modus;
    @FXML
    private Label mean;

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
        int rangeFive = 0;
        int rangeSix = 0;
        for(Mahasiswa mhs : mahasiswa.get()) {
            String nama = mhs.getNamaMahasiswa();
            Float ipk = mhs.getIpkMahasiswa();
            if( ipk >= 1.0 && ipk <= 1.5 ) {
                rangeOne++;
            } else if(ipk >= 1.6 && ipk <= 2.0) {
                rangeTwo++;
            } else if( ipk >= 2.1 && ipk <= 2.5 ) {
                rangeThree++;
            } else if( ipk >= 2.6 && ipk <= 3.0) {
                rangeFour++;
            } else if( ipk >= 3.1 && ipk <= 3.5) {
                rangeFive++;
            } else if(ipk >= 3.6 && ipk <= 4.0) {
                rangeSix++;
            }
        }

        xAxis.setLabel("Range Nilai");

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("IPK");

        series.getData().add(new XYChart.Data("1.0 - 1.5", rangeOne));
        series.getData().add(new XYChart.Data("1.6 - 2.0", rangeTwo));
        series.getData().add(new XYChart.Data("2.1 - 2.5", rangeThree));
        series.getData().add(new XYChart.Data("2.6 - 3.0", rangeFour));
        series.getData().add(new XYChart.Data("3.1 - 3.5", rangeFive));
        series.getData().add(new XYChart.Data("3.6 - 4.0", rangeSix));

        barChart.getData().add(series);

        float nilaiTengah1 = (1.0f + 1.5f) / 2;
        float nilaiTengah2 = (1.6f + 2.0f) / 2;
        float nilaiTengah3 = (2.1f + 2.5f) / 2;
        float nilaiTengah4 = (2.6f + 3.0f) / 2;
        float nilaiTengah5 = (3.1f + 3.5f) / 2;
        float nilaiTengah6 = (3.6f + 4.0f) / 2;

        float meanTot = ((rangeOne * nilaiTengah1) + (rangeTwo * nilaiTengah2) + (rangeThree * nilaiTengah3) + (rangeFour * nilaiTengah4) + (rangeFive *nilaiTengah5) + (rangeSix * nilaiTengah6)) / (rangeOne + rangeTwo + rangeThree + rangeFour + rangeFive + rangeSix);
        mean.setText(Float.toString(meanTot));

        int[] range = {rangeOne, rangeTwo, rangeThree, rangeFour, rangeFive, rangeSix};

        Arrays.sort(range);
        int max = range[range.length - 1];
        int highFrekuensi = 0;
        float selisihFreAtas = 0;
        float selisihFreBawah = 0;
        if (max == rangeOne) {
            highFrekuensi = rangeOne;
            selisihFreAtas = rangeOne - 0;
            selisihFreBawah = rangeOne - rangeTwo;
        } else if (max == rangeTwo) {
            highFrekuensi = rangeTwo;
            selisihFreBawah = rangeTwo - rangeThree;
            selisihFreAtas = rangeTwo - rangeOne;
        } else if (max == rangeThree) {
            highFrekuensi = rangeThree;
            selisihFreBawah = rangeThree - rangeFour;
            selisihFreAtas = rangeThree - rangeTwo;
        } else if (max == rangeFour) {
            highFrekuensi = rangeFour;
            selisihFreBawah = rangeFour - rangeFive;
            selisihFreAtas = rangeFour - rangeThree;
        } else if (max == rangeFive) {
            highFrekuensi = rangeFive;
            selisihFreBawah = rangeFive - rangeSix;
            selisihFreAtas = rangeFive - rangeFour;
        } else if (max == rangeSix) {
            highFrekuensi = rangeSix;
            selisihFreBawah = rangeSix - 0;
            selisihFreAtas = rangeSix - rangeFive;
        }

        float batasBawah = (float) highFrekuensi - 0.5f;
        int interval = 5;

        float modusTot = batasBawah + (selisihFreAtas / (selisihFreAtas + selisihFreBawah)) * interval;

        modus.setText(Float.toString(modusTot));

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
