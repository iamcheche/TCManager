package zarch.sampleproject.gradecompulation;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity
{
	
	TextView text;
	
	List<Sections> class_section = new ArrayList<Sections>();
	List<Students> class_student = new ArrayList<Students>();
	
	private String[] sections = {"Section 1"};
	private int[] sectionId = {1};
	private String[] studentName = {"John Richard Serrano", "Archie Valdez", "Christina Misa", "Yugenesis Ibit", "Denisse De Vera"};
	private int[] studentId = {1, 2, 3, 4, 5};
	private String[] subjects = {"Filipino", "English", "Math", "Science", "PE", "History", "Values Education", "Scouting", "Practical Arts"};
	private String[] activities = {"Exam", "Quiz", "Homework", "Seatwork", "Boardwork", "Projects", "Special Projects"};
    
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		populateSection();
    }

	private void populateSection(){
		SectionListStatus.setSectionList(class_section);
		SectionListStatus.getSectionList().add(new Sections(sections[0]));
		Sections sectionsField = SectionListStatus.getSectionList().get(0);
		Sections sectionsField = class_section.get(0);
		 
		for(int i = 0; i < studentId.length; i++){
			class_student.add(new Students(studentName[i], studentId[i], sectionsField.getSection().toString()));
		}
		
		TextView text = (TextView) findViewById(R.id.scoreList);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < class_student.size(); i++){
			Students studentsField = class_student.get(i);
			sb.append(studentsField.getSection() + " | " + studentsField.getStudentId() + " | " + studentsField.getStudentName() + "\n\n");
		}
		
		text.setText(sb.toString());
		
		
	}
	
}

class Sections{
	private String section;
	
	public Sections(String section){
		this.section = section;
	}
	
	public String getSection(){
		return section;
	}
	
}

class SectionListStatus{
	private static List sectionList;

	public static void setSectionList(List sectionList)
	{
		SectionListStatus.sectionList = sectionList;
	}

	public static List getSectionList()
	{
		return sectionList;
	}
}

class Students{
	private String studentName;
	private int studentId;
	private String section;

	public Students(String studentName, int studentId, String section){
		this.studentName = studentName;
		this.studentId = studentId;
		this.section = section;
	}
	
	public String getStudentName()
	{
		return studentName;
	}

	public int getStudentId()
	{
		return studentId;
	}
	
	public String getSection()
	{
		return section;
	}
}

class Subjects{
	private static String subject;
	private static int studentId;

	public static void Subjects(String subject, Students studentId)
	{
		Subjects.subject = subject;
		Subjects.studentId = studentId.getStudentId();
	}

	public static String getSubject()
	{
		return subject;
	}
	
	public static int getStudentId()
	{
		return studentId;
	}
}

class ActivityFields{
	private static String activity;
	private static String subject;

	public static void ActivityFields(String activity, Subjects subject)
	{
		ActivityFields.activity = activity;
		ActivityFields.subject = subject.getSubject();
	}

	public static String getActivity()
	{
		return activity;
	}
	
	public static String getSubject()
	{
		return subject;
	}
}

class Fields{
	private static String fields;
	private static int score;
	private static String activity;

	public static void setFields(String fields, ActivityFields activity)
	{
		Fields.fields = activity.getActivity();
		Fields.activity = activity.getActivity();
	}

	public static String getFields()
	{
		return fields;
	}
	
	public static int getScore()
	{
		return score;
	}

	public static String getActivity()
	{
		return activity;
	}
}
