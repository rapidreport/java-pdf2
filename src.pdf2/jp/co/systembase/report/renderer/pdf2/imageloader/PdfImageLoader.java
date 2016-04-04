package jp.co.systembase.report.renderer.pdf2.imageloader;

import java.io.IOException;

import jp.co.systembase.report.renderer.ImageMap;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class PdfImageLoader implements IPdfImageLoader {

	public PdfImageMap imageMap;

	public PdfImageLoader(){
		this(new PdfImageMap());
	}

	public PdfImageLoader(ImageMap imageMap) throws BadElementException, IOException{
		this();
		for(Object k: imageMap.keySet()){
			this.imageMap.put(k, Image.getInstance(imageMap.get(k), null));
		}
	}

	public PdfImageLoader(PdfImageMap imageMap){
		this.imageMap = imageMap;
	}

	public Image getImage(Object param) {
		return this.imageMap.get(param);
	}

}
