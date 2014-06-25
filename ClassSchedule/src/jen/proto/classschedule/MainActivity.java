package jen.proto.classschedule;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity
{
	List<Schedule> sched = new ArrayList<Schedule>();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		populateData();
		setCustomListView();
    }
	
	private void populateData(){
		sched.add(new Schedule("7:30-9:00am", "wed", "MSD002L1", "5401", "Technological Institute of the Philippines"));
		sched.add(new Schedule("9:00-10:30am", "wed", "C0012", "IT423", "Access Computer College"));
	}
	
	private void setCustomListView(){
		ArrayAdapter<Schedule> thisAdapter = new CustomAdapter();
		ListView classschedList = (ListView) findViewById(R.id.id_listview_classsched);
		classschedList.setAdapter(thisAdapter);
	}
	
	private class CustomAdapter extends ArrayAdapter{
		public CustomAdapter(){
			super(MainActivity.this, R.layout.layout_listview_classsched, sched);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			View listcustom = convertView;
			if(listcustom == null){
				listcustom = getLayoutInflater().inflate(R.layout.layout_listview_classsched, parent, false);
			}
			
			Schedule posSched = sched.get(position);
			
			TextView subject = (TextView) listcustom.findViewById(R.id.id_txt_subject);
			subject.setText(posSched.getSubject());
			
			TextView room = (TextView) listcustom.findViewById(R.id.id_txt_room);
			room.setText(posSched.getRoom());
			
			TextView schoolname = (TextView) listcustom.findViewById(R.id.id_txt_schoolname);
			schoolname.setText(posSched.getSchoolname());
			
			TextView curtime = (TextView) listcustom.findViewById(R.id.id_txt_schedtime);
			curtime.setText(posSched.getCurTime());
			
			return listcustom;
		}
		
	}
}

class Schedule{
	private String curtime;
	private String date;
	private String subject;
	private String room;
	private String schoolname;

	public Schedule(String curtime, String date, String subject, String room, String schoolname)
	{
		this.curtime = curtime;
		this.date = date;
		this.subject = subject;
		this.room = room;
		this.schoolname = schoolname;
	}

	public String getCurTime()
	{
		return curtime;
	}

	public String getDate()
	{
		return date;
	}

	public String getSubject()
	{
		return subject;
	}

	public String getRoom()
	{
		return room;
	}

	public String getSchoolname()
	{
		return schoolname;
	}
}
