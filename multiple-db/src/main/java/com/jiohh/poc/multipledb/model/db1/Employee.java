package com.jiohh.poc.multipledb.model.db1;

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
@Table(name="employee")
@Data
public class Employee {

	 @Id
	 @GeneratedValue(strategy= GenerationType.IDENTITY)
	 private Long id;

	 @Column(name="first_name", nullable = false)
	 private String transactionId;
	 
	 @Column(name="last_name", nullable = false)
	 private String amount;
	 
	 @Column(name="emp_id", nullable = false)
	 private String employeeId;

	@Override
	public String toString() {
		return "\n{id=" + id + ", transactionId=" + transactionId + ", amount=" + amount + ", employeeId="
				+ employeeId + "}";
	}
}
