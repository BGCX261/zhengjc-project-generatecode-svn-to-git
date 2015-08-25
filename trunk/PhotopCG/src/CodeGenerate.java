import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.ParametersSelection;
import model.ProcessingLogic;
import util.DateUtil;
import util.FileUtil;

public class CodeGenerate {

	private static final String AUTHOR = "#Author#";
	private static final String EMAIL = "#Email#";
	private static final String DATE = "#Date#";
	private static final String REPORT = "#Report#";
	private static final String OBJECT = "#Object#";
	private static final String TABLES = "#Tables#";
	private static final String SELECTION_SCREEN = "#Selection_Screen#";
	private static final String DO_SO = "#Do_So#";
	private static final String RANGES = "#Ranges#";
	private static final String TYPE_DATA = "#Type_Data#";
	private static final String FILL_FIELDCAT = "#Fill_Fieldcat#";
	private static final String INTERNAL_TABLE = "#Internal_Table#";
	private static final String INTERNAL_GET_TABLE = "#Internal_Get_Table#";
	private static final String INTERNAL_SET_TABLE = "#Internal_Set_Table#";

	private List<String> nameList = new ArrayList<String>();

	private String author;
	private String email;
	private String date;
	private String report;
	private String object;
	private String fileName;
	private String annotation;
	private String separate;
	private String abapDataType;
	private String variableType;
	private Boolean checkoutFile;
	private String checkoutRegex;
	private String separateSpace;
	private String separateOther;
	private String tableCharacter;

	private String do_c_status;
	private Set<String> tableSet = new HashSet<String>();
	private Set<String> dataTableSet = new HashSet<String>();
	// 选择屏幕的相关参数对象列表
	private List<ParametersSelection> psList = new ArrayList<ParametersSelection>();
	// 显示ALV屏幕的相关参数对象列表
	private List<ProcessingLogic> plList = new ArrayList<ProcessingLogic>();

	private StringBuffer pselection = new StringBuffer();

	CodeGenerate() {
		// String uri = ResourcePath.getAppPath(CodeGenerate.class)
		// + "/config/common.properties";
		InputStream is = CodeGenerate.class
				.getResourceAsStream("/config/common.properties");
		Configuration rc = new Configuration(is);// 相对路径

		author = rc.getValue("Author");// 以下读取properties文件的值"郑建诚";//
		email = rc.getValue("Email");// "dahaha@189";//
		annotation = rc.getValue("Annotation").trim();// 注释的符号集
		if ("".equals(annotation)) {
			annotation = "#*\"";
		}
		separate = rc.getValue("Separate");
		separateOther = separate;
		// abap的常规数据类型
		abapDataType = rc.getValue("AbapDataType");
		// 数据字段名称的正则表达式
		variableType = rc.getValue("VariableType");
		checkoutFile = Boolean.valueOf(rc.getValue("CheckoutFile"));
		checkoutRegex = rc.getValue("CheckoutRegex");
		separateSpace = rc.getValue("SeparateSpace");
		tableCharacter = rc.getValue("TableCharacter");
		date = DateUtil.getToday();
		rc.setValue(  "regeditDate", date);
		
	}

	public void readGenerateFile(String filePath) throws IOException {

		String reportStr = fileToString(CodeGenerate.class
				.getResourceAsStream("/resource/r.rp"));
		String objectStr = fileToString(CodeGenerate.class
				.getResourceAsStream("/resource/c.clz"));

		File folder = new File(filePath);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				if (!"TXT".equalsIgnoreCase(FileUtil.ext(file))) {
					continue;
				}

				fileName = file.getName().substring(0,
						file.getName().length() - 4);
				if (checkoutFile) {
					if (!fileName.matches(checkoutRegex)) {
						continue;
					}
				}
				nameList.add(fileName);
				/*对全局变量进行一次初始化*/
				do_c_status = "";
				tableSet = new HashSet<String>();
				dataTableSet = new HashSet<String>();				
				psList = new ArrayList<ParametersSelection>();
				plList = new ArrayList<ProcessingLogic>();
				pselection = new StringBuffer();
				
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				for (String line = br.readLine(); line != null; line = br
						.readLine()) {
					if (0 != line.length()) {
						do_txt_context(line);
					}

				}
				br.close();

				File generate_File = new File(".\\" + fileName);
				if (!generate_File.exists()) {
					generate_File.mkdirs();
				}

				// 创建Report文件
				File generate_reportFile = new File(".\\" + fileName + "\\"
						+ fileName + ".report");
				this.createReportFile(generate_reportFile, fileName, reportStr);
				// 创建Object文件
				File generate_objectFile = new File(".\\" + fileName + "\\"
						+ object + ".object");
				this.createObjectFile(generate_objectFile, object, objectStr);

			}
		}

	}

	private void do_txt_context(String line) {
		line = line.trim();
		if ("".equals(line)) {
			return;
		}
		line = line.replaceAll( tableCharacter , separateSpace );
		String regEx = "[' ']+"; // 一个或多个空格
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(line);
		line = m.replaceAll(" ").trim();
		
		// 判断是否有 | 或者 # 如果没有就用空格做分割
		if (!line.contains("#") && !line.contains("|")) {
			separate = separateSpace;
		} else {
			separate = separateOther;
		}

		String begChar = line.substring(0, 1);
		if (annotation.contains(begChar)) {
			line = line.substring(1, line.length()).trim();
			if ("".equals(line)) {
				return;
			}

			if ("SCREEN".equalsIgnoreCase(line) || line.toUpperCase().contains("SCREEN")) {
				do_c_status = "Screen";
			} else if ("LOGIC".equalsIgnoreCase(line) || line.toUpperCase().contains("LOGIC")) {
				do_c_status = "Logic";
			} else if ("AUTHOR".equalsIgnoreCase(line) || line.toUpperCase().contains("AUTHOR")) {
				do_c_status = "Author";
			} else if ("EMAIL".equalsIgnoreCase(line) || line.toUpperCase().contains("EMAIL")) {
				do_c_status = "Email";
			} else if ("REPORT".equalsIgnoreCase(line) || line.toUpperCase().contains("REPORT")) {
				do_c_status = "Report";
			}
		} else {
			if ("Author".equalsIgnoreCase(do_c_status)) {
				author = line;
			} else if ("Email".equalsIgnoreCase(do_c_status)) {
				email = line;
			} else if ("Report".equalsIgnoreCase(do_c_status)) {
				report = line;
				fileName = report;
			}
			// 处理数据逻辑的内容字段
			else if ("Logic".equalsIgnoreCase(do_c_status)) {
				ProcessingLogic pl = new ProcessingLogic();
				String[] pla = line.split(separate);
				if (pla.length >= 0) {
					pl.setName(pla[0].trim());
					String tableNameTmp = pla[1].trim();
					pl.setTable(tableNameTmp);
					if (!"".equals(tableNameTmp)
							&& tableNameTmp.toUpperCase().matches(abapDataType)) {
						pl.setIsDataType(true);
					} else {
						pl.setIsDataType(false);
						if (!"".equals(pl.getTable().trim())) {
							dataTableSet.add(pl.getTable());
						}
					}
					String columTmp = pla[2].trim();
					if (!"".equals(columTmp)) {
						if (columTmp.matches(variableType)) {
							pl.setIsCommonColumn(false);
						}
					}

					pl.setColumn(columTmp);

					if (pla.length >= 4) {
						pl.setAlv(pla[3].trim());
					} else {
						pl.setAlv("");
					}
					if (pla.length >= 5) {
						pl.setNozero(pla[4].trim());
					} else {
						pl.setNozero("");
					}

					plList.add(pl);
				}

			} else if ("Screen".equalsIgnoreCase(do_c_status)) {
				ParametersSelection ps = new ParametersSelection();
				String[] psa = line.split(separate, 4);
				if (psa.length >= 0) {
					ps.setName(psa[0].trim());
					ps.setType(psa[1].trim());
					if (psa.length >= 3) {
						if (!"".equals(psa[2])) {
							if (psa[2].contains("-")) {
								String[] temps = psa[2].trim().split("-");
								ps.setTable(temps[0]);
								if (temps.length >= 2) {
									ps.setColumn(temps[1]);
								} else {
									ps.setColumn("");
								}

								if (!"".equals(ps.getTable().trim())) {
									tableSet.add(ps.getTable().trim());
								}
							}else {
								ps.setTable("" );
								ps.setColumn(psa[2]);
							}

						} else {
							ps.setTable(psa[2]);
							ps.setColumn("");
						}
					}

					if (psa.length >= 4) {
						ps.setStatus(psa[3]);
					}
					psList.add(ps);

				}
			}
		}
	}

	private String fileToString(File file) {
		BufferedInputStream br = null;
		byte[] buffer = null;
		try {
			br = new BufferedInputStream(new FileInputStream(file));
			buffer = new byte[br.available()];
			br.read(buffer, 0, buffer.length);
			br.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return new String(buffer);

	}

	private String fileToString(InputStream in) {
		BufferedInputStream br = null;
		byte[] buffer = null;
		try {
			br = new BufferedInputStream(in);
			buffer = new byte[br.available()];
			br.read(buffer, 0, buffer.length);
			br.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
		return new String(buffer);

	}

	private void createReportFile(File reportFile, String moduleName,
			String content) {
		String do_so = "";
		try {
			// 报表名称
			report = moduleName;
			// 类的文件名称
			object = report + "_01";
			FileWriter fileWriter = new FileWriter(reportFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			content = content.replace(AUTHOR, author);
			content = content.replace(EMAIL, email);
			content = content.replace(DATE, date);
			content = content.replace(REPORT, report);

			for (int i = 0; i < psList.size(); i++) {
				ParametersSelection ps = psList.get(i);
				String tmpS = ps.getPS();
				pselection.append(tmpS);
				if ("S".equals(ps.getType())) {
					do_so = do_so + "  " + ps.getColumnScPc() + "[] = "
							+ ps.getColumnSP() + "[].\r\n";

				} else {
					do_so = do_so + "  " + ps.getColumnScPc() + " = "
							+ ps.getColumnSP() + ".\r\n";
				}
			}

			content = content.replace(DO_SO, do_so);

			content = content.replace(SELECTION_SCREEN, pselection.toString());
			if (!tableSet.isEmpty()) {
				String tableStr = "";
				for (String s : tableSet) {
					tableStr = tableStr + s + ",";
				}

				tableStr = tableStr.substring(0, tableStr.length() - 1);
				if (tableStr.length() > 0) {
					content = content.replace(TABLES, tableStr);
				}
			}
			// 类的文件名称
			content = content.replace(OBJECT, object);

			fileWriter.write(content);
			fileWriter.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createObjectFile(File objectFile, String moduleName,
			String content) {
		try {
			FileWriter fileWriter = new FileWriter(objectFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			content = content.replace(AUTHOR, author);
			content = content.replace(EMAIL, email);
			content = content.replace(DATE, date);
			content = content.replace(OBJECT, object);
			String objectS = pselection.toString();
			objectS = objectS.replaceAll("SELECT-OPTIONS", "RANGES");
			objectS = objectS.replaceAll("PARAMETERS", "DATA");
			objectS = objectS.replaceAll("RADIOBUTTON", "TYPE C.    \"RADIOBUTTON");
			objectS = objectS.replaceAll(" AS CHECKBOX DEFAULT 'X' USER-COMMAND CHECK.", ".    \"CHECKBOX");
			objectS = objectS.replaceAll(" AS LISTBOX.", ".    \"LISTBOX ");
			 
			objectS = objectS.replaceAll(" OBLIGATORY", "");
			objectS = objectS.replaceAll("P_", "PC_");
			objectS = objectS.replaceAll("S_", "SC_");
			content = content.replace(RANGES, objectS);
			StringBuffer plsb = new StringBuffer();
			StringBuffer plalvsb = new StringBuffer();
			for (int i = 0; i < plList.size(); i++) {
				ProcessingLogic pl = plList.get(i);
				plsb.append(pl.getPL());
				plalvsb.append(pl.getALV());

			}
			content = content.replace(TYPE_DATA, plsb.toString());
			content = content.replace(FILL_FIELDCAT, plalvsb.toString());

			// 定义获取数据的内表
			String dataTableStr = "";
			String getDataTableStr = "";
			String setDataTableStr = "";
			for (String s : dataTableSet) {
				String tableName = s.trim().toLowerCase();
				// 定义内表
				String tmp = "    \"定义table内表 \r\n"
						+ "    DATA: it_table TYPE TABLE OF table,\r\n"
						+ "          wa_table TYPE table.\r\n\r\n";
				tmp = tmp.replace("table", tableName);
				dataTableStr = dataTableStr + tmp;
				// 获取内表数据
				String tmpData = "   \"获取table内表数据\r\n"
						+ "    IF gt_data[] IS NOT INITIAL.\r\n"
						+ "      CLEAR:it_table,wa_table.\r\n"
						+ "*      SELECT\r\n"
						+ "*          *\r\n"
						+ "*        FROM table AS a\r\n"
						+ "*        INTO CORRESPONDING FIELDS OF TABLE it_table\r\n"
						+ "*        FOR ALL ENTRIES IN gt_data\r\n"
						+ "*        WHERE a~ = gt_data-\r\n"
						+ "*         AND a~ = gt_data-\r\n" + "          .\r\n"
						+ "    ENDIF.\r\n"
						+ "*    SORT it_table BY * ASCENDING.\r\n\r\n";

				tmpData = tmpData.replace("table", tableName);
				getDataTableStr = getDataTableStr + tmpData;

				String tmpSetData = "      \"table数据 赋值给gt_data\r\n"
						+ "*      READ TABLE it_table INTO wa_table WITH KEY * = wa_data-* BINARY SEARCH.\r\n"
						+ "*      IF sy-subrc = 0.\r\n"
						+ "*        wa_data-* = wa_lfas-*.\r\n"
						+ "*      ELSE.\r\n"
						+ "**        DELETE gt_data INDEX lv_tabix.\r\n"
						+ "**        CONTINUE.\r\n" + "*      ENDIF.\r\n\r\n";
				tmpSetData = tmpSetData.replace("table", tableName);
				setDataTableStr = setDataTableStr + tmpSetData;
			}
			content = content.replace(INTERNAL_TABLE, dataTableStr);
			content = content.replace(INTERNAL_GET_TABLE, getDataTableStr);
			content = content.replace(INTERNAL_SET_TABLE, setDataTableStr);
			fileWriter.write(content);
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String message = "代码生成成功!";
		CodeGenerate codeGenerateCode = new CodeGenerate();
		try {
			codeGenerateCode.readGenerateFile(".\\");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message = "代码生成失败，请查看异常信息！";
		}
		javax.swing.JOptionPane.showConfirmDialog((java.awt.Component) null,
				message, "提示",
				javax.swing.JOptionPane.DEFAULT_OPTION);

	}

}
