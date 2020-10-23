package ldx.soria.techbox.ui

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_camera.*
import ldx.soria.techbox.R
import me.dm7.barcodescanner.zxing.ZXingScannerView

class CameraActivity : AppCompatActivity(), PermissionListener, ZXingScannerView.ResultHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        //Solicitar permisos al usuario
        Dexter.withActivity(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(this)
            .check()

        btn_back.setOnClickListener {
            startActivity( Intent(this, NewItem::class.java))
            finish()
        }
    }
    override fun handleResult(rawResult: Result?) {
        //Toast.makeText(this,rawResult!!.text,Toast.LENGTH_LONG)
        val camIntent = intent
        var option = camIntent.getStringExtra("CameraSelectec")

        var result = rawResult!!.text

        when(option){
            "uno" -> sendResult(1,result)
            "dos" -> sendResult(2,result)
        }
    }

    fun sendResult(option:Int,result:String?){
        var intent1 = intent
        when(option){
            1 -> intent1.putExtra("Code", result)
            2 -> intent1.putExtra("Serial", result)
        }
        setResult(RESULT_OK, intent1)
        finish()
    }

    override fun onPermissionGranted(response: PermissionGrantedResponse) {
        // This method will be called when the permission is denied
        zxscan.setResultHandler(this@CameraActivity)
        zxscan.startCamera()
    }

    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken?) {
        // This method will be called when the user rejects a permission request
        // You must display a dialog box that explains to the user why the application needs this permission
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse) {
        // This method will be called when the permission is granted
        Toast.makeText(this, "Activa el permiso de usar la camara",Toast.LENGTH_LONG).show()
    }



}