package cubex.mahesh.mvp_feb9am19.presenter

import cubex.mahesh.mvp_feb9am19.beans.IncExpBean

interface IncExpPresenterAPI {
    fun  save(bean:IncExpBean)
    fun  read()
}