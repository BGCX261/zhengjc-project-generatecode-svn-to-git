package cn.com.photop.generatecode.model;

/**
 * AWT 传过来的参数
 * @author jiancheng.zheng
 *
 */
public class AWTModule {
	
	//文件夹名称
	private String fileName;
	//报表名称
	private String report;
	//对象名称 report + "_01"
	private String object;
	//作者
	private String author;
	//邮箱
	private String email;
	//选择界面参数
	private String ps_str;
	//展示AVL界面
	private String alv_str;
	//导出路径
	private String exp_path;
	
	public String getExp_path() {
		return exp_path;
	}
	public void setExp_path(String exp_path) {
		this.exp_path = exp_path;
	}
	// set get
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPs_str() {
		return ps_str;
	}
	public void setPs_str(String psStr) {
		ps_str = psStr;
	}
	public String getAlv_str() {
		return alv_str;
	}
	public void setAlv_str(String alvStr) {
		alv_str = alvStr;
	}
	
	
}
