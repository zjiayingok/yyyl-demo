
package yyyl.show.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import yyyl.show.activity.UserActivity;
import zuo.biao.library.base.BaseView;

/**BaseView布局类，可直接写在layout文件内
 * @author Lemon
 * @param <T>
 * @see UserActivity#initView()
 * @use
 * <br>  BaseViewLayout<T> bvl = (BaseViewLayout<T>) findViewById(R.id.bvl);
 * <br>  bvl.createView(new ModelView(context, getResources()));
 * <br>  bvl.bindView(model);
 */
public class BaseViewLayout<T> extends FrameLayout {
	private static final String TAG = "BaseViewLayout";

	public BaseViewLayout(Context context) {
		super(context);
		init((Activity) context);
	}
	public BaseViewLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init((Activity) context);
	}
	public BaseViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init((Activity) context);
	}

	private Activity context;
	protected void init(Activity context) {
		this.context = context;
	}


	public BaseView<T> bv;
	public void createView(BaseView<T> bv) {
		this.bv = bv;

		removeAllViews();
		super.addView(bv.createView());
		bindView(null);
	}

	@Override
	public void addView(View child) {
		throw new UnsupportedOperationException(TAG + "不支持该方法");
	}

	public void bindView(T data) {
		bv.bindView(data);
	}

}
