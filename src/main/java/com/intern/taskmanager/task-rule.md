# Task Business Rules

## 1. Task Status Flow
- TODO → IN_PROGRESS → DONE
- Không được quay ngược trạng thái
- Task ở trạng thái DONE thì không được update nữa

## 2. Create Task
- ProjectId phải tồn tại
- Task mới tạo mặc định status = TODO

## 3. Assign Task
- Task chỉ được assign cho User thuộc cùng Project
- Nếu User không thuộc Project → reject

## 4. Update Task Status
- Không cho update nếu task đã DONE

## 5. Deadline Validation 
- Deadline phải là ngày tương lai (@Future) 
- Title không được rỗng, tối đa 50 ký tự 

## 6. Create Task 
- Status mặc định = TODO (client không tự set) 

## 7. Assign Task chỉ MANAGER mới được làm 

## 8. Flow: TODO → IN_PROGRESS → DONE (1 chiều)