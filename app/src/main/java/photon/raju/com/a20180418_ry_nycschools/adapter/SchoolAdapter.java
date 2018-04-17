package photon.raju.com.a20180418_ry_nycschools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import photon.raju.com.a20180418_ry_nycschools.R;
import photon.raju.com.a20180418_ry_nycschools.data.model.SchoolData;

/**
 * Created by Raju Yadav
 */

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {
    private ArrayList<SchoolData> schoolData;
    private Context context;
    private SendDataToDetail nullSendDataToDetail = new SendDataToDetail() {
        @Override
        public void onSendDataToDetail(SchoolData schools) {/*Ignore*/}
    };
    private SendDataToDetail sendDataToDetail;

    public interface SendDataToDetail{
        void onSendDataToDetail(SchoolData schools);
    }
    public SchoolAdapter(ArrayList<SchoolData> schoolData, SendDataToDetail sendDataToDetail) {
        this.schoolData = schoolData;
        this.sendDataToDetail = sendDataToDetail !=null ? sendDataToDetail : nullSendDataToDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.school_adapter, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        SchoolData school = schoolData.get(position);
        if (!TextUtils.isEmpty(school.getSchoolName())) {
            holder.schoolName.setVisibility(View.VISIBLE);
            holder.schoolName.setText(school.getSchoolName());

        }
        if (!TextUtils.isEmpty(school.getLocation())) {
            holder.schoolAddress.setVisibility(View.VISIBLE);
            holder.schoolAddress.setText("Address: " + school.getLocation());
        }
        if (!TextUtils.isEmpty(school.getSchoolDes())) {
            holder.schoolDescription.setVisibility(View.VISIBLE);
            holder.schoolDescription.setText(school.getSchoolDes());
        }

        if (!TextUtils.isEmpty(school.getSchoolPhone())) {
            holder.schoolPhoneNo.setVisibility(View.VISIBLE);
            holder.schoolPhoneNo.setText("Call: " + school.getSchoolPhone());
        }

    }

    @Override
    public int getItemCount() {
        if (schoolData == null) {
            return 0;
        }
        return schoolData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.school_name)
        TextView schoolName;
        @BindView(R.id.school_address)
        TextView schoolAddress;
        @BindView(R.id.school_description)
        TextView schoolDescription;
        @BindView(R.id.school_phone_no)
        TextView schoolPhoneNo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            sendDataToDetail.onSendDataToDetail(schoolData.get(getLayoutPosition()));
        }
    }
}

