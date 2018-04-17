package photon.raju.com.a20180418_ry_nycschools.data.service;

import java.util.ArrayList;

import io.reactivex.Observable;
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolData;
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolDetailData;
import retrofit2.http.GET;

/**
 * Created by Raju Yadav
 */

public interface SchoolService {
    String BASE_URL = "https://data.cityofnewyork.us/";

    @GET("resource/97mf-9njv.json")
    Observable<ArrayList<SchoolData>> getSchoolList();

    @GET("resource/734v-jeq5.json")
    Observable<ArrayList<SchoolDetailData>> getSchoolDetail();
}
