package util;

public class Paging {
	private int pageNo; //����������
	private int pageSize; //������ �� �� ����
	private int blockSize; //�������� �� ������ ����
	private int firstData; //���������� �����  
	private int anchor;//������ ��� ������(���ΰ�ħ ���� ���ϱ�)
	private int startPageNo; //�������� ���� ������
	private int endPageNo; //�������� �� ������
	private int totalPages; //�� ������ ��
	private int totalData; //�� ������ �� 
	private int startData; //������ ���� ��
	
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
		//�⺻����
		if(this.totalData == 0) {//�Խñ��� ���� ���
			return;
		}
		this.setBlockSize(3);
		this.setPageSize(3);
		this.totalPages = (this.totalData + (this.pageSize-1)) / this.pageSize; //�������� 
		this.setFirstData((this.pageNo - 1) * this.pageSize); //��µ� ù ������
		
		try {
			this.setStartPageNo(1 + ((this.pageNo - 1) / this.blockSize) * this.blockSize) ; 
		}catch (ArithmeticException e) {//ArithmeticException: / by zero ó��
			this.setStartPageNo(1);		
		}
		
		int endPage = this.startPageNo + (this.blockSize - 1); //���� ������������ ���ϱ�
		if(endPage > this.totalPages) { //������ ��: ���������� �� �������������� �����ϱ� ���� ����
			endPage = this.totalPages;
		}
		this.setEndPageNo(endPage);//���� ���������� ����
		
	}
	
}