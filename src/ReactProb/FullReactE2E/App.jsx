import Navbar from "./components/Navbar";
import ProductList from "./components/ProductList";
import Cart from "./components/Cart";

export default function App() {
  return (
    <>
      <Navbar />

      <main>
        <ProductList />
        <Cart />
      </main>
    </>
  );
}