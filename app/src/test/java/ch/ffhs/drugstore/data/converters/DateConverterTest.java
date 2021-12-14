package ch.ffhs.drugstore.data.converters;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

public class DateConverterTest {

    @Test
    public void fromTimestamp() {
        long valid_timestamp = 61597649011000L;
        Date expected_date = new Date(2021, 11, 14, 19, 3, 31);
        assertEquals(expected_date, DateConverter.fromTimestamp(valid_timestamp));
    }

    @Test
    public void dateToTimestamp() {
        Date expected_date = new Date(2021, 11, 14, 19, 3, 31);
        long valid_timestamp = 61597649011000L;
        assertEquals(Long.valueOf(valid_timestamp), DateConverter.dateToTimestamp(expected_date));
    }
}