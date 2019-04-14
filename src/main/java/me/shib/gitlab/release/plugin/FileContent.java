package me.shib.gitlab.release.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

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
            Files.write(destination.toPath(), Arrays.asList(lines), Charset.forName(charset));
        } catch (IOException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }
}
