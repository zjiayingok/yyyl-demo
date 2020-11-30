

package zuo.biao.library.model;

import android.content.Intent;

/**菜单类
 * @author Lemon
 */
public class Menu {

	private String name;
	private int imageRes;
	private int operationType;
	private Intent operationIntent;
	private int intentCode;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	public Menu(String name) {
		this();
		this.name = name;
	}
	public Menu(String name, int imageRes) {
		this(name);
		this.imageRes = imageRes;
	}
	public Menu(String name, int imageRes, int intentCode) {
		this(name, imageRes);
		this.intentCode = intentCode;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImageRes() {
		return imageRes;
	}
	public void setImageRes(int imageRes) {
		this.imageRes = imageRes;
	}
	public int getOperationType() {
		return operationType;
	}
	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
	public Intent getOperationIntent() {
		return operationIntent;
	}
	public void setOperationIntent(Intent operationIntent) {
		this.operationIntent = operationIntent;
	}
	public int getIntentCode() {
		return intentCode;
	}
	public void setIntentCode(int intentCode) {
		this.intentCode = intentCode;
	}
	
}
