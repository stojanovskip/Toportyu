package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import javax.inject.Inject;

public class FilesBasedIOProvider implements IOProvider {

	private final String path;

	@Inject
	public FilesBasedIOProvider() {
		this("default.txt");
	}

	public FilesBasedIOProvider(String path) {
		this.path = path;
	}

	@Override
	public PrintWriter createWriter() throws IOException {
		return new PrintWriter(new FileWriter(path, true), true);
	}

	@Override
	public Reader createReader() throws FileNotFoundException {
		return new FileReader(path);
	}

}
