import { useCart } from "../context/CartContext";

export default function Navbar() {
  const { itemCount } = useCart();

  return (
    <nav>
      <h2>MyShop</h2>
      <h3>🛒 Cart ({itemCount})</h3>
    </nav>
  );
}