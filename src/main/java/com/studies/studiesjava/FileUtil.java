package com.studies.studiesjava;

import com.studies.studiesjava.enums.UtilMSGEnum;
import com.studies.studiesjava.exception.BusinessException;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Log4j2
public class FileUtil {

    public static void replaceFilesFolder(String pathFolder, String oldName, String newName) {
        File folder = new File(pathFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new BusinessException(UtilMSGEnum.ORIGIN_FOLDER_NOT_EXIST);
        }
        if (oldName == null || oldName.isEmpty()) {
            throw new BusinessException(UtilMSGEnum.OLD_NAME_NOT_NULL);
        }
        if (newName == null) {
            throw new BusinessException(UtilMSGEnum.NEW_NAME_NOT_NULL);
        }
        replaceNameFilesRecursively(folder, oldName, newName);
    }

    private static void replaceNameFilesRecursively(File folder, String oldName, String newName) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    replaceNameFilesRecursively(file, oldName, newName);
                } else {
                    String findName = file.getName();
                    if (findName.contains(oldName) && !findName.equals(newName)) {
                        String newFileName = findName.replace(oldName, newName);
                        File newFile = new File(file.getParent(), newFileName);
                        if (file.renameTo(newFile)) {
                            log.info("File: {} change to : {} \n", findName, newFileName);
                        }
                    }
                }
            }
        }
    }

    public static void setUpCaseFilesFolder(String pathFolder) {
        File folder = new File(pathFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new BusinessException(UtilMSGEnum.ORIGIN_FOLDER_NOT_EXIST);
        }
        setUpCaseName(folder, true);
    }

    public static void setLowerCaseFilesFolder(String pathFolder) {
        File folder = new File(pathFolder);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new BusinessException(UtilMSGEnum.ORIGIN_FOLDER_NOT_EXIST);
        }
        setUpCaseName(folder, false);
    }

    private static void setUpCaseName(File folder, boolean isUpcase) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    setUpCaseName(file, isUpcase);
                } else {
                    String findName = file.getName();
                    String newFileName = isUpcase ? findName.toUpperCase() : findName.toLowerCase();
                    File newFile = new File(file.getParent(), newFileName);
                    if (file.renameTo(newFile)) {
                        log.info("File: {} change to : {} \n", findName, newFileName);
                    }
                }
            }
        }
    }

    public static void moveFileToFolder(String originPath, String destinyPath, String filter) {
        File originFolder = new File(originPath);
        if (!originFolder.exists() || !originFolder.isDirectory()) {
            throw new BusinessException(UtilMSGEnum.ORIGIN_FOLDER_NOT_EXIST);
        }
        File destinyFolder = new File(destinyPath);
        if (!destinyFolder.exists() || !destinyFolder.isDirectory()) {
            throw new BusinessException(UtilMSGEnum.DESTINY_FOLDER_NOT_EXIST);
        }
        moveFilesRecursively(originFolder, destinyFolder, filter);
    }

    public void moveFileToFolder(String originPath, String destinyPath) {
        moveFileToFolder(originPath, destinyPath, null);
    }

    private static void moveFilesRecursively(File sourceFolder, File destinationFolder, String filter) {
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    moveFilesRecursively(file, destinationFolder, filter); // Chamada recursiva para pastas
                } else {
                    String fileName = file.getName();
                    try {
                        if (filter == null || filter.isEmpty()) {
                            moveFile(destinationFolder, file, fileName);
                        } else if (fileName.contains(filter)) {
                            moveFile(destinationFolder, file, fileName);
                        }
                    } catch (IOException e) {
                        log.error("Error ao tentar mover arquivo: {}", fileName);
                    }
                }
            }
        }
    }

    private static void moveFile(File destinationFolder, File file, String fileName) throws IOException {
        Path sourceFilePath = file.toPath();
        Path destinationFilePath = Paths.get(destinationFolder.getAbsolutePath(), fileName);
        Files.move(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
    }

}
