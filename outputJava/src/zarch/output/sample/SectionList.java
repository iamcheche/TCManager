package zarch.output.sample;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class SectionList extends Activity
{

	List<Section> section = new ArrayList<Section>();

    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.section_list_layout);

		setListView();
		populateFinalSection();
	}

	private void populateFinalSection(){
		section.add(new Section("ELEMENTARY", "10001"));
		section.add(new Section("HIGHSCHOOL", "10002"));
		section.add(new Section("TERTIARY", "10003"));
	}

	private void setListView(){
		ArrayAdapter<Section> adapter = new CategoryAdapter();
		ListView mySecList = (ListView) findViewById(R.id.category_listview);
		mySecList.setAdapter(adapter);
	}

	private class CategoryAdapter extends ArrayAdapter{
		public CategoryAdapter(){
			super(SectionList.this, R.layout.section_item_layout, section);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View itemView = convertView;
			if(itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.section_item_layout, parent, false);
			}

			Section secItemPos = section.get(position);

			TextView sectionName = (TextView) itemView.findViewById(R.id.category_item_id);
			sectionName.setText(secItemPos.getSectionName());

			return itemView;
		}
	}
}

class Section{
	private String sectionName;
	private String sectionID;

	public Section(String sectionName, String sectionID)
	{
		this.sectionName = sectionName;
		this.sectionID = sectionID;
	}

	public String getSectionName()
	{
		return sectionName;
	}

	public String getCategoryID()
	{
		return sectionID;
	}
}



