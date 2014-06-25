package zarch.project.selectclass;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;

public class MainActivity extends Activity
{
	
	String[] listOfClass ={"Sec001", "Sec002", "Sec003", "Sec004", "Sec005", "Sec006", "Sec007", "Sec008"};
	List<ClassName> myClassList = new ArrayList<ClassName>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.main);
		populateListOfClass();
    }
	
	private void populateListOfClass(){
		for(int i = 0; i < listOfClass.length; i++){
			myClassList.add(new ClassName(listOfClass[i]));
		}
		
		ArrayAdapter<ClassName> adapter = new MyArrayAdapter(this);
		ListView list = (ListView) findViewById(R.id.myListId);
		list.setAdapter(adapter);
	}
	
	private class MyArrayAdapter extends ArrayAdapter{
		public MyArrayAdapter(Context c){
			super(MainActivity.this, R.layout.class_list, myClassList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View myView = convertView;
			if(myView == null){
				myView = getLayoutInflater().inflate(R.layout.class_list, parent, false);
			}
			
			ClassName cl = myClassList.get(position);
			TextView name = (TextView) myView.findViewById(R.id.class_listTextView);
			name.setText(cl.getClassName());
			return myView;
		}
	}
}

class ClassName{
	private String className;

	public ClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}
}
