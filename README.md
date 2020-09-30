Java FAQ Generator
==================

**Configurable HTML FAQ page generator**

CONFIGURING YOUR OWN FAQ
------------------------

You will need 3 files in order to configure you own faq:

* A configuration file
* A FAQ XML file
* A template file for rendering HTML/XHTML

The distribution comes with working version of all 3 files for you to start
from:

* faq.cfg - configuration file
* faq.xml - FAQ xml sample file
* faq.tpl - Template file

It is recommended that you do not modify the files within the distribution
folder, but instead copy them into a separate working folder.

You should be able to get a decent understanding of each file just by
viewing the contents.


RUNNING
-------

The main class that we execute is com.inamik.faqgen.FAQGen

This class takes one single paramater: The location of the faq.cfg file for
your FAQ.

Since this is java, you will need a classpath in order to run the app.

The classpath needs to contain the 'properties' folder as well as all the
jar files found in the 'lib' folder.

There are 2 helper scripts (faq.bat, faq.sh) to get you started.


REQUIREMENTS OF PROJECT
------------

This project has the following dependencies:

* iNamik Template Engine (0.63.3a)
* iNamik Template Lib - XML (0.50.1)
* Activation (1.1.1)
* ANTLR (2.7.7)
* Commons Beanutils (1.8.3) Core
* Commons Logging (1.1.1)
* EHCache (2.2.0) Core
* JAXB API (2.2.1 20100511)
* JAXB IMPL (2.2.1 20100511)
* Jaxen (1.1.4)
* JSR173 API (1.0)


DOWNLOAD LINKS
--------

* View the source code on [GitHub](https://github.com/iNamik/Java-FAQ-Generator)
* Download [Source/Binary Distributions](https://github.com/iNamik/Java-FAQ-Generator/downloads)


AUTHORS
-------

 * David Farrell
