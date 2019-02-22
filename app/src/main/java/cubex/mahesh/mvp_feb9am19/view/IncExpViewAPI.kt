package cubex.mahesh.mvp_feb9am19.view

import android.database.Cursor

interface IncExpViewAPI {
    fun saveResponse(msg:String)
    fun readResponse(c:Cursor)
}