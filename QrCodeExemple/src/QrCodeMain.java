import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QrCodeMain {
	public static void main(String[] args) {
		try {
			String data = "Je suis Thierry et je suis gentil.";
			int size = 400;

			// encode
			BitMatrix bitMatrix = generateMatrix(data, size);
			String imageFormat = "png";
			String outputFileName = "c:/code/qrcode-01." + imageFormat;

			// write in a file
			writeImage(outputFileName, imageFormat, bitMatrix);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BitMatrix generateMatrix(String data, int size) {
		BitMatrix bitMatrix;
		try {
			bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE,
					size, size);
			return bitMatrix;
		} catch (WriterException e) {
			return null;
		}
	}

	private static void writeImage(final String outputFileName,
			final String imageFormat, final BitMatrix bitMatrix) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				outputFileName));
		MatrixToImageWriter.writeToStream(bitMatrix, imageFormat,
				fileOutputStream);
		fileOutputStream.close();
	}
}
