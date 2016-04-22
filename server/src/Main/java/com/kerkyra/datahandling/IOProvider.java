package com.kerkyra.datahandling;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

/**
 * Created by Andras.Timar on 4/12/2016.
 */
public interface IOProvider {
    Reader createReader() throws FileNotFoundException;

    PrintWriter createWriter() throws IOException;
}
