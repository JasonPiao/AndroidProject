package com.easiio.shutup.provider;

import java.util.ArrayList;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

/**
 * 
 * @author Jason
 * 
 */
public class DbHelper extends SQLiteOpenHelper {

	private static final String TAG = "[DualWorld] DbHelper";

	public static final String DB_NAME = "dualworld.db";
	public static final int DB_VERSION = 1;

	public DbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate()");

		for (Table table : DbMetadata.sTableList) {
			String sql = DbHelper.getCreateTableStatement(table);
			db.execSQL(sql);
		}

		Set<String> keySet = DbMetadata.sValueMap.keySet();
		for (String table : keySet) {
			ArrayList<ContentValues> valueList = DbMetadata.sValueMap
					.get(table);
			if (valueList == null) {
				continue;
			}
			for (ContentValues value : valueList) {
				db.insert(table, null, value);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade()");
		for (Table table : DbMetadata.sTableList) {
			String sql = DbHelper.getDropTableStatement(table);
			db.execSQL(sql);
		}
		onCreate(db);
	}

	public static String getCreateTableStatement(Table table) {

		if (table == null) {
			return null;
		}

		if (TextUtils.isEmpty(table.getName())) {
			return null;
		}

		if (table.getAttributeCount() < 1) {
			return null;
		}

		String s = "CREATE TABLE IF NOT EXISTS [" + table.getName() + "] ( ";
		ArrayList<String> primeAttributeList = new ArrayList<String>();
		for (int i = 0; i < table.getAttributeCount(); i++) {

			if (i != 0) {
				s += ", ";
			}

			Attribute attribute = table.getAttribute(i);

			s += "[" + attribute.getName() + "] ";
			s += "[" + attribute.getType() + "] ";

			if (Attribute.ATTRIBUTE_TYPE_TEXT.equals(attribute.getType())
					|| Attribute.ATTRIBUTE_TYPE_BLOB
							.equals(attribute.getType())) {
				if (attribute.getLength() > 0) {
					s += "(" + attribute.getLength() + ") ";
				}
			} else if (Attribute.ATTRIBUTE_TYPE_REAL
					.equals(attribute.getType())) {
				if (attribute.getLength() > 0) {
					if (attribute.getDecimalLength() > 0) {
						s += "(" + attribute.getLength() + ","
								+ attribute.getDecimalLength() + ") ";
					} else {
						s += "(" + attribute.getLength() + ") ";
					}
				}
			}

			if (attribute.getName().equals(table.getAutoIncrementColumn())) {
				if (Attribute.ATTRIBUTE_TYPE_INTEGER
						.equals(attribute.getType())) {
					s += "PRIMARY KEY AUTOINCREMENT ";
				} else {
					return null;
				}
			}
			if (!attribute.isAllowNull()) {
				s += "NOT NULL ";
			}
			if (!TextUtils.isEmpty(attribute.getDefaultValue())) {
				s += "DEFAULT [" + attribute.getDefaultValue() + "] ";
			}
			if (attribute.isPrimeAttribute()) {
				primeAttributeList.add(attribute.getName());
			}

		}

		if (TextUtils.isEmpty(table.getAutoIncrementColumn())
				&& primeAttributeList.size() > 0) {
			s += ", PRIMARY KEY ( ";

			for (int i = 0; i < primeAttributeList.size(); i++) {

				if (i != 0) {
					s += ", ";
				}
				s += "[" + primeAttributeList.get(i) + "] ";
			}

			s += ") ";
		}

		s += ");";
		return s;

	}

	public static String getDropTableStatement(String table) {
		if (TextUtils.isEmpty(table)) {
			return "";
		}
		return "DROP TABLE IF EXISTS " + table;
	}

	public static String getDropTableStatement(Table table) {
		if (table == null) {
			return "";
		}
		return getDropTableStatement(table.getName());
	}

}
