package photon.raju.com.a20180418_ry_nycschools.schoollist;

import android.util.Log;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolData;
import photon.raju.com.a20180418_ry_nycschools.data.service.ApiService;
import photon.raju.com.a20180418_ry_nycschools.data.service.SchoolService;

/**
 * Todo Used Null Object pattern for SchoolListView ,
 * A null object replaces check of NULL object instance.
 * Instead of putting if check for a null value,
 * Null Object reflects a do nothing relationship.
 * Such Null object can also be used to provide default behaviour
 * in case data is not available.
 * Todo https://en.wikipedia.org/wiki/Null_object_pattern
 * <p>
 * Created by Raju Yadav
 */

public class SchoolListPresenter {
    private static final String TAG = SchoolListPresenter.class.getSimpleName();
    // This full null object pattern
    private SchoolListView nullSchoolListView = new SchoolListView() {
        @Override
        public void initializeView() {/*ignore*/}

        @Override
        public void loadLoadList(ArrayList<SchoolData> schools) {/*ignore*/}

        @Override
        public void showProgress() {/*ignore*/}

        @Override
        public void hideProgress() {/*ignore*/}

        @Override
        public void showMessage(String message) {/*ignore*/}
    };
    private SchoolListView schoolListView;
    private Disposable schoolListDisposable;

    public SchoolListPresenter(SchoolListView schoolListView) {
        this.schoolListView = schoolListView != null ? schoolListView : nullSchoolListView;
        initializeView();
    }

    private void initializeView() {
        schoolListView.initializeView();
        loadSchoolData();
    }

    private void loadSchoolData() {
        schoolListView.showProgress();
        SchoolService schoolService = ApiService.INSTANCE.schoolListView();
        if (schoolService != null) {
            schoolListDisposable = schoolService.getSchoolList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(schoolList -> {
                        schoolListView.loadLoadList(schoolList);
                        schoolListView.hideProgress();
                    }, error -> {
                        Log.e(TAG, "Failed to download list of school");
                        schoolListView.hideProgress();
                    });
        }
    }

    public void Destroy() {
        schoolListView = null;
        if (schoolListDisposable != null){
            schoolListDisposable.dispose();
        }
    }
}

