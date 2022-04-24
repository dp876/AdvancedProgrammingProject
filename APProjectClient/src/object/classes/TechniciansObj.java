package object.classes;

import java.io.Serializable;

public class TechniciansObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idPrimaryKey;
	private String fullName;
	
	public TechniciansObj() {
		this.idPrimaryKey = 0;
		this.fullName = "";
	}
	
	public TechniciansObj(int idPrimaryKey, String fullName) {
		this.idPrimaryKey = idPrimaryKey;
		this.fullName = fullName;
	}
	
	public TechniciansObj(TechniciansObj obj) {
		this.idPrimaryKey = obj.idPrimaryKey;
		this.fullName = obj.fullName;
	}

	public int getIdPrimaryKey() {
		return idPrimaryKey;
	}

	public void setIdPrimaryKey(int idPrimaryKey) {
		this.idPrimaryKey = idPrimaryKey;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "idPrimaryKey: " + idPrimaryKey + "\nfullName: " + fullName + "\n";
	}
	
}
