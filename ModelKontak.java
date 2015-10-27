package com.theheran.volleyinsertdata;

/*
 * Programmer		: Giri Astra
 * Website			: www.theheran.com
 * Email			: info@theheran.com
 */

public class ModelKontak {
	String 	id_kontak,
			 nama_depan,
			 nama_belakang,
			 tgl_lahir,
			 alamat,
			 no_hp,
			 no_telp,
			 email,		
			 tgl_input,
			 tgl_update,
			 status_data;

	public ModelKontak() {
		
	}

	public ModelKontak(
						String id_kontak,
						String nama_depan,
						String nama_belakang,
						String tgl_lahir,
						String alamat,
						String no_hp,
						String no_telp,
						String email,		
						String tgl_input,
						String tgl_update,
						String status_data
					) {
		
			this.id_kontak=id_kontak;
			this.nama_depan=nama_depan;
			this.nama_belakang=nama_belakang;
			this.tgl_lahir=tgl_lahir;
			this.alamat=alamat;
			this.no_hp=no_hp;
			this.no_telp=no_telp;
			this.email=	email;	
			this.tgl_input=tgl_input;
			this.tgl_update=tgl_update;
			this.status_data=status_data;
		
	}
	

	public String getIdKontak() {
		return id_kontak;
	}
	public void SetIdKontak(String id_kontak) {
		this.id_kontak = id_kontak;
	}
	
	
	public String getNamaDepan() {
		return nama_depan;
	}
	public void SetNamaDepan(String nama_depan) {
		this.nama_depan = nama_depan;
	}
	
	
	public String getNamaBelakang() {
		return nama_belakang;
	}
	public void SetNamaBelakang(String nama_belakang) {
		this.nama_belakang = nama_belakang;
	}
	
	public String getTglLahir() {
		return tgl_lahir;
	}
	public void SetTglLahir(String tgl_lahir) {
		this.tgl_lahir = tgl_lahir;
	}
		
	public String getAlamat() {
		return alamat;
	}
	public void SetAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public String getNoHp() {
		return no_hp;
	}
	public void SetNoHp(String no_hp) {
		this.no_hp = no_hp;
	}
	
	public String getNoTelp() {
		return no_telp;
	}
	public void SetNoTelp(String no_telp) {
		this.no_telp = no_telp;
	}
	
	public String getEmail() {
		return email;
	}
	public void SetEmail(String email) {
		this.email = email;
	}
	
	public String getTglInput() {
		return tgl_input;
	}
	public void SetTglInput(String tgl_input) {
		this.tgl_input = tgl_input;
	}
	
	public String getTglUpdate() {
		return tgl_update;
	}
	public void SetTglUpdate(String tgl_update) {
		this.tgl_update = tgl_update;
	}
	
	public String getStatusData() {
		return status_data;
	}
	public void SetStatusData(String status_data) {
		this.status_data = status_data;
	}
	



}
