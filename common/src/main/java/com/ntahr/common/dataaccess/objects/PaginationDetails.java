package com.ntahr.common.dataaccess.objects;

public class PaginationDetails {

    private long totalCount;
    private int totalPages;
    private int detailsPerPage;

    public PaginationDetails(long totalCount, int lastPageNumber, int detailsPerPage) {
        this.totalCount = totalCount;
        this.totalPages = lastPageNumber;
        this.detailsPerPage = detailsPerPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getLastPageNumber() {
        return totalPages;
    }

    public void setLastPageNumber(int lastPageNumber) {
        this.totalPages = lastPageNumber;
    }

    public int getDetailsPerPage() {
        return detailsPerPage;
    }

    public void setDetailsPerPage(int detailsPerPage) {
        this.detailsPerPage = detailsPerPage;
    }

    @Override
    public String toString() {
        return "PaginationDetails{" +
                "totalCount=" + totalCount +
                ", lastPageNumber=" + totalPages +
                ", detailsPerPage=" + detailsPerPage +
                '}';
    }
}
