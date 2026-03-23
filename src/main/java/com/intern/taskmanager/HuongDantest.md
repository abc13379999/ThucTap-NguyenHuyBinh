# Hướng dẫn test project — Task Manager

## Bước 1: Clone và chạy
git clone <link GitHub>
Mở IntelliJ → sửa application.properties → Run

## Bước 2: Tạo tài khoản
POST http://localhost:8080/api/auth/register
Body: { "name":"Test", "email":"test@gmail.com",
"password":"123456", "role":"MANAGER" }

## Bước 3: Đăng nhập lấy token
POST http://localhost:8080/api/auth/login
Body: { "email":"test@gmail.com", "password":"123456" }
→ Copy token trong "data"

## Bước 4: Test API
Postman: Authorization → Bearer Token → paste token
Swagger: click Authorize → Bearer <token>

## Các API cần test
1. POST /api/projects — tạo project
2. POST /api/tasks — tạo task
3. PUT /api/tasks/1/assign/2 — assign task
4. PATCH /api/tasks/1/status — update status