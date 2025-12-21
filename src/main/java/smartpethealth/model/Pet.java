package smartpethealth.model;

public class Pet {

    private int id;
    private String nama;
    private String jenis;
    private String umur;
    private String pemilik;

    public Pet(int id, String nama, String jenis, String umur, String pemilik) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.umur = umur;
        this.pemilik = pemilik;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String getUmur() {
        return umur;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }

}
