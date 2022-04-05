# bookstore-openapi

Open API specs for https://github.com/luanvuhlu/bookstore-microservices

Example:

```bash
java -jar scripts/openapi-generator-cli.jar generate -i api/com/luanvv/bookstore/product.yaml -g spring -o out/product --additional-properties=groupId=com.luanvv.bookstore.product,artifactId=product-api,artifactUrl=https://github.com/luanvuhlu/bookstore-microservices,modelPackage=com.luanvv.bookstore.product.client.model,apiPackage=com.luanvv.bookstore.product.client.api,artifactVersion=1.0.0,java8=true,developerEmail=luanvuhlu@gmail.com,developerName=luanvu,interfaceOnly=true
```
