import { products } from "../data/products";
import { useCart } from "../context/CartContext";

export default function ProductList() {
  const { addToCart } = useCart();

  return (
    <section>
      <h2>Products</h2>

      <div className="products">
        {products.map((product) => (
          <div className="product" key={product.id}>
            <h3>{product.emoji}</h3>
            <h3>{product.name}</h3>
            <p>₹{product.price}</p>

            <button
              onClick={() => addToCart(product)}
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </section>
  );
}