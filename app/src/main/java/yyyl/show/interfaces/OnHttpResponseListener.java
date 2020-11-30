

package yyyl.show.interfaces;

/**网络请求回调接口
 * @author Lemon
 */
public interface OnHttpResponseListener {
	/**
	 * @param requestCode 请求码，自定义，在发起请求的类中可以用requestCode来区分各个请求
	 * @param resultCode 服务器返回状态码
	 * @param resultData 服务器返回的Json串
	 */
	void onHttpSuccess(int requestCode, int resultCode, String resultData);
	/**
	 * @param requestCode 请求码，自定义，在发起请求的类中可以用requestCode来区分各个请求
	 * @param e 请求异常
	 */
	void onHttpError(int requestCode, Exception e);
}