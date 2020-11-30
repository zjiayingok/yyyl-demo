

package yyyl.show.application;

import yyyl.show.manager.DataManager;
import yyyl.show.model.DaoMaster;
import yyyl.show.model.DaoSession;
import yyyl.show.model.User;
import zuo.biao.library.base.BaseApplication;
import zuo.biao.library.util.StringUtil;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

/**Application
 * @author Lemon
 */
public class DemoApplication extends BaseApplication {
	private static final String TAG = "DemoApplication";

	private static DemoApplication context;
	public static DemoApplication getInstance() {
		return context;
	}
	private DaoSession daoSession;
	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		// regular SQLite database
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "yiliao-db");
		Database db = helper.getWritableDb();

		// encrypted SQLCipher database
		// note: you need to add SQLCipher to your dependencies, check the build.gradle file
		// DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
		// Database db = helper.getEncryptedWritableDb("encryption-key");

		daoSession = new DaoMaster(db).newSession();

	}

	public DaoSession getDaoSession() {
		return daoSession;
	}
	/**获取当前用户id
	 * @return
	 */
	public long getCurrentUserId() {
		currentUser = getCurrentUser();
		Log.d(TAG, "getCurrentUserId  currentUserId = " + (currentUser == null ? "null" : currentUser.getId()));
		return currentUser == null ? 0 : currentUser.getId();
	}
	/**获取当前用户phone
	 * @return
	 */
	public String getCurrentUserPhone() {
		currentUser = getCurrentUser();
		return currentUser == null ? null : currentUser.getPhone();
	}


	private static User currentUser = null;
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = DataManager.getInstance().getCurrentUser();
		}
		return currentUser;
	}

	public void saveCurrentUser(User user) {
		if (user == null) {
			Log.e(TAG, "saveCurrentUser  currentUser == null >> return;");
			return;
		}
		if (user.getId() <= 0 && StringUtil.isNotEmpty(user.getName(), true) == false) {
			Log.e(TAG, "saveCurrentUser  user.getId() <= 0" +
					" && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
			return;
		}

		currentUser = user;
		DataManager.getInstance().saveCurrentUser(currentUser);
	}

	public void logout() {
		currentUser = null;
		DataManager.getInstance().saveCurrentUser(currentUser);
	}
	
	/**判断是否为当前用户
	 * @param userId
	 * @return
	 */
	public boolean isCurrentUser(long userId) {
		return DataManager.getInstance().isCurrentUser(userId);
	}

	public boolean isLoggedIn() {
		return getCurrentUserId() > 0;
	}



}
