package fileCopy;

import fileCopy.dto.FileWrapper;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileCopyWithPriorityQueue {
    private static PriorityQueue<FileWrapper> fileQueue;

    public FileCopyWithPriorityQueue() {

        // using method refrence syntax
        fileQueue = new PriorityQueue<>(Comparator.comparingLong(FileWrapper::getFileSize));
    }

    // Add files to the queue
    public void addFile(File file) {
        fileQueue.add(new FileWrapper(file));
        System.out.println(file.getName() + " has been added to the queue.");
    }


    // Copy files from the queue
    public void copyFiles(String destinationDir) {
        while (!fileQueue.isEmpty()) {
            FileWrapper fileWrapper = fileQueue.poll();
            File file = fileWrapper.getFile();
            Path sourcePath = file.toPath();
            Path destinationPath = Paths.get(destinationDir, file.getName());

            try {
                //method for copying file
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copied: " + file.getName());
            } catch (IOException e) {
                System.err.println("Failed to copy: " + file.getName());
                e.printStackTrace();
            }
        }
    }

    public static void startTheApp() {
        FileCopyWithPriorityQueue fileCopier = new FileCopyWithPriorityQueue();

        // Add files to the queue
        fileCopier.addFile(new File("C:\\ayush\\a1.txt"));   //22 bytes
        fileCopier.addFile(new File("C:\\ayush\\a2.txt"));   // 11 bytes
        fileCopier.addFile(new File("C:\\ayush\\a3.txt"));   // 12 bytes
        fileCopier.addFile(new File("C:\\ayush\\img1.jpg"));   //152 kb
        fileCopier.addFile(new File("C:\\ayush\\img2.jpg"));  //8 kb

        // Copy all files here
        fileCopier.copyFiles("D:\\copyhere");

        /*
        copy order by size---> a2--->a3---->a1--->img2---->img1
         */

    }
}