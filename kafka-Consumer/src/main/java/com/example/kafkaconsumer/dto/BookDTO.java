package com.example.kafkaconsumer.dto;

import com.example.kafkaconsumer.model.Book;


import java.io.Serializable;
import java.util.Date;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String author;


	private String title;
	
	private Double price;
	
	private Date launchDate;
	
	
	public BookDTO() {
		super();
	}

	public BookDTO(Book obj) {
		super();
		this.id = obj.getId();
		this.author = obj.getAuthor();
		this.title = obj.getTitle();
		this.price = obj.getPrice();
		this.launchDate = obj.getLaunchDate();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getLaunchDate() {
		return launchDate;
	}

	public void setLaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}
	
	
	
	

}