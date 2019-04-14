package me.shib.gitlab.release.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Goal which writes text content to a file
 *
 * @goal text-file-writer
 */
public class TextFileWriterMojo extends AbstractMojo {

    /**
     * List of File Contents that need to written to respective destination files
     *
     * @parameter
     */
    private FileContent[] files;
    /**
     * The charset to be used to write the file content
     *
     * @parameter default-value="UTF-8"
     */
    private String charset;

    @Override
    public void execute() throws MojoExecutionException {
        if (null != files) {
            for (FileContent content : files) {
                content.writeContentToFile(charset, getLog());
            }
        }
    }
}
