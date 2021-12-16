package ch.ffhs.drugstore.data.converters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateConverterTest {

    @Test
    public void fromTimestamp() {
        long valid_timestamp = 1639436411L;
        ZonedDateTime expected_date = ZonedDateTime.of(2021, 12, 13, 23, 0, 11, 0, ZoneOffset.UTC);
        assertEquals(expected_date.toLocalTime(), DateConverter.fromTimestampToUTC(valid_timestamp).toLocalTime());
    }

    @Test
    public void dateToTimestamp() {
        ZonedDateTime expected_date = ZonedDateTime.of(2021, 12, 13, 23, 0, 11, 0, ZoneOffset.UTC);
        long valid_timestamp = 1639436411L;
        assertEquals(Long.valueOf(valid_timestamp), DateConverter.dateToTimestamp(expected_date));
    }
}