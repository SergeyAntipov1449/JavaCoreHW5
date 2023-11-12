import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class Task1 {
    public static void main(String[] args) {
        File sourceDir = new File("D:\\OopGbProjects\\JavaCoreHW5\\source");
        Backup(sourceDir, "D:\\OopGbProjects\\JavaCoreHW5\\backup");
    }

    public static void Backup(File dir, String backupPath) {
        System.out.println("Проверка пути: " + dir.getAbsolutePath());
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("Директория указана неверно");
            return;
        }

        File backup = new File(backupPath);
        if (!backup.exists()) {
            backup.mkdir();
        }

        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                copyFile(file, new File(backup, file.getName()));
            }
        }
    }

    public static void copyFile(File source, File dest) {
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }

            try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
                 FileChannel destChannel = new FileOutputStream(dest).getChannel()) {
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
