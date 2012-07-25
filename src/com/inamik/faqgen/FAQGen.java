package com.inamik.faqgen;
/*
 * iNamik FAQ Generator
 * Copyright (C) 2008 David Farrell (davidpfarrell@yahoo.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


import com.inamik.template.*;
import com.inamik.template.lib.xml.util.XMLUtil;
import com.inamik.template.lib.xml.util.XPathUtil;

/**
 * FAQGen
 *
 * Usage:
 *   FAQGen [cfg_filename]
 *
 * Created on Feb 16, 2008
 * @author Dave
 */
public class FAQGen
{
	public static final class FAQ
	{
		private String question;
		private String answer;
		public String getQuestion() { return question; }
		public String getAnswer()   { return answer;   }
	}

	public static final class Section
	{
		private String title;
		private List<FAQ> faqs;
		public String getTitle()   { return title; }
		public List<FAQ> getFaqs() { return faqs;  }
	}

	final String[] args;

	/**
	 * Constructor
	 */
	public FAQGen(final String[] args)
	{
		super();

		this.args = args;
	}

	/**
	 * main
	 */
	public static void main(String[] args) throws Exception
	{
		new FAQGen(args).run();
	}

	public void run() throws Exception
	{
		// Add properties, if cfg file exists
		Properties properties = new Properties();

		final File cfgFile;
		final File cfgParentFile;

		if (args.length > 0 && args[0] != null && args[0].length() > 0)
		{
			cfgFile = new File(args[0]);
		}
		else
		{
			cfgFile = new File("faq.cfg");
		}

		if (cfgFile.exists() && cfgFile.canRead())
		{
			properties.load(new FileInputStream(cfgFile));
			cfgParentFile = cfgFile.getParentFile();
		}
		else
		{
			cfgParentFile = null;
		}

		TemplateEngine templateEngine = TemplateEngine.getInstance();

		final String tplFilename = properties.getProperty("template_file", "faq.tpl");

		Template template = templateEngine.loadTemplateFromFile(tplFilename);

		final String xmlFilename = properties.getProperty("xml_file", "faq.xml");

		File xmlFile = new File(cfgParentFile, xmlFilename);

		if (xmlFile.exists() == false || xmlFile.canRead() == false)
		{
			throw new Exception("Unable to locate xml file '" + xmlFilename + "'");
		}

		final org.w3c.dom.Document document = XMLUtil.getDocument(xmlFile);

		if (document == null)
		{
			throw new Exception("Unable to load xml file '" + xmlFilename + "'");
		}

		template.addVariable("doc", TemplateVariable.newInstance(document));

		final List sectionNodes = XPathUtil.evaluateXPathAsList("/faqs/section", document);

		final List<Section> sections = new ArrayList<Section>(sectionNodes.size());

		for(Object sectionNode : sectionNodes)
		{
			Section section = new Section();

			section.title = XPathUtil.evaluateXPathAsString("@title", sectionNode);

			final List      faqNodes = XPathUtil.evaluateXPathAsList("./faq", sectionNode);
			final List<FAQ> faqs     = new ArrayList<FAQ>(faqNodes.size());

			for (Object faqNode: faqNodes)
			{
				final FAQ faq = new FAQ();

				faq.question = XPathUtil.evaluateXPathAsString("./question", faqNode);
				faq.answer   = XPathUtil.evaluateXPathAsString("./answer"  , faqNode);;

				faqs.add(faq);
			}

			section.faqs = faqs;

			sections.add(section);
		}

		template.addVariable("sections", sections);

		for (Object key : properties.keySet())
		{
			final String name  = (String)key;
			final String value = properties.getProperty(name);

			template.addVariable(name, value);
		}

		final String outputFilename = properties.getProperty("output_file", null);

		if (outputFilename != null && outputFilename.length() > 0)
		{
			File outputFile = new File(cfgParentFile, outputFilename);

			if (outputFile.exists() == false || outputFile.canWrite() == true)
			{
				PrintWriter outputWriter = new PrintWriter(outputFile);
				template.setOut(outputWriter);
			}
		}

		template.process();
	}
}