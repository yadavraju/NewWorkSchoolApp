package photon.raju.com.a20180418_ry_nycschools.data.service

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Raju Yadav
 * Factory class for convenient creation of the Api Service interface
 */

object ApiService {
    private var schoolService: SchoolService? = null

    fun schoolListView(): SchoolService? {
        schoolService = Retrofit.Builder()
                .baseUrl(SchoolService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(SchoolService::class.java)
        return schoolService
    }
}
