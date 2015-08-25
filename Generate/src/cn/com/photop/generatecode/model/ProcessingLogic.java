package cn.com.photop.generatecode.model;

public class ProcessingLogic {
	private String name;
	private String table;
	private String column;
	private String alv = "";
	private String nozero;
	private Boolean isDataType = false;
	private Boolean isCommonColumn = true;

	public Boolean getIsCommonColumn() {
		return isCommonColumn;
	}

	public void setIsCommonColumn(Boolean isCommonColumn) {
		this.isCommonColumn = isCommonColumn;
	}

	public Boolean getIsDataType() {
		return isDataType;
	}

	public void setIsDataType(Boolean isDataType) {
		this.isDataType = isDataType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getAlv() {
		return alv;
	}

	public void setAlv(String isAlv) {
		alv = isAlv;
	}

	public String getNozero() {
		return nozero;
	}

	public void setNozero(String isNozero) {
		nozero = isNozero;
	}
	
	public String getPL(){
		String tmp = "";
		String column = getColumn().toUpperCase();
		String table = getTable().toUpperCase();
		String name = getName().toUpperCase();
		if( getIsDataType() ){
			tmp = "  " + column + " TYPE " + table + ", \"" + name + "\r\n";
		}else{
			if( table.toUpperCase().startsWith("SY-") ){
				tmp = "  " + column + " LIKE " + table + ", \"" + name + "\r\n";
			}else{
				tmp = "  " + column + " LIKE " + table + "-" + column + ", \"" + name + "\r\n";	
			}
				
		}
		
		return tmp;
	}
	
	public String getALV(){
		String tmp = "";
		String columnTmp = getColumn().trim().toUpperCase();
		if( !getIsCommonColumn() ){
			int index = columnTmp.indexOf( "(" );
			columnTmp = columnTmp.substring( 0,index );
		}
		if ( "".equals( getAlv().trim() ) ){
			if("x".equalsIgnoreCase( getNozero().trim() ) 
			|| "true".equalsIgnoreCase( getNozero().trim() )){
				tmp = "    fill_fieldcat:  \'" + columnTmp + "\'   \'\'  \'" + getName() + "\' \'\' \'\' \'\' \'\' \'X\' \'\'.\r\n";				
			}else{
				tmp = "    fill_fieldcat:  \'" + columnTmp + "\'   \'\'  \'" + getName() + "\' \'\' \'\' \'\' \'\' \'\' \'\'.\r\n";	
			}
		}
		return tmp;
	}
	
}
