

package yyyl.show.DEMO;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import org.greenrobot.greendao.query.Query;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import yyyl.show.R;
import yyyl.show.adapter.NotesAdapter;
import yyyl.show.application.DemoApplication;
import yyyl.show.model.DaoSession;
import yyyl.show.model.Note;
import yyyl.show.model.NoteDao;
import yyyl.show.model.NoteType;
import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnBottomDragListener;


/** 使用方法：复制>粘贴>改名>改代码 */
/**数据库SQLite示例Activity
 * @author Lemon
 * @use toActivity(DemoSQLActivity.createIntent(...));
 */
public class DemoSQLActivity extends BaseActivity implements OnClickListener, OnBottomDragListener {
	private static final String TAG = "DemoSQLActivity";

	//启动方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


	/**启动这个Activity的Intent
	 * @param context
	 * @return
	 */
	public static Intent createIntent(Context context) {
		return new Intent(context, DemoSQLActivity.class);
	}

	//启动方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_sql_activity, this);
		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

//		showShortToast("点击[重置]按钮会恢复数据");
	}


	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private NoteDao noteDao;
	private Query<Note> notesQuery;
	private NotesAdapter notesAdapter;
	private EditText editText;
	private View addNoteButton;

	private void updateNotes() {
		List<Note> notes = notesQuery.list();
		notesAdapter.setNotes(notes);
		dismissProgressDialog();
	}

	private void addNote() {
		String noteText = editText.getText().toString();
		editText.setText("");

		final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		String comment = "Added on " + df.format(new Date());
		Note note = new Note();
		note.setText(noteText);
		note.setComment(comment);
		note.setDate(new Date());
		note.setType(NoteType.TEXT);
		noteDao.insert(note);
		Log.d(TAG, "Inserted new note, ID: " + note.getId());
		query();
	}


	@Override
	public void initView() {//必须在onCreate方法内调用

		RecyclerView recyclerView = findViewById(R.id.recyclerViewNotes);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		notesAdapter = new NotesAdapter(noteClickListener);
		recyclerView.setAdapter(notesAdapter);

		addNoteButton = findViewById(R.id.buttonAdd);
		addNoteButton.setEnabled(false);

		editText = findViewById(R.id.editTextNote);
		editText.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_DONE) {
				addNote();
				return true;
			}
			return false;
		});
		editText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				boolean enable = s.length() != 0;
				addNoteButton.setEnabled(enable);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}


	private void printAll() {
		runUiThread(new Runnable() {

			@Override
			public void run() {

			}
		});
		runThread(TAG + "printAll", new Runnable() {

			@Override
			public void run() {

			}
		});
	}


	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	@Override
	public void initData() {//必须在onCreate方法内调用
        // get the note DAO
		DaoSession daoSession = ((DemoApplication)getApplication()).getDaoSession();
		noteDao = daoSession.getNoteDao();
	}


	private void delete() {
		showProgressDialog("Deleting...");
		runThread(TAG + "delete", new Runnable() {
			@Override
			public void run() {
				runUiThread(new Runnable() {
					@Override
					public void run() {
						query();
					}
				});
			}
		});
	}
	private void update() {
		showProgressDialog("Updating...");
		runThread(TAG + "update", new Runnable() {

			@Override
			public void run() {

				runUiThread(new Runnable() {

					@Override
					public void run() {
						query();
					}
				});
			}
		});
	}
	private void query() {
		showProgressDialog("Querying...");
		notesQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
		updateNotes();
	}


	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须在onCreate方法内调用
		findView(R.id.buttonAdd).setOnClickListener(this);
	}

	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (rightToLeft) {
			return;
		}

		finish();
	}



	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonAdd:
			addNote();
			break;
		default:
			break;
		}
	}




	//生命周期、onActivityResult<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//生命周期、onActivityResult>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	NotesAdapter.NoteClickListener noteClickListener = new NotesAdapter.NoteClickListener() {
		@Override
		public void onNoteClick(int position) {
			Note note = notesAdapter.getNote(position);
			Long noteId = note.getId();

			noteDao.deleteByKey(noteId);
			Log.d("DaoExample", "Deleted note, ID: " + noteId);

			updateNotes();
		}
	};






	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}