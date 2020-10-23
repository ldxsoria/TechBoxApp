package ldx.soria.techbox.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import ldx.soria.techbox.Item

class Repo {

    fun getItemData():LiveData<MutableList<Item>>{
        val mutableData = MutableLiveData<MutableList<Item>>()
        FirebaseFirestore.getInstance().collection("items").get().addOnSuccessListener { result ->
            val listData = mutableListOf<Item>()
            for(document in result){
                val imageUrl = document.getString("imageUrl")
                val area = document.getString("area")
                val codigo = document.getString("codigo")
                val desc = document.getString("desc")
                val marca = document.getString("marca")
                val model = document.getString("model")
                val serial = document.getString("serial")
                val tipo = document.getString("tipo")

                val registro2 =  Item(imageUrl!!,area!!,codigo!!,desc!!,marca!!,model!!,serial!!,tipo!!)
                listData.add(registro2)

            }

            mutableData.value = listData
        }
        return mutableData
    }
}