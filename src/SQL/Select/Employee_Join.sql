select * from Employees;
select * from Departments;

--Display employees along with their department names.
select e.employee_name, d.department_name
from employees e
inner join departments d
on e.department_id= d.department_id;

--Display all employees, including those without a department.
select e.employee_name, d.department_name
from employees e
left join departments d
on e.department_id= d.department_id;

--Display all departments, including those without employees.
select e.employee_name, d.department_name
from employees e
right join departments d
on e.department_id= d.department_id;

--Interview takeaway: If the requirement is only to find employees with no department ID, use IS NULL.
--If the requirement is to find employees without a valid matching department, use LEFT JOIN.
--Find employees who do not belong to any existing department.

SELECT e.*
FROM employees e
LEFT JOIN departments d
    ON e.department_id = d.department_id
WHERE d.department_id IS NULL;


--Find departments that have no employees.
--
--
--
--Display employee names with their manager names.
--
--
--
--Find employees who earn more than their managers.
--
--
--
--Display employees along with their project names.
--
--
--
--Find employees assigned to more than one project.
--
--
--
--Find projects that have no employees assigned.
--
--
--
--Find employees who are not assigned to any project.
--
--
--
--Calculate total hours worked on each project.
--
--
--
--Find the employee who worked the most total project hours.
--
--
--
--Find the total project budget associated with each department's employees.
--
--
--
--Explain the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and CROSS JOIN, with examples.