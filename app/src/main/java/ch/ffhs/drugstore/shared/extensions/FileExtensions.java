package ch.ffhs.drugstore.shared.extensions;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Extension to handle files
 *
 * @author Marc Bischof, Luca Hostettler, Sebastian Roethlisberger
 * @version 2021.12.15
 */
public class FileExtensions {

    private FileExtensions() {
        throw new IllegalStateException("Utility class");
    }

    public static void zipDirectory(Context context, File sourceDirectory, File targetFile) {
        try {
            File tmp = new File(context.getCacheDir(), "tmp");
            copyDirectory(sourceDirectory, tmp);
            File zipFile = zipDirectory(tmp, targetFile.getName());
            copyFile(zipFile, targetFile);
            deleteFolder(tmp);
        } catch (Exception ignored) {
        }
    }

    private static File zipDirectory(File directory, String zipFileName) throws IOException {
        String zipName = directory + "/" + zipFileName;
        FileOutputStream fos = new FileOutputStream(zipName);
        ZipOutputStream zipOut = new ZipOutputStream(fos);

        zipFile(directory, directory.getName(), zipOut);
        zipOut.close();
        fos.close();

        return new File(zipName);
    }

    public static void copyDirectory(File sourceDirectory, File destinationDirectory)
            throws IOException {
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }
        for (String f : Objects.requireNonNull(sourceDirectory.list())) {
            copyDirectoryCompatibilityMode(
                    new File(sourceDirectory, f), new File(destinationDirectory, f));
        }
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { // some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    private static void copyDirectoryCompatibilityMode(File source, File destination)
            throws IOException {
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    public static void copyFile(File sourceFile, File destinationFile) throws IOException {
        File parentDestDir = destinationFile.getParentFile();
        if (parentDestDir != null) {
            parentDestDir.mkdirs();
        }
        try (InputStream in = new FileInputStream(sourceFile);
             OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut)
            throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            File[] children = fileToZip.listFiles();
            if (children != null) {
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }
}
