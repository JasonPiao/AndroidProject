package com.easiio.shutup.provider.table;

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

		Attribute gameId = new Attribute(COLUMN_GAME_ID);
		gameId.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute isActivated = new Attribute(COLUMN_IS_ACTIVATED);
		isActivated.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute ringTime = new Attribute(COLUMN_RING_TIME);
		ringTime.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute maxRingLength = new Attribute(COLUMN_MAX_RING_LENGTH);
		maxRingLength.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute repeatMode = new Attribute(COLUMN_REPEAT_MODE);
		repeatMode.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute isSkipHolidays = new Attribute(COLUMN_IS_SKIP_HOLIDAYS);
		isSkipHolidays.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute isVibrate = new Attribute(COLUMN_IS_VIBRATE);
		isVibrate.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute volume = new Attribute(COLUMN_VOLUME);
		volume.setType(Attribute.ATTRIBUTE_TYPE_INTEGER);

		Attribute remark = new Attribute(COLUMN_REMARK);
		remark.setType(Attribute.ATTRIBUTE_TYPE_TEXT);

		addAttribute(id);
		addAttribute(gameId);
		addAttribute(isActivated);
		addAttribute(ringTime);
		addAttribute(maxRingLength);
		addAttribute(repeatMode);
		addAttribute(isSkipHolidays);
		addAttribute(isVibrate);
		addAttribute(volume);
		addAttribute(remark);
		setAutoIncrementColumn(COLUMN_ID);

	}

	public class AlarmClock {

		public static final int REPEAT_MONDAY = 0x1;
		public static final int REPEAT_TUESDAY = 0x2;
		public static final int REPEAT_WEDNESDAY = 0x4;
		public static final int REPEAT_THURSDAY = 0x8;
		public static final int REPEAT_FRIDAY = 0x10;
		public static final int REPEAT_SATURDAY = 0x20;
		public static final int REPEAT_SUNDAY = 0x40;

		private int id;
		private int gameId;
		private boolean isActivated;
		private int ringTime;
		private int maxRingLength;
		private int repeatMode;
		private boolean isSkipHolidays;
		private boolean isVibrate;
		private int volume;
		private String remark;

		public AlarmClock() {
			gameId = 1;
			isActivated = true;
			ringTime = 60 * 8;
			maxRingLength = 60;
			repeatMode = REPEAT_MONDAY + REPEAT_TUESDAY + REPEAT_WEDNESDAY
					+ REPEAT_THURSDAY + REPEAT_FRIDAY;
			isSkipHolidays = false;
			isVibrate = true;
			volume = 100;
			remark = "";
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getGameId() {
			return gameId;
		}

		public void setGameId(int gameId) {
			this.gameId = gameId;
		}

		public boolean isActivated() {
			return isActivated;
		}

		public void setActivated(boolean isActivated) {
			this.isActivated = isActivated;
		}

		public int getRingTime() {
			return ringTime;
		}

		public void setRingTime(int ringTime) {
			this.ringTime = ringTime;
		}

		public int getMaxRingLength() {
			return maxRingLength;
		}

		public void setMaxRingLength(int maxRingLength) {
			this.maxRingLength = maxRingLength;
		}

		public int getRepeatMode() {
			return repeatMode;
		}

		public void setRepeatMode(int repeatMode) {
			this.repeatMode = repeatMode;
		}

		public boolean isSkipHolidays() {
			return isSkipHolidays;
		}

		public void setSkipHolidays(boolean isSkipHolidays) {
			this.isSkipHolidays = isSkipHolidays;
		}

		public boolean isVibrate() {
			return isVibrate;
		}

		public void setVibrate(boolean isVibrate) {
			this.isVibrate = isVibrate;
		}

		public int getVolume() {
			return volume;
		}

		public void setVolume(int volume) {
			this.volume = volume;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

	}

}
