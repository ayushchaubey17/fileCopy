package fileCopy.dto;

import java.io.File;

public class FileWrapper {
    private File file;

    public FileWrapper(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public  long  getFileSize() {
        return file.length();
    }

    @Override
    public String toString() {
        return file.getName();
    }
}