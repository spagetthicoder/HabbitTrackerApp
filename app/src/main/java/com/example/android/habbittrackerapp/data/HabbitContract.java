package com.example.android.habbittrackerapp.data;

import android.provider.BaseColumns;

public final class HabbitContract {
    private HabbitContract() {
    }

    public static final class HabbitEntry implements BaseColumns {
        public static final String TABLE_NAME = "nutrition";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HAD_BREAKFAST = "hadBreakfast";
        public static final String COLUMN_BREAKFAST_MEAL = "breakfastMeal";
        public static final String COLUMN_HAD_LUNCH = "hadLunch";
        public static final String COLUMN_LUNCH_MEAL = "lunchMeal";
        public static final String COLUMN_HAD_SUPPER = "hadSupper";
        public static final String COLUMN_SUPPER_MEAL = "supperMeal";

        public static final String COLUMN_ADDITIONAL_DATA = "additionalData";
        public static final String COLUMN_DATETIME_STAMP = "datetimeStamp";

    }
}
