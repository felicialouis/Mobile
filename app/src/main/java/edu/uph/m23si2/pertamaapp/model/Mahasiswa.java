package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Mahasiswa extends RealmObject {
    @PrimaryKey
    private int studentID;
    private String nama, jenisKelamin, hobi, email;
    private Prodi prodi;

    public Mahasiswa(){}

    public Mahasiswa(int studentID, String nama, String jenisKelamin, String hobi, String email, Prodi prodi) {
        this.studentID = studentID;
        this.nama = nama;
        this.jenisKelamin = jenisKelamin;
        this.hobi = hobi;
        this.email = email;
        this.prodi = prodi;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Prodi getProdi() {
        return prodi;
    }

    public void setProdi(Prodi prodi) {
        this.prodi = prodi;
    }

    @Override
    public String toString() {
        return "Mahasiswa{" +
                "Email='" + email + '\'' +
                ", studentID=" + studentID +
                ", Nama='" + nama + '\'' +
                ", JenisKelamin='" + jenisKelamin + '\'' +
                ", Hobi='" + hobi + '\'' +
                ", Prodi='" + prodi + '\'' +
                '}';
    }
}
