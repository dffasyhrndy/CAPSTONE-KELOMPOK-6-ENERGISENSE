/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrator
 */
public class Pengguna {
    protected int id;
    protected String nama;
    protected String email;
    protected String kataSandi;
    protected String tipe;

    public Pengguna(int id, String nama, String email, String kataSandi, String tipe) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.kataSandi = kataSandi;
        this.tipe = tipe;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getEmail() { return email; }
    public String getKataSandi() { return kataSandi; }
    public String getTipe() { return tipe; }
}