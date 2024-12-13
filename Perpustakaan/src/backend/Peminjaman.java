/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Rahmad Dwi Ferdyan
 */
public class Peminjaman {

    private int idPeminjaman;
    private Pegawai pegawai = new Pegawai();
    private Anggota anggota = new Anggota();
    private Buku buku = new Buku();
    private String tanggalPinjam, tanggalKembali;

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public Peminjaman() {
    }

    public Peminjaman(Pegawai pegawai, Anggota anggota, Buku buku, int idPeminjaman, String tanggalPinjam, String tanggalKembali) {
        this.pegawai = pegawai;
        this.anggota = anggota;
        this.buku = buku;
        this.idPeminjaman = idPeminjaman;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

     public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "p.idPeminjaman AS idPeminjaman, "
                + "p.tanggalPinjam AS tanggalPinjam, "
                + "p.tanggalKembali AS tanggalKembali, "
                + "pg.idPegawai AS idPegawai, "
                + "pg.nama AS namaPegawai, "
                + "a.idAnggota AS idAnggota, "
                + "a.nama AS namaAnggota, "
                + "b.idBuku AS idBuku, "
                + "b.judul AS judulBuku "
                + "FROM peminjaman p "
                + "LEFT JOIN pegawai pg ON p.idPegawai = pg.idPegawai "
                + "LEFT JOIN anggota a ON p.idAnggota = a.idAnggota "
                + "LEFT JOIN buku b ON p.idBuku = b.idBuku "
                + "WHERE p.idPeminjaman = '" + id + "'");
        try {
            while (rs.next()) {
                peminjaman.setIdPeminjaman(rs.getInt("idPeminjaman"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                Pegawai pegawai = new Pegawai();
                pegawai.setIdpegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                peminjaman.setPegawai(pegawai);

                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idAnggota"));
                anggota.setNama(rs.getString("namaAnggota"));
                peminjaman.setAnggota(anggota);

                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idBuku"));
                buku.setJudul(rs.getString("judulBuku"));
                peminjaman.setBuku(buku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }

    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> listPeminjaman = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT "
                + "p.idPeminjaman AS idPeminjaman, "
                + "p.tanggalPinjam AS tanggalPinjam, "
                + "p.tanggalKembali AS tanggalKembali, "
                + "pg.idPegawai AS idPegawai, "
                + "pg.nama AS namaPegawai, "
                + "a.idAnggota AS idAnggota, "
                + "a.nama AS namaAnggota, "
                + "b.idBuku AS idBuku, "
                + "b.judul AS judulBuku "
                + "FROM peminjaman p "
                + "LEFT JOIN pegawai pg ON p.idPegawai = pg.idPegawai "
                + "LEFT JOIN anggota a ON p.idAnggota = a.idAnggota "
                + "LEFT JOIN buku b ON p.idBuku = b.idBuku");
        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idPeminjaman"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalPinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalKembali"));

                Pegawai pegawai = new Pegawai();
                pegawai.setIdpegawai(rs.getInt("idPegawai"));
                pegawai.setNama(rs.getString("namaPegawai"));
                peminjaman.setPegawai(pegawai);

                Anggota anggota = new Anggota();
                anggota.setIdanggota(rs.getInt("idAnggota"));
                anggota.setNama(rs.getString("namaAnggota"));
                peminjaman.setAnggota(anggota);

                Buku buku = new Buku();
                buku.setIdbuku(rs.getInt("idBuku"));
                buku.setJudul(rs.getString("judulBuku"));
                peminjaman.setBuku(buku);

                listPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listPeminjaman;
    }

   public void save() {
    if (getById(idPeminjaman).getIdPeminjaman() == 0) {
        String SQL = "INSERT INTO peminjaman (idPegawai, idAnggota, idBuku, tanggalPinjam, tanggalKembali) VALUES ("
                + "'" + this.getPegawai().getIdpegawai() + "', "
                + "'" + this.getAnggota().getIdanggota() + "', "
                + "'" + this.getBuku().getIdbuku() + "', "
                + "'" + this.getTanggalPinjam() + "', "
                + (this.getTanggalKembali() == null || this.getTanggalKembali().isEmpty() ? "NULL" : "'" + this.getTanggalKembali() + "'") + ")";
        this.idPeminjaman = DBHelper.insertQueryGetId(SQL);
    } else {
        String SQL = "UPDATE peminjaman SET "
                + "idPegawai = '" + this.getPegawai().getIdpegawai() + "', "
                + "idAnggota = '" + this.getAnggota().getIdanggota() + "', "
                + "idBuku = '" + this.getBuku().getIdbuku() + "', "
                + "tanggalPinjam = '" + this.getTanggalPinjam() + "', "
                + "tanggalKembali = " + (this.getTanggalKembali() == null || this.getTanggalKembali().isEmpty() ? "NULL" : "'" + this.getTanggalKembali() + "'") + " "
                + "WHERE idPeminjaman = '" + this.idPeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }
}


    public void delete() {
        String SQL = "DELETE FROM peminjaman WHERE idPeminjaman = '" + this.idPeminjaman + "'";
        DBHelper.executeQuery(SQL);
    }

}
