package com.example.kotlindua

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    companion object{
        val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        ambilData()
        btnEditNama.setOnClickListener { navigasiKeEditProfil() }
        btnCall.setOnClickListener {
            dialPhoneNumber(txtTlp.text.toString())
        }
    }

    private fun ambilData(){
        val bundle = intent.extras

        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")

        txtNama.text = nama
        txtGender.text = gender
        txtEmial.text = email
        txtTlp.text = telp
        txtAlamat.text = alamat
    }

    private fun navigasiKeEditProfil(){
        val intent = Intent(this, editProfilActivity::class.java)

        val namaUser = txtNama.text.toString()
        intent.putExtra("nama",namaUser)

        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                val result = data?.getStringExtra("nama")
                txtNama.text = result
            }else{
                Toast.makeText(this, "Edit failed",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dialPhoneNumber(phoneNumber: String){
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}