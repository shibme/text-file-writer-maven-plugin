package me.shib.gitlab.release.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;

@Mojo(name = TextFileWriterMojo.mojoName, requiresProject = true)
public class TextFileWriterMojo extends AbstractMojo {

    static final String mojoName = "maven-text-file-writer-plugin";
    @Parameter
    private FileContent[] files;
    @Parameter(defaultValue = "UTF-8")
    private String charset;

    @Override
    public void execute() throws MojoExecutionException {
        if (null != files) {
            for (FileContent content : files) {
                content.writeContentToFile(charset);
            }
        }
    }

    private class FileContent {
        private File destination;
        private boolean overwrite;
        private String[] lines;

        private void writeContentToFile(String charset) throws MojoExecutionException {
            if (null == destination) {
                throw new MojoExecutionException("Content path not specified");
            }
            if (!destination.exists() || overwrite) {
                destination.getParentFile().mkdirs();
                if (destination.exists()) {
                    getLog().info("Overwriting to file: " + destination.getAbsolutePath());
                } else {
                    getLog().info("Creating and writing to file: " + destination.getAbsolutePath());
                }
            } else {
                getLog().info("Skipped writing to file: " + destination.getAbsolutePath());
            }
            try {
                Files.write(destination.toPath(), Arrays.asList(lines), Charset.forName(charset));
            } catch (IOException e) {
                throw new MojoExecutionException(e.getMessage(), e);
            }
        }

    }
}
