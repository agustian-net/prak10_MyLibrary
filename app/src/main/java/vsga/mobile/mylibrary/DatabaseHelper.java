package vsga.mobile.mylibrary;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";

    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_STUDENTS + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STUDENTS + "'");
        onCreate(db);
    }

    public long addStudentDetail(String student) {
        SQLiteDatabase db = this.getWritableDatabase();
        //membuat value konten
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, student);
        //memasukkan baris dalam table student
        long insert = db.insert(TABLE_STUDENTS, null, values);

        return insert;
    }

    public ArrayList<String> getALLStudentList() {
        ArrayList<String> studentArrayList = new ArrayList<>();
        String name="";
        String selectQuery = "SELECT * FROM " +TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        //looping through all row and adding to list
        if(c.moveToFirst()){
            do {
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                //adding to Student List
                studentArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", studentArrayList.toString());
        }
        return studentArrayList;
    }
}