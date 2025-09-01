package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KRS extends RealmObject {
    @PrimaryKey
    private int KRSID;
    private String semester;
    private String tahunAkademik;
    private Mahasiswa mahasiswa;

    public KRS (){};

    public KRS(int KRSID, String semester, String tahunAkademik, Mahasiswa mahasiswa) {
        this.KRSID = KRSID;
        this.semester = semester;
        this.tahunAkademik = tahunAkademik;
        this.mahasiswa = mahasiswa;
    }

    public int getKRSID() {
        return KRSID;
    }

    public void setKRSID(int KRSID) {
        this.KRSID = KRSID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        this.tahunAkademik = tahunAkademik;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }
}
