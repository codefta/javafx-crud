package mahasiswa.model;

public class SimpleMahasiswa {

    private int id;
    private String nama;
    private String gender;
    private float ipk;

    public SimpleMahasiswa(int id, String nama, String gender, float ipk) {
        this.id = id;
        this.nama = nama;
        this.gender = gender;
        this.ipk = ipk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getIpk() {
        return ipk;
    }

    public void setIpk(float ipk) {
        this.ipk = ipk;
    }
}
