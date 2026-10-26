

function CharacterCounter(){

    const [name, setName] = React.useState("");

    const handleChange = (event) => {
        setName(event.target.value);
    };

    return (
        <div style={{ padding: '60px 20px', fontFamily: "'Segoe UI', sans-serif" }}>
            <h2>Counte length</h2>
            <label>
                Name:&nbsp;
                <input
                    type="text"
                    value={name}
                    onChange={handleChange}
                />
            </label>
            <p>The length is = <strong>{name.length}</strong></p>
        </div>
    );

}