package ch.ffhs.drugstore.data.converters;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ProvidedTypeConverter
public class DateConverter {
    @TypeConverter
    public static ZonedDateTime fromTimestampToUTC(Long value) {
        if (value == null) {
            return null;
        }
        Instant i = Instant.ofEpochSecond(value);
        return ZonedDateTime.ofInstant(i, ZoneId.of("UTC"));
    }

    @TypeConverter
    public static Long dateToTimestamp(ZonedDateTime date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().getEpochSecond();
    }
}
