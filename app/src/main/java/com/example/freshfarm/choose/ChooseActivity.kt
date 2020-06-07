package com.example.freshfarm.choose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.freshfarm.R
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    private lateinit var chooseItemMap: HashMap<String, ArrayList<ChooseItem>>

    private var chooseItemList = arrayListOf<ChooseItem>()

    private lateinit var chooseItemAdapter: ChooseItemAdapter
    private lateinit var chooseItemLayoutManager: LinearLayoutManager

    private lateinit var chooseMenuAdapter: ChooseMenuAdapter
    private lateinit var chooseMenuLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

//        init arrays
        chooseItemMap = hashMapOf<String, ArrayList<ChooseItem>>(
            "animal" to arrayListOf<ChooseItem>(ChooseItem("Chicken"), ChooseItem("Pig")),
            "crop" to arrayListOf<ChooseItem>(ChooseItem("Rice"), ChooseItem("Pepper"))
        )

        var chooseMenuList = arrayListOf<ChooseMenu>()
        chooseMenuList.add(ChooseMenu("animal"))
        chooseMenuList.add(ChooseMenu("crop"))

        chooseItemList.clear()
        chooseItemList.addAll(chooseItemMap[chooseMenuList[0].name]!!)

//        set adapter
        chooseItemAdapter = ChooseItemAdapter(this, chooseItemList)
        chooseItemLayoutManager = LinearLayoutManager(this)

        chooseItemRV.apply {
            adapter = chooseItemAdapter
            layoutManager = chooseItemLayoutManager
//            need to know why to add below code
//            setHasFixedSize(true)
        }

        chooseMenuAdapter = ChooseMenuAdapter(this, chooseMenuList)
        chooseMenuLayoutManager = LinearLayoutManager(this)

        chooseMenuRV.apply {
            adapter = chooseMenuAdapter
            layoutManager = chooseMenuLayoutManager
//            need to know why to add below code
//            setHasFixedSize(true)
        }
    }

    fun selectMenu(menuName: String) {
        chooseItemList.clear()
        chooseItemList.addAll(chooseItemMap[menuName]!!)
        chooseItemAdapter.notifyDataSetChanged()
    }
}
