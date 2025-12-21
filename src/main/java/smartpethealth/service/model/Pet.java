package smartpethealth.service.model;

public class Pet {

    private int id;
    private String nama;
    private String jenis;
    private String umur;
    private String pemilik;

    public Pet(int id, String nama, String jenis, String umur, String pemilik) {
        validateString(nama, jenis, umur, pemilik);
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.umur = umur;
        this.pemilik = pemilik;
    }

    private static void validateString(String nama, String jenis, String umur, String pemilik) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        if (jenis == null || jenis.trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis tidak boleh kosong");
        }
        if (umur == null || umur.trim().isEmpty()) {
            throw new IllegalArgumentException("Umur tidak boleh kosong");
        }
        if (pemilik == null || pemilik.trim().isEmpty()) {
            throw new IllegalArgumentException("Pemilik tidak boleh kosong");
        }
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


    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        if (jenis == null || jenis.trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis tidak boleh kosong");
        }
        this.jenis = jenis;
    }

    public void setUmur(String umur) {
        if (umur == null || umur.trim().isEmpty()) {
            throw new IllegalArgumentException("Umur tidak boleh kosong");
        }
        this.umur = umur;
    }

    public void setPemilik(String pemilik) {
        if (pemilik == null || pemilik.trim().isEmpty()) {
            throw new IllegalArgumentException("Pemilik tidak boleh kosong");
        }
        this.pemilik = pemilik;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", jenis='" + jenis + '\'' +
                ", umur='" + umur + '\'' +
                ", pemilik='" + pemilik + '\'' +
                '}';
    }
}