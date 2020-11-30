

package yyyl.show.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import yyyl.show.model.User;
import yyyl.show.view.UserView;
import zuo.biao.library.base.BaseAdapter;

/**用户adapter
 * @author Lemon
 */
public class UserAdapter extends BaseAdapter<User, UserView> {
	//	private static final String TAG = "UserAdapter";

	public UserAdapter(Activity context) {
		super(context);
	}

	@Override
	public UserView createView(int position, ViewGroup parent) {
		return new UserView(context, parent);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

}