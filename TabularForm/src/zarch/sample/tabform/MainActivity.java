package zarch.sample.tabform;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import java.util.*;
import javax.crypto.*;
import android.view.*;
import android.graphics.*;

public class MainActivity extends Activity
{
	
	LinearLayout layout;
	List<SheetList> myList = new ArrayList<SheetList>();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		populateList();
		populateListLayout();
    }

	private void populateListLayout(){
		Display display = getWindowManager().getDefaultDisplay();
		int width = display.getWidth();
		
		layout = (LinearLayout) findViewById(R.id.layout);
		LayoutParams myRowLayout = new LayoutParams(width, LayoutParams.WRAP_CONTENT);
		
		for(int row = 0; row < myList.size(); row++){
			LinearLayout rowLayout = new RowLayout(this, row);
			if(row == 0){
				rowLayout.setBackgroundColor(Color.parseColor("#7E433F"));
			}else if(row > 0){
				if(row % 2 == 0){
					rowLayout.setBackgroundColor(Color.parseColor("#7E433F"));
				}
			}
			layout.addView(rowLayout, myRowLayout);
		}
	}

	private void populateList(){
		myList.add(new SheetList("Clores, Archie", "100", "100", "100", "100"));
		myList.add(new SheetList("Lacanilao, Jennifer Lyn", "0", "0", "0", "0"));
		myList.add(new SheetList("Escarpe, Kenny James", "85", "90", "81", "95"));
		myList.add(new SheetList("Gabriel, Rihanna Jessie", "50", "51", "52", "70"));
		myList.add(new SheetList("Gabriel, Renz Justin", "75", "78", "80", "85"));
		myList.add(new SheetList("Medenilla, Gina", "50", "50", "50", "50"));
	}
	
	private class RowLayout extends LinearLayout{
		
		LinearLayout layout;
		
		public RowLayout(Context c, int position){
			super(c);
			layout = (LinearLayout) inflate(c, R.layout.layout_inflate, this);
			getView(position);
		}
		
		public void getView(int position){
			SheetList list = myList.get(position);
			
			TextView name = (TextView) layout.findViewById(R.id.name);
			name.setText(list.getName());
			
			TextView hwork = (TextView) layout.findViewById(R.id.hwork);
			hwork.setText("" + list.getAssignment());
			
			TextView swork = (TextView) layout.findViewById(R.id.swork);
			swork.setText("" + list.getSeatwork());
			
			TextView bwork = (TextView) layout.findViewById(R.id.bwork);
			bwork.setText("" + list.getBoardwork());
			
			TextView recite = (TextView) layout.findViewById(R.id.recite);
			recite.setText("" + list.getRecitation());
			
		}
	}
}

class SheetList{
	private String name;
	private String assignment;
	private String seatwork;
	private String boardwork;
	private String recitation;

	public SheetList(String name, String hw, String sw, String bw, String recite){
		this.name = name;
		this.assignment = hw;
		this.seatwork = sw;
		this.boardwork = bw;
		this.recitation = recite;
	}

	public String getName()
	{
		return name;
	}

	public String getAssignment()
	{
		return assignment;
	}

	public String getSeatwork()
	{
		return seatwork;
	}

	public String getBoardwork()
	{
		return boardwork;
	}

	public String getRecitation()
	{
		return recitation;
	}
}
