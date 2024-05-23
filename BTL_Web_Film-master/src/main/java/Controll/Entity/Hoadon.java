package Controll.Entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Hoadon")
public class Hoadon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String TxnRef;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "movieId", referencedColumnName = "id")
	private Movie movie;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;

	private String OrderInfo;

	private String ResponseCode;

	private String TransactionNo;

	private String BankCode;

	private String Amount;

	private Timestamp PayDate;

	public String getTxnRef() {
		return TxnRef;
	}

	public void setTxnRef(String TxnRef) {
		this.TxnRef = TxnRef;
	}

	public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOrderInfo() {
		return OrderInfo;
	}

	public void setOrderInfo(String OrderInfo) {
		this.OrderInfo = OrderInfo;
	}

	public String getResponseCode() {
		return ResponseCode;
	}

	public void setResponseCode(String ResponseCode) {
		this.ResponseCode = ResponseCode;
	}

	public String getTransactionNo() {
		return TransactionNo;
	}

	public void setTransactionNo(String TransactionNo) {
		this.TransactionNo = TransactionNo;
	}

	public String getBankCode() {
		return BankCode;
	}

	public void setBankCode(String BankCode) {
		this.BankCode = BankCode;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String Amount) {
		this.Amount = Amount;
	}

	public Timestamp getPayDate() {
		return PayDate;
	}

	public void setPayDate(Timestamp PayDate) {
		this.PayDate = PayDate;
	}

}
