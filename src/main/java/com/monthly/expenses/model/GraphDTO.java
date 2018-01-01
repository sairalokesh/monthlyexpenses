package com.monthly.expenses.model;

import java.util.ArrayList;
import java.util.List;

public class GraphDTO {

	private List<GraphDataDTO> dataDTO = new ArrayList<GraphDataDTO>();
	private List<String> monthYear = new ArrayList<String>();
	
	
	public GraphDTO() {
		super();
	}

	public GraphDTO(List<GraphDataDTO> dataDTO, List<String> monthYear) {
		super();
		this.dataDTO = dataDTO;
		this.monthYear = monthYear;
	}

	/**
	 * @return the dataDTO
	 */
	public List<GraphDataDTO> getDataDTO() {
		return dataDTO;
	}

	/**
	 * @param dataDTO
	 *            the dataDTO to set
	 */
	public void setDataDTO(List<GraphDataDTO> dataDTO) {
		this.dataDTO = dataDTO;
	}

	/**
	 * @return the monthYear
	 */
	public List<String> getMonthYear() {
		return monthYear;
	}

	/**
	 * @param monthYear
	 *            the monthYear to set
	 */
	public void setMonthYear(List<String> monthYear) {
		this.monthYear = monthYear;
	}

}
