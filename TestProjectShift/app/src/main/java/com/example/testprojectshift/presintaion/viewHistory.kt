package com.example.testprojectshift.presintaion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectshift.App
import com.example.testprojectshift.R
import com.example.testprojectshift.data.db.BankCardRepository
import javax.inject.Inject

class viewHistory : AppCompatActivity() {

    @Inject
    lateinit var repository:BankCardRepository

    private val controller = InfoController(InfoLocalOrDb.Local)

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).component.plus(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_history)

        findViewById<RecyclerView>(R.id.recyclerHistory).apply {
            layoutManager = LinearLayoutManager(this@viewHistory)
            adapter = controller.adapter
        }
        with(controller){
            infos = repository.getAll()
            this.requestModelBuild()
        }

    }


}