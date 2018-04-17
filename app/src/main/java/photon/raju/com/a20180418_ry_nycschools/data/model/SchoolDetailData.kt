package photon.raju.com.a20180418_ry_nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SchoolDetailData (@SerializedName("dbn") val dSchoolId: String,
                             @SerializedName("num_of_sat_test_takers") val dSchoolSatTestTaker: String,
                             @SerializedName("sat_critical_reading_avg_score") val dSchoolReading: String,
                             @SerializedName("sat_math_avg_score") val dSchoolMath: String,
                             @SerializedName("sat_writing_avg_score") val dSchoolWriting: String,
                             @SerializedName("school_name") val dSchoolName: String)

/*
{
    "dbn": "01M292",
    "num_of_sat_test_takers": "29",
    "sat_critical_reading_avg_score": "355",
    "sat_math_avg_score": "404",
    "sat_writing_avg_score": "363",
    "school_name": "HENRY STREET SCHOOL FOR INTERNATIONAL STUDIES"
  }
*/