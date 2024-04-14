package com.AnkitIndia.jwtauthentication.responseDTO;

import java.util.List;

import org.springframework.data.domain.Page;

import com.AnkitIndia.jwtauthentication.model.Item_master;

public class Paging_SortingDTO {
	
	private Page<Item_master> content;
	
	private int totalPages;
	
	private long totalElements;
	
	private int numberOfElements;
	
	private int  size;
	
	private int number;

	public Page<Item_master> getContent() {
		return content;
	}

	public void setContent(Page<Item_master> content) {
		this.content = content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Paging_SortingDTO() {
		super();
	}

	public Paging_SortingDTO(Page<Item_master> pagedCount, int totalPages, long totalElements, int numberOfElements,
			int size, int number) {
		super();
		this.content = pagedCount;
		this.totalPages = totalPages;
		this.totalElements = totalElements;
		this.numberOfElements = numberOfElements;
		this.size = size;
		this.number = number;
	}
	
	

}
