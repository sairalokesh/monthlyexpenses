package com.monthly.expenses.model;

import java.util.ArrayList;
import java.util.List;

public class PieGraphDTO {

	private List<String> labels = new ArrayList<String>();
	private List<Double> data = new ArrayList<Double>();
	private List<String> color = new ArrayList<String>();

	/**
	 * @return the labels
	 */
	public List<String> getLabels() {
		return labels;
	}

	/**
	 * @param labels
	 *            the labels to set
	 */
	public void setLabels(List<String> labels) {
		this.labels = labels;
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
	 * @return the color
	 */
	public List<String> getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(List<String> color) {
		this.color = color;
	}

}
