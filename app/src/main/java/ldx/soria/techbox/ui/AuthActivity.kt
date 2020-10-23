package ldx.soria.techbox.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import ldx.soria.techbox.R

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //val btnFPass = findViewById<TextView>(R.id.tvForgotPassword)
        //btnFPass.setOnClickListener{buttonForgotPassword()}

        tvForgotPassword.setOnClickListener {
            val intent1 = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent1)
        }

        // Setup
        setup()
    }
    private fun setup(){
        title = "Autenticación"
        btnLogin.setOnClickListener{
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(etEmail.text.toString(),
                        etPassword.text.toString()).addOnCompleteListener {

                        if (it.isSuccessful){
                            showMain(it.result?.user?.email ?: "")
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
        builder.setMessage("Se ha producido un error autenticando al usuario.")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMain(email: String){
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            //putExtra("typeOfUser", typeOfUser.name)
        }
        startActivity(mainIntent)
    }

    //private fun buttonForgotPassword() = startActivity(Intent(this, ForgotPasswordActivity::class.java))

}