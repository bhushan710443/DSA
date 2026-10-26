
CREATE DATABASE IF NOT EXISTS sql_practice;
USE sql_practice;

-- 1. Create Department table
CREATE TABLE IF NOT EXISTS Department (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL,
    location VARCHAR(100)
);

-- 2. Create Employee table
CREATE TABLE IF NOT EXISTS Employee (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    salary DECIMAL(10, 2),
    department_id INT,
    CONSTRAINT fk_employee_department
        FOREIGN KEY (department_id)
        REFERENCES Department(department_id)
);

-- 3. Insert departments
INSERT INTO Department (department_id, department_name, location)
VALUES
    (1, 'Engineering', 'Bangalore'),
    (2, 'HR', 'Pune'),
    (3, 'Finance', 'Mumbai'),
    (4, 'Marketing', 'Hyderabad');

-- 4. Insert employees
INSERT INTO Employee
    (employee_id, employee_name, email, salary, department_id)
VALUES
    (101, 'Amit', 'amit@example.com', 75000.00, 1),
    (102, 'Priya', 'priya@example.com', 68000.00, 1),
    (103, 'Rahul', 'rahul@example.com', 55000.00, 2),
    (104, 'Sneha', 'sneha@example.com', 82000.00, 3),
    (105, 'Vikas', 'vikas@example.com', 60000.00, 4);

-- 5. Verify inserted data
SELECT * FROM Department;
SELECT * FROM Employee;
