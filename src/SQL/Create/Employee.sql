drop database sql_interview;
CREATE DATABASE sql_interview;
USE sql_interview;

DROP TABLE IF EXISTS employee_projects;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL,
    location VARCHAR(50)
);

CREATE TABLE employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    salary DECIMAL(10,2),
    department_id INT,
    manager_id INT,
    hire_date DATE,
    city VARCHAR(50),
    status VARCHAR(20),
    FOREIGN KEY (department_id)
        REFERENCES departments(department_id),
    FOREIGN KEY (manager_id)
        REFERENCES employees(employee_id)
);

CREATE TABLE projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    budget DECIMAL(12,2)
);

CREATE TABLE employee_projects (
    employee_id INT,
    project_id INT,
    assigned_date DATE,
    hours_worked INT,
    PRIMARY KEY (employee_id, project_id),
    FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id),
    FOREIGN KEY (project_id)
        REFERENCES projects(project_id)
);

INSERT INTO departments VALUES
(1, 'Engineering', 'Bangalore'),
(2, 'HR', 'Pune'),
(3, 'Finance', 'Mumbai'),
(4, 'Sales', 'Delhi'),
(5, 'Marketing', 'Hyderabad'),
(6, 'Support', 'Bangalore');

-- Insert managers and employees in manager-first order
INSERT INTO employees VALUES
(101, 'Amit Sharma', 'amit@company.com', 120000, 1, NULL, '2018-01-15', 'Bangalore', 'ACTIVE'),
(102, 'Priya Patel', 'priya@company.com', 110000, 1, 101, '2019-03-10', 'Pune', 'ACTIVE'),
(103, 'Rahul Verma', 'rahul@company.com', 90000, 1, 101, '2020-06-20', 'Bangalore', 'ACTIVE'),
(104, 'Sneha Joshi', 'sneha@company.com', 90000, 1, 102, '2021-02-05', 'Mumbai', 'ACTIVE'),
(105, 'Karan Mehta', 'karan@company.com', 75000, 2, NULL, '2019-07-12', 'Pune', 'ACTIVE'),
(106, 'Neha Singh', 'neha@company.com', 70000, 2, 105, '2022-04-18', 'Pune', 'ACTIVE'),
(107, 'Vikas Rao', 'vikas@company.com', 95000, 3, NULL, '2017-11-25', 'Mumbai', 'ACTIVE'),
(108, 'Pooja Desai', 'pooja@company.com', 85000, 3, 107, '2020-09-14', 'Mumbai', 'ACTIVE'),
(109, 'Rohit Kulkarni', 'rohit@company.com', 80000, 4, NULL, '2018-05-30', 'Delhi', 'ACTIVE'),
(110, 'Anjali Shah', 'anjali@company.com', 80000, 4, 109, '2021-08-22', 'Delhi', 'ACTIVE'),
(111, 'Suresh Iyer', 'suresh@company.com', 65000, 5, NULL, '2022-01-17', 'Hyderabad', 'ACTIVE'),
(112, 'Meera Nair', 'meera@company.com', 60000, 5, 111, '2023-03-09', 'Bangalore', 'ACTIVE'),
(113, 'Arjun Das', 'arjun@company.com', 55000, 6, NULL, '2021-12-01', 'Bangalore', 'INACTIVE'),
(114, 'Kavita Rao', 'kavita@company.com', 55000, 6, 113, '2022-10-11', 'Pune', 'ACTIVE'),
(115, 'Manoj Kumar', 'manoj@company.com', 90000, 1, 102, '2023-07-01', 'Bangalore', 'ACTIVE'),
(116, 'Riya Sen', 'riya@company.com', 50000, NULL, NULL, '2024-01-10', 'Chennai', 'ACTIVE');

INSERT INTO projects VALUES
(201, 'Payment Service', 500000),
(202, 'Customer Portal', 300000),
(203, 'Data Platform', 800000),
(204, 'HR Automation', 150000),
(205, 'CRM Upgrade', 250000);

INSERT INTO employee_projects VALUES
(101, 201, '2020-01-01', 400),
(102, 201, '2020-02-01', 350),
(103, 202, '2021-01-15', 280),
(104, 202, '2021-03-10', 220),
(105, 204, '2021-05-01', 180),
(106, 204, '2022-06-01', 150),
(107, 203, '2019-04-01', 500),
(108, 203, '2020-07-01', 320),
(109, 205, '2019-09-01', 250),
(110, 205, '2021-10-01', 200),
(101, 203, '2022-01-01', 300),
(102, 202, '2022-02-01', 240),
(103, 203, '2023-01-01', 160);

-- Verify your data
SELECT * FROM departments;
SELECT * FROM employees;
SELECT * FROM projects;
SELECT * FROM employee_projects;
