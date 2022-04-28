package com.bank.backend.util;

public class DataResponsePagination<E, U> extends DataResponseList<E> {

	private Integer page;
	private Integer size;
	private Integer totalPages;
	private Long totalRowCount;
	private boolean lastPage;

	public DataResponsePagination(PaginationList<E, U> paginationList) {
		super(paginationList.getData());
		this.page = paginationList.getPage();
		this.size = paginationList.getSize();
		this.totalPages = paginationList.getTotalPages();
		this.totalRowCount = paginationList.getTotalRowCount();
		this.lastPage = (this.totalPages - 1) == this.page ? true : false;
	}

	public DataResponsePagination(boolean status, String message) {
		super(status, message);
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalRowCount() {
		return totalRowCount;
	}

	public void setTotalRowCount(Long totalRowCount) {
		this.totalRowCount = totalRowCount;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

}
