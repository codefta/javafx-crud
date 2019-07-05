package mahasiswa.model;

import javafx.beans.property.*;

public class Mahasiswa {

    private IntegerProperty idMahasiswa;
    private StringProperty namaMahasiswa;
    private StringProperty genderMahasiswa;
    private FloatProperty ipkMahasiswa;

    public Mahasiswa() {
        this(0, "", "", 0);
    }

    public Mahasiswa(int idMahasiswa, String namaMahasiswa, String genderMahasiswa, float ipkMahasiswa) {
        this.idMahasiswa = new SimpleIntegerProperty( 17523 + (int) (Math.random() * 300) + 1);
        this.namaMahasiswa = new SimpleStringProperty(namaMahasiswa);
        this.genderMahasiswa = new SimpleStringProperty(genderMahasiswa);
        this.ipkMahasiswa = new SimpleFloatProperty(ipkMahasiswa);
    }

    public void setIdMahasiswa(Integer id) {
        this.idMahasiswa.set(id);
    }

    public Integer getIdMahasiswa() {
        return this.idMahasiswa.get();
    }

    public IntegerProperty idMahasiswaProperty() {
        return idMahasiswa;
    }

    public void setNamaMahasiswa(String nama) {
        this.namaMahasiswa.set(nama);
    }

    public String getNamaMahasiswa() {
        return this.namaMahasiswa.get();
    }

    public StringProperty namaMahasiswaProperty() {
        return namaMahasiswa;
    }

    public void setGenderMahasiswa(String  gender) {
        this.genderMahasiswa.set(gender);
    }

    public String getGenderMahasiswa() {
        return this.genderMahasiswa.get();
    }

    public StringProperty genderMahasiswaProperty() {
        return genderMahasiswa;
    }

    public void setIpkMahasiswa(Float ipk) {
        this.ipkMahasiswa.set(ipk);
    }

    public Float getIpkMahasiswa() {
        return this.ipkMahasiswa.get();
    }

    public FloatProperty ipkMahasiswaProperty() {
        return ipkMahasiswa;
    }
}
