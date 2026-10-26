# React Counter App 📊

A simple yet beautiful React counter application that allows you to increment, decrement, and reset a number counter.

## Features

✨ **Increment** - Increase the counter by 1
✨ **Decrement** - Decrease the counter by 1
✨ **Reset** - Reset the counter to 0
✨ **Status Display** - Shows the current status (Negative, Zero, Low, Medium, High)
✨ **Beautiful UI** - Modern gradient background with smooth animations

## Project Structure

```
ReactProb/
├── index.html              # HTML entry point
├── ReactProbs.jsx           # React counter component
├── package.json            # Project configuration
└── README.md               # This file
```

## How to Run

### Option 1: Direct Browser (Easiest)
1. Open `index.html` directly in your web browser
2. No server needed - React is loaded from CDN
3. The app should load immediately

### Option 2: Using Python (Built-in)
```bash
# Windows
cd C:\Users\BVarpe\IdeaProjectsSolera\test\Test\src\ReactProb
python -m http.server 8000

# Then open: http://localhost:8000
```

### Option 3: Using Node.js HTTP Server
```bash
# First install http-server
npm install -g http-server

# Navigate to the ReactProb folder
cd C:\Users\BVarpe\IdeaProjectsSolera\test\Test\src\ReactProb

# Start the server
http-server . -p 8000

# Open: http://localhost:8000
```

## Component Overview

### CounterApp Component
- **useState Hook**: Manages the count state
- **increment()**: Increases count by 1
- **decrement()**: Decreases count by 1
- **reset()**: Sets count back to 0
- **getStatus()**: Returns status based on count value

### Status Levels
- **❌ Negative**: Count < 0
- **⚪ Zero**: Count = 0
- **🟢 Low**: 0 < Count < 5
- **🟡 Medium**: 5 ≤ Count < 10
- **🔴 High**: Count ≥ 10

## Technologies Used

- **React 18** - UI library from CDN
- **React DOM** - For rendering components
- **Babel** - For JSX transformation
- **HTML5** - Document structure
- **CSS3** - Styling with flexbox and gradients

## Code Explanation

### Key React Concepts

1. **Functional Component**
   ```jsx
   function CounterApp() {
       // Component logic here
   }
   ```

2. **useState Hook**
   ```jsx
   const [count, setCount] = React.useState(0);
   ```
   - `count`: Current state value
   - `setCount`: Function to update state

3. **Event Handlers**
   ```jsx
   const increment = () => setCount(count + 1);
   const decrement = () => setCount(count - 1);
   ```

4. **Conditional Rendering**
   ```jsx
   {getStatus(count)}  // Dynamic status display
   ```

5. **Inline Styling**
   ```jsx
   style={{...styles.button, ...styles.buttonIncrement}}
   ```

## Browser Compatibility

Works on all modern browsers:
- Chrome ✅
- Firefox ✅
- Safari ✅
- Edge ✅

## Future Enhancements

- [ ] Add step size controls
- [ ] Add increment by custom value
- [ ] Add counter history
- [ ] Add local storage persistence
- [ ] Add keyboard shortcuts (+ and -)
- [ ] Add animations
- [ ] Add theme switcher

## Notes

- No build process required
- Runs directly in the browser
- React is loaded from CDN (no installation needed)
- Perfect for learning React basics

---

**Happy Coding! 🎉**

