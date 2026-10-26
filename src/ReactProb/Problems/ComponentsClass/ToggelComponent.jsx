// ============================================
// Toggle Component - ON/OFF Switch
// ============================================

function ToggleComponent() {
    const [isOn, setIsOn] = React.useState(false);
    const [toggleCount, setToggleCount] = React.useState(0);

    const handleToggle = () => {
        setIsOn(!isOn);
        setToggleCount(toggleCount + 1);
    };

    const reset = () => {
        setIsOn(false);
        setToggleCount(0);
    };

    return (
        <div style={tStyles.container}>
            <h1 style={tStyles.title}>🔘 Toggle Component</h1>

            <div style={tStyles.card}>
                <p style={tStyles.label}>Current State</p>
                <div style={{
                    ...tStyles.stateDisplay,
                    color: isOn ? '#4CAF50' : '#f44336'
                }}>
                    {isOn ? '✅ ON' : '❌ OFF'}
                </div>

                {/* Toggle Switch */}
                <div
                    onClick={handleToggle}
                    style={{
                        ...tStyles.switch,
                        background: isOn ? '#4CAF50' : '#ccc'
                    }}
                >
                    <div style={{
                        ...tStyles.switchKnob,
                        left: isOn ? '32px' : '4px'
                    }} />
                </div>

                <div style={tStyles.info}>
                    <p>Toggled <strong>{toggleCount}</strong> times</p>
                </div>

                <div style={tStyles.buttonContainer}>
                    <button onClick={handleToggle} style={tStyles.button}>
                        {isOn ? 'Turn OFF' : 'Turn ON'}
                    </button>
                    <button
                        onClick={reset}
                        style={{ ...tStyles.button, background: '#2196F3' }}
                    >
                        Reset
                    </button>
                </div>
            </div>
        </div>
    );
}

const tStyles = {
    container: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #f093fb 0%, #f5576c 100%)',
        fontFamily: "'Segoe UI', sans-serif",
        padding: '20px',
    },
    title: {
        color: '#fff',
        fontSize: '2.5rem',
        marginBottom: '30px',
        textShadow: '2px 2px 4px rgba(0,0,0,0.3)',
    },
    card: {
        background: '#fff',
        borderRadius: '15px',
        padding: '40px',
        boxShadow: '0 10px 30px rgba(0,0,0,0.3)',
        textAlign: 'center',
        maxWidth: '400px',
        width: '100%',
    },
    label: {
        fontSize: '1.2rem',
        color: '#666',
        marginBottom: '10px',
    },
    stateDisplay: {
        fontSize: '3rem',
        fontWeight: 'bold',
        margin: '20px 0',
    },
    switch: {
        width: '70px',
        height: '34px',
        borderRadius: '34px',
        position: 'relative',
        cursor: 'pointer',
        margin: '20px auto',
        transition: 'background 0.3s',
    },
    switchKnob: {
        width: '26px',
        height: '26px',
        borderRadius: '50%',
        background: '#fff',
        position: 'absolute',
        top: '4px',
        transition: 'left 0.3s',
        boxShadow: '0 2px 4px rgba(0,0,0,0.2)',
    },
    info: {
        margin: '20px 0',
        padding: '15px',
        background: '#f5f5f5',
        borderRadius: '8px',
        color: '#333',
    },
    buttonContainer: {
        display: 'flex',
        gap: '10px',
        justifyContent: 'center',
    },
    button: {
        padding: '12px 24px',
        fontSize: '1rem',
        fontWeight: 'bold',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        background: '#f5576c',
        color: '#fff',
        boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
    },
};

