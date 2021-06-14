package com.luanvv.microservices.bookstore.service.upload;

import java.io.InputStream;

public interface BookImportService {

	void run(InputStream input);

}
