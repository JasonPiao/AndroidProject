package com.easiio.shutup.provider;

import java.util.ArrayList;
import java.util.HashMap;

import com.easiio.shutup.provider.table.SettingTable;

import android.content.ContentValues;

public class DbMetadata {

	public static final ArrayList<Table> sTableList;
	public static final HashMap<String, ArrayList<ContentValues>> sValueMap;

	static {
		sTableList = new ArrayList<Table>();
		sTableList.add(new SettingTable());

		sValueMap = new HashMap<String, ArrayList<ContentValues>>();
		ArrayList<ContentValues> valueList;
		ContentValues value;

		valueList = new ArrayList<ContentValues>();

		value = new ContentValues();
		value.put(SettingTable.COLUMN_ID, 1);
		value.put(SettingTable.COLUMN_VALUE, 15);
		valueList.add(value);

		value = new ContentValues();
		value.put(SettingTable.COLUMN_ID, 2);
		value.put(SettingTable.COLUMN_VALUE, 20);
		valueList.add(value);

		value = new ContentValues();
		value.put(SettingTable.COLUMN_ID, 3);
		value.put(SettingTable.COLUMN_VALUE, 25);
		valueList.add(value);

		value = new ContentValues();
		value.put(SettingTable.COLUMN_ID, 4);
		value.put(SettingTable.COLUMN_VALUE, 35);
		valueList.add(value);

		value = new ContentValues();
		value.put(SettingTable.COLUMN_ID, 5);
		value.put(SettingTable.COLUMN_VALUE, 50);
		valueList.add(value);

		sValueMap.put(SettingTable.TABLE_NAME, valueList);

	}

}
