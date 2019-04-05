# spring-boot-barcode-decoder
A Spring Boot app to decode barcode information from images using zxing.

### After running the generated Spring Boot jar, the folling command will demonstrate the output
curl -X POST "http://127.0.0.1:8080/decode/" -d barcode_url="https://upload.wikimedia.org/wikipedia/commons/3/38/Qr-code-ver-10.png"
