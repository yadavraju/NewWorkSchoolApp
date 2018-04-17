package photon.raju.com.a20180418_ry_nycschools.schoollist;

import java.util.ArrayList;

import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolData;

/**
 * Created by Raju Yadav.
 */

public interface SchoolListView {

    void initializeView();
    void loadLoadList(ArrayList<SchoolData> schools);
    void showProgress();
    void hideProgress();
    void showMessage(String message);


}
