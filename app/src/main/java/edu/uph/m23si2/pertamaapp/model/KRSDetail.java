package edu.uph.m23si2.pertamaapp.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class KRSDetail extends RealmObject {
    @PrimaryKey
    private int KRSDetailID;
    private String status;
    private KRS KRS;
    private Kelas_MataKuliah kelas_mataKuliah;
    public KRSDetail(){};

    public KRSDetail(int KRSDetailID, String status, KRS KRS, Kelas_MataKuliah kelas_mataKuliah) {
        this.KRSDetailID = KRSDetailID;
        this.status = status;
        this.KRS = KRS;
        this.kelas_mataKuliah = kelas_mataKuliah;
    }

    public int getKRSDetailID() {
        return KRSDetailID;
    }

    public void setKRSDetailID(int KRSDetailID) {
        this.KRSDetailID = KRSDetailID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KRS getKRS() {
        return KRS;
    }

    public void setKRS(KRS KRS) {
        this.KRS = KRS;
    }

    public Kelas_MataKuliah getKelas_mataKuliah() {
        return kelas_mataKuliah;
    }

    public void setKelas_mataKuliah(Kelas_MataKuliah kelas_mataKuliah) {
        this.kelas_mataKuliah = kelas_mataKuliah;
    }
}
