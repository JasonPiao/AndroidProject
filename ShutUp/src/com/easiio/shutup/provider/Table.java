package com.easiio.shutup.provider;

import java.util.ArrayList;

import android.text.TextUtils;

public class Table {

	private String name;
	private String autoIncrementColumn;
	private ArrayList<Attribute> attributeList;

	public Table() {
		name = null;
		autoIncrementColumn = null;
		attributeList = new ArrayList<Attribute>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutoIncrementColumn() {
		return autoIncrementColumn;
	}

	public boolean setAutoIncrementColumn(String autoIncrementColumn) {
		if (TextUtils.isEmpty(autoIncrementColumn)) {
			this.autoIncrementColumn = null;
			return true;
		}

		for (Attribute attribute : attributeList) {
			if (autoIncrementColumn.equals(attribute.getName())) {
				this.autoIncrementColumn = autoIncrementColumn;
				return true;
			}
		}

		return false;
	}

	public int getAttributeCount() {
		return attributeList.size();
	}

	public Attribute getAttribute(int index) {
		if (index < 0 || index >= attributeList.size()) {
			return null;
		}
		return new Attribute(attributeList.get(index));
	}

	public Attribute getAttribute(String name) {
		if (TextUtils.isEmpty(name) || attributeList.size() < 1) {
			return null;
		}

		for (Attribute attribute : attributeList) {
			if (name.equals(attribute.getName())) {
				return new Attribute(attribute);
			}
		}

		return null;
	}

	public boolean addAttribute(Attribute attribute) {
		if (attribute == null) {
			return false;
		}

		if (TextUtils.isEmpty(attribute.getName())) {
			return false;
		}

		if (TextUtils.isEmpty(attribute.getType())) {
			return false;
		}

		for (Attribute a : attributeList) {
			if (attribute.getName().equals(a.getName())) {
				return false;
			}
		}

		attributeList.add(attribute);
		return true;
	}

}
