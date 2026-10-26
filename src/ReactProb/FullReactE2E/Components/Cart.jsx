import { useCart } from "../context/CartContext";

export default function Cart() {
  const {
    items,
    totalPrice,
    increaseQuantity,
    decreaseQuantity,
    removeFromCart,
    clearCart,
  } = useCart();

  return (
    <section>
      <h2>Your Cart</h2>

      {items.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          {items.map((item) => (
            <div className="cart-item" key={item.id}>
              <div>
                <h3>{item.emoji} {item.name}</h3>
                <p>₹{item.price}</p>

                <button
                  onClick={() => decreaseQuantity(item.id)}
                >
                  −
                </button>

                <span> {item.quantity} </span>

                <button
                  onClick={() => increaseQuantity(item.id)}
                >
                  +
                </button>

                <button
                  onClick={() => removeFromCart(item.id)}
                >
                  Remove
                </button>
              </div>

              <strong>
                ₹{item.price * item.quantity}
              </strong>
            </div>
          ))}

          <h3>Total: ₹{totalPrice}</h3>

          <button onClick={clearCart}>
            Clear Cart
          </button>
        </>
      )}
    </section>
  );
}