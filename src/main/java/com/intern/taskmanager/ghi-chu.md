# Task Manager — Mô tả Domain

## Các đối tượng chính
- User: người dùng hệ thống, có thể là USER hoặc MANAGER
- Project: dự án, gồm nhiều Task và nhiều User tham gia
- Task: công việc thuộc 1 Project, được giao cho 1 User
- Role: phân quyền (USER / MANAGER)

## Quan hệ
- 1 User có nhiều Task (1–n)
- 1 Project có nhiều Task (1–n)
- Nhiều User thuộc nhiều Project (n–n) qua bảng user_project
- 1 User có 1 Role