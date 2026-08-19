
function SearchFilter() {
    const users = ["John", "Alice", "Bob", "David"];

    const [searchTerm, setSearchTerm] = React.useState("");

    const filteredUsers = users.filter((user) =>
        user.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div>
            <h2> Search Filters</h2>
            <input
                type="text"
                placeholder="Search users..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
            />

            <ul>
                {filteredUsers.map((user) => (
                    <li key={user}>{user}</li>
                ))}
            </ul>
        </div>
    );
}