package jp.co.systembase.report.renderer.pdf2.elementrenderer;

import java.util.List;

import jp.co.systembase.report.ReportDesign;
import jp.co.systembase.report.component.ElementDesign;
import jp.co.systembase.report.component.Region;
import jp.co.systembase.report.expression.EmbeddedTextProcessor;
import jp.co.systembase.report.renderer.pdf2.PdfRenderer;
import jp.co.systembase.report.renderer.pdf2.PdfText;

public class TextRenderer implements IElementRenderer {

	public void render(
			PdfRenderer renderer,
			ReportDesign reportDesign,
			Region region,
			ElementDesign design,
			Object data) throws Throwable {
		if (!design.isNull("rect")){
			renderer.setting.getElementRenderer("rect").render(
			  renderer,
			  reportDesign,
			  region,
			  design.child("rect"),
			  null);
		}
		String text = (String)design.get("text");
		if (data != null){
			EmbeddedTextProcessor textProcessor = new EmbeddedTextProcessor();
			text = textProcessor.embedData(reportDesign, design.child("formatter"), text, (List<?>)data);
		}
		if (text == null){
			return;
		}
		if (renderer.setting.replaceBackslashToYen){
			text = text.replaceAll("\\\\", "\u00a5");
		}
		PdfText pdfText = new PdfText();
		pdfText.Initialize(renderer, reportDesign, region, design, text);
		pdfText.draw();
	}
}
