package photon.raju.com.a20180418_ry_nycschools.schooldetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import photon.raju.com.a20180418_ry_nycschools.R
import photon.raju.com.a20180418_ry_nycschools.Utills.Util
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolDetailData

class DetailActivity : AppCompatActivity(), DetailView {

    private var detailActivityPresenter : DetailActivityPresenter?= null
    private var schoolID : String ?= null
    private var schoolName : String ?= null
    private var schoolAddress : String ?= null
    private var schoolPhone : String ?= null
    private var schoolDescription : String ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent data is coming from SchoolListActivity
        val intent = intent
        if (intent != null){
            schoolID = intent.getStringExtra(Util.SCHOOL_ID)
            schoolName = intent.getStringExtra(Util.SCHOOL_NAME)
            schoolAddress = intent.getStringExtra(Util.SCHOOL_ADDRESS)
            schoolPhone = intent.getStringExtra(Util.SCHOOL_PHONE)
            schoolDescription = intent.getStringExtra(Util.SCHOOL_DESCRIPTION)
        }
        Log.e("Raju", "SchoolID "+schoolID)
        detailActivityPresenter = DetailActivityPresenter(this, schoolID)

    }

    override fun initializeView() {
        if (!TextUtils.isEmpty(schoolName)) {
            school_name.visibility = View.VISIBLE
            school_name.text = schoolName

        }
        if (!TextUtils.isEmpty(schoolAddress)) {
            school_address.visibility = View.VISIBLE
            school_address.text = "Address: " + schoolAddress
        }
        if (!TextUtils.isEmpty(schoolDescription)) {
            school_description.visibility = View.VISIBLE
            school_description.text = schoolDescription
        }

        if (!TextUtils.isEmpty(schoolPhone)) {
            school_phone_no.visibility = View.VISIBLE
            school_phone_no.text = "Call: " + schoolPhone
        }

    }

    override fun loadDetailData(schools: SchoolDetailData?) {
        Log.e("Raju ", "data "+schools.toString())
        if (schools != null){
            sat_math_score.text = "Math : "+schools.dSchoolMath
            sat_reading_score.text = "Reading : "+schools.dSchoolReading
            sat_writing_score.text = "Writing : "+schools.dSchoolWriting
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        detailActivityPresenter?.Destroy();
    }
}
