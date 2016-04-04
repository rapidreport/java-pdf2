package jp.co.systembase.report.renderer.pdf2.barcode;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfTemplate;

public class BarcodeUtil {

	public static void renderText(PdfTemplate tmp, String text) throws Throwable{
		tmp.beginText();
		float fs = getFontSize(tmp, text);
		setFont(tmp, fs);
		tmp.setTextMatrix((tmp.getWidth() - (text.length() + 2) * fs / 2) / 2,  tmp.getHeight() - (tmp.getHeight() * 0.7f + fs));
		tmp.showText(text);
		tmp.endText();
	}

	public static float getFontSize(PdfTemplate tmp, String text){
		float ret = tmp.getHeight() * 0.3f;
		return Math.min(ret, ((tmp.getWidth() * 0.9f) / text.length()) * 2);
	}

	public static void setFont(PdfTemplate tmp, float fontSize) throws Throwable{
		BaseFont f = BaseFont.createFont("Helvetica", "winansi", false);
		tmp.setFontAndSize(f, fontSize);
	}

}
