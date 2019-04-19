package com.act360_android_list_me.helper;


public class Constants {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static final class DATABASE {

        public static final String DB_NAME = "yipl";
        public static final String TABLE_NAME = "list";
        public static final int DB_VERSION = 1;

        public static final String USER_ID = "userId";
        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String BODY = "body";


        public static final String DROP_QUERY = "DROP TABLE IF EXIST " + TABLE_NAME;

        public static final String GET_LIST_QUERY = "SELECT * FROM " + TABLE_NAME;

        public static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + "" +
                "(" + USER_ID + " INTEGER not null," +
                ID + " INTEGER not null," +
                TITLE + " TEXT not null," +
                BODY + " TEXT not null)";

    }
}
