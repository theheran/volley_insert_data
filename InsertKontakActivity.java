package com.theheran.volleyinsertdata;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.theheran.volleyinsertdata.volley.AppController;

/*
 * Programmer		: Giri Astra
 * Website			: www.theheran.com
 * Email			: info@theheran.com
 */
public class InsertKontakActivity extends Activity {
	
	//File Yang dipanggil sesuaikan dengan alamat IP dan portnya.
	private static String URL = "http://10.0.2.2:81/latihan_volley/kontakManager.php";
	public  String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";	
	String formKosong = "Form Kosong";
	String tgl_lahir,status_data;
	ProgressDialog progressDialog = null; 
	ModelKontak modelKontak;
	private DatePicker dpResult;
	private int year,month,day;
	static final int DATE_DIALOG_ID = 999;
	EditText tglLahir;
	
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(InsertKontakActivity.this, datePickerListener, year, month,day);
		}
		return null;
	}
	//Inisialisasi Date Picker
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
				year = selectedYear;month = selectedMonth;day = selectedDay;
				String date=""+new StringBuilder().append(day).append("/").append(month+1).append("/").append(year).append("");
				String date2=""+new StringBuilder().append(year).append("-").append(month+1).append("-").append(day).append("");
				tglLahir.setText(date);
				tgl_lahir =date2 ;
				dpResult.init(year, month, day, null);
		}
	};


	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		final EditText txtNamaDepan 		= (EditText) findViewById(R.id.txtNamaDepan);
		final EditText txtNamaBelakang 		= (EditText) findViewById(R.id.txtNamaBelakang);
		tglLahir 							= (EditText) findViewById(R.id.txtTglLahir);
		tglLahir.setEnabled(false);
		final EditText txtEmail 			= (EditText) findViewById(R.id.txtEmail);
		final EditText txtNoHP 				= (EditText) findViewById(R.id.txtNoHP);
		final EditText txtNoTelp 			= (EditText) findViewById(R.id.txtNoTelp);
		final EditText txtAlamat 			= (EditText) findViewById(R.id.txtAlamat);
		
		ImageButton btnTglLahir 			= (ImageButton) findViewById(R.id.btnTglLahir);
		Button 	btnSimpan	 				= (Button) findViewById(R.id.btnSimpan);
		dpResult							= (DatePicker) findViewById(R.id.datePic);
		
		Spinner spinerStatus		= (Spinner) findViewById(R.id.SpinStatusData);
		spinerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        @Override
	        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {                
        	 	if(position==0){
        	 		status_data = "Y";
				}else{
					status_data = "N";   
				}
	        }
	        @Override
	        public void onNothingSelected(AdapterView<?> parent) {
	        }
	    }); 
		
		
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		dpResult.init(year, month, day, null);
		
		btnTglLahir.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				DatePickerDialog dpd = new DatePickerDialog(InsertKontakActivity.this, datePickerListener, year, month, day);
                dpd.show();
				
		} });
		
		btnSimpan.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//Validasi Form (Text Field)
				Boolean validEmail =txtEmail.getText().toString().matches(EMAIL_REGEX);
				 if(txtNamaDepan.getText().toString().trim().isEmpty()){
					 txtNamaDepan.setError(formKosong);
					 txtNamaDepan.requestFocus();
		          }else if(txtNamaBelakang.getText().toString().trim().isEmpty()){
		        	  txtNamaBelakang.setError(formKosong);
		        	  txtNamaBelakang.requestFocus();
		          }else if(tglLahir.getText().toString().trim().isEmpty()){
		        	  tglLahir.setError(formKosong);
		        	  tglLahir.requestFocus();
		          }else if(txtEmail.getText().toString().trim().isEmpty()){
		        	  txtEmail.setError(formKosong);
		        	  txtEmail.requestFocus();
		          }else if(validEmail==false){
						txtEmail.setError("Format Email Salah");
						txtEmail.requestFocus();
				  }else if(txtNoHP.getText().toString().trim().isEmpty()){
		        	  txtNoHP.setError(formKosong);
		        	  txtNoHP.requestFocus();
		          }else if(txtNoTelp.getText().toString().trim().isEmpty()){
		        	  txtNoTelp.setError(formKosong);
		        	  txtNoTelp.requestFocus();
		          }else if(txtAlamat.getText().toString().trim().isEmpty()){
		        	  txtAlamat.setError(formKosong);
		        	  txtAlamat.requestFocus();
		          }else{
		        	  	//Setting Value To Model
		        	  
		        	  	ModelKontak data = new ModelKontak();
			  		    data.SetNamaDepan(txtNamaDepan.getText().toString());
			  		    data.SetNamaBelakang(txtNamaBelakang.getText().toString());
			  		    data.SetTglLahir(tgl_lahir);
			  		    data.SetEmail(txtEmail.getText().toString());
			  		    data.SetNoHp(txtNoHP.getText().toString());
			  		    data.SetNoTelp(txtNoTelp.getText().toString());
			  		    data.SetAlamat(txtAlamat.getText().toString());
			  		    data.SetStatusData(status_data);
			  		    
			  		    //call Funtion Insert Data with model data parse
			  		    InsertData(data);
		          }
				
		} });
		
	
}
	
	
	private void InsertData(final ModelKontak data ){
		progressDialog=ProgressDialog.show(InsertKontakActivity.this, "","Mengirim Data", true);
		//Request and send data using POST method
	    StringRequest linkInsert = new StringRequest(Request.Method.POST,URL, new Response.Listener<String>() {
	        @Override
	        public void onResponse(String response) {
	        	hidePDialog();
	        	if(response.equals("gagal")){
	        		 PopupGagal("Data Gagal di Update",InsertKontakActivity.this);
	        	}else{
	        		try {
						  JSONObject jsonObject = new JSONObject(response);
						  JSONArray jsonArray = jsonObject.getJSONArray("value");
						  if(jsonArray.length()<=0){
							  PopupGagal("Data Gagal di Update",InsertKontakActivity.this);
						  }else{
							  String statusData = "";
							  String ID = "";
							for (int i = 0; i < jsonArray.length(); i++) {
									JSONObject obj = jsonArray.getJSONObject(i);
									
									statusData 	= obj.getString("statusData");
									ID 			= obj.getString("lastId");
							}
							
							if(statusData.equals("sukses")){	
								final AlertDialog alertDialog = new AlertDialog.Builder(InsertKontakActivity.this).create();
								alertDialog.setTitle("Informasi");
								alertDialog.setMessage("Data Sukses Disimpan");
								//alertDialog.setIcon(R.drawable.sukses);
								alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int which) {
											Intent in = new Intent(InsertKontakActivity.this,InsertKontakActivity.class);
											in.putExtra("mode", "admin");
											startActivity(in);
											
											alertDialog.dismiss();
											finish();
										}
								});
								alertDialog.show();
								 
							}else{
								final AlertDialog alertDialog = new AlertDialog.Builder(InsertKontakActivity.this).create();
								alertDialog.setTitle("Informasi");
								alertDialog.setMessage("Data Gagal Disimpan");
								//alertDialog.setIcon(R.drawable.gagal);
								alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog, int which) {
											Intent in = new Intent(InsertKontakActivity.this,InsertKontakActivity.class);
											in.putExtra("mode", "admin");
											startActivity(in);
											alertDialog.dismiss();
											finish();
										}
								});
								alertDialog.show();
							}
							
						}
			        } catch (Throwable t) {
			            return ;
			        }
		        	  
	        	}
	        	
	        }
	    }, new Response.ErrorListener() {
	        @Override
	        public void onErrorResponse(VolleyError error) {
	        	hidePDialog();
	        	PopupGagal("Error Respon."+error,InsertKontakActivity.this);
	        }
	    }){
	    	
	        @Override
	        protected Map<String,String> getParams(){
	            Map<String,String> params = new HashMap<String, String>();
 	            params.put("nama_depan",data.getNamaDepan());
 	            params.put("nama_belakang",data.getNamaBelakang());
 	            params.put("tgl_lahir",data.getTglLahir());
 	            params.put("alamat",data.getAlamat());
 	            params.put("no_hp",data.getNoHp());
 	            params.put("no_telp",data.getNoTelp());
 	            params.put("email",data.getEmail());
 	            params.put("status_data",data.getStatusData());
 	           
 	            params.put("req","insertKontak");
	            return params;

	        }

	        @Override
	        public Map<String, String> getHeaders() throws AuthFailureError {
	            Map<String,String> params = new HashMap<String, String>();
	            params.put("Content-Type","application/x-www-form-urlencoded");
	            return params;
	        }
	    };
		    //Set time out
			int socketTimeout = 30000;
			RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
			linkInsert.setRetryPolicy(policy);
			AppController.getInstance().addToRequestQueue(linkInsert);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		hidePDialog();
	}

	private void hidePDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	   public AlertDialog PopupGagal(String pesan,Context activity){
	    	
	    	AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
			alertDialog.setTitle("Informasi");
			alertDialog.setMessage(pesan);
			//alertDialog.setIcon(R.drawable.gagal);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					//Toast.makeText(getApplicationContext(), "Terima Kasih", Toast.LENGTH_SHORT).show();
					}
			});
			alertDialog.show();
			
			return alertDialog;
	    }
	
}
	
	
	
	
	
	
	



