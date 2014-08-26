package com.easiio.shutup.provider;

import java.util.ArrayList;
import java.util.HashMap;

import com.easiio.shutup.provider.table.AlarmClockTable;

import android.content.ContentValues;

public class DbMetadata {

	public static final ArrayList<Table> sTableList;
	public static final HashMap<String, ArrayList<ContentValues>> sValueMap;

	static {
		sTableList = new ArrayList<Table>();
		sTableList.add(new AlarmClockTable());

		sValueMap = new HashMap<String, ArrayList<ContentValues>>();
		ArrayList<ContentValues> valueList;
		ContentValues value;

		valueList = new ArrayList<ContentValues>();

		value = new ContentValues();
		valueList.add(value);

		sValueMap.put(AlarmClockTable.TABLE_NAME, valueList);
	}

}
