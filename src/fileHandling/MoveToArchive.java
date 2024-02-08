package fileHandling;

import java.io.File;

public class MoveToArchive {
    public static void moveFilesToArchive(File archiveDir, File[] arrayWithFilesFromInput) {
        for (File file:arrayWithFilesFromInput) {
            if (file.renameTo(new File(archiveDir, file.getName()))) {
                System.out.println(file.getName() + " is moved to archive");
            } else {
                System.out.println("File can't be moved to archive");
            }
        }



    }


}
