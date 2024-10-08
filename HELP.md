# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.


### **Summary of All Steps**:

1. **Generate a self-signed certificate** using Keytool:
```bash
keytool -genkeypair -alias app-ssl-cert -keyalg RSA -keystore src/main/resources/keystore.p12 -storetype PKCS12 -storepass password -dname "CN=localhost, OU=Development, O=Company, L=City, ST=State, C=US"
```

2. **Verify Keystore File Permissions**
   ```bash
   ls -l src/main/resources/keystore.p12
   ```

3. **Configure Spring Boot** to use the `keystore.p12`:
   ```properties
   server.port=8443
   server.ssl.key-store=classpath:keystore.p12
   server.ssl.key-store-password=password
   server.ssl.key-store-type=PKCS12
   server.ssl.key-alias=app-ssl-cert
   ```

4. **Export the certificate**:
   ```bash
   keytool -exportcert -alias app-ssl-cert -keystore src/main/resources/keystore.p12 -storepass password -file localhost.crt -rfc && sudo keytool -importcert -alias app-ssl-cert -file localhost.crt -keystore $(/usr/libexec/java_home)/lib/security/cacerts -storepass changeit -noprompt
   ```
   
5. **Find where Java home directory is installed**:
    ```bash
    ```

6. **Check if the Certificate was Successfully Imported**:
   ```bash
   sudo keytool -list -keystore $(/usr/libexec/java_home)/lib/security/cacerts -storepass changeit | grep localhost
   ```