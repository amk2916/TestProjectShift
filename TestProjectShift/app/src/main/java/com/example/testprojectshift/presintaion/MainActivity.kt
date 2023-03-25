package com.example.testprojectshift.presintaion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectshift.App
import com.example.testprojectshift.R
import com.example.testprojectshift.data.db.BankCardRepository
import com.example.testprojectshift.data.server.BankInfoService
import com.example.testprojectshift.data.toDomainModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
     lateinit var bankInfoService: BankInfoService
    @Inject
     lateinit var repository: BankCardRepository

    private val search by lazy { findViewById<Button>(R.id.searchButton) }
    private val history by lazy { findViewById<Button>(R.id.historyButton) }

    private var numberCard = ""
    private val controller = InfoController(InfoLocalOrDb.Server)
    private val disposable = CompositeDisposable()

    private val editTextView by lazy { findViewById<EditText>(R.id.editNumberCard) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).component.inject(this)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = controller.adapter
        }
        // controller.requestModelBuild()

        controller.mutableLiveData.observe(this) {
            Log.e("asdasd", "card was clicked with id  $it")
        }

        controller.actionBankPhone.observe(this){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$it")
            startActivity(intent)
        }


        search.apply {
            setOnClickListener {
                if (!editTextView.text.isNullOrEmpty()) {
                    numberCard = editTextView.text.toString()

                    val resultConnectableObservalbe = bankInfoService.getCardInfo(numberCard)
                        .toObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        //.map { it.toDomainModel(numberCard) }
                        .share()
                        .onErrorComplete()

                 //   Log.e("***", resultConnectableObservalbe.toString())
                    resultConnectableObservalbe
                        .map { it.toDomainModel(numberCard) }
                        //  .onErrorComplete()
                        .subscribe {
                            with(controller) {
                                infos = infos
                                    .toMutableList()
                                    .apply {
                                        if(this.size != 0) clear()
                                        add(it)
                                    }
                                    .toList()
                                this.requestModelBuild()
                            }
                        }
                        .addTo(disposable)



                    resultConnectableObservalbe
                      //  .onErrorComplete()
                        .subscribe {
                            repository.add(it,numberCard)
                        }
                        .addTo(disposable)

//                    resultConnectableObservalbe.subscribe {
//                    //disable loading
//                    }
//                        .addTo(disposable)
//
//                    resultConnectableObservalbe
//                        .subscribe {
//                            // write db
//                        }
//                        .addTo(disposable)


//                        .subscribe(
//                            { info ->
//                                //controller.infos = listOf(info)
//                                with(controller) {
//                                    infos = infos
//                                        .toMutableList()
//                                        .apply { add(info) }
//                                        .toList()
//                                    this.requestModelBuild()
//                                }
//
//                            },
//                            {
//
//                            }
//                        )

//                        .enqueue(object : Callback<BankInfoServerModel> {
//                            override fun onResponse(
//                                call: Call<BankInfoServerModel>,
//                                response: Response<BankInfoServerModel>
//                            ) {
//                                val a =  response.body()?.toBankCardInfo()!!
//
//                                with(controller) {
//                                   infos =  infos
//                                        .toMutableList()
//                                        .apply { add(a) }
//                                        .toList()
//
//                                }
//                                controller.requestModelBuild()
//                                Log.e("***", response.body().toString())
//                            }
//
//                            override fun onFailure(call: Call<BankInfoServerModel>, t: Throwable) {
//                                Log.e("***", "error $t")
//                            }
//                        })
                }
            }
        }
        history.apply {
            setOnClickListener{
                val intent = Intent(this@MainActivity, viewHistory::class.java)
                startActivity(intent)
            }
        }
    }
    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) =
    compositeDisposable.add(this)