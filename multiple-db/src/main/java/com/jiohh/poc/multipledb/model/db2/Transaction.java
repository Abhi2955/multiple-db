package com.jiohh.poc.multipledb.model.db2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="transactions")
@Data
public class Transaction {
	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Long id;

	 @Column(name="transaction_id", nullable = false)
	 private String transactionId;
	 
	 @Column(name="emp_id", nullable = false)
	 private String employeeId;
	 
	 @Column(name="amount", nullable = false)
	 private String amount;
	 
	 @Column(name="status", nullable = false)
	 private String status;

	@Override
	public String toString() {
		return "\n{id=" + id + ", transactionId=" + transactionId + ", employeeId=" + employeeId + ", amount="
				+ amount + ", status=" + status + "}";
	}
}