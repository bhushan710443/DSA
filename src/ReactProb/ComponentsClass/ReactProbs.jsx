// ============================================
// React Counter App - Counting Numbers
// ============================================

// Counter Component
function CounterApp() {
    const [count, setCount] = React.useState(0);

    // Increment counter
    const increment = () => setCount(count + 1);

    // Decrement counter
    const decrement = () => setCount(count - 1);

    // Reset counter
    const reset = () => setCount(0);

    return (
        <div style={styles.container}>
            <h1 style={styles.title}>📊 React Counter App</h1>
            
            <div style={styles.card}>
                <p style={styles.label}>Current Count</p>
                <div style={styles.countDisplay}>{count}</div>
                
                <div style={styles.buttonContainer}>
                    <button 
                        onClick={decrement} 
                        style={{...styles.button, ...styles.buttonDecrement}}
                    >
                        ➖ Decrease
                    </button>
                    
                    <button 
                        onClick={reset} 
                        style={{...styles.button, ...styles.buttonReset}}
                    >
                        🔄 Reset
                    </button>
                    
                    <button 
                        onClick={increment} 
                        style={{...styles.button, ...styles.buttonIncrement}}
                    >
                        ➕ Increase
                    </button>
                </div>

                <div style={styles.status}>
                    <p>Status: <strong>{getStatus(count)}</strong></p>
                    <p>Counter: <strong>{count}</strong></p>
                </div>
            </div>

            <footer style={styles.footer}>
                <p>Built with React ⚛️</p>
            </footer>
        </div>
    );
}

// Helper function to determine status
function getStatus(count) {
    if (count < 0) return "❌ Negative";
    if (count === 0) return "⚪ Zero";
    if (count > 0 && count < 5) return "🟢 Low";
    if (count >= 5 && count < 10) return "🟡 Medium";
    return "🔴 High (≥10)";
}

// Styling object
const styles = {
    container: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
        margin: 0,
        padding: '20px',
    },
    title: {
        color: '#992d50',
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
        marginTop: 0,
        marginBottom: '10px',
    },
    countDisplay: {
        fontSize: '4rem',
        fontWeight: 'bold',
        color: '#667eea',
        margin: '20px 0',
        fontFamily: 'monospace',
    },
    buttonContainer: {
        display: 'flex',
        gap: '10px',
        justifyContent: 'center',
        marginBottom: '30px',
        flexWrap: 'wrap',
    },
    button: {
        padding: '12px 20px',
        fontSize: '1rem',
        fontWeight: 'bold',
        border: 'none',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s ease',
        boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
    },
    buttonIncrement: {
        background: '#4CAF50',
        color: '#fff',
    },
    buttonDecrement: {
        background: '#f44336',
        color: '#fff',
    },
    buttonReset: {
        background: '#2196F3',
        color: '#fff',
    },
    status: {
        marginTop: '20px',
        padding: '15px',
        background: '#f5f5f5',
        borderRadius: '8px',
        color: '#333',
    },
    footer: {
        color: '#fff',
        marginTop: '30px',
        fontSize: '1rem',
        textShadow: '1px 1px 2px rgba(0,0,0,0.3)',
    },
};

// NOTE: Rendering is handled by index.html's main <App /> wrapper.
// To run standalone, uncomment the line below:
// ReactDOM.createRoot(document.getElementById('root')).render(<CounterApp />);

