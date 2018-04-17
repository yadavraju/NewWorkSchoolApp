package photon.raju.com.a20180418_ry_nycschools.schooldetail;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolDetailData;
import photon.raju.com.a20180418_ry_nycschools.data.service.ApiService;
import photon.raju.com.a20180418_ry_nycschools.data.service.SchoolService;


/**
 * Todo Used Null Object pattern for schoolDetailListView ,
 * A null object replaces check of NULL object instance.
 * Instead of putting if check for a null value,
 * Null Object reflects a do nothing relationship.
 * Such Null object can also be used to provide default behaviour
 * in case data is not available.
 * Todo https://en.wikipedia.org/wiki/Null_object_pattern
 * <p>
 * Created by Raju Yadav
 */
public class DetailActivityPresenter {

    private static final String TAG = DetailActivityPresenter.class.getSimpleName();
    // This full fill null object pattern
    private DetailView nullSchoolDetailListView = new DetailView() {
        @Override
        public void initializeView() {/*ignore*/}

        @Override
        public void loadDetailData(SchoolDetailData schools) {/*ignore*/}

    };
    private DetailView schoolDetailListView;
    private Disposable schoolDetailListDisposable;
    private Disposable filteredDataDisposable;

    public DetailActivityPresenter(DetailView schoolDetailListView, String schoolID) {
        this.schoolDetailListView = schoolDetailListView != null ? schoolDetailListView : nullSchoolDetailListView;
        initializeView(schoolID);
    }

    private void initializeView(String schoolID) {
        schoolDetailListView.initializeView();
        loadSchoolData(schoolID);
    }

    private void loadSchoolData(String schoolID) {
        Log.e(TAG, "id"+schoolID);
        SchoolService schoolService = ApiService.INSTANCE.schoolListView();
        if (schoolService != null) {
            schoolDetailListDisposable = schoolService.getSchoolDetail()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(schoolList -> {
                        getFilterDetailData(schoolList,schoolID);
                    }, error -> Log.e(TAG, "Failed to download list of school"));
        }
    }

    private void getFilterDetailData(ArrayList<SchoolDetailData>schoolList, String schoolId) {
        filteredDataDisposable = Observable.just(schoolList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(schoolDetailDatas -> {
                    for (SchoolDetailData schoolDetailData : schoolDetailDatas) {
                        if (schoolDetailData.getDSchoolId().equalsIgnoreCase(schoolId)) {
                            schoolDetailListView.loadDetailData(schoolDetailData);
                            break;
                        }
                    }

                });
    }

    public void Destroy() {
        schoolDetailListView = null;
        if (schoolDetailListDisposable != null){
            schoolDetailListDisposable.dispose();
        }
        if (filteredDataDisposable != null){
            filteredDataDisposable.dispose();
        }
    }
}
