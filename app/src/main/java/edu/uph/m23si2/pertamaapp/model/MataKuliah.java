package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MataKuliah extends RealmObject {
    @PrimaryKey
    private int mataKuliahID;
    private String nama;
    private int sks;
    private Prodi prodi;

    public MataKuliah(){};

    public MataKuliah(int mataKuliahID, String nama, int sks, Prodi prodi) {
        this.mataKuliahID = mataKuliahID;
        this.nama = nama;
        this.sks = sks;
        this.prodi = prodi;
    }

    public int getMataKuliahID() {
        return mataKuliahID;
    }

    public void setMataKuliahID(int mataKuliahID) {
        this.mataKuliahID = mataKuliahID;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public Prodi getProdi() {
        return prodi;
    }

    public void setProdi(Prodi prodi) {
        this.prodi = prodi;
    }
}
