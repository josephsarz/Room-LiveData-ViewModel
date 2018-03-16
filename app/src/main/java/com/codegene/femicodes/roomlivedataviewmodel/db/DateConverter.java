package com.codegene.femicodes.roomlivedataviewmodel.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by femicodes on 3/16/2018.
 */

class DateConverter {

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
