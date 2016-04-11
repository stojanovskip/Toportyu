package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import com.google.inject.ImplementedBy;

public interface IOProvider {

	PrintWriter createWriter() throws IOException;

	Reader createReader() throws IOException;

}
