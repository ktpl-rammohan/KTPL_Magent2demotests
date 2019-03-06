set projectLocation=E:\Projects\LaFamilia\KTPL_Magento2demotests\KTPL_Magento2demotests
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\libs\*
java org.testng.TestNG %projectLocation%\testng.xml
pause