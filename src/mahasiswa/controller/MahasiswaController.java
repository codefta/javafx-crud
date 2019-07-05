package mahasiswa.controller;

import javafx.util.converter.FloatStringConverter;
import mahasiswa.Main;
import mahasiswa.model.Mahasiswa;
import mahasiswa.model.MahasiswaList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MahasiswaController implements Initializable {
    private MahasiswaList mahasiswaList;
    private Main mainApp;

    // For Table View
    @FXML
    private TableView<Mahasiswa> mahasiswaTable;

    @FXML
    private TableColumn<Mahasiswa, Integer> idMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> namaMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> genderMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, Float> ipkMahasiswa;

    // For Add Mahasiswa
    @FXML
    private TextField nama;

    @FXML
    private TextField gender;

    @FXML
    private TextField ipk;

    // For Visible Add Form
    @FXML
    private Pane formAdd;


    @Override
    public void initialize(URL uri, ResourceBundle rb) {
        // Get data from XML;
        mahasiswaList = new MahasiswaList();
        mahasiswaList.loadXMLFile();
        mahasiswaTable.setItems(mahasiswaList.get());

        // Set add form not visible
        formAdd.setVisible(false);
        idMahasiswa.setCellValueFactory(cellData -> cellData.getValue().idMahasiswaProperty().asObject());
        namaMahasiswa.setCellValueFactory(cellData -> cellData.getValue().namaMahasiswaProperty());
        genderMahasiswa.setCellValueFactory(cellData -> cellData.getValue().genderMahasiswaProperty());
        ipkMahasiswa.setCellValueFactory(cellData -> cellData.getValue().ipkMahasiswaProperty().asObject());


        // Edit Section
        mahasiswaTable.setEditable(true);
        namaMahasiswa.setCellFactory(TextFieldTableCell.forTableColumn());
        genderMahasiswa.setCellFactory(TextFieldTableCell.forTableColumn());
        ipkMahasiswa.setCellFactory(TextFieldTableCell.forTableColumn( new FloatStringConverter()));
    }

    // Add Button
    @FXML
    public void addButton(ActionEvent event) {
        nama.setText("");
        gender.setText("");
        ipk.setText("");
        formAdd.setVisible(true);
    }

    // Cancel Button on form
    @FXML
    public void cancelButton(ActionEvent event) {
        formAdd.setVisible(false);
    }

    @FXML
    public void handleCreate() {
        Mahasiswa mhs = new Mahasiswa();

        mhs.setNamaMahasiswa(nama.getText());
        mhs.setGenderMahasiswa(gender.getText());
        mhs.setIpkMahasiswa(Float.parseFloat(ipk.getText()));

        mahasiswaTable.getItems().add(mhs);
        mahasiswaList.saveXMLFile();
        formAdd.setVisible(false);
    }

    @FXML
    public void handleDelete() {
        int pilihan = mahasiswaTable.getSelectionModel().getSelectedIndex();

        if(pilihan >= 0) {
            mahasiswaTable.getItems().remove(pilihan);
        }

        mahasiswaList.saveXMLFile();
    }

    // Edit section
    @FXML
    public void editNamaMahasiswa(TableColumn.CellEditEvent<Mahasiswa, String> mhsEditEvent) {
        Mahasiswa mhs = mahasiswaTable.getSelectionModel().getSelectedItem();
        mhs.setNamaMahasiswa(mhsEditEvent.getNewValue());
        mahasiswaList.saveXMLFile();
    }

    @FXML
    public void editGenderMahasiswa(TableColumn.CellEditEvent<Mahasiswa, String> mhsEditEvent) {
        Mahasiswa mhs = mahasiswaTable.getSelectionModel().getSelectedItem();
        mhs.setGenderMahasiswa(mhsEditEvent.getNewValue());
        mahasiswaList.saveXMLFile();
    }

    @FXML
    public void editIpkMahasiswa(TableColumn.CellEditEvent<Mahasiswa, Float> mhsEditEvent) {
        Mahasiswa mhs = mahasiswaTable.getSelectionModel().getSelectedItem();
        mhs.setIpkMahasiswa(mhsEditEvent.getNewValue());
        mahasiswaList.saveXMLFile();
    }
}