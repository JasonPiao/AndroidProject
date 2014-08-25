package com.easiio.shutup.provider.table;

import android.text.format.Time;

import com.easiio.shutup.provider.Attribute;
import com.easiio.shutup.provider.Table;

public class AlarmClockTable extends Table {

	public static final String TABLE_NAME = "ALARM_CLOCK";

	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_GAME_ID = "GAME_ID";

	public static final String COLUMN_IS_ACTIVATED = "IS_ACTIVATED";
	public static final String COLUMN_RING_TIME = "RING_TIME";
	public static final String COLUMN_MAX_RING_LENGTH = "MAX_RING_LENGTH";
	public static final String COLUMN_REPEAT_MODE = "REPEAT_MODE";
	public static final String COLUMN_IS_SKIP_HOLIDAYS = "IS_SKIP_HOLIDAYS";
	public static final String COLUMN_IS_VIBRATE = "IS_VIBRATE";
	public static final String COLUMN_VOLUME = "VOLUME";
	public static final String COLUMN_REMARK = "REMARK";

	public AlarmClockTable() {

		setName(TABLE_NAME);

		Attribute id = new Attribute(COLUMN_ID);
		id.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);
		id.setPrimeAttribute(true);

		Attribute game_id = new Attribute(COLUMN_GAME_ID);
		game_id.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute is_activated = new Attribute(COLUMN_IS_ACTIVATED);
		is_activated.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute ring_time = new Attribute(COLUMN_RING_TIME);
		ring_time.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		Attribute max_ring_length = new Attribute(COLUMN_MAX_RING_LENGTH);
		max_ring_length.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		Attribute repeat_mode = new Attribute(COLUMN_REPEAT_MODE);
		repeat_mode.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		Attribute is_skip_holidays = new Attribute(COLUMN_IS_SKIP_HOLIDAYS);
		is_skip_holidays.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute is_vibrate = new Attribute(COLUMN_IS_VIBRATE);
		is_vibrate.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute volume = new Attribute(COLUMN_VOLUME);
		volume.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute remark = new Attribute(COLUMN_REMARK);
		remark.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		addAttribute(id);
		addAttribute(game_id);
		addAttribute(is_activated);
		addAttribute(ring_time);
		addAttribute(max_ring_length);
		addAttribute(repeat_mode);
		addAttribute(is_skip_holidays);
		addAttribute(is_vibrate);
		addAttribute(volume);
		addAttribute(remark);
		setAutoIncrementColumn(COLUMN_ID);

	}

	public class AlarmClock {


		public static final String COLUMN_IS_ACTIVATED = "IS_ACTIVATED";
		public static final String COLUMN_RING_TIME = "RING_TIME";
		public static final String COLUMN_MAX_RING_LENGTH = "MAX_RING_LENGTH";
		public static final String COLUMN_REPEAT_MODE = "REPEAT_MODE";
		public static final String COLUMN_IS_SKIP_HOLIDAYS = "IS_SKIP_HOLIDAYS";
		public static final String COLUMN_IS_VIBRATE = "IS_VIBRATE";
		public static final String COLUMN_VOLUME = "VOLUME";
		public static final String COLUMN_REMARK = "REMARK";
		
		private int id;
		private int game_id;
		private boolean is_activated;
		private Time ring_time;
		

	}

}
