/**   
 * @project Generate
 * @Title: StringUtil.java 
 * @Package cn.com.photop.generatecode.util 
 * @author Jiancheng.Zheng
 * @email dahaha@189.cn
 * @date 2013-7-10 ����08:24:12 
 * @Copyright photop.cn.Co.,Ltd
 * @All right reserved
 * @version V1.0   
 * @Description: TODO(��һ�仰�������ļ���ʲô) 
 *
 */
package cn.com.photop.generatecode.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: TODO(������һ�仰��������������)
 * @author Jiancheng.Zheng
 * @email dahaha@189.cn
 * @date 2013-7-10 ����08:24:12
 * 
 */
public class StringUtil {

	/**
	 * 
	 * @Title: full2HalfChange(������һ�仰�����������������)
	 * @Description: ȫ��ת��ǵ� ת������
	 * @param @param QJstr
	 * @param @return �趨�ļ�
	 * @return String ��������
	 * @throws
	 * 
	 */
	public static final String full2HalfChange(String QJstr) {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			// ȫ�ǿո�ת���ɰ�ǿո�
			if (Tstr.equals("��")) {
				outStrBuf.append(" ");
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				// �õ� unicode �ֽ�����
				if (b[2] == -1) {
					// ��ʾȫ��
					b[3] = (byte) (b[3] + 32);
					b[2] = 0;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} // end for.
		return outStrBuf.toString();

	}

	/**
	 * 
	 * @Title: half2Fullchange(������һ�仰�����������������)
	 * @Description: ���תȫ��
	 * @param @param QJstr
	 * @param @return �趨�ļ�
	 * @return String ��������
	 * @throws
	 * 
	 */
	public static final String half2Fullchange(String QJstr) {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			if (Tstr.equals(" ")) {
				// ��ǿո�
				outStrBuf.append(Tstr);
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				if (b[2] == 0) {
					// ���
					b[3] = (byte) (b[3] - 32);
					b[2] = -1;
					outStrBuf.append(new String(b, "unicode"));
				} else {
					outStrBuf.append(Tstr);
				}
				return outStrBuf.toString();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return outStrBuf.toString();
	}

	/**
	 * 
	 * @Title: ToSBC(������һ�仰�����������������)
	 * @Description: ���תȫ��
	 * @param @param input
	 * @param @return �趨�ļ�
	 * @return String �������� ȫ���ַ���.
	 * @throws
	 * 
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}

	/**
	 * 
	 * @Title: ToDBC(������һ�仰�����������������)
	 * @Description: ȫ��ת���
	 * @param @param input
	 * @param @return �趨�ļ�
	 * @return String �������� ����ַ���
	 * @throws
	 * 
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);

			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * @Title: main(������һ�仰�����������������)
	 * @Description: TODO(������һ�仰�����������������)
	 * @param @param args �趨�ļ�
	 * @return void ��������
	 * @throws
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "�ɹ������б�ע	��c	��Ordertxt1(255)";
//		str = StringUtil.ToDBC( str );
//		System.out.println( str );
////		str = str.replaceAll( "	","");
//		System.out.println( str );
		
		String line = "����|P|MARC -   WERKS|M";
		String regEx = "[' ']+"; // һ�������ո�
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(line);
		line = m.replaceAll(" ").trim();
		System.out.println( line );
		regEx = "[' ']*-[' ']*"; // һ�������ո�
		p = Pattern.compile(regEx);
		m = p.matcher(line);
		line = m.replaceAll("-").trim();
		System.out.println( line );
	}

}
