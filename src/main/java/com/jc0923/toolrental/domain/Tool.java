package com.jc0923.toolrental.domain;

public class Tool {

	private String toolCode;
	private ToolType tooltype;
	private String brand;
	
	public Tool(String toolCode, ToolType tooltype, String brand) {
		this.toolCode = toolCode;
		this.tooltype = tooltype;
		this.brand = brand;
	}
	
	public Tool() {}
	
	public String getToolCode() {
		return toolCode;
	}
	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}
	public ToolType getTooltype() {
		return tooltype;
	}
	public void setTooltype(ToolType tooltype) {
		this.tooltype = tooltype;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
}
