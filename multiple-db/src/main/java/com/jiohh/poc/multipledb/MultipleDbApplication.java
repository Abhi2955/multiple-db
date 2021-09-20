package com.jiohh.poc.multipledb;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jiohh.poc.multipledb.repository.db1.EmployeeRepository;
import com.jiohh.poc.multipledb.repository.db2.TransactionRepository;

@SpringBootApplication
public class MultipleDbApplication {
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	TransactionRepository transactionRepo;

	public static void main(String[] args) {
		SpringApplication.run(MultipleDbApplication.class, args);
	}
	
	@PostConstruct
	public void init() {
		long startTime = (long)0;
		long endTime = (long)0;
		long duration = (long)0;
		//
		System.out.println("Lodding 1000 Templates from s3 with single connection");
		startTime = System.nanoTime();
		getS3();
		endTime = System.nanoTime();
		duration = endTime-startTime;
		System.out.println("Programme Run In "+(duration/1000000)+" miliseconds \n\n");
		//
		System.out.println("Loding 1000 From DB 1 by 1");
		startTime = System.nanoTime();
		
		for(int i=0; i<1000; i++) {
			employeeRepo.findById((long)1);
		}
		
		endTime = System.nanoTime();
		duration = endTime-startTime;
		System.out.println("Programme Run In "+(duration/1000000)+" miliseconds \n\n");
		//
		System.out.println("Loading From DB At Once 1000+ records");
		startTime = System.nanoTime();
		employeeRepo.findAll();
		endTime = System.nanoTime();
		duration = endTime-startTime;
		System.out.println("Programme Run In "+(duration/1000000)+" miliseconds \n\n");
		//System.out.println("Fetching From Db1 "+employeeRepo.findAll());
		//System.out.println("Fetching From Db2 "+transactionRepo.findAll());
    }
	
	public static void getS3() {
		HttpURLConnection con = null;
        try {

        	try {
        		URL url = new URL("https://test-hh-001.s3.ap-south-1.amazonaws.com/test.html");
            	con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");
				con.setConnectTimeout(5000);
				con.setReadTimeout(5000);
				for(int i=0; i<1000; i++) {
					InputStream requestBodyInput = con.getInputStream(); 
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } finally {

            con.disconnect();
        }
	}

}
