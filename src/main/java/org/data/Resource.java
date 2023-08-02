package org.data;

public class Resource {

    private final int page;
    private final int perPage;
    private final int total;
    private final int totalPages;

    public Resource(int page, int perPage, int total, int totalPages) {

        this.page = page;
        this.perPage = perPage;
        this.total = total;
        this.totalPages = totalPages;
    }

    public int getPage() {

        return page;
    }

    public int getPerPage() {

        return perPage;
    }

    public int getTotal() {

        return total;
    }

    public int getTotalPages() {

        return totalPages;
    }
}
