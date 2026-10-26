import { createContext, useContext, useReducer, useMemo, useCallback}   from "react";


const CartContext = createContext(null);

const initialState = {
  items: [],
};


function cartReducer(state, action) {
  switch (action.type) {
    case "ADD_TO_CART": {
      const product = action.payload;

      const existingItem = state.items.find(
        (item) => item.id === product.id
      );

      if (existingItem) {
        return {
          ...state,
          items: state.items.map((item) =>
            item.id === product.id
              ? { ...item, quantity: item.quantity + 1 }
              : item
          ),
        };
      }

      return {
        ...state,
        items: [...state.items, { ...product, quantity: 1 }],
      };
    }

    case "INCREASE_QUANTITY":
      return {
        ...state,
        items: state.items.map((item) =>
          item.id === action.payload
            ? { ...item, quantity: item.quantity + 1 }
            : item
        ),
      };

    case "DECREASE_QUANTITY":
      return {
        ...state,
        items: state.items
          .map((item) =>
            item.id === action.payload
              ? { ...item, quantity: item.quantity - 1 }
              : item
          )
          .filter((item) => item.quantity > 0),
      };

    case "REMOVE_FROM_CART":
      return {
        ...state,
        items: state.items.filter(
          (item) => item.id !== action.payload
        ),
      };

    case "CLEAR_CART":
      return {
        ...state,
        items: [],
      };

    default:
      return state;
  }
}

export function CartProvider({ children }) {
  const [state, dispatch] = useReducer(
    cartReducer,
    initialState
  );

  const addToCart = useCallback((product) => {
    dispatch({
      type: "ADD_TO_CART",
      payload: product,
    });
  }, []);

  const increaseQuantity = useCallback((id) => {
    dispatch({
      type: "INCREASE_QUANTITY",
      payload: id,
    });
  }, []);

  const decreaseQuantity = useCallback((id) => {
    dispatch({
      type: "DECREASE_QUANTITY",
      payload: id,
    });
  }, []);

  const removeFromCart = useCallback((id) => {
    dispatch({
      type: "REMOVE_FROM_CART",
      payload: id,
    });
  }, []);

  const clearCart = useCallback(() => {
    dispatch({ type: "CLEAR_CART" });
  }, []);

  const itemCount = useMemo(
    () =>
      state.items.reduce(
        (total, item) => total + item.quantity,
        0
      ),
    [state.items]
  );

  const totalPrice = useMemo(
    () =>
      state.items.reduce(
        (total, item) =>
          total + item.price * item.quantity,
        0
      ),
    [state.items]
  );

  const value = useMemo(
    () => ({
      items: state.items,
      itemCount,
      totalPrice,
      addToCart,
      increaseQuantity,
      decreaseQuantity,
      removeFromCart,
      clearCart,
    }),
    [
      state.items,
      itemCount,
      totalPrice,
      addToCart,
      increaseQuantity,
      decreaseQuantity,
      removeFromCart,
      clearCart,
    ]
  );

  return (
    <CartContext.Provider value={value}>
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  const context = useContext(CartContext);

  if (!context) {
    throw new Error(
      "useCart must be used inside CartProvider"
    );
  }

  return context;
}