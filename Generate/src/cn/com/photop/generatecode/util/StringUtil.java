/**   
 * @project Generate
 * @Title: StringUtil.java 
 * @Package cn.com.photop.generatecode.util 
 * @author Jiancheng.Zheng
 * @email dahaha@189.cn
 * @date 2013-7-10 上午08:24:12 
 * @Copyright photop.cn.Co.,Ltd
 * @All right reserved
 * @version V1.0   
 * @Description: TODO(用一句话描述该文件做什么) 
 *
 */
package cn.com.photop.generatecode.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Jiancheng.Zheng
 * @email dahaha@189.cn
 * @date 2013-7-10 上午08:24:12
 * 
 */
public class StringUtil {

	/**
	 * 
	 * @Title: full2HalfChange(这里用一句话描述这个方法的作用)
	 * @Description: 全角转半角的 转换函数
	 * @param @param QJstr
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 * 
	 */
	public static final String full2HalfChange(String QJstr) {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;
		for (int i = 0; i < QJstr.length(); i++) {
			Tstr = QJstr.substring(i, i + 1);
			// 全角空格转换成半角空格
			if (Tstr.equals("　")) {
				outStrBuf.append(" ");
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				// 得到 unicode 字节数据
				if (b[2] == -1) {
					// 表示全角
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
	 * @Title: half2Fullchange(这里用一句话描述这个方法的作用)
	 * @Description: 半角转全角
	 * @param @param QJstr
	 * @param @return 设定文件
	 * @return String 返回类型
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
				// 半角空格
				outStrBuf.append(Tstr);
				continue;
			}
			try {
				b = Tstr.getBytes("unicode");
				if (b[2] == 0) {
					// 半角
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
	 * @Title: ToSBC(这里用一句话描述这个方法的作用)
	 * @Description: 半角转全角
	 * @param @param input
	 * @param @return 设定文件
	 * @return String 返回类型 全角字符串.
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
	 * @Title: ToDBC(这里用一句话描述这个方法的作用)
	 * @Description: 全角转半角
	 * @param @param input
	 * @param @return 设定文件
	 * @return String 返回类型 半角字符串
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
	 * @Title: main(这里用一句话描述这个方法的作用)
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param args 设定文件
	 * @return void 返回类型
	 * @throws
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String str = "采购申请行备注	　c	　Ordertxt1(255)";
//		str = StringUtil.ToDBC( str );
//		System.out.println( str );
////		str = str.replaceAll( "	","");
//		System.out.println( str );
		
		String line = "工厂|P|MARC -   WERKS|M";
		String regEx = "[' ']+"; // 一个或多个空格
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(line);
		line = m.replaceAll(" ").trim();
		System.out.println( line );
		regEx = "[' ']*-[' ']*"; // 一个或多个空格
		p = Pattern.compile(regEx);
		m = p.matcher(line);
		line = m.replaceAll("-").trim();
		System.out.println( line );
	}

}
