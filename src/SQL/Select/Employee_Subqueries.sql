--Find employees earning more than the average salary using a subquery.

select *
from employees
where salary > (
select AVG(salary)
from employees
);

--Find employees working in the same department as Amit Sharma.
select *
from employees
where department_id =(
select department_id
from employees
where employee_name='Amit Sharma'
);

--Find employees earning more than at least one employee in the HR department.
--
--
--
--Find employees earning more than every employee in the HR department.
--
--
--
--Find departments whose average salary is higher than the overall company average.
--
--
--
--Find the employee with the highest salary using a subquery.
--
--
--
--Find employees who earn the maximum salary in their department.
--
--
--
--Find employees who have been assigned to the same projects as Amit Sharma.
--
--
--
--Find employees who have worked on every project assigned to Amit Sharma.



--Explain correlated vs non-correlated subqueries.