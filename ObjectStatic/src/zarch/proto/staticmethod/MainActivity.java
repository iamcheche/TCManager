package zarch.proto.staticmethod;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.database.sqlite.*;
import android.database.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button primary = (Button) findViewById(R.id.id_primary);
		Button secondary = (Button) findViewById(R.id.id_secondary);
		Button tertiary = (Button) findViewById(R.id.id_tertiary);
		
		// set button listener
		primary.setOnClickListener(this);
		secondary.setOnClickListener(this);
		tertiary.setOnClickListener(this);
    }
	
	@Override
	public void onClick(View view)
	{
		switch(view.getId()){
			case R.id.id_primary:
				setSecondActivity("primary");
				break;
			case R.id.id_secondary:
				setSecondActivity("secondary");
				break;
			case R.id.id_tertiary:
				setSecondActivity("tertiary");
				break;
		}
	}
	
	private void setSecondActivity(String category){
		ListStatus.setCategory(category);
		Intent intent = new Intent(MainActivity.this, SectionActivity.class);
		startActivity(intent);
	}
}
	
class ListStatus{
	private static String category;
	
	public static void setCategory(String category){
		ListStatus.category = category;
	}

	public static String getCategory(){
		return category;
	}
}

//====================== database

class Databank
{
	public static final String KEY_CATID = "catID";
	public static final String KEY_SECTIONNAME = "secName";

	private static final String DB_NAME = "myDatabase";
	private static final String DB_TABLE = "mySection";
	private static final int DB_VERSION = 1;

	private DataWord myData;
	private final Context myContext;
	private SQLiteDatabase myDatabase;

	private static class DataWord extends SQLiteOpenHelper
	{

		public DataWord (Context context){
			super(context, DB_NAME, null, DB_VERSION);
		}

		public void onCreate(SQLiteDatabase p1)
		{
			p1.execSQL("CREATE TABLE " + DB_TABLE + " (" +
					   KEY_CATID + " TEXT NOT NULL, " +
					   KEY_SECTIONNAME + " TEXT NOT NULL);"
					   );
		}

		public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
		{
			p1.execSQL("DROP TABLE IF EXIST ");
			onCreate(p1);
		}
	}

	public Databank(Context c){
		myContext = c;
	}

	public Databank open() throws SQLiteException{
		myData = new DataWord(myContext);
		myDatabase = myData.getWritableDatabase();
		return this;
	}

	public void close(){
		myData.close();
	}

	public long createEntry(String id, String section)
	{
		ContentValues cv = new ContentValues();
		cv.put(KEY_CATID, id);
		cv.put(KEY_SECTIONNAME, section);
		return myDatabase.insert(DB_TABLE, null, cv);
	}

	public String[] getData(String categoryID)
	{
		String[] cols = new String[] {KEY_CATID, KEY_SECTIONNAME};
		Cursor c = myDatabase.query(DB_TABLE, cols, null, null, null, null, null);
		int count = 0, ite = 0;

		int id = c.getColumnIndex(KEY_CATID);
		int section = c.getColumnIndex(KEY_SECTIONNAME);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			if(categoryID.equals(c.getString(id))){
				count++;
			}
		}
		
		String[] result = new String [count];
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			if(categoryID.equals(c.getString(id))){
				result[ite] = c.getString(section);
				ite++;
			}
		}
		return result;
	}

	public String toSearch(String word)
	{
		String[] cols = new String[] {KEY_CATID, KEY_SECTIONNAME};
		Cursor c = myDatabase.query(DB_TABLE, cols, null, null, null, null, null);
		String result = "";
		int ite = 0;

		int iWord = c.getColumnIndex(KEY_CATID);
		int iDes = c.getColumnIndex(KEY_SECTIONNAME);

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			if(word.equals(c.getString(iWord))){
				result = /* myContext.getDatabasePath(Databank.DB_NAME).toString() + " " +*/ c.getString(iDes);
				c.isAfterLast();
			}else if(c.isAfterLast()){
				result = "No Match!";
			}
		}
		return result;
	}
}

