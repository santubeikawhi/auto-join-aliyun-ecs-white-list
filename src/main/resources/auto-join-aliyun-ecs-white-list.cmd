@echo off

set /a a=0

:foreach
java -jar \autojoinEcsWhiteList.jar regionId securityGroupId ipProtocol portRange accessKeyId accessKeySecret endpoint

echo %errorlevel%
if %errorlevel% == 0 exit

set /a a+=1
echo fail times: %a%
if %a% == 20 exit

goto foreach
