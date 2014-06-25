package zarch.project.gradecomputations;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.graphics.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	
	List<AddField> myField = new ArrayList<AddField>();
	Button addBtn, deleteBtn, useBtn;
	TextView appendText;
	private int selectedPosition;
	private String selectedString;
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		setButtonClick();
		setTextSetup();
		populatrListItems();
		populateList();
		onItemCallBack();
    }
	
	private void populatrListItems(){
		myField.add(new AddField("Quizzes"));
		myField.add(new AddField("Exams"));
		myField.add(new AddField("Homework"));
		myField.add(new AddField("Seatwork"));
		myField.add(new AddField("Boardwork"));
	}
	
	private void populateList(){
		ArrayAdapter<AddField> adapter = new MyAdapter();
		ListView mylist = (ListView) findViewById(R.id.grading_list);
		mylist.setAdapter(adapter);
	}
	
	private void setButtonClick(){
		addBtn = (Button) findViewById(R.id.addField);
		deleteBtn = (Button) findViewById(R.id.deleteField);
		useBtn = (Button) findViewById(R.id.useField);
		addBtn.setOnClickListener(this);
		deleteBtn.setOnClickListener(this);
		useBtn.setOnClickListener(this);
	}
	
	private void setTextSetup(){
		appendText = (TextView) findViewById(R.id.appendText);
	}
	
	@Override
	public void onClick(View btnView){
		ListView onClickList = (ListView) findViewById(R.id.grading_list);
		if(btnView.getId() == R.id.addField){
			myField.add(new AddField("Homework"));
			((ArrayAdapter) onClickList.getAdapter()).notifyDataSetChanged();
		}else if(btnView.getId() == R.id.deleteField){
			if(myField.isEmpty() == false){
				AddField field = myField.get(getSelectedPosition());
				this.selectedString = field.getAddfieldname();
				myField.remove(getSelectedPosition());
				((ArrayAdapter) onClickList.getAdapter()).notifyDataSetChanged();
				selectedPosition = 0;
			}else if(myField.isEmpty() == true){
				Toast.makeText(MainActivity.this, "nothing to delete", Toast.LENGTH_SHORT).show();
			}
		}else if(btnView.getId() == R.id.useField){
			if(getSelectedString() != null){
				appendText.setText(getSelectedString());
			}
		}
	}
	
	private void onItemCallBack(){
		ListView myLV = (ListView) findViewById(R.id.grading_list);
		myLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					selectedPosition = position;
					AddField field = myField.get(getSelectedPosition());
					selectedString = field.getAddfieldname();
					Toast.makeText(MainActivity.this, "Postion: [" + getSelectedPosition() + "] String: [" + getSelectedString() + "]", Toast.LENGTH_SHORT).show();
				}
		});
	}
	
	public int getSelectedPosition(){
		return this.selectedPosition;
	}
	
	public String getSelectedString(){
		return this.selectedString;
	}

	private class MyAdapter extends ArrayAdapter{
		
		public MyAdapter(){
			super(MainActivity.this, R.layout.grade_field_list, myField);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View lview = convertView;
			if(lview == null){
				lview = getLayoutInflater().inflate(R.layout.grade_field_list, parent, false);
			}
			
			AddField addfield = myField.get(position);
			
			TextView text = (TextView) lview.findViewById(R.id.grade_listItem);
			text.setText(addfield.getAddfieldname());
			
			return lview;
		}
		
	}
}

class AddField{
	private String addfieldname;

	public AddField(String addfieldname){
		this.addfieldname = addfieldname;
	}

	public String getAddfieldname(){
		return addfieldname;
	}
}
