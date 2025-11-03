/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrator
 */
public class Admin extends Pengguna {
    private int hakAkses;
    private String jabatan;

    public Admin(int id, String nama, String email, String kataSandi, String tipe, int hakAkses, String jabatan) {
        super(id, nama, email, kataSandi, tipe);
        this.hakAkses = hakAkses;
        this.jabatan = jabatan;
    }

    public int getHakAkses() { return hakAkses; }
    public String getJabatan() { return jabatan; }
}