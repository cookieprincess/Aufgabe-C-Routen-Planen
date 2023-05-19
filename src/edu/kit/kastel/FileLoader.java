/*
 * Copyright (c) 2023, KASTEL. All rights reserved.
 */

package edu.kit.kastel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class represents a file loader.
 * <p>
 * It provides methods to load a file.
 * </p>
 *
 * @author Mattis Justus Kaiser
 * @version v0.1
 */
public class FileLoader {

    private final File file;

    /**
     * Creates a new file loader.
     *
     * @param path The path to the file.
     */
    public FileLoader(String path) {
        this.file = new File(path);
    }

    /**
     * Reads the file with the specified path and returns its content stored in a {@code String} array, whereas the
     * first array field contains the file's first line, the second field contains the second line, and so on.
     *
     * @author  ITI, VeriAlg Group
     * @author  IPD, SDQ Group
     * @return The file content.
     */
    public String[] readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
            return reader.lines().toArray(String[]::new);
        } catch (final IOException e) {
            /*
             * You can expect that artemis exclusively provides valid file-paths. Therefore,
             * there will no IOException occur while reading in files during the tests, the
             * following RuntimeException does not have to get handled.
             */
            throw new RuntimeException(e);
        }
    }
}
