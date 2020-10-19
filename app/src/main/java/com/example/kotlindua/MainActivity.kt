package com.example.kotlindua

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDataSpinner()
        btnSave.setOnClickListener { (validasiInput()) }
    }

    private fun setDataSpinner(){
        val adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamian, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinerGender.adapter = adapter
    }

    private fun validasiInput() {
        namaInput = editnama.text.toString()
        emailInput = editEmail.text.toString()
        telpInput = editTelp.text.toString()
        alamatInput = editAddress.text.toString()
        genderInput = spinerGender.selectedItem.toString()
        when {
            namaInput.isEmpty() -> editnama.error = "Nama tidak boleh kosong"
            genderInput.equals(
                "Pilih Jenis kelamin",
                ignoreCase = true
            ) -> tampilToast("Jenis Kelamin harus dipilih")
            emailInput.isEmpty() -> editEmail.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> editTelp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> editAddress.error = "Alamat tidak boleh kosong"

            else -> {
                tampilToast("Navigasi ke halaman profil")
                goToProfilActivity()
            }
        }
    }

    private fun tampilToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goToProfilActivity(){
        val intent = Intent(this, ProfilActivity::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)

        startActivity(intent)
    }

}





