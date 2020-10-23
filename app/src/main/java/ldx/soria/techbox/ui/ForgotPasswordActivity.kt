package ldx.soria.techbox.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot_password.*
import ldx.soria.techbox.R

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val btnBackLogin = findViewById<ImageView>(R.id.btnBackLogin)
        btnBackLogin.setOnClickListener { goToLoginActivity() }

        resetPassword()

    }

    private fun goToLoginActivity() = startActivity(Intent(this, AuthActivity::class.java))

    private fun resetPassword(){
        title = "Recuperación"
        btnForgot.setOnClickListener {
            if(etEmailForgot.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .sendPasswordResetEmail(etEmailForgot.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful){
                            showAlertSuccessful()
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Por favor asegurate que el correo es el correcto.")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun showAlertSuccessful(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Estimado usuario")
        builder.setMessage("Te acabamos de enviar un correo para que cambies tu contraseña. Si no ves el mensaje en tu buzón, mira en la carpeta de spam.")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}