package com.aleangelozi.mvppattern.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.aleangelozi.mvppattern.R
import com.aleangelozi.mvppattern.presenter.CountriesPresenter

class CountriesView: AppCompatActivity(), View {

    private val listValues: MutableList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>
    lateinit var list: ListView
    lateinit var presenter: CountriesPresenter
    lateinit var retryButton: Button
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        title = "Cities List"

        presenter = CountriesPresenter(this)

        list = findViewById(R.id.list)
        retryButton = findViewById(R.id.retryButton)
        progress = findViewById(R.id.progress)
        adapter = ArrayAdapter(this, R.layout.row_layout, R.id.listText, listValues)

        list.adapter = adapter
        list.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(
                    this@CountriesView, "You clicked " +
                            listValues[position], Toast.LENGTH_SHORT
                ).show()
            }
    }

    override fun setValues(countries: List<String>) {
        listValues.clear()
        listValues.addAll(countries)
        retryButton.visibility = android.view.View.GONE
        progress.visibility = android.view.View.GONE
        list.visibility = android.view.View.VISIBLE
        adapter.notifyDataSetChanged()
    }

    override fun onError() {
        Toast.makeText(
            this@CountriesView, getString(R.string.error_message), Toast.LENGTH_SHORT
        ).show()
        progress.visibility = android.view.View.GONE
        list.visibility = android.view.View.GONE
        retryButton.visibility = android.view.View.VISIBLE
    }

    fun onRetry(view: android.view.View) {
        presenter.onRefresh()
        list.visibility = android.view.View.GONE
        retryButton.visibility = android.view.View.GONE
        progress.visibility = android.view.View.VISIBLE
    }
}