@echo off
REM #
REM # This simple Windows batch script should help get you started using FAQGen.
REM #
REM # NOTE: You will need to have 'delayed expansion' enabled in order to use
REM #       this test script.  You enable it via:
REM #
REM #       cmd.exe /V:ON
REM #
REM # You will need to configure the location of the FAGGen root folder.
REM #
REM # Also, the script assumes it is being executed from the directly that contains
REM # the cfg file and that the cfg file is named 'faq.cfg'
REM #
REM # You may want to change 'faq.cfg' to '%1' so that you can pass in the
REM # cfg file at runtime.
REM #

REM # Point to your FAQGen root directory
SET wd=.

REM # Build the classpath from jars in the lib folder
SET cp=%wd%\properties
FOR %%i IN (%wd%\lib\*.jar) do SET cp=!cp!;%%i

REM # Generate the faq
java -client -cp "!cp!" com.inamik.faqgen.FAQGen faq.cfg
