/**
 * 
 */
package cn.com.photop.generatecode.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.photop.generatecode.model.AWTModule;
import cn.com.photop.generatecode.model.ParametersSelection;
import cn.com.photop.generatecode.model.ProcessingLogic;
import cn.com.photop.generatecode.util.StringUtil;

/**
 * @author jiancheng.zheng AWT���ɵķ�������
 */
public class AwtCodeGenerate extends CodeGenerate {

	private AWTModule aWTModule;
	private String exp_path;
	public AwtCodeGenerate() {
		super();

	}

	public AwtCodeGenerate(AWTModule aWTModule) {
		super();
		this.aWTModule = aWTModule;
		this.exp_path = aWTModule.getExp_path().trim();
		
	}

	/**
	 * ��������
	 */
	public String doGenerate() {
		String message = "�������ɳɹ�!";
		String mess = "";
		try {
			mess = readGenerateFile();
			if( mess.equalsIgnoreCase( "false" ) ){
				message = "��������ʧ�ܣ�������Ϣ���á��б���Ϣ���õĲ�������Ϊ��,���������������Ϣ��";	
			}else{
				message = message + mess;
			}
		} catch (IOException e) {
			message = "��������ʧ�ܣ���鿴�쳣��Ϣ��";
		}
		return message;
	}

	/**
	 * ��ȡ���������ļ�
	 * 
	 * @throws IOException
	 */
	public String readGenerateFile() throws IOException {
		String mess = "";
		String reportStr = fileToString(CodeGenerate.class
				.getResourceAsStream(getReportpath()));
		String objectStr = fileToString(CodeGenerate.class
				.getResourceAsStream(getObjectpath()));

		fileName = aWTModule.getFileName().trim();
		if( fileName == null || fileName.equals("") ){
			fileName = "ZMOUDLER0001";
			mess = "��������Ϊ��,ϵͳĬ�������ļ�����ZMOUDLER0001";
		}
		author = aWTModule.getAuthor().trim();
		email = aWTModule.getEmail().trim();
		report = fileName;
		
		/* ��ȫ�ֱ�������һ�γ�ʼ�� */
		do_c_status = "";
		tableSet = new HashSet<String>();
		dataTableSet = new HashSet<String>();
		psList = new ArrayList<ParametersSelection>();
		plList = new ArrayList<ProcessingLogic>();
		pselection = new StringBuffer();
		if( (aWTModule.getPs_str().trim() == null || aWTModule.getPs_str().trim().equals("")) 
				&&	( aWTModule.getAlv_str().trim() == null || aWTModule.getAlv_str().trim().equals("") )){
				mess = "false";//mess + "ѡ����桢ALV����Ĳ�������Ϊ��,���������������Ϣ��";
				return mess;
			}
		if( aWTModule.getPs_str().trim() == null || aWTModule.getPs_str().trim().equals("") ){
			mess = mess + "ѡ�����Ĳ�������Ϊ��,";
		}
		
		if( aWTModule.getAlv_str().trim() == null || aWTModule.getAlv_str().trim().equals("") ){
			mess = mess + "ALV����Ĳ���������ϢΪ��,";
		}
		
		
		
		//�����߼�������
		do_c_status = "Screen";
		String[] ps_stringArr = aWTModule.getPs_str().split("\n");
		for( String line:ps_stringArr ){
//			line = line.replaceAll( tableCharacter, " ").replace(qBlank, " ");
			do_txt_context(line);	
		}
		
		//������Ļ������
		do_c_status = "Logic";
		String[] alv_stringArr = aWTModule.getAlv_str().split("\n");
		for( String line:alv_stringArr ){
//			line = line.replaceAll( tableCharacter, " ").replace(qBlank, " ");
			do_txt_context(line);	
		}
		File generate_File = null;
		if (  exp_path != null && !"".equals( exp_path )){
			generate_File = new File(exp_path + "\\" + fileName);
		}else{
			generate_File = new File(".\\" + fileName);	
		}
		
		if (!generate_File.exists()) {
			generate_File.mkdirs();
		}
		
		if (  exp_path != null && !"".equals( exp_path )){
			// ����Report�ļ�
			File generate_reportFile = new File(exp_path +"\\" + fileName + "\\" + fileName
					+ ".report");
			createReportFile(generate_reportFile, fileName, reportStr);
			// ����Object�ļ�
			File generate_objectFile = new File(exp_path +"\\" + fileName + "\\" + object
					+ ".object");
			createObjectFile(generate_objectFile, object, objectStr); 
		}else{
			// ����Report�ļ�
			File generate_reportFile = new File(".\\" + fileName + "\\" + fileName
					+ ".report");
			createReportFile(generate_reportFile, fileName, reportStr);
			// ����Object�ļ�
			File generate_objectFile = new File(".\\" + fileName + "\\" + object
					+ ".object");
			createObjectFile(generate_objectFile, object, objectStr);	
		}
		
		return mess;

	}

	private void do_txt_context(String line) {
		line = StringUtil.full2HalfChange( line );
		line = line.trim();
		if ("".equals(line)) {
			return;
		}
		line = line.replaceAll(tableCharacter, separateSpace);
		String regEx = "[' ']+"; // һ�������ո�
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(line);
		line = m.replaceAll(" ").trim();

		// �ж��Ƿ��� | ���� # ���û�о��ÿո����ָ�
		if (!line.contains("#") && !line.contains("|")) {
			separate = separateSpace;
		} else {
			separate = separateOther;
		}

		String begChar = line.substring(0, 1);
		if (annotation.contains(begChar)) {
			return;
		} else {
			// ���������߼��������ֶ�
			if ("Logic".equalsIgnoreCase(do_c_status)) {
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
						String tabName = pl.getTable().trim(); 
						if (!"".equals( tabName ) && !"sy".equalsIgnoreCase( tabName ) ) {							
							dataTableSet.add( tabName );
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
				regEx = "[' ']*-[' ']*"; // ���� ��Ļ����  :���� TABLE - COLUMN 
				p = Pattern.compile(regEx);
				m = p.matcher(line);
				line = m.replaceAll("-").trim();
				
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
								String tabName = ps.getTable().trim(); 
								if (!"".equals(tabName) && !"sy".equalsIgnoreCase( tabName )) {
									tableSet.add(tabName);
								}
							} else {
								ps.setTable("");
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
