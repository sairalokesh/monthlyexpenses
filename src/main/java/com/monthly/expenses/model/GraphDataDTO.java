package com.monthly.expenses.model;

import java.util.ArrayList;
import java.util.List;

public class GraphDataDTO implements Comparable<GraphDataDTO> {

	private List<Double> data = new ArrayList<Double>();
	private String label;
	private Boolean fill;
	private String backgroundColor;
	private String borderColor;

	public GraphDataDTO(String label) {
		super();
		this.label = label;
	}

	public GraphDataDTO(List<Double> data, String label, Boolean fill, String backgroundColor, String borderColor) {
		super();
		this.data = data;
		this.label = label;
		this.fill = fill;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
	}

	/**
	 * @return the data
	 */
	public List<Double> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<Double> data) {
		this.data = data;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the fill
	 */
	public Boolean getFill() {
		return fill;
	}

	/**
	 * @param fill
	 *            the fill to set
	 */
	public void setFill(Boolean fill) {
		this.fill = fill;
	}

	/**
	 * @return the backgroundColor
	 */
	public String getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the borderColor
	 */
	public String getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor
	 *            the borderColor to set
	 */
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	@Override
	public int compareTo(GraphDataDTO o) {
		return this.getLabel().compareTo(o.getLabel());
	}

}
