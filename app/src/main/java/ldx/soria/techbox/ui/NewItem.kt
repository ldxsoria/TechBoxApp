package ldx.soria.techbox.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_new_item.*
import ldx.soria.techbox.R


class NewItem : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        iv_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

//INICIA - Indicar que boton estoy usando
        btn_camera1.setOnClickListener {
            val intent1 = Intent(this, CameraActivity::class.java)
            intent1.putExtra("CameraSelectec", "uno")
            startActivityForResult(intent1, 101)

        }

        btn_camera2.setOnClickListener {
            val intent1 = Intent(this, CameraActivity::class.java)
            intent1.putExtra("CameraSelectec", "dos")
            startActivityForResult(intent1, 102)
        }
//FIN - Indicar que boton estoy usando

//Inicia Spinner XML, que esta en @values/strings
        //val typeRef = db.collection("types")
        val spinner: Spinner = findViewById(R.id.spr_tipo)
        ArrayAdapter.createFromResource(
            this,
            R.array.types_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
//Fin Spinner XML


//Inicia BTN registrar
        btn_add_item.setOnClickListener {
            if (et_cod.text.isNotEmpty() && et_serial.text.isNotEmpty()) {
                db.collection("items").document(et_cod.text.toString()).set(
                    hashMapOf(
                        "codigo" to et_cod.text.toString(),
                        "tipo" to spr_tipo.selectedItem.toString(),
                        "marca" to et_marca.text.toString(),
                        "modelo" to et_modelo.text.toString(),
                        "serial" to et_serial.text.toString(),
                        "desc" to et_desc.text.toString(),
                        "area" to et_area.text.toString())
                )
                Toast.makeText(this,"Se registro correctamente",Toast.LENGTH_LONG).show()
                finish()
            } else {
                showAlert()
            }
        }
//Fin BTN registrar

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Completa los datos para registrar.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            101 -> {if(resultCode == Activity.RESULT_OK){
                var result = data?.getStringExtra("Code")
                et_cod.setText(result)}}
            102 -> {if(resultCode == Activity.RESULT_OK){
                var result = data?.getStringExtra("Serial")
                et_serial.setText(result)}}
        }
    }
}