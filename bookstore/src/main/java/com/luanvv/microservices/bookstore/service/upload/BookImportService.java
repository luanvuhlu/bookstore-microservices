package com.luanvv.microservices.bookstore.service.upload;

import java.io.IOException;
import java.io.InputStream;

public interface BookImportService {

	void run(InputStream input) throws IOException;

}
