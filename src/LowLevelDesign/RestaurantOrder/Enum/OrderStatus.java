package LowLevelDesign.RestaurantOrder.Enum;

/**
 * Order lifecycle states:
 * PLACED → CONFIRMED → PREPARING → READY → OUT_FOR_DELIVERY → DELIVERED
 *                                        → PICKED_UP (for takeaway/dine-in)
 * Any state can → CANCELLED (before PREPARING)
 */
public enum OrderStatus {
    PLACED,
    CONFIRMED,
    PREPARING,
    READY,
    OUT_FOR_DELIVERY,
    DELIVERED,
    PICKED_UP,
    CANCELLED
}

