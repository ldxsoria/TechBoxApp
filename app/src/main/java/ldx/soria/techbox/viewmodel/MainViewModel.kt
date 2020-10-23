package ldx.soria.techbox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ldx.soria.techbox.Item
import ldx.soria.techbox.network.Repo

class MainViewModel: ViewModel() {

    private val repo = Repo()

    fun fetchItemData(): LiveData<MutableList<Item>>{
        val mutableData = MutableLiveData<MutableList<Item>>()
        repo.getItemData().observeForever{itemList ->
            mutableData.value = itemList
        }
        return mutableData
    }
}