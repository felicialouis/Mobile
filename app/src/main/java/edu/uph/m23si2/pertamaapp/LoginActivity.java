package edu.uph.m23si2.pertamaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import edu.uph.m23si2.pertamaapp.api.ApiResponse;
import edu.uph.m23si2.pertamaapp.api.ApiService;
import edu.uph.m23si2.pertamaapp.model.KRS;
import edu.uph.m23si2.pertamaapp.model.KRSDetail;
import edu.uph.m23si2.pertamaapp.model.Kelas_MataKuliah;
import edu.uph.m23si2.pertamaapp.model.Kota;
import edu.uph.m23si2.pertamaapp.model.Mahasiswa;
import edu.uph.m23si2.pertamaapp.model.MataKuliah;
import edu.uph.m23si2.pertamaapp.model.Prodi;
import edu.uph.m23si2.pertamaapp.model.Provinsi;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText edtNama, edtPassword;
    Spinner spnProvinsi, spnKota;
    List<Provinsi> provinsiList =  new ArrayList<>();
    List<String> namaProvinsi = new ArrayList<>();
    List<Kota> kotaList =  new ArrayList<>();
    List<String> namaKota = new ArrayList<>();
    ArrayAdapter<String> adapterProvinsi, adapterKota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("default.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true) // sementara aktifkan untuk demo
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        initdata();
        btnLogin = findViewById(R.id.btnLogin);
        edtNama = findViewById(R.id.edtNama);
        edtPassword = findViewById(R.id.edtPassword);

        // SPINNER PROVINSI
        spnProvinsi = findViewById(R.id.spnProvinsi);
        adapterProvinsi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaProvinsi);
        adapterProvinsi.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnProvinsi.setAdapter(adapterProvinsi);
        // SPINNER KOTA
        spnKota = findViewById(R.id.spnKota);
        adapterKota = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, namaKota);
        adapterKota.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnKota.setAdapter(adapterKota);


        // init retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://wilayah.id")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        // panggil API
        apiService.getProvinsi().enqueue(new Callback<ApiResponse<Provinsi>>() {
            @Override
            public void onResponse(Call<ApiResponse<Provinsi>> call, Response<ApiResponse<Provinsi>> response) {
                if(response.isSuccessful() && response.body()!=null){
                    provinsiList = response.body().getData();
                    namaProvinsi.clear();
                    for(Provinsi p: provinsiList){
                        if(p.getName()!=null){
                            Log.d("Provinsi", p.getName());
                            namaProvinsi.add(p.getName());
                        }
                    }

                    adapterProvinsi.notifyDataSetChanged();

                    spnProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Provinsi selectedProvinsi = provinsiList.get(position);
                            Log.d("Provinsi", selectedProvinsi.getCode() + " - " + selectedProvinsi.getName());

                            apiService.getKota(selectedProvinsi.getCode()).enqueue(new Callback<ApiResponse<Kota>>() {
                                @Override
                                public void onResponse(Call<ApiResponse<Kota>> call, Response<ApiResponse<Kota>> response) {
                                    if(response.isSuccessful() && response.body()!=null){
                                        kotaList = response.body().getData();
                                        namaKota.clear();
                                        for(Kota k: kotaList){
                                            if(k.getName()!=null){
                                                Log.d("Kota", k.getName());
                                                namaKota.add(k.getName());
                                            }
                                        }

                                        adapterKota.notifyDataSetChanged();

                                        spnKota.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                            @Override
                                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                Kota selectedKota = kotaList.get(position);
                                                Log.d("Kota", selectedKota.getCode() + " - " + selectedKota.getName());
                                            }
                                            @Override
                                            public void onNothingSelected(AdapterView<?> parent) {
                                            }
                                        });
                                    }
                                }
                                @Override
                                public void onFailure(Call<ApiResponse<Kota>> call, Throwable t) {
                                    Toast.makeText(LoginActivity.this,"Gagal :"+t.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
        }
            @Override
            public void onFailure(Call<ApiResponse<Provinsi>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Gagal :"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDashboard();
            }
        });
    }

    public void initdata(){
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(r -> {
            // cek apakah data sudah ada, kalau sudah jangan bikin lagi
            if (r.where(Prodi.class).count() > 0) {
                return; // sudah ada data, langsung keluar
            }

            Prodi prodiSI = r.createObject(Prodi.class, 0);
            prodiSI.setNama("Sistem Informasi");
            prodiSI.setFakultas("Fakultas Ilmu Komputer");

            Mahasiswa mahasiswa1 = r.createObject(Mahasiswa.class, 0);
            mahasiswa1.setNama("Felicia Louis");
            mahasiswa1.setJenisKelamin("Wanita");
            mahasiswa1.setEmail("felicialouis12@gmail.com");
            mahasiswa1.setHobi("Makan; Tidur");
            mahasiswa1.setProdi(prodiSI);

            Mahasiswa mahasiswa2 = r.createObject(Mahasiswa.class, 1);
            mahasiswa2.setNama("Vinola Lorencia");
            mahasiswa2.setJenisKelamin("Wanita");
            mahasiswa2.setEmail("vinolalorencia@gmail.com");
            mahasiswa2.setHobi("Belajar; Makan; Tidur");
            mahasiswa2.setProdi(prodiSI);

            MataKuliah mataKuliah1 = r.createObject(MataKuliah.class, 0);
            mataKuliah1.setNama("Pemrograman Aplikasi Bergerak");
            mataKuliah1.setSks(3);
            mataKuliah1.setProdi(prodiSI);

            MataKuliah mataKuliah2 = r.createObject(MataKuliah.class, 1);
            mataKuliah2.setNama("Audit Sistem Informasi");
            mataKuliah2.setSks(3);
            mataKuliah2.setProdi(prodiSI);

            Kelas_MataKuliah kelas_mataKuliah1 = r.createObject(Kelas_MataKuliah.class, 0);
            kelas_mataKuliah1.setNamaKelas("23SI2");
            kelas_mataKuliah1.setMataKuliah(mataKuliah1);
            kelas_mataKuliah1.setDosenPengampu("Ade Maulana");

            Kelas_MataKuliah kelas_mataKuliah2 = r.createObject(Kelas_MataKuliah.class, 1);
            kelas_mataKuliah2.setNamaKelas("23SI3");
            kelas_mataKuliah2.setMataKuliah(mataKuliah1);
            kelas_mataKuliah2.setDosenPengampu("Ade Maulana");

            Kelas_MataKuliah kelas_mataKuliah3 = r.createObject(Kelas_MataKuliah.class, 2);
            kelas_mataKuliah3.setNamaKelas("23SI3");
            kelas_mataKuliah3.setMataKuliah(mataKuliah2);
            kelas_mataKuliah3.setDosenPengampu("Kevin Bastian Sirait");

            KRS krs1 = r.createObject(KRS.class, 0);
            krs1.setMahasiswa(mahasiswa1);
            krs1.setSemester("Ganjil");
            krs1.setTahunAkademik("2025/2026");

            KRS krs2 = r.createObject(KRS.class, 1);
            krs2.setMahasiswa(mahasiswa1);
            krs2.setSemester("Genap");
            krs2.setTahunAkademik("2025/2026");

            KRSDetail krsDetail1 = r.createObject(KRSDetail.class, 0);
            krsDetail1.setKRS(krs1);
            krsDetail1.setKelas_mataKuliah(kelas_mataKuliah1);
            krsDetail1.setStatus("aktif");

            KRSDetail krsDetail2 = r.createObject(KRSDetail.class, 1);
            krsDetail2.setKRS(krs1);
            krsDetail2.setKelas_mataKuliah(kelas_mataKuliah3);
            krsDetail2.setStatus("drop");
        });
    }
    public void toProfil(){
        Intent intent = new Intent(this,ProfilActivity.class);
        intent.putExtra("nama",edtNama.getText().toString());
        startActivity(intent);
    }
    public void toDashboard(){
        Intent intent = new Intent(this,DashboardActivity.class);
        startActivity(intent);
    }
}