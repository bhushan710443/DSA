# SQL Practice with MySQL

## Prerequisites
- IntelliJ IDEA Community Edition
- MySQL Server 8.0+
- MySQL command-line client available in PATH

## Connect to MySQL

Open the IntelliJ Terminal and run:

```bash
mysql -h localhost -P 3306 -u root -p
```

Enter your MySQL password when prompted.

## Create and Select Database

```sql
CREATE DATABASE IF NOT EXISTS interview_db;
USE interview_db;
SHOW TABLES;
```

## Execute a SQL File

Create a `.sql` file in your project, for example `SQLPractice/practice.sql`.

Execute it from the project root:

```bash
mysql -u root -p < SQLPractice/practice.sql
```

Alternatively, from the MySQL prompt:

```sql
source C:/full/path/to/practice.sql;
```

Replace the example path with your actual file path.

## Example Query

```sql
USE interview_db;
SELECT VERSION();
SHOW TABLES;
```

## Notes
- IntelliJ Community supports SQL file creation and terminal-based execution.
- The MySQL server must be running before connecting.
- Never commit database passwords to Git.
