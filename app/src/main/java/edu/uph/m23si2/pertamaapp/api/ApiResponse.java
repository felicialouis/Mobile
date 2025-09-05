package edu.uph.m23si2.pertamaapp.api;

import java.util.List;

import edu.uph.m23si2.pertamaapp.model.Kota;
import edu.uph.m23si2.pertamaapp.model.Provinsi;

public class ApiResponse<T> {
    private List<T> data;

    public List<T> getData(){
        return data;
    }
}
