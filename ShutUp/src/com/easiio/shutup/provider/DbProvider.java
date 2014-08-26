package com.easiio.shutup.provider;

import java.util.HashMap;

import com.easiio.shutup.provider.table.AlarmClockTable;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class DbProvider extends ContentProvider {

	private static final String TAG = "[DualWorld] DbProvider";

	public static final String AUTHORITY = "com.easiio.shutup.provider.dbprovider";
	public static final String CONTENT_URI = "content://" + AUTHORITY + "/";
	private static final String DEFAULT_SORT_ORDER = "[ID] ASC";

	private static final UriMatcher sUriMatcher;
	private static final HashMap<Integer, String> sUriPathMap;

	private DbHelper mDbHelper;

	static {
		sUriPathMap = new HashMap<Integer, String>();
		sUriPathMap.put(10, AlarmClockTable.TABLE_NAME);

		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		for (Integer i : sUriPathMap.keySet()) {
			sUriMatcher.addURI(AUTHORITY, sUriPathMap.get(i), i);
			sUriMatcher.addURI(AUTHORITY, sUriPathMap.get(i) + "/#", i + 1);
		}
	}

	@Override
	public boolean onCreate() {
		Log.d(TAG, "onCreate()");
		mDbHelper = new DbHelper(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		Log.d(TAG, "getType( " + uri + " )");
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {

		Log.d(TAG, "query( " + uri + ", ... )");

		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			Log.e(TAG, "query( " + uri + ", ... ) Wrong URI");
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		queryBuilder.setTables(matchTableName(match));
		String appendWhere = matchAppendWhere(match, uri, selection);
		if (!TextUtils.isEmpty(appendWhere)) {
			queryBuilder.appendWhere(appendWhere);
		}

		if (TextUtils.isEmpty(sortOrder)) {
			sortOrder = DEFAULT_SORT_ORDER;
		}

		SQLiteDatabase db;
		try {
			db = mDbHelper.getReadableDatabase();
		} catch (SQLiteException e) {
			Log.e(TAG, "query() Error opening readable database", e);
			throw e;
		}

		Cursor cursor;
		synchronized (mDbHelper) {
			try {
				cursor = queryBuilder.query(db, projection, selection,
						selectionArgs, null, null, sortOrder);
			} catch (Exception e) {
				Log.e(TAG, "query() Exception in db querying", e);
				throw new RuntimeException("Exception in db querying: "
						+ e.getMessage());
			}
		}
		Log.d(TAG, "query() Cursor has " + cursor.getCount() + " rows");

		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;

	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		Log.d(TAG, "insert( " + uri + ", ... )");

		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH || match % 10 != 0) {
			Log.e(TAG, "insert( " + uri + ", ... ) Wrong URI");
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		SQLiteDatabase db;
		try {
			db = mDbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.e(TAG, "insert() Error opening writable database", e);
			throw e;
		}

		long rowId;
		synchronized (mDbHelper) {
			try {
				rowId = db.insert(matchTableName(match), null, values);
			} catch (SQLException e) {
				Log.e(TAG, "insert() Failed", e);
				throw e;
			}
		}

		if (rowId <= 0) {
			Log.e(TAG, "insert() Error return: " + rowId);
			throw new RuntimeException("Exception in db inserting");
		}
		uri = ContentUris.withAppendedId(Uri.parse(matchTableName(match)),
				rowId);
		Log.d(TAG, "insert() Return: " + uri);

		getContext().getContentResolver().notifyChange(uri, null);

		return uri;

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {

		Log.d(TAG, "delete( " + uri + ", ... )");

		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			Log.e(TAG, "delete( " + uri + ", ... ) Wrong URI");
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		SQLiteDatabase db;
		try {
			db = mDbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.e(TAG, "delete() Error opening writable database", e);
			throw e;
		}

		int count;
		synchronized (mDbHelper) {
			try {
				String where = TextUtils.isEmpty(selection) ? matchAppendWhere(
						match, uri, selection) : selection
						+ matchAppendWhere(match, uri, selection);
				count = db.delete(matchTableName(match), where, selectionArgs);
			} catch (SQLException e) {
				Log.e(TAG, "delete() Failed", e);
				throw e;
			}
		}
		Log.d(TAG, "delete() Delete " + count + " rows");

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return count;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		Log.d(TAG, "update( " + uri + ", ... )");

		int match = sUriMatcher.match(uri);
		if (match == UriMatcher.NO_MATCH) {
			Log.e(TAG, "update( " + uri + ", ... ) Wrong URI");
			throw new IllegalArgumentException("Wrong URI: " + uri);
		}

		SQLiteDatabase db;
		try {
			db = mDbHelper.getWritableDatabase();
		} catch (SQLiteException e) {
			Log.e(TAG, "update() Error opening writable database", e);
			throw e;
		}

		int count;
		synchronized (mDbHelper) {
			try {
				String where = TextUtils.isEmpty(selection) ? matchAppendWhere(
						match, uri, selection) : selection
						+ matchAppendWhere(match, uri, selection);
				count = db.update(matchTableName(match), values, where,
						selectionArgs);
			} catch (SQLException e) {
				Log.e(TAG, "update() Failed", e);
				throw e;
			}
		}
		Log.d(TAG, "update() Update " + count + " rows");

		if (count > 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}

		return 0;

	}

	private String matchTableName(int match) {
		return sUriPathMap.get(match / 10 * 10);
	}

	private String matchAppendWhere(int match, Uri uri, String selection) {
		switch (match % 10) {
		case 1:
			return TextUtils.isEmpty(selection) ? " [ID]="
					+ uri.getPathSegments().get(1) : " AND [ID]="
					+ uri.getPathSegments().get(1);
		}
		return "";
	}

}
