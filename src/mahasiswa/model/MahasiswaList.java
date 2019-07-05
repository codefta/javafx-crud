package mahasiswa.model;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MahasiswaList {
    private ObservableList<Mahasiswa> list;
    private String extFile;

    public MahasiswaList() {
        list = FXCollections.observableArrayList();
        extFile = "mahasiswa.xml";
    }

    public ObservableList<Mahasiswa> get() {
        return list;
    }

    public void setFromArray(SimpleMahasiswa[] array) {
        list = FXCollections.observableArrayList();

        for(SimpleMahasiswa sm : array) {
            list.add(new Mahasiswa(sm.getId(), sm.getNama(), sm.getGender(), sm.getIpk()));
        }
    }

    public void loadXMLFile() {
        try {
            XStream xs = new XStream(new StaxDriver());
            FileInputStream in = new FileInputStream(extFile);
            String s = "";
            int c = in.read();
            while (c != -1) {
                s += (char) c;
                c = in.read();
            }

            SimpleMahasiswa[] array = (SimpleMahasiswa[]) xs.fromXML(s);
            this.setFromArray(array);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SimpleMahasiswa[] getArray() {
        SimpleMahasiswa[] array = new SimpleMahasiswa[list.size()];
        for(int i = 0; i < list.size(); i++) {
            int idMahasiswa = list.get(i).getIdMahasiswa();
            String namaMahasiswa = list.get(i).getNamaMahasiswa();
            String genderMahasiswa = list.get(i).getGenderMahasiswa();
            float ipkMahasiswa = list.get(i).getIpkMahasiswa();
            array[i] = new SimpleMahasiswa(idMahasiswa, namaMahasiswa, genderMahasiswa, ipkMahasiswa);
        }

        return array;
    }

    public void saveXMLFile() {
        SimpleMahasiswa[] array = this.getArray();
        XStream xs = new XStream(new StaxDriver());
        String xml = xs.toXML(array);

        try{
            FileOutputStream output = new FileOutputStream(extFile);
            byte[] bytes = xml.getBytes();
            output.write(bytes);
            output.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
