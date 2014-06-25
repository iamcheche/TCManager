package zarch.proto.staticmethod;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class SectionActivity extends Activity 
{
	List<Sections> sections = new ArrayList<Sections>();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list_activity_layout);
		
		setUpdated();
		setSectionActivity();
		setListView();
		addButtonAction();
	}
	
	private void setUpdated(){
		
		Databank info = new Databank(this);
		String category = ListStatus.getCategory();
		info.open();
		String[] sectionList = info.getData(category);
		info.close();
		for(int i = 0; i < sectionList.length; i++){
			sections.add(new Sections(sectionList[i]));
		}
		
	}
	
	private void setSectionActivity(){
		TextView header = (TextView) findViewById(R.id.id_categoryName);
		String headerTitle = ListStatus.getCategory();
		header.setText(headerTitle);
	}
	
	private void setListView(){
		ArrayAdapter<Sections> adapter = new CustomListAdapter();
		ListView sectionList = (ListView) findViewById(R.id.id_listview_sections);
		sectionList.setAdapter(adapter);
	}
	
	private void addButtonAction(){
		Button addSection = (Button) findViewById(R.id.id_addbtn);
		addSection.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				
				boolean didWork = true;
				
				try{
					Databank addData = new Databank(SectionActivity.this);
					String category = ListStatus.getCategory();
					String sectionName = "Apolinario Mabini";
					addData.open();
					addData.createEntry(category, sectionName);
					addData.close();
					
					// notify the adapter
					sections.add(new Sections(sectionName));
					ListView sectionList = (ListView) findViewById(R.id.id_listview_sections);
					((ArrayAdapter) sectionList.getAdapter()).notifyDataSetChanged();
					
				}catch(Exception e){
					didWork = false;
					String error = e.toString();
					Dialog d = new Dialog(SectionActivity.this);
					d.setTitle("Error");
					TextView t = new TextView(SectionActivity.this);
					t.setText(error);
					d.setContentView(t);
					d.show();
				}finally{
					Dialog d = new Dialog(SectionActivity.this);
					d.setTitle("Insert Data...");
					TextView t = new TextView(SectionActivity.this);
					t.setText("Successfully Inserted");
					d.setContentView(t);
					d.show();
				}
			}
		});
	}
	
	private class CustomListAdapter extends ArrayAdapter{
		public CustomListAdapter(){
			super(SectionActivity.this, R.layout.section_itemlist_layout, sections);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if(view == null){
				view = getLayoutInflater().inflate(R.layout.section_itemlist_layout, parent, false);
			}
			
			Sections sectionPos = sections.get(position);
			
			TextView sectionName = (TextView) view.findViewById(R.id.id_section_name);
			sectionName.setText(sectionPos.getSectionName());
			
			return view;
		}
	
	}
}
	
class Sections{
	private String sectionName;

	public Sections(String sectionName)
	{
		this.sectionName = sectionName;
	}

	public String getSectionName()
	{
		return sectionName;
	}
}
