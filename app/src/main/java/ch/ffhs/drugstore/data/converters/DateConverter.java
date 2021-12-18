package ch.ffhs.drugstore.data.converters;

import androidx.room.ProvidedTypeConverter;
import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * This class converts the date to timestamp and reverse needed because SQLite can't handle date
 * formats
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
@ProvidedTypeConverter
public class DateConverter {
    /**
     * @param value (Long) checks if not null
     * @return ZonedDateTime
     */
    @TypeConverter
    public static ZonedDateTime fromTimestampToUTC(Long value) {
        if (value == null) {
            return null;
        }
        Instant i = Instant.ofEpochSecond(value);
        return ZonedDateTime.ofInstant(i, ZoneId.of("UTC"));
    }

    /**
     * @param date (ZonedDateTime) checks if not null
     * @return Long
     */
    @TypeConverter
    public static Long dateToTimestamp(ZonedDateTime date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().getEpochSecond();
    }
}
