#!/bin/bash

#
# This simple unix shell script should help get you started using FAQGen.
#
# You will need to configure the location of the FAQGen root folder.
#
# Also, the script assumes it is being executed from the directly that contains
# the cfg file and that the cfg file is named 'faq.cfg'
#
# You may want to change 'faq.cfg' to '$1' so that you can pass in the
# cfg file at runtime.
#

# Point to your FAQGen root directory
wd="."

# Build the classpath from jars in the lib folder
cp="$wd/properties"
for i in `ls -1 $wd/lib/*.jar`
do
	cp="$cp:$i"
done

# Generate the faq
java -client -cp "$cp" com.inamik.faqgen.FAQGen faq.cfg
