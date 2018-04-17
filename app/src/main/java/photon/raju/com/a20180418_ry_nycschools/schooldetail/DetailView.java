package photon.raju.com.a20180418_ry_nycschools.schooldetail;

import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolDetailData;

public interface DetailView {
    void initializeView();
    void loadDetailData(SchoolDetailData schools);
}
