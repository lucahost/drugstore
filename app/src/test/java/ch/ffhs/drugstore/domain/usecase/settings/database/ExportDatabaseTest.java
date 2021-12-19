package ch.ffhs.drugstore.domain.usecase.settings.database;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.ffhs.drugstore.domain.service.DatabaseService;

public class ExportDatabaseTest {
    @Mock
    DatabaseService databaseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        databaseService = null;
    }

    @Test
    public void testExecuteExportDatabaseUseCase() {
        // Arrange

        // Act
        ExportDatabase exportDatabase = new ExportDatabase(databaseService);
        exportDatabase.execute(null);

        // Verify
        verify(databaseService, times(0)).importDatabase();
        verify(databaseService, times(1)).exportDatabase();
    }
}