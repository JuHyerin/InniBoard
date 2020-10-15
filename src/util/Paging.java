package util;

public class Paging {
	private int pageNo; //현재페이지
	private int pageSize; //페이지 당 행 갯수
	private int blockSize; //페이지블럭 당 페이지 갯수
	private int firstData; //페이지마다 출력할  
	private int anchor;//데이터 출력 시작점(새로고침 적용 피하기)
	private int startPageNo; //페이지블럭 시작 페이지
	private int endPageNo; //페이지블럭 끝 페이지
	private int totalPages; //총 페이지 수
	private int totalData; //총 데이터 수 
	private int startData; //페이지 시작 행
	
	public Paging(int page) {
		// TODO Auto-generated constructor stub
		this.pageNo = page;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getFirstData() {
		return firstData;
	}
	public void setFirstData(int firstData) {
		this.firstData = firstData;
	}
	public int getStartPageNo() {
		return startPageNo;
	}
	public void setStartPageNo(int startPage) {
		this.startPageNo = startPage;
	}
	public int getEndPageNo() {
		return endPageNo;
	}
	public void setEndPageNo(int endPage) {
		this.endPageNo = endPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
		this.makePaging();
	}

	public void makePaging() {
		//기본설정
		if(this.totalData == 0) {//게시글이 없는 경우
			return;
		}
		this.setBlockSize(3);
		this.setPageSize(3);
		this.totalPages = (this.totalData + (this.pageSize-1)) / this.pageSize; //총페이지 
		this.setFirstData((this.pageNo - 1) * this.pageSize); //출력될 첫 데이터
		
		try {
			this.setStartPageNo(1 + ((this.pageNo - 1) / this.blockSize) * this.blockSize) ; 
		}catch (ArithmeticException e) {//ArithmeticException: / by zero 처리
			this.setStartPageNo(1);		
		}
		
		int endPage = this.startPageNo + (this.blockSize - 1); //블럭의 마지막페이지 구하기
		if(endPage > this.totalPages) { //마지막 블럭: 총페이지가 맨 마지막페이지에 도달하기 전에 끝남
			endPage = this.totalPages;
		}
		this.setEndPageNo(endPage);//최종 블럭끝페이지 설정
		
	}
	
}