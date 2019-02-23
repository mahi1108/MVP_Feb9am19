package cubex.mahesh.mvp_feb9am19.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import cubex.mahesh.mvp_feb9am19.beans.IncExpBean
import cubex.mahesh.mvp_feb9am19.presenter.IncExpPresenterAPI
import cubex.mahesh.mvp_feb9am19.view.IncExpViewAPI
import cubex.mahesh.mvp_feb9am19.view.MainActivity

class IncExpModel(var api:IncExpViewAPI):IncExpPresenterAPI {

    var dBase :SQLiteDatabase ? = null
    var mActivity : MainActivity ?= null

    init {
            mActivity = api as MainActivity
            dBase = mActivity!!.openOrCreateDatabase(
   "and_9am_feb19", Context.MODE_PRIVATE,null)
            dBase?.execSQL("create table if not exists incexp(_id integer primary key autoincrement,date varchar(20),money integer,_desc varchar(500),type varchar(20) )")
    }
    override fun save(bean: IncExpBean) {
        var cv = ContentValues( )
        cv.put("date",bean.date)
        cv.put("money",bean.money)
        cv.put("_desc",bean.desc)
        cv.put("type",bean.type)
        var status = dBase?.insert("incexp",
            null,cv)
        if(status !== -1L)
            api.saveResponse("Row Inserted Successfully")
        else
            api.saveResponse("Row Insetion Failed..")
    }
    override fun read() {

        var c:Cursor? = dBase?.query("incexp",null,
            null,null,
                            null,null,null)
        api.readResponse(c!!)
    }
}