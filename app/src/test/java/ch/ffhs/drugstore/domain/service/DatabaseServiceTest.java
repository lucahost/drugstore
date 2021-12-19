package ch.ffhs.drugstore.domain.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.ffhs.drugstore.data.database.DatabaseInterface;

public class DatabaseServiceTest {
    @Mock
    DatabaseInterface databaseInterface;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        databaseInterface = null;
    }

    @Test
    public void testExportDatabase() {
        // Arrange

        // Act
        DatabaseService databaseService = new DatabaseService(databaseInterface);
        databaseService.exportDatabase();

        // Verify
        verify(databaseInterface, times(0)).importDatabase();
        verify(databaseInterface, times(1)).exportDatabase();
    }

    @Test
    public void testImportDatabase() {
        // Arrange

        // Act
        DatabaseService databaseService = new DatabaseService(databaseInterface);
        databaseService.importDatabase();

        // Verify
        verify(databaseInterface, times(1)).importDatabase();
        verify(databaseInterface, times(0)).exportDatabase();
    }
}