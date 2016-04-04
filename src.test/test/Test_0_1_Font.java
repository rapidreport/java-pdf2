package test;

import java.io.FileOutputStream;
import java.util.Date;

import jp.co.systembase.report.Report;
import jp.co.systembase.report.ReportPages;
import jp.co.systembase.report.data.DummyDataSource;
import jp.co.systembase.report.renderer.pdf2.PdfRenderer;
import jp.co.systembase.report.renderer.pdf2.PdfRendererSetting;

import com.itextpdf.text.pdf.BaseFont;

public class Test_0_1_Font {

	public static void main(String[] args) throws Throwable {
		String name = "test_0_1_Font";

		PdfRendererSetting setting = new PdfRendererSetting();
		setting.fontMap.put("gothic", BaseFont.createFont("C:\\Windows\\Fonts\\msgothic.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));
		setting.fontMap.put("mincho", BaseFont.createFont("C:\\Windows\\Fonts\\msmincho.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED));

		Report report = new Report(ReadUtil.readJson("rrpt/" + name + ".rrpt"));
		report.globalScope.put("time", new Date());
		report.globalScope.put("lang", "java");
		report.fill(DummyDataSource.getInstance());

		ReportPages pages = report.getPages();
		{
			FileOutputStream fos = new FileOutputStream("out/" + name + ".pdf");
			try {
				pages.render(new PdfRenderer(fos, setting));
			} finally {
				fos.close();
			}
		}
	}

}
