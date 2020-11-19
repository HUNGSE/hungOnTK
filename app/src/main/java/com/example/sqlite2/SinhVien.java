package com.example.sqlite2;

public class SinhVien {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    private int id;

    public SinhVien(String tenSV) {
        this.tenSV = tenSV;
    }

    private  String tenSV;

    public SinhVien(int id, String tenSV) {
        this.id = id;
        this.tenSV = tenSV;
    }

    public SinhVien() {
    }
}
