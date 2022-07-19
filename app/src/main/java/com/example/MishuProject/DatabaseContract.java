package com.example.MishuProject;

import android.provider.BaseColumns;

public final class DatabaseContract {
    // to not allow for someone to instantiate this class by accident
    private DatabaseContract(){}


    /** Shot Data Table. */
    public static final class ShotTABLE implements BaseColumns {
        public static final String TABLE_NAME = "Shot_Data";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_OPERATOR = "operator";
        public static final String COLUMN_PROFILE = "profile";
        public static final String COLUMN_STARTTIME="profileStartTime";

        // shot number, shot time ( to add : lat/long)
        public static final String COLUMN_SHOTNUMBER = "shotNumber";
        public static final String COLUMN_TIME = "time";

        //public static final String COLUMN_LATITUDE = "latitude";
        //public static final String COLUMN_LONGITUDE = "longitude";
        // public static final String COLUMN_GPSTIME = "GPS time";


    }
    /** Forms Data Table. */
    public static final class FormsTABLE implements BaseColumns{
        public static final String TABLE_NAME = "Form_Data";
        public static final String COLUMN_OPERATOR = "operator";
        public static final String COLUMN_PROFILE = "profile";
        public static final String COLUMN_STARTTIME="profileStartTime";

        //SOL / TEREN
        public static final String COLUMN_TIP="tip";
        public static final String COLUMN_CULTURA="cultura";
        public static final String COLUMN_TIP_SOL="tipSol";
        public static final String COLUMN_SUPRAFATA="suprafata";
        public static final String COLUMN_USCAT="uscat";
        public static final String COLUMN_PRIZA="priza";

        // ZGOMOT
        public static final String COLUMN_VANT="vant";
        public static final String COLUMN_TRAFIC="trafic";

        //FORAJ
        public static final String COLUMN_LOCALIZAT="localizat";
        public static final String COLUMN_PICHET="pichet";
        public static final String COLUMN_DISTANCE="distance";

        //OTHER
        public static final String COLUMN_OTHER_COMMENTS="otherComments";


    }

}
