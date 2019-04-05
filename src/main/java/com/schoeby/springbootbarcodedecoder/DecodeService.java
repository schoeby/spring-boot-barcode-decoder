package com.schoeby.springbootbarcodedecoder;

import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import com.google.common.xml.XmlEscapers;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

@RestController
public class DecodeService {
	
	@RequestMapping(value="/decode", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String decodeBarcode(String barcode_url) throws NotFoundException, IOException {
		
		//String barcode_url_clean = XmlEscapers.xmlContentEscaper().escape(barcode_url);
		URL barcode_url_clean = new URL(barcode_url);
		Result BarcodeText = decodeQRCode(barcode_url_clean);
		//BarcodeText.getResultPoints();
		
		String pattern = "{ "
				+ "\"Text\":\"%s\","
				+ "\"Data\":\"%s\","
				+ "\"Type\":\"%s\","
				+ "\"Length\":\"%s\","
				//+ "\"Points\":\"%s\","
				+ "\"File\":\"%s\" }";
		String json= String.format(pattern,
				BarcodeText.getText(),
				BarcodeText.getRawBytes(),
				BarcodeText.getBarcodeFormat(),
				BarcodeText.getText().length(),
				//BarcodeText.getResultPoints(),
				barcode_url);
		return json;
	}
	
	//@RequestMapping(value="/decode", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	//public String getBarcode() {
	//	wins = 4;
	//	losses = 5;
	//	ties = 6;
	//	String pattern = "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
	//	String json= String.format(pattern,  wins, losses, ties);
	//	return json;
	//}
	//
	private static Result decodeQRCode(URL qrCodeImage) throws IOException, NotFoundException {
	    BufferedImage bufferedImage = ImageIO.read(qrCodeImage);
	    LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
	    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

	    Result result = new MultiFormatReader().decode(bitmap);
	    return result;
	}
}
