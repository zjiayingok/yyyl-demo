

package yyyl.show.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import yyyl.show.activity.UserActivity;
import yyyl.show.model.User;

/**UserView的布局类，可直接写在layout文件内。
 * *不如直接使用BaseViewLayout方便
 * @author Lemon
 * @see UserActivity#initView()
 * @use uvl = (BaseViewLayout<Model>) findViewById(R.id.uvl);
 *      uvl.bindView(model);
 */
public class UserViewLayout extends BaseViewLayout<User> {

	public UserViewLayout(Context context) {
		super(context);
	}
	public UserViewLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public UserViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void init(Activity context) {
		super.init(context);
		createView(new UserView(context, null));
	}

}
