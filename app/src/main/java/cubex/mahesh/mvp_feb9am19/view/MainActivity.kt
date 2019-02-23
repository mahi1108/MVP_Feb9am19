package cubex.mahesh.mvp_feb9am19.view

import android.database.Cursor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import cubex.mahesh.mvp_feb9am19.R
import cubex.mahesh.mvp_feb9am19.beans.IncExpBean
import cubex.mahesh.mvp_feb9am19.model.IncExpModel
import cubex.mahesh.mvp_feb9am19.presenter.IncExpPresenterAPI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IncExpViewAPI {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var model:IncExpPresenterAPI =
                                        IncExpModel(this@MainActivity)
        save.setOnClickListener {
            var bean = IncExpBean(et_date.text.toString(),
                et_mony.text.toString().toInt(),
                et_desc.text.toString(),
                sp_type.selectedItem.toString())
            model.save(bean)
        }
        read.setOnClickListener {
            model.read()
        }
    }

    override fun saveResponse(msg: String) {
        Toast.makeText(this@MainActivity,
                        msg,Toast.LENGTH_LONG).show()
    }

    override fun readResponse(c: Cursor) {
        var cAdapter = SimpleCursorAdapter(
            this@MainActivity,
            R.layout.indiview,c,
            arrayOf("date","money","_desc","type"),
            intArrayOf(R.id.date,R.id.money,R.id.desc,R.id.type),
            0)
        lview.adapter = cAdapter
        var iSum = 0
        var eSum = 0
        while (c.moveToNext()){
    if(c.getString(4).equals("income",true))
            iSum = iSum + c.getInt(2)
    else
            eSum =eSum + c.getInt(2)
        }
        tv_income.setText("Income Sum : $iSum")
        tv_expense.setText("Expense Sum : $eSum")
    }
}
