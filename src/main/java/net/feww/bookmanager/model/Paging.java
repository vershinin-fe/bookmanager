package net.feww.bookmanager.model;

public class Paging {
    public static final int MAX_LINES_PER_PAGE = 5;

    private Long currentCount = 0L;
    private int currentPage = 0;

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPagesCount() {
        if(currentCount % MAX_LINES_PER_PAGE > 0){
            return currentCount.intValue() / MAX_LINES_PER_PAGE + 1;
        } else {
            return currentCount.intValue() / MAX_LINES_PER_PAGE;
        }
    }

    public boolean incrementPage(){
        if((currentCount - (currentPage + 1) * MAX_LINES_PER_PAGE) > 0){
            currentPage++;
            return true;
        }

        return false;
    }

    public boolean decrementPage(){
        if(currentPage > 0){
            currentPage--;
            return true;
        }

        return false;
    }
}
