# spring-boot-barcode-decoder
A Spring Boot app to decode barcode information from images using zxing.

### Generate jar file
mvn package

### Run the app
java -jar target/spring-boot-barcode-decoder-X.X.X.jar

### The following command will demonstrate the output
curl -X POST "http://127.0.0.1:8081/decode/" -d barcode_url="https://upload.wikimedia.org/wikipedia/commons/3/38/Qr-code-ver-10.png"
