package me.shib.gitlab.release.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileContent {
    private File destination;
    private boolean overwrite;
    private String[] lines;

    void writeContentToFile(String charset, Log log) throws MojoExecutionException {
        if (null == destination) {
            throw new MojoExecutionException("Content destination not specified");
        }
        if (!destination.exists() || overwrite) {
            destination.getParentFile().mkdirs();
            if (destination.exists()) {
                log.info("Overwriting to file: " + destination.getAbsolutePath());
            } else {
                log.info("Creating and writing to file: " + destination.getAbsolutePath());
            }
        } else {
            log.info("Skipped writing to file: " + destination.getAbsolutePath());
        }
        try {
            if (null != lines && lines.length > 0) {
                StringBuilder content = new StringBuilder(lines[0]);
                for (int i = 1; i < lines.length; i++) {
                    content.append("\n");
                    if (null != lines[i]) {
                        content.append(lines[i]);
                    }
                }
                FileWriter writer = new FileWriter(destination);
                writer.append(content.toString());
                writer.close();
            }
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }
}
