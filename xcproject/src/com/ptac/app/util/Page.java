package com.ptac.app.util;

public class Page {

	/** �ܹ���ҳ�� */
	private int totalPage;

	/** �ܹ��ж��ټ�¼ */
	private long resultCount;

	/** Ŀǰ��ҳ�� */
	private int page;

	/** ÿҳ��ʾ��Ŀ�� */
	private int pageSize;

	/** Ҫ������ֶ� */
	private String orderBy;

	/** ��ʲô����ֻ���ǣ�asc||desc */
	private String order;


	/**
	 * ��ҳ��ϢJava��Ĺ��캯��
	 * 
	 * @param resultCount
	 *            �ܼ�¼��
	 * @param pageSize
	 *            ÿҳ��ʾ��Ŀ��
	 */
	public Page(long resultCount, int pageSize) {

		if (resultCount > 0) {
			this.resultCount = resultCount;
		}
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		if (resultCount > 0 && pageSize > 0) {
			this.totalPage = (int) ((resultCount + pageSize - 1) / pageSize);
		}
		this.page = 1;
	}


	/**
	 * ��õ�ǰҳ��ǰһҳ�������ǰҳ�ǵ�һҳ�����ص�ǰҳ��
	 * 
	 * @return ��ǰҳ��ǰһҳ
	 */
	public int getPreviousPage() {
		if (this.page - 1 <= 0) {
			return 1;
		}	else {
			return (this.page - 1);
		}
	}

	/**
	 * ��õ�ǰҳ����һҳ�������ǰҳ�����һҳ�����ص�ǰҳ��
	 * 
	 * @return ��ǰҳ����һҳ
	 */
	public int getNextPage() {
		if (this.page + 1 >= totalPage) {
			return totalPage;
		}		else {
			return (this.page + 1);
		}
	}

	/**
	 * ��ҳ��ѯ����ʼλ��
	 * 
	 * @return ��ҳ��ѯ����ʼλ��
	 */
	public int getFirstItemPos() {
		int temp = (page - 1) * pageSize;
		if(temp<0)temp=0;
		return temp;
	}

	/**
	 * ��Ҫ����ʼλ�ÿ�ʼ����ѯ���ܼ�¼���������ڷ�ҳԭ�����һҳ��¼����һ����ÿҳ��ʾ����Ŀ�����ܱ���С��
	 * 
	 * @return ��Ҫ����ʼλ�ÿ�ʼ����ѯ���ܼ�¼����
	 */
	public long getMaxItemNum() {
		long maxItemNum = 0;
		if (resultCount <= pageSize) {
			maxItemNum = resultCount;
		}		else if ((resultCount - (page - 1) * pageSize) >= pageSize) {
			maxItemNum = pageSize;
		}		else {
			maxItemNum = (resultCount - (page - 1) * pageSize);
		}
		return maxItemNum;
	}
	
	/**
	 * ��ȡ��ǰ��ҳ���һ�����ݵ����
	 * @return
	 */
	public long getEndItemPos(){
		return this.getFirstItemPos()+this.getMaxItemNum();
	}
	public int getPage() {
		return page;
	}

	/**
	 * ���õ�ǰҳ��������õ�ҳ��������ҳ������ǰҳΪ���һҳ���������ҳ��С��0����ǰҳΪ1��
	 * 
	 * @param page
	 *            Ҫ���õĵ�ǰҳ
	 */
	public void setPage(int page) {
		if (page > totalPage) {
			this.page = totalPage;
		} else if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getPageSize() {
		return pageSize;
	}


	/**
	 * ��ü�¼��
	 * 
	 * @return �ܼ�¼��
	 */
	public long getResultCount() {
		return resultCount;
	}

	/**
	 * �����ҳ��
	 * 
	 * @return ��ҳ��
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * ����������
	 * 
	 * @return ���� asc����desc��
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * �����������
	 * 
	 * @param order
	 *            Ҫ���õ�����ʽ��asc����desc��
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * ���Ҫ������ֶ�
	 * 
	 * @return ���������ֶΡ�
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * ����Ҫ������ֶ�
	 * 
	 * @param orderBy
	 *            Ҫ���õ������ֶΡ�
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}
