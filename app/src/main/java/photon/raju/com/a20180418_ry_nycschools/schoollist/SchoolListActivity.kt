package photon.raju.com.a20180418_ry_nycschools.schoollist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_school_list.*
import kotlinx.android.synthetic.main.toolbar.*
import photon.raju.com.a20180418_ry_nycschools.R
import photon.raju.com.a20180418_ry_nycschools.Utills.Util
import photon.raju.com.a20180418_ry_nycschools.adapter.GridSpaceItemDecoration
import photon.raju.com.a20180418_ry_nycschools.adapter.SchoolAdapter
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolData
import photon.raju.com.a20180418_ry_nycschools.schooldetail.DetailActivity
import java.util.*

class SchoolListActivity() : AppCompatActivity(), SchoolListView, SchoolAdapter.SendDataToDetail {

    private var schoolListPresenter: SchoolListPresenter? = null
    private var schoolAdapter : SchoolAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_list)
        schoolListPresenter = SchoolListPresenter(this)
    }

    override fun initializeView() {
        toolbar.title = resources.getString(R.string.app_name)
        setSupportActionBar(toolbar)

        //Setting grid view for tablet and linear layout for mobile device
        val columnCount = resources.getInteger(R.integer.list_column_count)
        if (columnCount <= 1) list.layoutManager = LinearLayoutManager(this) else list.layoutManager = GridLayoutManager(this, columnCount)

        //Setting RecyclerView ItemDecoration
        list.addItemDecoration(GridSpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.card_spacing)))
    }

    override fun loadLoadList(schools: ArrayList<SchoolData>?) {
        //Setting RecyclerView adapter
        schoolAdapter = SchoolAdapter(schools, this)
        list.adapter = schoolAdapter
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showMessage(messge: String?) {
    }

    override fun onDestroy() {
        super.onDestroy()
        schoolListPresenter?.Destroy();
    }

    override fun onSendDataToDetail(schools: SchoolData?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(Util.SCHOOL_ID, schools?.schoolId)
        intent.putExtra(Util.SCHOOL_NAME, schools?.schoolName)
        intent.putExtra(Util.SCHOOL_ADDRESS, schools?.location)
        intent.putExtra(Util.SCHOOL_PHONE, schools?.schoolPhone)
        intent.putExtra(Util.SCHOOL_DESCRIPTION, schools?.schoolDes)
        startActivity(intent)
    }
}
