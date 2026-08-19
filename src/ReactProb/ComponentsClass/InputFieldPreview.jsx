

function AppPreview() {
    const [name, setName] = React.useState("");

    const handleChange = (event) => {
        setName(event.target.value);
    };

    return (
        <div style={{ padding: '60px 20px', fontFamily: "'Segoe UI', sans-serif" }}>
            <h2>Input Field and Live Preview</h2>
            <label>
                Name:&nbsp;
                <input
                    type="text"
                    value={name}
                    onChange={handleChange}
                />
            </label>
            <p>Preview: <strong>{name}</strong></p>
        </div>
    );
}
