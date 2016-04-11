package com.company;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {

	@Override
	public void configure() {
		bind(IOProvider.class).toInstance(new FilesBasedIOProvider("orders.txt"));
	}

}
