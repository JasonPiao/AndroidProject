package com.easiio.shutup.provider;

public class Attribute {

	public static final String ATTRIBUTE_TYPE_INTEGER = "INTEGER";
	public static final String ATTRIBUTE_TYPE_TEXT = "TEXT";
	public static final String ATTRIBUTE_TYPE_REAL = "REAL";
	public static final String ATTRIBUTE_TYPE_BLOB = "BLOB";

	private String name;
	private String type;
	private String defaultValue;
	private long length;
	private long decimalLength;
	private boolean isAllowNull;
	private boolean isPrimeAttribute;

	public Attribute(String name) {
		this.name = name;
		this.type = ATTRIBUTE_TYPE_INTEGER;
		this.defaultValue = null;
		this.length = -1;
		this.decimalLength = -1;
		this.isAllowNull = true;
		this.isPrimeAttribute = false;
	}

	public Attribute(Attribute attribute) {
		this.name = attribute.name;
		this.type = attribute.type;
		this.defaultValue = attribute.defaultValue;
		this.length = attribute.length;
		this.decimalLength = attribute.decimalLength;
		this.isAllowNull = attribute.isAllowNull;
		this.isPrimeAttribute = attribute.isPrimeAttribute;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public long getDecimalLength() {
		return decimalLength;
	}

	public void setDecimalLength(long decimalLength) {
		this.decimalLength = decimalLength;
	}

	public boolean isAllowNull() {
		return isAllowNull;
	}

	public void setAllowNull(boolean isAllowNull) {
		this.isAllowNull = isAllowNull;
	}

	public boolean isPrimeAttribute() {
		return isPrimeAttribute;
	}

	public void setPrimeAttribute(boolean isPrimeAttribute) {
		this.isPrimeAttribute = isPrimeAttribute;
	}

}
