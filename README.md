**Upload app browserStack**
curl -u "YOUR_USERNAME:YOUR_ACCESS_KEY" \
-X POST "https://api-cloud.browserstack.com/app-automate/upload" \
-F "file=@/path/to/app/file/Application-debug.apk" \
-F "custom_id=CalculatorApp"
** **
**response app upload**
- {
"app_url":"bs://f7c874f21852ba57957a3fdc33f47514288c4ba4",
"custom_id":"CalculatorApp",
"shareable_id":"exampleuser/CalculatorApp"
}
- Use "custom_id" to specify the app: (in file browserstack.yml)
** **
**Run appium cmd**

1. Cho Android
- appium --address 127.0.0.1 --port 4723 --base-path /wd/hub

2. Cho IOS
- appium --address 127.0.0.1 --port 4723 --base-path /wd/hub --driver-xcuitest-webdriveragent-port 8100

** **
**Cách lấy thông tin device ios từ cmd** 
- brew install libimobiledevice
- Sử dụng các hàm có sẵn của libimobiledevice 
 + get version : ideviceinfo -u "id device" -k ProductVersion
 + get id : idevice_id -l
 + get name :  idevicename -u "id device"

**Kiểm tra trạng thái App đang chạy trên device**
+ driver.queryAppState("id app")


** **
**Cách setup reports**
1. Tạo biến môi trường JAVA_HOME
2. Thêm biến môi trường Allure-2.14.0 vào máy tính như cài đặt biến môi trường Java (link đến tận folder bin của allure)
3. Link download theo phiên bản: https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
4. Tiếp theo, giải nén ra để nó vào ổ C (ổ chứa hệ điều hành) để tạo biến môi trường trong máy ổn định.
5. Tạo biến môi trường trong biến Path với %allure_home%\bin

**Cách chạy reports:**
1. Mở terminal trên intellij
2. Chạy lệnh: allure serve target/allure-results
   //  allure generate --single-file target/allure-results --clean      để ghi đè lên file cũ
** **
**Run mvn**
1. Run full project :
   mvn test
2. Run 1 profile cụ thể :
   mvn test -PloginTest
3. Khi run browser stack với username, pass, id :
   mvn test -DuserName=cuongvu_FerjhE -DaccessKey=idKAyrfQhD8DzT2su7Xe -DappId=bs://8223a66b66c48e4e42c5fc779252d85a829d1bdd