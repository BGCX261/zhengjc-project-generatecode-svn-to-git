package model;


public class ParametersSelection {
	private String name;
	private String type;
	private String table;
	private String column;
	private String status;
	private static int number = 0;

	public String getName() {
		return name.toUpperCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTable() {
		return table.toUpperCase();
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getColumn() {
		return column.toUpperCase();
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getColumnSP() {
		String tmp = "";
		if ("S".equalsIgnoreCase(getType())) {
			
			tmp = "S_" + getColumn();
		} else {
			tmp = "P_" + getColumn();
		}
		return tmp;

	}

	public String getColumnScPc() {
		String tmp = "";
		if ("S".equalsIgnoreCase(getType())) {
			
			tmp = "SC_" + getColumn();
		} else {
			tmp = "PC_" + getColumn();
		}
		return tmp;

	}

	public String getPS() {
		String tmpS = "";
		if ("S".equalsIgnoreCase(getType())) {
			tmpS = "  SELECT-OPTIONS: " + "S_" + getColumn() + " FOR "
					+ getTable() + "-" + getColumn();
			if ("M".equalsIgnoreCase(getStatus())) {
				tmpS = tmpS + " OBLIGATORY.";
			} else {
				tmpS = tmpS + ".";
			}
			tmpS = tmpS + "    \"" + getName() + "\r\n";

		} else if ("P".equalsIgnoreCase(getType())){
			tmpS = "  PARAMETERS: " + "P_" + getColumn() + " LIKE "
					+ getTable() + "-" + getColumn();
			if ("M".equalsIgnoreCase(getStatus())) {
				tmpS = tmpS + " OBLIGATORY.";
			} else {
				tmpS = tmpS + ".";
			}
			tmpS = tmpS + "    \"" + getName() + "\r\n";

		}else if ("RB".equalsIgnoreCase(getType())){
//			PARAMETERS: p1 RADIOBUTTON GROUP g1,
			if ( getStatus() != null && !"".equals( getStatus() ) ){
				tmpS = "  PARAMETERS: " + "P_" + getColumn() + " RADIOBUTTON GROUP " + getStatus() + ".";
				tmpS = tmpS + "    \"" + getName() + "\r\n";
			}else{
				number ++;
				tmpS = "  PARAMETERS: " + "P_" + getColumn() + " RADIOBUTTON GROUP G" + number + ".";
				tmpS = tmpS + "    \"" + getName() + "\r\n";
			}
			
		}else if ("CB".equalsIgnoreCase(getType())){
//			PARAMETERS: P_CB1 TYPE C AS CHECKBOX DEFAULT 'X' USER-COMMAND CHECK.
			tmpS = "  PARAMETERS: " + "P_" + getColumn() + " TYPE C AS CHECKBOX DEFAULT 'X' USER-COMMAND CHECK.";
			tmpS = tmpS + "    \"" + getName() + "\r\n";
		}else if ("LB".equalsIgnoreCase(getType())){
//			PARAMETERS: P_LIST1(10) TYPE C AS LISTBOX.
			tmpS = "  PARAMETERS: " + "P_" + getColumn() + " TYPE C AS LISTBOX VISIBLE LENGTH 255.";
			tmpS = tmpS + "    \"" + getName() + "\r\n";
		}
		
		
		
		return tmpS;
	}

}
