// Todo Application
// Features: Add task, Delete task, Mark complete
// Concepts: CRUD operations, Array state updates

function ToDoApp() {
    const [tasks, setTasks] = React.useState([]); // list of { id, text, done }
    const [input, setInput] = React.useState(""); // current input value

    const addTask = () => {
        if (!input.trim()) return;
        setTasks([...tasks, { id: Date.now(), text: input, done: false }]);
        setInput("");
    };

    const deleteTask = (id) => {
        setTasks(tasks.filter(t => t.id !== id));
    };

    const toggleDone = (id) => {
        setTasks(tasks.map(t =>
            t.id === id ? { ...t, done: !t.done } : t
        ));
    };

    return (
        <div>
            <h2>To-Do App</h2>

            <input
                type="text"
                value={input}
                placeholder="Enter a task"
                onChange={(e) => setInput(e.target.value)}
            />
            <button onClick={addTask}>Add</button>

            <ul>
                {tasks.map(t => (
                    <li key={t.id}>
                        <input
                            type="checkbox"
                            checked={t.done}
                            onChange={() => toggleDone(t.id)}
                        />
                        <span style={{ textDecoration: t.done ? 'line-through' : 'none' }}>
                            {t.text}
                        </span>
                        <button onClick={() => deleteTask(t.id)}>Delete</button>
                    </li>
                ))}
            </ul>

            <p>{tasks.filter(t => t.done).length} / {tasks.length} done</p>
        </div>
    );

}