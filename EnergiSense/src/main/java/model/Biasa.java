/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Biasa extends Pengguna {
    private Date tanggalDaftar;
    private boolean statusAktif;

    public Biasa(int id, String nama, String email, String kataSandi, String tipe, Date tanggalDaftar, boolean statusAktif) {
        super(id, nama, email, kataSandi, tipe);
        this.tanggalDaftar = tanggalDaftar;
        this.statusAktif = statusAktif;
    }

    public Date getTanggalDaftar() { return tanggalDaftar; }
    public boolean isStatusAktif() { return statusAktif; }
}