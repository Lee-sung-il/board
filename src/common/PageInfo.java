package common;

public class PageInfo {
    //    한 페이지에 보여줄 글의 개수
    private final int articleCountPerPage = 10;

    //    페이지 그룹 개수
    private final int pageGroupCount = 5;

    //    현재 페이지 번호
    private int nowPageNumber;

//    글의 총 개수
    private int totalArticleCount;

//        시작 글 번호
    private int startArticleNumber;

//    총 페이지 개수
    private int totalPageCount;


//    시작 페이지 번호
    private int startPageNumber;

//    끝 페이지 번호
    private int endPageNumber;

    private int prePageNumber;

    private int nextPageNumber;

    public PageInfo(int nowPageNumber,int totalArticleCount) {
        this.nowPageNumber = nowPageNumber;

        this.totalArticleCount = totalArticleCount;


//        페이지 개수 계산
        this.totalPageCount = (int) Math.ceil((double) this.totalArticleCount / this.articleCountPerPage);
        if (this.totalPageCount < 1){
            this.totalPageCount = 1;
        }
        if (nowPageNumber > totalPageCount) {
            this.nowPageNumber = totalPageCount;
        }
//        이전페이지 번호 계산
        this.prePageNumber = this.nowPageNumber - 1;
        if (this.prePageNumber < 1){
            this.prePageNumber = 1;
        }
//        다음 페이지 번호 계산
        this.nextPageNumber = this.nowPageNumber +1;
        if (this.nextPageNumber > this.totalPageCount) {
            this.nextPageNumber = this.totalPageCount;
        }

        this.startArticleNumber = (this.nowPageNumber - 1) * this.articleCountPerPage;



        this.startPageNumber = ((int) (((double) this.nowPageNumber / this.pageGroupCount + 0.9) - 1)) * this.pageGroupCount + 1;
        this.endPageNumber = this.startPageNumber + (this.pageGroupCount - 1);
        if (this.endPageNumber > this.totalPageCount) {
            this.endPageNumber = this.totalPageCount;
        }
    }

    public int getArticleCountPerPage() {
        return articleCountPerPage;
    }

    public int getPageGroupCount() {
        return pageGroupCount;
    }

    public int getNowPageNumber() {
        return nowPageNumber;
    }

    public void setNowPageNumber(int nowPageNumber) {
        this.nowPageNumber = nowPageNumber;
    }

    public int getTotalArticleCount() {
        return totalArticleCount;
    }

    public void setTotalArticleCount(int totalArticleCount) {
        this.totalArticleCount = totalArticleCount;
    }

    public int getStartArticleNumber() {
        return startArticleNumber;
    }

    public void setStartArticleNumber(int startArticleNumber) {
        this.startArticleNumber = startArticleNumber;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getStartPageNumber() {
        return startPageNumber;
    }

    public void setStartPageNumber(int startPageNumber) {
        this.startPageNumber = startPageNumber;
    }

    public int getEndPageNumber() {
        return endPageNumber;
    }

    public void setEndPageNumber(int endPageNumber) {
        this.endPageNumber = endPageNumber;
    }

    public int getPrePageNumber() {
        return prePageNumber;
    }

    public void setPrePageNumber(int prePageNumber) {
        this.prePageNumber = prePageNumber;
    }

    public int getNextPageNumber() {
        return nextPageNumber;
    }

    public void setNextPageNumber(int nextPageNumber) {
        this.nextPageNumber = nextPageNumber;
    }
}
