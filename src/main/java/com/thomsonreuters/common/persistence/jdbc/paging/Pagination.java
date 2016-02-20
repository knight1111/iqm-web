package com.thomsonreuters.common.persistence.jdbc.paging;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Pagination
 * 
 * @author 
 * @version 
 * @see Pagination
 * @since
 */
public class Pagination implements Serializable {

	private static final long serialVersionUID = -2554565760258955645L;

	/**
	 * numPerPage
	 */
	private int numPerPage;

	/**
	 * total
	 */
	private int total;

	/**
	 * totalPages
	 */
	private int totalPages;

	/**
	 * currentPage
	 */
	private int currentPage;

	/**
	 * startIndex
	 */
	private int startIndex;

	/**
	 * lastIndex
	 */
	private int lastIndex;

	/**
	 * ResultList
	 */
	private List<Map<String, Object>> rows;

	/**
	 * Construction
	 * 
	 * @param sql
	 * @param currentPage
	 * @param numPerPage
	 * @param jdbcTemplate
	 */
	public Pagination(String sql, int currentPage, int numPerPage,
			JdbcTemplate jdbcTemplate) {
		if (jdbcTemplate == null) {
			throw new IllegalArgumentException(
					"jdbcTemplate is null , pls initialize ... ");
		} else if (StringUtils.isBlank(sql)) {
			throw new IllegalArgumentException(
					"sql is blank , pls initialize ... ");
		}

		setNumPerPage(numPerPage);

		setCurrentPage(currentPage);

		StringBuffer totalSQL = new StringBuffer(" select count(1) from ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) ");

		setTotal(jdbcTemplate.queryForObject(totalSQL.toString(), null,
				Integer.class));

		setTotalPages();

		setStartIndex();

		setLastIndex();

		StringBuffer paginationSQL = new StringBuffer(" select * from ( ");
		paginationSQL.append(" select row_limit.*,rownum rownum_ from ( ");
		paginationSQL.append(sql);
		paginationSQL.append("　) row_limit where rownum <= " + lastIndex);
		paginationSQL.append(" ) where　rownum_ > " + startIndex);

		setRows(ConvertMapKey.listKeyToLower(jdbcTemplate
				.queryForList(paginationSQL.toString())));
	}

	/**
	 * setTotalPages
	 * 
	 * @see
	 */
	private void setTotalPages() {
		if (total % numPerPage == 0) {
			this.totalPages = total / numPerPage;
		} else {
			this.totalPages = (total / numPerPage) + 1;
		}
	}

	/**
	 * setStartIndex
	 * 
	 * @see
	 */
	private void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage;
	}

	/**
	 * setLastIndex
	 * 
	 * @see
	 */
	private void setLastIndex() {
		if (total < numPerPage) {
			this.lastIndex = total;
		} else if ((total % numPerPage == 0)
				|| (total % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (total % numPerPage != 0 && currentPage == totalPages) {
			this.lastIndex = total;
		}
	}

	// setter and getter
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<Map<String, Object>> getRows() {
		return rows;
	}

	public void setRows(List<Map<String, Object>> rows) {
		this.rows = rows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

}
