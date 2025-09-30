package com.cdp.health.util;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	public String markdown(String markdown) {
		
		Parser parser = Parser.builder().build();
		
		Node document = parser.parse(markdown);
		
		HtmlRenderer htmlRender = HtmlRenderer.builder().build();
		
		
		return htmlRender.render(document);
		
	}
	

}
