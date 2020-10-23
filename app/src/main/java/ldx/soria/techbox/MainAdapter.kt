package ldx.soria.techbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_row.view.*

class MainAdapter(private val context: Context): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    //https://youtu.be/X_YHDa_kolc
    private var dataList = mutableListOf<Item>()

    fun setListData(data:MutableList<Item>){
        dataList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item1 = dataList[position]
        holder.bindView(item1)
    }

    override fun getItemCount(): Int {
        return if(dataList.size > 0){
            dataList.size
        }else{
            0
        }
    }

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindView(item:Item){
            Glide.with(context).load(item.imageUrl).into(itemView.circleImageView)
            itemView.tv_code.text = item.codigo
            itemView.tv_type.text = item.tipo
        }
    }

}