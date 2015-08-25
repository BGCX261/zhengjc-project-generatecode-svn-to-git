package cn.com.photop.generatecode.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GainMoudleData {
	
	private static final String moudlePath = "/cn/com/photop/generatecode/resource/zmdr0001.txt";
	
	private static final String commonPath = "/cn/com/photop/generatecode/resource/moudle/";
	
	private List<String> frameFileNames = new ArrayList<String>();
	

	/**
	 * 获取模板数据
	 */
	public GainMoudleData(){
//		frameFileNames.add( "ZCMNTOP" );
//		frameFileNames.add( "ZCMN0001" );
//		frameFileNames.add( "ZCMNCLS0001" );
//		frameFileNames.add( "ZCMNCLS0002" );
		
//		String strUrl = this.getClass().getResource( "/" ).getPath()+commonPath.substring(1);		
//		strUrl = strUrl.replaceAll("%20", " ");
//		File folder = new File( strUrl );
//		File[] files = null;
//		files = folder.listFiles();	
//		if( files != null ){
//			for ( File file:files ){
//				if( file.isFile() ){
//					int len = file.getName().trim().lastIndexOf(".");
//					String fileName = file.getName().substring( 0,len );
//					frameFileNames.add( fileName );
//				}
//			} 	
//		}
		
		InputStream is = CodeGenerate.class.getResourceAsStream( CodeGenerate.getPropertiespath() );
		Configuration rc = new Configuration(is);// 相对路径
		String moudles = rc.getValue("Moudle");// 以下读取properties文件的值"郑建诚";//
		if ( moudles != null ){
			String[] tmpStr = moudles.split("\\|");
			for( String fileName:tmpStr ){
				frameFileNames.add( fileName );
			}
		}
		
	}
	
	/**&
	 * 获取模板数据
	 * @return
	 */
	
	public String getModleString(){
		String dataStr = "";
		dataStr = fileToString(GainMoudleData.class.getResourceAsStream( moudlePath ));
	
		return dataStr;
	}
	
	/**&
	 * 获取模板数据
	 * @return
	 */
	
	public String getModleString( String fileName ){
		String dataStr = "";
		String path = commonPath + fileName + ".txt";
		dataStr = fileToString(CodeGenerate.class.getResourceAsStream( path ));
		
		return dataStr;
	}
	
	/**
	 * 获取文件名称
	 * @return
	 */
	public String getModleName(){
		File file = new File( moudlePath );
		String fileName = "";
		if( file != null ){
			fileName = file.getName();
			int potIndex = fileName.lastIndexOf(".");
			fileName = file.getName().substring(0,potIndex );	
		}
		return fileName;
	}
	
	/**
	 * 文件转为字符串
	 * @param in
	 * @return
	 */
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GainMoudleData gd = new GainMoudleData();
//		System.out.println( gd.getModleString() );
//		System.out.println( gd.getModleName() );
//		List<String> strList = gd.getFrameFileNames();
//		for ( String str:strList ){
//			System.out.println( str );
//			
//		}
		String str = gd.getModleString( "ZCMNCLS0001" ) ;
		
		System.out.println( gd.getModleString( "ZCMNCLS0001" ) );
		
//		URL url = GainMoudleData.class.getResource("/");
//		System.out.println( url );
//		System.out.println( url.getFile() );
//		System.out.println( url.getPath() );
//		System.out.println( url.get );
//		
//		System.out.println(gd.getModleString());
//		String strUrl = GainMoudleData.class.getResource("/").getPath()+commonPath;
//		strUrl = strUrl.replaceAll("%20", " ");
//		strUrl = "/D:/Workspaces/MyEclipse 8.5/Generate/bin/cn/com/photop/generatecode/resource/moudle/";
//		System.out.println( strUrl );
//		File folder = new File( strUrl );
//		File[] files = null;
//		files = folder.listFiles();
//		for ( File file:files ){
//			if( file.isFile() ){
//				String fileName = file.getName().substring(0,file.getName().length() - 4);
//				System.out.println( fileName );
//			}
//		} 
//		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
  
	}

	public List<String> getFrameFileNames() {
		return frameFileNames;
	}
	
}
