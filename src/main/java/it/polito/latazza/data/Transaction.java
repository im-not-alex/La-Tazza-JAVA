package it.polito.latazza.data;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class Transaction {
	private Timestamp d;
	private Integer total;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  

	
	public Timestamp getD() {
		return d;
	}
	/*public void setD(Timestamp d) {
		this.d = d;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}*/
	public Integer getTotal() {
		return total;
	}
	public String getDateFormat() {
		return dateFormat.format(d);
	}
	public Transaction(Integer total,Timestamp d) {
		super();
		this.d = d;
		this.total = total;
		
	}
}
