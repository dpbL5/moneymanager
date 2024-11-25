# moneymanager
# Cài đặt
## Yêu cầu:
###1.	Java JDK version 22 trở lên (Download)
###2.	MySQL Workbench 8.0.40 (Download)
###3.	Một text editor hoặc IDE bất kì.
Với lợi ích được xây dựng và tích hợp sẵn với công cụ build dự án là Maven, việc cài đặt và chạy dự án rất đơn giản với các bước như sau:
## Cài đặt MySQL:
Bước 1: Tải và cài đặt theo đường link
Bước 2: Tạo database server với MySQL
Bước 3: Khai báo tài khoản và mật khẩu cho server. (Hãy lưu thông tin cho phần sau)
Bước 4: Tải file cấu hình database (Tại đây)
##	Cài đặt dự án
Bước 1: Mở terminal, chuyển tới thư mục ta muốn lưu dự án;
Bước 2: Sử dụng git clone, để tải mã nguồn về, nhập lệnh sau:
git clone https://github.com/dpbL5/moneymanager.git
Hoặc tải trực tiếp từ trình duyệt với link (LINK)
Bước 3: Sau khi tải xong. Chuyển vào thư mục dự án. Ta cài đặt các module bằng lệnh sau:
.\mvnw install
Bước 4: Tới đường dẫn:
.\moneymanager\src\main\java\oop\moneymanager\dao\JDBCUtil.java
Tại đây ta thay đổi 3 giá trị url, username, password lần lượt là url tới server đã tạo, tên tài khoản và mật khẩu hợp lệ.
Bước 5: Cuối cùng để chạy chương trình với mã nguồn ta nhập:
.\mvnw clean javafx:run

Lưu ý: Dự án này sử dụng phiên bản JDK 22 hoặc cao hơn, nghĩa là nếu sử dụng phiên bản JDK thấp hơn, có thể sẽ gặp vấn đề.
