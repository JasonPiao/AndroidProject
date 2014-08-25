package com.easiio.shutup.provider.table;

import com.easiio.shutup.provider.Attribute;
import com.easiio.shutup.provider.Table;

public class SettingTable extends Table {

	public static final String TABLE_NAME = "SETTING";

	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_VALUE = "VALUE";

	public static final int SETTING_TEXT_SIZE_TINY = 1;
	public static final int SETTING_TEXT_SIZE_SMALL = 2;
	public static final int SETTING_TEXT_SIZE_NORMAL = 3;
	public static final int SETTING_TEXT_SIZE_BIG = 4;
	public static final int SETTING_TEXT_SIZE_LARGE = 5;

	public SettingTable() {
		setName(TABLE_NAME);

		Attribute id = new Attribute(COLUMN_ID);
		id.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);
		id.setPrimeAttribute(true);

		Attribute value = new Attribute(COLUMN_VALUE);
		value.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		addAttribute(id);
		addAttribute(value);
	}

	public class Setting {

		private int id;
		private String value;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}

}
