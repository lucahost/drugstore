package ch.ffhs.drugstore.shared.extensions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import android.content.Context;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

public class FileExtensionsTest {
    /* This folder and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void zipDirectory() throws IOException {
        // Arrange
        Context context = mock(Context.class);
        tempFolder.create();
        TemporaryFolder tempFolder2 = new TemporaryFolder();
        tempFolder2.create();
        when(context.getCacheDir()).thenReturn(tempFolder2.getRoot());

        File targetZip = tempFolder.newFile("test.zip").getAbsoluteFile();

        // Act
        FileExtensions.zipDirectory(context, tempFolder.getRoot(), targetZip);

        // Verify
        assertTrue(targetZip.exists());
    }

    @Test
    public void copyDirectory() throws IOException {
        // Arrange
        TemporaryFolder destFolder = new TemporaryFolder();
        tempFolder.create();
        File file1 = tempFolder.newFile("testfile1.txt");
        File file2 = tempFolder.newFile("testfile2.txt");
        File folder1 = tempFolder.newFolder("testfolder");
        File file3 = tempFolder.newFile("testfolder/testfile3.txt");

        destFolder.create();

        // Act
        FileExtensions.copyDirectory(tempFolder.getRoot(), destFolder.getRoot());

        // Verify
        File targetFile1 = new File(destFolder.getRoot() + "/testfile1.txt");
        File targetFile2 = new File(destFolder.getRoot() + "/testfile2.txt");
        File targetFile3 = new File(destFolder.getRoot() + "/testfolder/testfile3.txt");
        assertTrue(targetFile1.exists());
        assertTrue(targetFile2.exists());
        assertTrue(targetFile3.exists());
    }

    @Test
    public void deleteFolder() throws IOException {
        // Arrange
        tempFolder.create();
        File folder1 = tempFolder.newFolder("testfolder");
        File file1 = tempFolder.newFile("testfolder/testfile1.txt");
        File file2 = tempFolder.newFile("testfolder/testfile2.txt");

        // Act
        FileExtensions.deleteFolder(folder1);

        // Verify
        assertFalse(folder1.exists());
        assertFalse(file1.exists());
        assertFalse(file2.exists());
    }

    @Test
    public void copyFile() throws IOException {
        // Arrange
        tempFolder.create();
        File folder = tempFolder.newFolder("testfolder");
        File destFolder = tempFolder.newFolder("destfolder");
        File file = tempFolder.newFile("testfolder/testfile1.txt");
        File destFile = tempFolder.newFile("destfolder/testfile1.txt");

        // Act
        FileExtensions.copyFile(file, destFile);

        // Verify
        assertTrue(file.exists());
        assertTrue(destFile.exists());
    }

    @Test(expected = IOException.class)
    public void copyFileDoesntReplaceThrowsIOException() throws IOException {
        // Arrange
        tempFolder.create();
        File folder = tempFolder.newFolder("testfolder");
        File file = tempFolder.newFile("testfolder/testfile1.txt");
        File destFile = tempFolder.newFile("testfolder/testfile1.txt");

        // Act
        FileExtensions.copyFile(file, destFile);
    }
}