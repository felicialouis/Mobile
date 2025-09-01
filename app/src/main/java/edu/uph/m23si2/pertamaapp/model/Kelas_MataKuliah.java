package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kelas_MataKuliah extends RealmObject {
    @PrimaryKey
    private int kelas_mataKuliahID;
    private String namaKelas;
    private MataKuliah mataKuliah;
    private String dosenPengampu;

    public Kelas_MataKuliah(){};

    public Kelas_MataKuliah(int kelas_mataKuliahID, String namaKelas, MataKuliah mataKuliah, String dosenPengampu) {
        this.kelas_mataKuliahID = kelas_mataKuliahID;
        this.namaKelas = namaKelas;
        this.mataKuliah = mataKuliah;
        this.dosenPengampu = dosenPengampu;
    }

    public int getKelas_mataKuliahID() {
        return kelas_mataKuliahID;
    }

    public void setKelas_mataKuliahID(int kelas_mataKuliahID) {
        this.kelas_mataKuliahID = kelas_mataKuliahID;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public String getDosenPengampu() {
        return dosenPengampu;
    }

    public void setDosenPengampu(String dosenPengampu) {
        this.dosenPengampu = dosenPengampu;
    }
}
